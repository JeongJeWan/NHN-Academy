package com.nhnacademy.airport;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DbUtilsModule12 {
    private static final DataSource DATASOURCE;

    private DbUtilsModule12() {
    }

//    public static Connection getConnection() {
//        try {
//            return DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/module06",
//                    "${아이디}",
//                    "${패스워드}");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static DataSource getDataSource() {
        return DATASOURCE;
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/module12");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("258011");
        basicDataSource.setInitialSize(10);
        basicDataSource.setMaxTotal(10);

        DATASOURCE = basicDataSource;
    }
}
