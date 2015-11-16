/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 CodeRevisited.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.coderevisited.simple;


import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.*;

public class CreateTable {
    private Connection connection;
    private static final String SUPPLIERS_SQL =
            "CREATE TABLE IF NOT EXISTS SUPPLIERS (SUP_ID INTEGER NOT NULL,SUP_NAME VARCHAR(40) NOT NULL, " +
                    "STREET VARCHAR(40) NOT NULL, CITY VARCHAR(20) NOT NULL, STATE CHAR(2) NOT NULL, ZIP CHAR(5), PRIMARY KEY (SUP_ID))";
    private static final String COFFEE_SQL =
            "CREATE TABLE IF NOT EXISTS COFFEES  (COF_NAME varchar(32) NOT NULL, SUP_ID int NOT NULL, " +
                    "PRICE numeric(10,2) NOT NULL,SALES integer NOT NULL, TOTAL integer NOT NULL, PRIMARY KEY (COF_NAME)," +
                    "FOREIGN KEY (SUP_ID) REFERENCES SUPPLIERS (SUP_ID))";

    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        DriverManager.setLogWriter(pw);
        CreateTable table = new CreateTable();
        try {
            table.createConnection();
            table.execute(SUPPLIERS_SQL);
            table.execute(COFFEE_SQL);
            table.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                table.close();
            } catch (SQLException e) {
                //ignore
            }
            pw.close();
        }


    }

    private void close() throws SQLException {
        if (connection != null)
            connection.close();
    }

    private void execute(String sql) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }

    }

    public void createConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorials", "root", "root");
    }
}
