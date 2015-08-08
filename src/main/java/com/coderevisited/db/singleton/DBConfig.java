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
import java.util.Properties;

/**
 * User :  Suresh
 * Date :  07/08/15
 * Version : v1
 */
public class DBConfig {

    private final String dbUrl;
    private final String dbPort;
    private final String dbName;
    private final String dbUser;
    private final String dbPassword;

    public DBConfig() throws IOException {
        Properties properties = new Properties();
        properties.load(DBConfig.class.getResourceAsStream("connection.properties"));
        dbUrl = properties.getProperty("db.url");
        dbPort = properties.getProperty("db.port");
        dbName = properties.getProperty("db.name");
        dbUser = properties.getProperty("db.user");
        dbPassword = properties.getProperty("db.password");
    }


    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbPort() {
        return dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
