/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

import edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author andre_000
 */
public abstract interface DatabaseStrategy {


    public abstract void closeConnection() throws DatabaseAccessException;
    
    public abstract int deleteById(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws DatabaseAccessException;

    public abstract List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws DatabaseAccessException;

    public abstract Map<String, Object> findById(String tableName, String primaryKey, Object primaryKeyValue) throws DatabaseAccessException;

    public abstract boolean insertRecord(String tableName, List colDescriptors, List colValues) throws DatabaseAccessException;

    public abstract void openConnection(DataSource ds) throws DatabaseAccessException;

    public abstract void openConnection(String driverClass, String url, String userName, String password) throws DatabaseAccessException;

    public abstract int updateRecords(String tableName, List columnNames, List columnValues, String whereField, Object whereValue) throws DatabaseAccessException;
    
}
