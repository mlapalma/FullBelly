package com.aieme.pleasedheart.models.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class MySqlDataSource implements DataSource {

    private Connection conn;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException ex) {
          ex.printStackTrace();
        }
        return conn;
    }

}
