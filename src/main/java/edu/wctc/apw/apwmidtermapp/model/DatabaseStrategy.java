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
public interface DatabaseStrategy {

    // Jim has a hahahahahaha... do I need a finally?
    public abstract void closeConnection() throws DatabaseAccessException;

    // this was my create method. It worked. I used it. Delete it if you don't end up using it.
    public abstract int createNewRecordInTable(String tableName, ArrayList<String> record) throws SQLException;

    // here we are. we had a long delete by id method... that was awful but worked Jim gave us
    // a four line method that did it all faster and better. Deleted old method for midterm
    // going with his... probably needs some try catching.
    /**
     * JIMS METHOD.
     *
     * @param tableName
     * @param primaryKeyFieldName
     * @param primaryKeyValue
     * @return
     * @throws edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException
     * @throws java.sql.SQLException
     */
    public abstract int deleteById(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws DatabaseAccessException;

    /**
     * Must open and close a connection when using this method. Future
     * optimizations may include changing the return type to an array. It will
     * save on memory. Because ArrayLists have empty slots.
     *
     * @param tableName
     * @param maxRecords - Limits result set to this number, or if maxRecords is
     * zero (0) then no limit
     * @return
     * @throws java.sql.SQLException
     */
    public abstract List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws DatabaseAccessException;

    public abstract Map<String, Object> findById(String tableName, String primaryKey, Object primaryKeyValue) throws DatabaseAccessException;

    // this is Jims... in my bookwebapp I do not believe I used it. I think I used my create meathod... So I'll have to review that.
    public abstract boolean insertRecord(String tableName, List colDescriptors, List colValues) throws DatabaseAccessException;

    //this is good. just needs custom exceptions see Jim Hardcopy. I believe Jim spoon fed this.
    /**
     * Open a connection using a connection pool configured on server.
     *
     * @param ds - a reference to a connection pool via a JNDI name, producing
     * this object. Typically done in a servlet using InitalContext object.
     * @throws edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException
     *
     *
     */
    public abstract void openConnection(DataSource ds) throws DatabaseAccessException;

    // the old way of doing this. Jim does not have a Java doc on this one...
    // possibly... no need for it unless you allow for non connection pooling...
    // Which Why Would you... If your server doesn't support it I suppose.
    // Needs Custom Exception
    public abstract void openConnection(String driverClass, String url, String userName, String password) throws DatabaseAccessException;

    public abstract int updateRecords(String tableName, List columnNames, List columnValues, String whereField, Object whereValue) throws DatabaseAccessException;
    
}
