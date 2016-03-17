/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

import edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.sql.DataSource;

/**
 *
 * @author andre_000
 */
public class WineListDao implements Serializable, WineListDaoStrategy {
    
    @Inject
    private DatabaseStrategy dbStrat;
    
    private DataSource dataSource;
    
    private String driver;
    private String url;
    private String userName;
    private String password;
    
    private final String TABLE_NAME = "wine_list";
    private final String PRIMARY_KEY_FIELD_NAME = "wine_id";
    private final String WINE_NAME = "wine_name";
    private final String WINE_PRICE = "wine_price";
    private final String WINE_IMAGE_URL = "image_url";

    public WineListDao() {
    }
    
    // this one for injection... and pooling I think
    @Override
    public final void initDao(DataSource dataSource) throws DatabaseAccessException {
        this.dataSource = dataSource;
    }
    
    // setters are used because validation in the setters validates these params.
    @Override
    public final void initDao(String driver, String url, String userName, String password){
        setDriver(driver);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
    }
    
    
    @Override
    public final Wine getWineById(Integer wineId) throws DatabaseAccessException {
        
        if (dataSource == null){
            dbStrat.openConnection(driver, url, userName, password);
        } else {
            dbStrat.openConnection(dataSource);
        }
        
        Map<String, Object> rawRec = dbStrat.findById(TABLE_NAME, PRIMARY_KEY_FIELD_NAME, wineId);
        Wine wine = new Wine();
        wine.setProductId((Integer)rawRec.get(PRIMARY_KEY_FIELD_NAME));
        wine.setProductName(rawRec.get(WINE_NAME).toString());
        wine.setProductPrice((double)rawRec.get(WINE_PRICE));
        wine.setImageURL(rawRec.get(WINE_IMAGE_URL).toString());
        return wine;
    }

    @Override
    public final List<Wine> getWineList() throws DatabaseAccessException {
         if (dataSource == null){
            dbStrat.openConnection(driver, url, userName, password);
        } else {
            dbStrat.openConnection(dataSource);
        }
         
         List<Wine>records = new ArrayList<>();
         
         List<Map<String, Object>>rawData = dbStrat.findAllRecords(TABLE_NAME, 0);
         
         for(Map rawRec: rawData){
             Wine wine = new Wine();
             
             Object object = rawRec.get(PRIMARY_KEY_FIELD_NAME);
             wine.setProductId(Integer.parseInt(object.toString()));
             
             String name = rawRec.get(WINE_NAME) == null ? "": rawRec.get(WINE_NAME).toString();
             wine.setProductName(name);
             
             object = rawRec.get(WINE_PRICE) == null ? "": rawRec.get(WINE_PRICE).toString();
             wine.setProductPrice(Double.parseDouble(object.toString()));
             
             String imageUrl = rawRec.get(WINE_IMAGE_URL) == null ? "" : rawRec.get(WINE_IMAGE_URL).toString();
             wine.setImageURL(imageUrl);
             
             records.add(wine);
         }
         return records;
    }
    
    
    @Override
    public final boolean deleteWineById(Integer wineId) throws DatabaseAccessException {
        if (dataSource == null){
            dbStrat.openConnection(driver, url, userName, password);
        } else {
            dbStrat.openConnection(dataSource);
        }
        
        int result = dbStrat.deleteById(TABLE_NAME, PRIMARY_KEY_FIELD_NAME, wineId);
        
        if(result == 0){
            return false;
        } else {
            return true;
        }
    }
        
    @Override
    public final boolean saveWine(Integer wineId, String wineName, double price, String imageUrl) throws DatabaseAccessException {
        if (dataSource == null){
            dbStrat.openConnection(driver, url, userName, password);
        } else {
            dbStrat.openConnection(dataSource);
        }
        
        boolean result = false;
        
        if(wineId == null || wineId.equals(0)){
            // new record
            result = dbStrat.insertRecord(TABLE_NAME, Arrays.asList(WINE_NAME, WINE_PRICE, WINE_IMAGE_URL ), 
                    Arrays.asList(wineName, price, imageUrl));
        } else {
            // updating an existing record
            int recsUpdated = dbStrat.updateRecords(TABLE_NAME, Arrays.asList(WINE_NAME, WINE_PRICE, WINE_IMAGE_URL ), 
                    Arrays.asList(wineName, price, imageUrl), PRIMARY_KEY_FIELD_NAME, wineId);
            
            // is zero really a magic number? I mean... Its 0 its a cosmic constant.
            if(recsUpdated > 0){
                result = true;
            }
        }
        return result;
        
    }
    
    
    
    
    
    @Override
    public final DatabaseStrategy getDbStrat() {
        return dbStrat;
    }

    @Override
    public final void setDbStrat(DatabaseStrategy dbStrat) {
        this.dbStrat = dbStrat;
    }

    @Override
    public final DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public final void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public final String getDriver() {
        return driver;
    }

    @Override
    public final void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public final String getUrl() {
        return url;
    }

    @Override
    public final void setUrl(String url) {
        this.url = url;
    }

    @Override
    public final String getUserName() {
        return userName;
    }

    @Override
    public final void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public final String getPassword() {
        return password;
    }

    @Override
    public final void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
