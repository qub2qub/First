/*
 * Copyright (c) 1995, 2011, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package main;

import utils.JDBUtils;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CoffeesTable {

  private String dbName;
  private Connection con;
  private String dbms;


  public CoffeesTable(Connection connArg, String dbNameArg, String dbmsArg) {
    super();
    this.con = connArg;
    this.dbName = dbNameArg;
    this.dbms = dbmsArg;
    System.out.println("______________INIT_____________");
    toString();
  }

  @Override
  public String toString() {
    return "CoffeesTable{" +
            "dbName='" + dbName + '\'' +
            ", conection=" + con +
            ", db provider='" + dbms + '\'' +
            '}';
  }

  public void createTable() throws SQLException {
    String createString =
      "create table COFFEES " + "(COF_NAME varchar(32) NOT NULL, " +
      "SUP_ID int NOT NULL, " + "PRICE numeric(10,2) NOT NULL, " +
      "SALES integer NOT NULL, " + "TOTAL integer NOT NULL, " +
      "PRIMARY KEY (COF_NAME), " +
      "FOREIGN KEY (SUP_ID) REFERENCES SUPPLIERS (SUP_ID))";
    Statement stmt = null;
    try {
//      con.setHoldability(..int..);
//      con.setReadOnly(...);
      stmt = con.createStatement();
      stmt.executeUpdate(createString);
    } catch (SQLException e) {
      JDBUtils.printSQLException(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }
  }

  public void populateTable() throws SQLException {
    Statement stmt = null;
    try {
      stmt = con.createStatement();
      stmt.executeUpdate("insert into COFFEES " +
                         "values('Colombian', 00101, 7.99, 0, 0)");
      stmt.executeUpdate("insert into COFFEES " +
                         "values('French_Roast', 00049, 8.99, 0, 0)");
      stmt.executeUpdate("insert into COFFEES " +
                         "values('Espresso', 00150, 9.99, 0, 0)");
      stmt.executeUpdate("insert into COFFEES " +
                         "values('Colombian_Decaf', 00101, 8.99, 0, 0)");
      stmt.executeUpdate("insert into COFFEES " +
                         "values('French_Roast_Decaf', 00049, 9.99, 0, 0)");
    } catch (SQLException e) {
      JDBUtils.printSQLException(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }
  }


  public void updateCoffeeSales(HashMap<String, Integer> salesForWeek) throws SQLException {
    PreparedStatement updateSales = null;
    PreparedStatement updateTotal = null;
    String updateString =
      "update COFFEES " + "set SALES = ? where COF_NAME = ?";
    String updateStatement =
      "update COFFEES " + "set TOTAL = TOTAL + ? where COF_NAME = ?";

    try {
      con.setAutoCommit(false);
      updateSales = con.prepareStatement(updateString);
      updateTotal = con.prepareStatement(updateStatement);

      for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) {
//        КАК БЫ НАЧАЛО ТРАНЗАКЦИИ
        updateSales.setInt(1, e.getValue().intValue());
        updateSales.setString(2, e.getKey());
        updateSales.executeUpdate();

        updateTotal.setInt(1, e.getValue().intValue());
        updateTotal.setString(2, e.getKey());
        updateTotal.executeUpdate();
        con.commit();
        // КОНЕЦ ТРАНЗАКЦИИ
      }
    } catch (SQLException e) {
      JDBUtils.printSQLException(e);
      if (con != null) {
        try {
          System.err.print("Transaction is being rolled back");
          con.rollback();
        } catch (SQLException excep) {
          JDBUtils.printSQLException(excep);
        }
      }
    } finally {
      if (updateSales != null) { updateSales.close(); }
      if (updateTotal != null) { updateTotal.close(); }
      con.setAutoCommit(true);
    }
  }

  public void modifyPrices(float percentage) throws SQLException {
    Statement stmt = null;
    try {
      stmt =
          con.createStatement(
                  ResultSet.TYPE_SCROLL_SENSITIVE,
                  ResultSet.CONCUR_UPDATABLE);
      ResultSet uprs = stmt.executeQuery("SELECT * FROM COFFEES");

      while (uprs.next()) {
        float f = uprs.getFloat("PRICE");
        uprs.updateFloat("PRICE", f * percentage);
        uprs.updateRow();
      }

    } catch (SQLException e) {
      JDBUtils.printSQLException(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }
  }


  public void modifyPricesByPercentage(String coffeeName,
             float priceModifier, float maximumPrice) throws SQLException {
    con.setAutoCommit(false);

    Statement getPrice = null;
    Statement updatePrice = null;
    ResultSet rs = null;
    String query =
      "SELECT COF_NAME, PRICE FROM COFFEES WHERE COF_NAME = '" +
      coffeeName + "'";

    try {
      Savepoint save1 = con.setSavepoint();
      getPrice = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//      getPrice = con.prepareStatement(query, ResultSet.CLOSE_CURSORS_AT_COMMIT);
      updatePrice = con.createStatement();

      if (!getPrice.execute(query)) {
        System.out.println("Could not find entry for coffee named " + coffeeName);
      } else {
        rs = getPrice.getResultSet();
        rs.first();
        float oldPrice = rs.getFloat("PRICE");
        float newPrice = oldPrice + (oldPrice * priceModifier);
        System.out.println("Old price of " + coffeeName + " is " + oldPrice);
        System.out.println("New price of " + coffeeName + " is " + newPrice);
        System.out.println("Performing update...");
        updatePrice.executeUpdate(
        "UPDATE COFFEES SET PRICE = " + newPrice + " WHERE COF_NAME = '" + coffeeName + "'");
        System.out.println("\nCOFFEES table after update:");
        CoffeesTable.viewTable(con);
        if (newPrice > maximumPrice) {
          System.out.println("\nThe new price, " + newPrice +
                             ", is greater than the maximum " + "price, " +
                             maximumPrice +
                             ". Rolling back the transaction...");
          con.rollback(save1);
          System.out.println("\nCOFFEES table after rollback:");
          CoffeesTable.viewTable(con);
        }
        con.commit();
      }
    } catch (SQLException e) {
      JDBUtils.printSQLException(e);
    } finally {
      if (getPrice != null) { getPrice.close(); }
      if (updatePrice != null) { updatePrice.close(); }
      con.setAutoCommit(true);
    }
  }


  public void insertRow(String coffeeName, int supplierID, float price,
                        int sales, int total) throws SQLException {
    Statement stmt = null;
    try {
      stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet selectAllQRS = stmt.executeQuery("SELECT * FROM COFFEES");

      selectAllQRS.moveToInsertRow();

      selectAllQRS.updateString("COF_NAME", coffeeName);
      selectAllQRS.updateInt("SUP_ID", supplierID);
      selectAllQRS.updateFloat("PRICE", price);
      selectAllQRS.updateInt("SALES", sales);
      selectAllQRS.updateInt("TOTAL", total);

      selectAllQRS.insertRow();
      selectAllQRS.beforeFirst();

    } catch (SQLException e) {
      JDBUtils.printSQLException(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }
  }

  /**
   * TEST DIRTY READ
   * @throws SQLException
     */
  public void update1coffee() throws SQLException {
    this.con.setAutoCommit(false);
    String update1coffee = "update COFFEES set PRICE=? where COF_NAME=?";
    PreparedStatement up1c = con.prepareStatement(update1coffee);
    up1c.setFloat(1, 10.10f);
    up1c.setString(2, "Kona2");
    up1c.executeUpdate();
    System.out.println("____________ BEFORE COMMIT ________update1coffee_______");
    viewTable(this.con);
//    this.con.commit();
    this.con.rollback();
    System.out.println("____________ AFTER COMMIT _________update1coffee______");
    viewTable(this.con);
    this.con.setAutoCommit(true);
  }

  public void batchUpdate() throws SQLException {

    Statement stmt = null;
    try {



      this.con.setAutoCommit(false);
      stmt = this.con.createStatement();
      stmt.addBatch("INSERT INTO COFFEES VALUES('Belarus_Amaretto', 49, 9.99, 0, 0)");
      stmt.addBatch("INSERT INTO COFFEES VALUES('Hazelnut', 49, 9.99, 0, 0)");
      stmt.addBatch("INSERT INTO COFFEES VALUES('Belarus_Amaretto_decaf', 49, 10.99, 0, 0)");
      stmt.addBatch("INSERT INTO COFFEES VALUES('Hazelnut_decaf', 49, 10.99, 0, 0)");

      PreparedStatement pstmt = con.prepareStatement(
              "INSERT INTO COFFEES VALUES( ?, ?, ?, ?, ?)");
      pstmt.setString(1, "Amaretto2");
      pstmt.setInt(2, 49);
      pstmt.setFloat(3, 7.77f);
      pstmt.setInt(4, 0);
      pstmt.setInt(5, 0);
      pstmt.addBatch();

      pstmt.setString(1, "Hazelnut2");
      pstmt.setInt(2, 49);
      pstmt.setFloat(3, 8.88f);
      pstmt.setInt(4, 0);
      pstmt.setInt(5, 0);
      pstmt.addBatch();

      int[] res1 = pstmt.executeBatch();
      int[] updateCounts = stmt.executeBatch();
      this.con.commit();

    } catch (BatchUpdateException b) {
      JDBUtils.printBatchUpdateException(b);
    } catch (SQLException ex) {
      JDBUtils.printSQLException(ex);
    } finally {
      if (stmt != null) { stmt.close(); }
      this.con.setAutoCommit(true);
    }
  }
  
  public static void viewTable(Connection con) throws SQLException {
    if (con != null) {


      String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";
//    Statement stmt = null;
//    try {
//      stmt = con.createStatement();
//    Note that the close methods of resources are called in the opposite order of their creation.
      try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query);) {
//      ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
          String coffeeName = rs.getString("COF_NAME");
          int supplierID = rs.getInt("SUP_ID");
          float price = rs.getFloat("PRICE");
          int sales = rs.getInt("SALES");
          int total = rs.getInt("TOTAL");
          System.out.println(coffeeName + ", " + supplierID + ", " + price + ", " + sales + ", " + total);
        }

      } catch (SQLException e) {
        JDBUtils.printSQLException(e);
      }
