/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

// This is where your custom exception import goes..
import edu.wctc.apw.apwmidtermapp.exception.DatabaseAccessException;

import java.io.Serializable;
import java.sql.Connection;
// Jim does not have this one.
import java.sql.DatabaseMetaData;
// these are good.
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
// I don't think we need this.... however Jim has it.
import java.sql.SQLException;
// these are good.
import java.sql.Statement;
import java.util.ArrayList;
// Jim has it not sure if its unused.
import java.util.Arrays;
// jim doesn't have this
import java.util.Date;
// these are good.
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// there are two that jim has I don't LEVEL AND LOGGER
// this is good.
import javax.enterprise.context.Dependent;

// Jim does not have this, I do not use it... yet.
import javax.enterprise.context.SessionScoped;

// Good
import javax.sql.DataSource;

/**
 * This is a low-level JDBC class that represents a DBDtrategy implementation
 * Specifically for a MySql database server.
 *
 * @author andre_000
 */
@Dependent
public class MySqlDBStrategy implements Serializable {

    private Connection conn;

    /**
     * This Default Constructor is required for injectable objects
     */
    public MySqlDBStrategy() {
    }

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

    public final void openConnection(DataSource ds) throws DatabaseAccessException {
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        }
    }

    // the old way of doing this. Jim does not have a Java doc on this one... 
    // possibly... no need for it unless you allow for non connection pooling... 
    // Which Why Would you... If your server doesn't support it I suppose. 
    // Needs Custom Exception

    public final void openConnection(String driverClass, String url,
            String userName, String password) throws DatabaseAccessException {

        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, userName, password);

        } // catch exceptions, throw custom exception.
        catch (ClassNotFoundException | SQLException e) {
            // throw your custom exception here.
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        }
    }

    // Jim has a hahahahahaha... do I need a finally? 

    public final void closeConnection() throws DatabaseAccessException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        }
    }

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
 
    public final List<Map<String, Object>> findAllRecords(String tableName,
            int maxRecords) throws DatabaseAccessException {

        // create MySql statement
        String sql;

        if (maxRecords < 1) {

            sql = "select * from " + tableName;
        } else {
            sql = "select * from " + tableName + " limit " + maxRecords;
        }
        // sorting could happen here
        Statement stmt = null;
        List<Map<String, Object>> recordList = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            // create result set object.
            ResultSet rs = stmt.executeQuery(sql);
            // get meta data
            ResultSetMetaData rsmd = rs.getMetaData();
            // find out how many columns there are in the table.
            int columnCount = rsmd.getColumnCount();
            // a list of maps to store our records

            while (rs.next()) {
                // loop for saving records into our map.
                Map<String, Object> record = new HashMap<>();
                for (int colNo = 1; colNo <= columnCount; colNo++) {

                    record.put(rsmd.getColumnName(colNo), rs.getObject(colNo));
                }
                // put our map into our list of maps.
                recordList.add(record);
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                throw new DatabaseAccessException(e.getMessage(), e.getCause());
            }
        }
        // return this list of maps
        return recordList;
    }


    public final Map<String, Object> findById(String tableName, String primaryKey,
            Object primaryKeyValue) throws DatabaseAccessException {

        String sql = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = ?";
        PreparedStatement stmt = null;
        final Map<String, Object> record = new HashMap();

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, primaryKeyValue);
            ResultSet rs = stmt.executeQuery();
            final ResultSetMetaData metaData = rs.getMetaData();
            final int fields = metaData.getColumnCount();

            // Retrieve the raw data from the ResultSet and copy the values into a Map
            // with the keys being the column names of the table.
            if (rs.next()) {
                for (int i = 1; i <= fields; i++) {
                    record.put(metaData.getColumnName(i), rs.getObject(i));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                throw new DatabaseAccessException(e.getMessage(), e.getCause());
            }
        }

        return record;
    }

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

    public final int deleteById(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws DatabaseAccessException {

        String sql = "delete from " + tableName + " where " + primaryKeyFieldName + " =?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, primaryKeyValue);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                throw new DatabaseAccessException(e.getMessage(), e.getCause());
            }
        }
    }

    // this is Jims... in my bookwebapp I do not believe I used it. I think I used my create meathod... So I'll have to review that.
    public final boolean insertRecord(String tableName, List colDescriptors, List colValues) throws DatabaseAccessException {

        PreparedStatement pstmt = null;
        int recsUpdated = 0;

        // this next bit should be in the exception handler? so you can depend on the finally block firing off.
        // the fuck does that mean exactly... like this should be... around the whole meathod when called?
        // look up exception handler java best practices. 
        try {
            pstmt = buildInsertStatement(conn, tableName, colDescriptors);

            final Iterator i = colValues.iterator();

            int index = 1;

            while (i.hasNext()) {
                final Object object = i.next();

                pstmt.setObject(index++, object);
            }
            recsUpdated = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        } catch (Exception e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        } finally {  // everything in all these finally blocks is the same can I helper meathod them? 
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                throw new DatabaseAccessException(e.getMessage(), e.getCause());
            }

        }
        if (recsUpdated == 1) {

            return true;
        } else {
            return false;
        }
    }

    // this was my create method. It worked. I used it. Delete it if you don't end up using it. 

    public int createNewRecordInTable(String tableName, ArrayList<String> record) throws SQLException {

        //     String sql = "INSERT INTO table_name (column1,column2,column3,...)\n" +
        //                  "VALUES (value1,value2,value3,...);";
        String columnName = "";
        String sql1 = "Select * from " + tableName;
        String valuePlaceHolder = "";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql1);

        ResultSetMetaData rsmd = rs.getMetaData();

        int columnNumber = rsmd.getColumnCount();

        // test
        // System.out.println(columnNumber);   this equals 3 so thats right.
        for (int i = 1; i <= columnNumber; i++) {
            if (i < columnNumber) {
                columnName = columnName + rsmd.getColumnName(i) + ", ";
                valuePlaceHolder = valuePlaceHolder + "?,";
            } else if (i == columnNumber) {
                columnName = columnName + rsmd.getColumnName(i) + ") ";
                valuePlaceHolder = valuePlaceHolder + "?)";
            }
        }
        System.out.println(columnName);
        System.out.println(valuePlaceHolder);

        String sql2 = "INSERT INTO " + tableName + " ( " + columnName
                + "VALUES (" + valuePlaceHolder;

        // test
        //System.out.println(sql2);
        PreparedStatement pstmt = conn.prepareStatement(sql2);

        for (int i = 1; i <= columnNumber; i++) {
            pstmt.setString(i, record.get(i - 1));
        }
        //    System.out.println(columnName);
        System.out.println(pstmt);

        return pstmt.executeUpdate();

