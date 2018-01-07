package com.aieme.pleasedheart.models.datasources;

import java.sql.Connection;

public interface DataSource {

    public Connection getConnection();
}