//      finally {
//      if (stmt != null) { stmt.close(); }
//    }
    } else {
      System.out.println("______________ con is NULL ___________viewTable_______");
    }

  }

  public static void alternateViewTable(Connection con) throws SQLException {
    String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";
    Statement stmt = null;
    try {
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String coffeeName = rs.getString(1);
        int supplierID = rs.getInt(2);
        float price = rs.getFloat(3);
        int sales = rs.getInt(4);
        int total = rs.getInt(5);
        System.out.println(coffeeName + ", " + supplierID + ", " + price +
                           ", " + sales + ", " + total);
      }
    } catch (SQLException e) {
      JDBUtils.printSQLException(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }
  }
  
  public Set<String> getKeys() throws SQLException {
    HashSet<String> keys = new HashSet<String>();
    Statement stmt = null;
    String query = "select COF_NAME from COFFEES";
    try {
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        keys.add(rs.getString(1));
      }
    } catch (SQLException e) {
      JDBUtils.printSQLException(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }
    return keys;
    
  }


  public void dropTable() throws SQLException {
    Statement stmt = null;
    try {
      stmt = con.createStatement();
      if (this.dbms.equals("mysql")) {
        stmt.executeUpdate("DROP TABLE IF EXISTS COFFEES");
      } else if (this.dbms.equals("derby")) {
        stmt.executeUpdate("DROP TABLE COFFEES");
      }
    } catch (SQLException e) {
      JDBUtils.printSQLException(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }
  }

}
