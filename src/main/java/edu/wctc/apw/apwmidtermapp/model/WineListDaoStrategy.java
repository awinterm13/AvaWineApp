/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

import edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;

/**
 *
 * @author andre_000
 */
// not in Jims copy but it is in his instructions regarding this topic.
@Dependent
public interface WineListDaoStrategy {

    boolean deleteWineById(Integer wineId) throws DatabaseAccessException;

    DataSource getDataSource();

    DatabaseStrategy getDbStrat();

    String getDriver();

    String getPassword();

    String getUrl();

    String getUserName();

    Wine getWineById(Integer wineId) throws DatabaseAccessException;

    List<Wine> getWineList() throws DatabaseAccessException;

    // this one for injection... and pooling I think
    void initDao(DataSource dataSource) throws DatabaseAccessException;

    // setters are used because validation in the setters validates these params.
    void initDao(String driver, String url, String userName, String password);

    boolean saveWine(Integer wineId, String wineName, double price, String imageUrl) throws DatabaseAccessException;

    void setDataSource(DataSource dataSource);

    void setDbStrat(DatabaseStrategy dbStrat);

    void setDriver(String driver);

    void setPassword(String password);

    void setUrl(String url);

    void setUserName(String userName);
    
}
