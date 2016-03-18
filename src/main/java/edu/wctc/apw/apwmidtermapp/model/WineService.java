/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

import edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException;
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
    
    public  Wine getWineById(String wineId) throws DatabaseAccessException {
        return dao.getWineById(Integer.parseInt(wineId));
    }
    
    public  List<Wine> getAllWines() throws DatabaseAccessException {
        return dao.getWineList();
    }
    
    public  void deleteWineById(String wineId) throws DatabaseAccessException {
        dao.deleteWineById(Integer.parseInt(wineId));
    }
    
    public  void saveOrUpdateWine(String wineId, String wineName, double price, String imageUrl ) throws DatabaseAccessException {
        
        
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
        this.dao = dao;
    }
    
    
}
