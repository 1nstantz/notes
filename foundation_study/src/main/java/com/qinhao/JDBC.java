package com.qinhao;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/11/29 - 22:07
 */
public class JDBC {

    @Test
    public void Jdbc() throws SQLException {
        Driver driver = new Driver();
        String url = "jdbc:mysql://localhost:3306/saas?serverTimezone=UTC";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        Connection connect = driver.connect(url,info);
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from event");
        connect.close();
        statement.close();
    }
}
