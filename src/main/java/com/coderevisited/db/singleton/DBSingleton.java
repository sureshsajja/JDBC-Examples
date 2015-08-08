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

package com.coderevisited.db.singleton;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User :  Suresh
 * Date :  06/08/15
 * Version : v1
 */
public class DBSingleton {

    private DBConfig config;
    private Statement connection;

    private DBSingleton() {

        try {
            config = new DBConfig();
            connection = setConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Statement setConnection() {
        try {
            String url = "" + config.getDbUrl() + ":" + config.getDbPort() + "/" + config.getDbName() + "";
            java.sql.Connection conn = DriverManager.getConnection(url, config.getDbUser(), config.getDbPassword());
            return conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    //Should be called before application shutdown.
    public void close() throws SQLException {
        connection.close();
    }


    private static class DBSingletonManagerHolder {
        private final static DBSingleton instance = new DBSingleton();
    }

    public static DBSingleton getInstance() {
        try {
            return DBSingletonManagerHolder.instance;
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Statement getStatement() throws SQLException {
        if (connection == null)
            throw new SQLException("Connection is not established");
        return connection;
    }


}
