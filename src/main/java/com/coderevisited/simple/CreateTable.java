package com.coderevisited.simple;


import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.*;

public class CreateTable
{
    private Connection connection;
    private static final String SUPPLIERS_SQL =
            "CREATE TABLE IF NOT EXISTS SUPPLIERS (SUP_ID INTEGER NOT NULL,SUP_NAME VARCHAR(40) NOT NULL, " +
                    "STREET VARCHAR(40) NOT NULL, CITY VARCHAR(20) NOT NULL, STATE CHAR(2) NOT NULL, ZIP CHAR(5), PRIMARY KEY (SUP_ID))";
    private static final String COFFEE_SQL =
            "CREATE TABLE IF NOT EXISTS COFFEES  (COF_NAME varchar(32) NOT NULL, SUP_ID int NOT NULL, " +
                    "PRICE numeric(10,2) NOT NULL,SALES integer NOT NULL, TOTAL integer NOT NULL, PRIMARY KEY (COF_NAME)," +
                    "FOREIGN KEY (SUP_ID) REFERENCES SUPPLIERS (SUP_ID))";

    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        DriverManager.setLogWriter(pw);
        CreateTable table = new CreateTable();
        table.createConnection();
        table.execute(SUPPLIERS_SQL);
        table.execute(COFFEE_SQL);
        table.close();
        pw.close();

    }

    private void close() throws SQLException
    {
        connection.close();
    }

    private void execute(String sql) throws SQLException
    {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.execute();
        stmt.close();
    }

    public void createConnection() throws SQLException, ClassNotFoundException
    {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorials", "root", "root");
    }
}
