/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

import edu.wctc.apw.apwmidtermapp.exception.DaoIsNullException;
import edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException;
import edu.wctc.apw.apwmidtermapp.exception.ParameterMissingException;
import java.io.Serializable;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 * This is a Service class used to manage the Dao interface object.
 * Scope causes methods to not be final.
 * @author andre_000
 */
@SessionScoped
public class WineService implements Serializable {
    
    @Inject
    private WineListDaoStrategy dao;

    
    /**
     * default constructor 
     */
    public WineService() {
    }
    
    /**
     * Constructor with mandatory dao property. Used for Testing.
     * @param dao WineListDaoStrategy object
     */
    public WineService(WineListDaoStrategy dao) {
        this.dao = dao;
    }
    
    
    
    
    /**
     * This Method takes an ID as a param and then passes that to the Dao object.
     * @param wineId String
     * @return Wine Object
     * @throws DatabaseAccessException
     * @throws ParameterMissingException 
     */
    public Wine getWineById(String wineId) throws DatabaseAccessException, ParameterMissingException {
        if(wineId.isEmpty() || wineId == null || Integer.parseInt(wineId) < 0){
            throw new ParameterMissingException();
        }
        
        return dao.getWineById(Integer.parseInt(wineId));
    }
    
    
    /**
     * This Method calls the dao Objects getWineList Method. It returns a List of Wine Objects.
     * @return List of Wine Objects
     * @throws DatabaseAccessException 
     */
    public List<Wine> getAllWines() throws DatabaseAccessException {
        return dao.getWineList();
    }
    
    
    /**
     * This Method takes an ID as a param and passes that to the dao objects delewinebyId method.
     * @param wineId Integer
     * @throws DatabaseAccessException 
     */
    public void deleteWineById(String wineId) throws DatabaseAccessException {
         if(wineId.isEmpty() || wineId == null || Integer.parseInt(wineId) < 0){
            throw new ParameterMissingException();
        }       
        dao.deleteWineById(Integer.parseInt(wineId));
    }
    
    
    /**
     * This Method takes an ID, name, price, and URL and passes them to the Dao saveWine method.
     * Id may be null if adding a new wine to WineList.
     * @param wineId String
     * @param wineName String
     * @param price double????  FIX THIS
     * @param imageUrl String
     * @throws DatabaseAccessException 
     */
    public void saveOrUpdateWine(String wineId, String wineName, String price, String imageUrl ) throws DatabaseAccessException {
        double priceDbl = Double.parseDouble(price);
        
        
      if(wineName.isEmpty() || wineName == null ) {
//            System.out.println("NAME IS PROBLEM");
            throw new ParameterMissingException();
        } if(priceDbl < 0 || priceDbl > 500){
//            System.out.println("price IS PROBLEM");
           
            throw new ParameterMissingException();
        } if(imageUrl.isEmpty() || imageUrl == null ) {
//            System.out.println("url IS PROBLEM");
            throw new ParameterMissingException();
        }
         
        System.out.println("HEY HEY HEY");
        Integer id = null;
        if(wineId == null || wineId.isEmpty()){
           id = null;
        } else {
            id = Integer.parseInt(wineId);
        }
        
        dao.saveWine(id, wineName, priceDbl, imageUrl);
    }

    /**
     * Getter for the dao object
     * @return dao object.
     */
    public WineListDaoStrategy getDao() {
        return dao;
    }
    /**
     * Setter for the dao object. 
     * @param dao WineListDaoStrategy object
     */
    public void setDao(WineListDaoStrategy dao) {
        if(dao == null){
            throw new DaoIsNullException();
        }
        this.dao = dao;
    }
    
    
}
