/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.controller;

import edu.wctc.apw.apwmidtermapp.model.Wine;
import edu.wctc.apw.apwmidtermapp.model.WineService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author andre_000
 */
@WebServlet(name = "WineListController", urlPatterns = {"/WineListController"})
public class WineListController extends HttpServlet {
    
    private static final String ACTION_PARAMETER = "action";
    private static final String WINE_LIST_PAGE = "/wineListPage.jsp";
    private static final String GET_WINE_LIST_ACTION = "getWineList";
    private static final String ADD_EDIT_DELETE = "addEditDelete";
    private static final String WINE_ID_PARAMETER_KEY = "wineID";
    private static final String SUBMIT_ACTION = "submit";
     private static final String DELETE_ACTION = "Delete";
     private static final String EDIT_ACTION = "Edit";
     private static final String ADD_EDIT_PAGE = "/addEdit.jsp";
    private static final String ADD_ACTION = "Add";
    private static final String CANCEL_ACTION = "Cancel";
    private static final String SAVE_ACTION = "Save";
    
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private String dbJndiName; 
    
    @Inject
     private WineService wineServ;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String destination = WINE_LIST_PAGE;
        String action = request.getParameter(ACTION_PARAMETER);
        
        try  {
             
      
            
            
            configDbConnection();
        switch (action) {
                case GET_WINE_LIST_ACTION:
                        // Jim made a method out of this. If I reuse these two lines I will do the same.
                        this.refreshList(request, wineServ);
                        // if you have two or more pages this tool can send to then this next line is smart.
                        destination = WINE_LIST_PAGE;
                    break;
                case ADD_EDIT_DELETE:
                    String subAction = request.getParameter(SUBMIT_ACTION);
                    String wineId = request.getParameter(WINE_ID_PARAMETER_KEY);
                    
                     if (subAction.equals(DELETE_ACTION)) {
                         wineServ.deleteWineById(wineId);
                         this.refreshList(request, wineServ);
                         destination = WINE_LIST_PAGE;
                    } else if(subAction.equals(EDIT_ACTION)){
                        destination = ADD_EDIT_PAGE;
                        Wine wine = wineServ.getWineById(wineId);
                        request.setAttribute("wine", wine);
                    } else if (subAction.equals(ADD_ACTION)){
                        request.setAttribute("wine", null);
                        destination = ADD_EDIT_PAGE;   
                           }
                   break;
                
                case CANCEL_ACTION:
                    this.refreshList(request, wineServ);
                    destination = WINE_LIST_PAGE;
                    break; 
                    
                case SAVE_ACTION:
                    String wineName = request.getParameter("productName");
                    String price = request.getParameter("price");
                    String imageUrl = request.getParameter("imageUrl");
                    String Id = request.getParameter(WINE_ID_PARAMETER_KEY);
                    System.out.println("THIS HAPPENED. " + wineName + price + imageUrl + Id);
                    wineServ.saveOrUpdateWine(Id, wineName, Double.parseDouble(price), imageUrl);
                    this.refreshList(request, wineServ);
                    destination = WINE_LIST_PAGE;
                    break;
            
        }
    }   catch (Exception e) { 
            request.setAttribute("errMsg", e.getMessage());
        } 
        
        RequestDispatcher view =
                getServletContext().getRequestDispatcher(response.encodeURL(destination));
                view.forward(request, response);
    }
        
        private void configDbConnection() throws NamingException, Exception { 
        if(dbJndiName == null) {
            wineServ.getDao().initDao(driverClass, url, userName, password);   
        } else {
            /*
             Lookup the JNDI name of the Glassfish connection pool
             and then use it to create a DataSource object.
             */
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dbJndiName);
            wineServ.getDao().initDao(ds);
        }
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

     @Override
    public void init() throws ServletException {
        driverClass = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        userName = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
        dbJndiName = getServletContext().getInitParameter("db.jndi.name");
    }
    
     private void refreshList(HttpServletRequest request, WineService wineServ) throws Exception {
        List<Wine> wineList = wineServ.getAllWines();
         System.out.println(wineList.toString());
        request.setAttribute("wineList", wineList);
    }
    

}
