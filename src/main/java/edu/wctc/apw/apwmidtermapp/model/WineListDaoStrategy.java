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
public abstract interface WineListDaoStrategy {

    public abstract boolean deleteWineById(Integer wineId) throws DatabaseAccessException;

    public abstract DataSource getDataSource();

    public abstract DatabaseStrategy getDbStrat();

    public abstract String getDriver();

    public abstract String getPassword();

    public abstract String getUrl();

    public abstract String getUserName();

    public abstract Wine getWineById(Integer wineId) throws DatabaseAccessException;

    public abstract List<Wine> getWineList() throws DatabaseAccessException;

    // this one for injection... and pooling I think
    public abstract void initDao(DataSource dataSource) throws DatabaseAccessException;

    // setters are used because validation in the setters validates these params.
    public abstract void initDao(String driver, String url, String userName, String password);

    public abstract boolean saveWine(Integer wineId, String wineName, double price, String imageUrl) throws DatabaseAccessException;

    public abstract void setDataSource(DataSource dataSource);

    public abstract void setDbStrat(DatabaseStrategy dbStrat);

    public abstract void setDriver(String driver);

    public abstract void setPassword(String password);

    public abstract void setUrl(String url);

    public abstract void setUserName(String userName);
    
}
