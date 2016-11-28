package main;

import utils.JDBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class MyRun {

    public static void main(String[] args) {
    Connection connect = null;
    Connection connect2 = null;
        JDBUtils myJDBUtils =
                JDBUtils.getInstance(args[0]);
        if (myJDBUtils == null) return;

    try {
        connect = myJDBUtils.getConnection();
        connect2 = myJDBUtils.getConnection();
        System.out.println("connection = " + connect);
        System.out.println("transaction isolation="+connect.getTransactionIsolation());
        System.out.println("connection2 = " + connect2);
        System.out.println("transaction isolation2="+connect.getTransactionIsolation());
        // Java DB does not have an SQL create database command; it does require createDatabase
//      JDBUtils.createDatabase(connect,
//                                           myJDBUtils.dbName,
//                                           myJDBUtils.dbms);
//      JDBUtils.initializeTables(connect,
//                                             myJDBUtils.dbName,
//                                             myJDBUtils.dbms);

        CoffeesTable myCoffeeTable =
                new CoffeesTable(connect, myJDBUtils.dbName, myJDBUtils.dbms);
/*
        System.out.println("\nContents of COFFEES table:");
        CoffeesTable.viewTable(connect);
        myCoffeeTable.update1coffee();

        System.out.println("\nRaising coffee prices by 25%");
        myCoffeeTable.modifyPrices(1.25f);

        System.out.println("\nInserting a new row:");
        myCoffeeTable.insertRow("Kona2", 150, 10.99f, 0, 0);
        CoffeesTable.viewTable(connect);

        System.out.println("\nUpdating sales of coffee per week:");
        HashMap<String, Integer> salesCoffeeWeek =new HashMap<String, Integer>();
        salesCoffeeWeek.put("Colombian", 175);
        salesCoffeeWeek.put("French_Roast", 150);
        salesCoffeeWeek.put("Espresso", 60);
        salesCoffeeWeek.put("Colombian_Decaf", 155);
        salesCoffeeWeek.put("French_Roast_Decaf", 90);
        myCoffeeTable.updateCoffeeSales(salesCoffeeWeek);
        CoffeesTable.viewTable(connect);
*/
        System.out.println("\nModifying prices by percentage");
        myCoffeeTable.modifyPricesByPercentage("Colombian", 0.10f, 9.00f);
        System.out.println("\nCOFFEES table after modifying prices by percentage:");
        CoffeesTable.viewTable(connect);
/*
        System.out.println("\nPerforming batch updates; adding new coffees");
        myCoffeeTable.batchUpdate();
        myCoffeeTable.viewTable(connect);
*/

//      System.out.println("\nDropping Coffee and Suplliers table:");
//
//      myCoffeeTable.dropTable();
//      mySuppliersTable.dropTable();

    } catch (SQLException e) {
        JDBUtils.printSQLException(e);
    } finally {
        JDBUtils.closeConnection(connect);
    }
}
}
