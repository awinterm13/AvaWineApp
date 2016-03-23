/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

import edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException;
import edu.wctc.apw.apwmidtermapp.exception.ParameterMissingException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * This class Serves as a Database Access Object, It manages the DatabaseStrategy class object.
 * basic crud methods.
 * @author andre_000
 */
@Dependent
public class WineListDao implements Serializable, WineListDaoStrategy {
    
    @Inject
    private DatabaseStrategy dbStrat;
    
    private DataSource dataSource;
    
    private String driver;
    private String url;
    private String userName;
    private String password;
    
    private final String TABLE_NAME = "wine";
    private final String PRIMARY_KEY_FIELD_NAME = "wine_id";
    private final String WINE_NAME = "wine_name";
    private final String WINE_PRICE = "wine_price";
    private final String WINE_IMAGE_URL = "image_url";

    public WineListDao() {
    }
    
    /**
     * an init method used for connection pooling, sets the datasource object.
     * @param dataSource DataSource
     * @throws DatabaseAccessException 
     */
    @Override
    public final void initDao(DataSource dataSource) throws DatabaseAccessException { 
        this.dataSource = dataSource;
    }
    
    /**
     * Init method used for injection without a connection pool.
     * @param driver String
     * @param url String
     * @param userName String
     * @param password String
     */
    @Override
    public final void initDao(String driver, String url, String userName, String password){
        setDriver(driver);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
    }
    
    /**
     * This Method takes a Integer as an ID and returns a Wine from the database.
     * wine id must be greater then 0 and not null.
     * @param wineId Integer
     * @return
     * @throws DatabaseAccessException 
     */
    @Override
    public final Wine getWineById(Integer wineId) throws DatabaseAccessException {
        if (wineId < 0 || wineId == null){
            throw new ParameterMissingException();
        }
        
        
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
    
    /**
     * This method gets a List of Wine objects from the dbStrat Object.
     * @return List of Wine objects.
     * @throws DatabaseAccessException 
     */
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
    
    /**
     * This method takes an Integer as a wineID and uses the dbStrategy object to delete 
     * that wine from the database. wineId may not be null or less then 0.
     * @param wineId Integer
     * @return true or false
     * @throws DatabaseAccessException 
     */
    @Override
    public final boolean deleteWineById(Integer wineId) throws DatabaseAccessException  {
          if( wineId == null || wineId < 0){
            throw new ParameterMissingException();
          }
        
        
        
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
    
    /**
     * This method takes the following parameters and uses the dbStrategy object to save them to 
     * the database.
     * @param wineId
     * @param wineName
     * @param price
     * @param imageUrl
     * @return
     * @throws DatabaseAccessException 
     */
    @Override
    public final boolean saveWine(Integer wineId, String wineName, double price, String imageUrl) throws DatabaseAccessException {
        
        System.out.println("This happened too!");
         if(wineName.isEmpty() || wineName == null ) {
//             System.out.println("NAME PROBLEM");
            throw new ParameterMissingException();
        } if(price < 0 || price > 500){
//            System.out.println("PRICE IS THE PROBLEM");
            throw new ParameterMissingException();
        } if(imageUrl.isEmpty() || imageUrl == null ) {
//            System.out.println("its the URL");
            throw new ParameterMissingException();
        }
       
//         System.out.println("HERE WE ARE! 1");
        if (dataSource == null){
            dbStrat.openConnection(driver, url, userName, password);
        } else {
            dbStrat.openConnection(dataSource);
        }
        
        boolean result = false;
//        System.out.println("HERE WE ARE! 2");
        if(wineId == null || wineId.equals(0)){
            // new record
            result = dbStrat.insertRecord(TABLE_NAME, Arrays.asList(WINE_NAME, WINE_PRICE, WINE_IMAGE_URL ), 
                    Arrays.asList(wineName, price, imageUrl));
            System.out.println("THIS!!!!!");
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
    
    
    
    
    /**
     * getter for the DatabaseStrategy object.
     * @return DatabaseStrategy object.
     */
    @Override
    public final DatabaseStrategy getDbStrat() {
        return dbStrat;
    }
    /**
     * Setter for the DatabaseStrategy object must not be null or empty
     * @param dbStrat 
     */
    @Override
    public final void setDbStrat(DatabaseStrategy dbStrat) {
        if(dbStrat == null){
            throw new ParameterMissingException();
        }
        this.dbStrat = dbStrat;
    }
    /**
     * Getter for DataSource object
     * @return DataSource object
     */
    @Override
    public final DataSource getDataSource() {
        return dataSource;
    }
    /**
     * Setter for DataSource object must not be null or empty
     * @param dataSource 
     */
    @Override
    public final void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    /**
     * getter for driver property
     * @return 
     */
    @Override
    public final String getDriver() {
        return driver;
    }
    /**
     * setter for driver property must not be null or empty
     * @param driver String driver
     */
    @Override
    public final void setDriver(String driver) {
         if(driver == null || driver.isEmpty()){
            throw new ParameterMissingException();
        }
        this.driver = driver;
    }
    /**
     * getter for URL
     * @return String URL
     */
    @Override
    public final String getUrl() {
        return url;
    }
    /**
     * setter for URL must not be null or empty
     * @param url 
     */
    @Override
    public final void setUrl(String url) {
         if(url == null || url.isEmpty()){
            throw new ParameterMissingException();
        }
        this.url = url;
    }
    /**
     * getter for username
     * @return String username
     */
    @Override
    public final String getUserName() {
        return userName;
    }
    /**
     * setter for username must not be null or empty
     * @param userName 
     */
    @Override
    public final void setUserName(String userName) {
         if(userName == null || userName.isEmpty()){
            throw new ParameterMissingException();
        }
        this.userName = userName;
    }
    /**
     * getter for password
     * @return String password
     */
    @Override
    public final String getPassword() {
        return password;
    }
    /**
     * setter for password, password must not be null or empty
     * @param password 
     */
    @Override
    public final void setPassword(String password) {
         if(password == null || password.isEmpty()){
            throw new ParameterMissingException();
        }
        this.password = password;
    }
    
//     test comment out for production
//    public static void main(String[] args) throws DatabaseAccessException {
//        WineListDao dao = new WineListDao();
//        dao.setDbStrat(new MySqlDBStrategy());
//        dao.initDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/ava_wine", "root", "admin");
// 
//        System.out.println(dao.getWineList().toString());
//        System.out.println(dao.getWineById(5).toString());
//        dao.deleteWineById(5);
//        System.out.println(dao.getWineList().toString());
//        dao.saveWine(null, "plungerhead", 4.95, "monkeydogdog");
//        System.out.println(dao.getWineList().toString());
//    }
//}
}