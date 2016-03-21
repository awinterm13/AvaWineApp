/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

import edu.wctc.apw.apwmidtermapp.exception.DaoIsNullException;
import edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException;
import edu.wctc.apw.apwmidtermapp.exception.QueryParameterMissingException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author andre_000
 */
@SessionScoped
public class WineService implements Serializable {
    
    @Inject
    private WineListDaoStrategy dao;

    
    
    public WineService() {
    }
    
    
    
    public  Wine getWineById(String wineId) throws DatabaseAccessException, QueryParameterMissingException {
        if(wineId.isEmpty() || wineId == null || Integer.parseInt(wineId) < 0){
            throw new QueryParameterMissingException();
        }
        
        return dao.getWineById(Integer.parseInt(wineId));
    }
    
    
    
    public  List<Wine> getAllWines() throws DatabaseAccessException {
        return dao.getWineList();
    }
    
    
    
    public  void deleteWineById(String wineId) throws DatabaseAccessException {
         if(wineId.isEmpty() || wineId == null || Integer.parseInt(wineId) < 0){
            throw new QueryParameterMissingException();
        }       
        dao.deleteWineById(Integer.parseInt(wineId));
    }
    
    
    
    public  void saveOrUpdateWine(String wineId, String wineName, double price, String imageUrl ) throws DatabaseAccessException {
         
//         this first one might present a problem with nulls.  TEST IT.
        if(Integer.parseInt(wineId) < 0){
            throw new QueryParameterMissingException();  
        }  if(wineName.isEmpty() || wineName == null ) {
            throw new QueryParameterMissingException();
        } if(price < 0 || price > 500){
            throw new QueryParameterMissingException();
        } if(imageUrl.isEmpty() || imageUrl == null ) {
            throw new QueryParameterMissingException();
        }
         
        System.out.println("HEY HEY HEY");
        Integer id = null;
        if(wineId == null || wineId.isEmpty()){
           id = null;
        } else {
            id = Integer.parseInt(wineId);
        }
        
        dao.saveWine(id, wineName, price, imageUrl);
    }

    public WineListDaoStrategy getDao() {
        return dao;
    }

    public void setDao(WineListDaoStrategy dao) {
        if(dao == null){
            throw new DaoIsNullException();
        }
        this.dao = dao;
    }
    
    
}
