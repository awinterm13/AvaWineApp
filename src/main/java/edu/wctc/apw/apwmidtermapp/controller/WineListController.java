/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.controller;

import edu.wctc.apw.apwmidtermapp.exception.DaoIsNullException;
import edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException;
import edu.wctc.apw.apwmidtermapp.exception.ParameterMissingException;
import edu.wctc.apw.apwmidtermapp.entity.Wine;
import edu.wctc.apw.apwmidtermapp.service.WineService;

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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * A Controller Class for the Ava Wine List Management. Basic Crud Operations.
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
    private static final String LOG_OUT_ACTION = "logOut";
    private static final String LOG_OUT_PAGE_URL = "/logOut.jsp";
    private static final String ERROR_MSG_KEY = "errMsg";
    private static final String IMAGE_URL_KEY = "imageUrl";
    private static final String PRICE_KEY = "price";
    private static final String PRODUCT_NAME_KEY = "productName";
    private static final String WINE_KEY = "wine";
    private static final String WINE_LIST_KEY = "wineList";

    private static final String DRIVER_CLASS_KEY = "db.driver.class";
    private static final String DATABASE_URL_KEY = "db.url";
    private static final String DATABASE_USERNAME_KEY = "db.username";
    private static final String DATABASE_PASSWORD_KEY = "db.password";
    private static final String DATABASE_JNDI_NAME_KEY = "db.jndi.name";

    private static final String FONT_COLOR_KEY = "fontColor";
    private static final String USER_NAME_KEY = "username";

    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private String dbJndiName;

    
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
    protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = WINE_LIST_PAGE;
        String action = request.getParameter(ACTION_PARAMETER);
        HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();
        Wine wine;
        try {

//            configDbConnection();
            switch (action) {
                case GET_WINE_LIST_ACTION:
                    // Jim made a method out of this. If I reuse these two lines I will do the same.
//                    if (request.getParameter(USER_NAME_KEY) != null || request.getParameter(USER_NAME_KEY).length() > 0) {
//                        session.setAttribute(USER_NAME_KEY, request.getParameter(USER_NAME_KEY));
//                    }
//                    if (request.getParameter(FONT_COLOR_KEY) != null || request.getParameter(FONT_COLOR_KEY).length() > 0) {
//                        ctx.setAttribute(FONT_COLOR_KEY, request.getParameter(FONT_COLOR_KEY));
//                    }

                    this.refreshList(request, wineServ);
                    // if you have two or more pages this tool can send to then this next line is smart.
                    destination = WINE_LIST_PAGE;
                    break;
                case ADD_EDIT_DELETE:
                    String subAction = request.getParameter(SUBMIT_ACTION);
                    String wineId = request.getParameter(WINE_ID_PARAMETER_KEY);

                    if (subAction.equals(DELETE_ACTION)) {
                        
                        wine = wineServ.findById(wineId);
                        wineServ.remove(wine);
                        this.refreshList(request, wineServ);
                        destination = WINE_LIST_PAGE;
                    } else if (subAction.equals(EDIT_ACTION)) {
                        destination = ADD_EDIT_PAGE;
                        wine = wineServ.findById(wineId);
                        request.setAttribute(WINE_KEY, wine);
                    } else if (subAction.equals(ADD_ACTION)) {
                        request.setAttribute(WINE_KEY, null);
                        destination = ADD_EDIT_PAGE;
                    }
                    break;

                case CANCEL_ACTION:
                    this.refreshList(request, wineServ);
                    destination = WINE_LIST_PAGE;
                    break;

                case SAVE_ACTION:
                    try {
                        String wineName = request.getParameter(PRODUCT_NAME_KEY);
                        String price = request.getParameter(PRICE_KEY);
                        String imageUrl = request.getParameter(IMAGE_URL_KEY);
                        String Id = request.getParameter(WINE_ID_PARAMETER_KEY);
                    System.out.println("THIS HAPPENED. " + wineName + price + imageUrl + Id);
                     
                    
                    if(Id.isEmpty()){
                        wine = new Wine();
                        wine.setWineName(wineName);
                        wine.setWinePrice(Double.valueOf(price));
                        wine.setImageUrl(imageUrl);
                        // if vineyard... then do that here like author.
//                        Author author = null;
                        // dont think this next line is needed.
//                        this.refreshList(request, wineServ);
                     } else {
                        wine = wineServ.findById(Id);
                        wine.setWineName(wineName);
                        System.out.println(wine.getWineName());
                        wine.setImageUrl(imageUrl);
                        wine.setWinePrice(Double.valueOf(price));
                        System.out.println(wine.toString());
                        }
                      
                        wineServ.edit(wine);
                       
                        this.refreshList(request, wineServ);
                        destination = WINE_LIST_PAGE;
                    } catch (ParameterMissingException e) {
                        request.setAttribute(ERROR_MSG_KEY, e.getMessage());
                        destination = ADD_EDIT_PAGE;
                    }
                    break;

                case LOG_OUT_ACTION:
                    destination = LOG_OUT_PAGE_URL;
                    break;

            }
        } catch (DaoIsNullException e) {
            request.setAttribute(ERROR_MSG_KEY, e.getMessage());
        } catch (ParameterMissingException e) {
            request.setAttribute(ERROR_MSG_KEY, e.getMessage());
        } catch (DatabaseAccessException e) {
            request.setAttribute(ERROR_MSG_KEY, e.getMessage());
        } catch (Exception e) {
            request.setAttribute(ERROR_MSG_KEY, e.getMessage());
        }

        RequestDispatcher view
                = getServletContext().getRequestDispatcher(response.encodeURL(destination));
        view.forward(request, response);
    }

//    private void configDbConnection() throws NamingException, Exception {
//        if (dbJndiName == null) {
//            wineServ.getDao().initDao(driverClass, url, userName, password);
//        } else {
//            /*
//             Lookup the JNDI name of the Glassfish connection pool
//             and then use it to create a DataSource object.
//             */
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource) ctx.lookup(dbJndiName);
//            wineServ.getDao().initDao(ds);
//        }
//    }

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

    /**
     * Init method works for connection pooling or standard connection.
     *
     * @throws ServletException
     */
  @Override
    public void init() throws ServletException {
        // Ask Spring for object to inject
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        wineServ = (WineService) ctx.getBean("wineService");

    }

    private void refreshList(HttpServletRequest request, WineService wineServ) throws Exception {
        List<Wine> wineList = wineServ.findAll();
//        System.out.println(wineList.toString());
        request.setAttribute(WINE_LIST_KEY, wineList);
    }

}