//test
//        rs = stmt.executeQuery(sql1);
//        System.out.println(rs);
    }


    public final int updateRecords(String tableName, List columnNames, List columnValues,
            String whereField, Object whereValue)
            throws DatabaseAccessException {
        PreparedStatement pstmt = null;
        int recsUpdated = 0;

        // do this in an excpetion handler so that we can depend on the
        // finally clause to close the connection
        try {
            pstmt = buildUpdateStatement(conn, tableName, columnNames, whereField);

            final Iterator i = columnValues.iterator();
            int index = 1;
            Object obj = null;

            // set params for column values
            while (i.hasNext()) {
                obj = i.next();
                pstmt.setObject(index++, obj);
            }
            // and finally set param for wehere value
            pstmt.setObject(index, whereValue);
            recsUpdated = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        } catch (Exception e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                throw new DatabaseAccessException(e.getMessage(), e.getCause());
            } // end try
        } // end finally

        return recsUpdated;
    }

    /*
	 * Builds a java.sql.PreparedStatement for an sql update using only one where clause test
	 * @param conn - a JDBC <code>Connection</code> object
	 * @param tableName - a <code>String</code> representing the table name
	 * @param colDescriptors - a <code>List</code> containing the column descriptors for
	 * the fields that can be updated.
	 * @param whereField - a <code>String</code> representing the field name for the
	 * search criteria.
	 * @return java.sql.PreparedStatement
	 * @throws SQLException
     */
    private PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName,
            List colDescriptors, String whereField)
            throws DatabaseAccessException {
        StringBuffer sql = new StringBuffer("UPDATE ");
        (sql.append(tableName)).append(" SET ");
        final Iterator i = colDescriptors.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(" = ?, ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")));
        ((sql.append(" WHERE ")).append(whereField)).append(" = ?");
        final String finalSQL = sql.toString();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(finalSQL);
        } catch (SQLException e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        }
        return pstmt;
    }
    
    private PreparedStatement buildInsertStatement(Connection conn, String tableName, List columnDescriptors)
            throws DatabaseAccessException{
        StringBuffer sql = new StringBuffer("INSERT INTO");
         (sql.append(tableName)).append(" (");
         final Iterator i = columnDescriptors.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(", ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ") VALUES (");
        for (int j = 0; j < columnDescriptors.size(); j++) {
            sql.append("?, ");
        }
        final String finalSQL = (sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ")";
        
        PreparedStatement psmt = null;
        try {
            psmt = conn.prepareStatement(finalSQL);
        } catch (SQLException e) {
            throw new DatabaseAccessException(e.getMessage(), e.getCause());
        }
        return psmt;
    }
    
    

    // Not Jims way... so probably lose this. 
//    @Override
//    public void insertNewRecordbyId(String tableName, ArrayList<String> record, int id) throws SQLException {
//        // is this not CreateNewRecordInTabledOneRecord method..? 
//        String columnName = "";
//        String sql1 = "Select * from " + tableName;
//        String valuePlaceHolder = "";
//
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery(sql1);
//
//        ResultSetMetaData rsmd = rs.getMetaData();
//
//        int columnNumber = rsmd.getColumnCount();
//
//        // test
//        // System.out.println(columnNumber);   this equals 3 so thats right.
//        for (int i = 1; i <= columnNumber; i++) {
//            if (i < columnNumber) {
//                columnName = columnName + rsmd.getColumnName(i) + ", ";
//
//                valuePlaceHolder = valuePlaceHolder + "?,";
//            } else if (i == columnNumber) {
//                columnName = columnName + rsmd.getColumnName(i) + ") ";
//                valuePlaceHolder = valuePlaceHolder + "?)";
//            }
//        }
//        System.out.println(columnName);
//        System.out.println(valuePlaceHolder);
//
//        String sql2 = "INSERT INTO " + tableName + " ( " + columnName
//                + "VALUES (" + valuePlaceHolder + "where " + columnName + "=" + id;
//
//        // test
//        //System.out.println(sql2);
//        PreparedStatement pstmt = conn.prepareStatement(sql2);
//
//        for (int i = 1; i <= columnNumber; i++) {
//            pstmt.setString(i, record.get(i - 1));
//        }
//        //    System.out.println(columnName);
//        //    System.out.println(pstmt);
//
//        pstmt.executeUpdate();
//    }

//  Testing Main 
//    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//
// //       DBStrategy db = new MySqlDBStrategy();
////        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
////        System.out.println(db.findAllRecords("author", 0).toString());
//        ArrayList<String> record = new ArrayList<>();
//        record.add(null);
//        record.add("Hunter S Thompson");
//        record.add("1111-11-11");
//        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//        db.createNewRecordInTable("author", record);
//        db.closeConnection();
//        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//        List<Map<String, Object>> rawData = db.findAllRecords("author", 0);
//        db.closeConnection();
////        List<Map<String, Object>> rawData = db.findAllRecords("author", 0);
////        List<String> colNames = Arrays.asList("author_name");
////        List<Object> colValues = Arrays.asList("Lucifer MorningStar");
////        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
////        int result = db.updateRecords("author", colNames, colValues, "author_id", 4);
////        db.closeConnection();
////        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
////        List<Map<String, Object>> rawData = db.findAllRecords("author", 0);
//        db.closeConnection();
//        System.out.println(rawData);
//
//    }

}
