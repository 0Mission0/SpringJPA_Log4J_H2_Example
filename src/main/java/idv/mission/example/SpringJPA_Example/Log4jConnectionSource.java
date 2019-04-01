package idv.mission.example.SpringJPA_Example;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.core.appender.db.jdbc.ConnectionSource;

public class Log4jConnectionSource implements ConnectionSource {
    private DataSource dataSource;

    public Log4jConnectionSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}