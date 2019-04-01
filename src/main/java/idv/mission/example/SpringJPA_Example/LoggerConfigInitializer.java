package idv.mission.example.SpringJPA_Example;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.db.ColumnMapping;
import org.apache.logging.log4j.core.appender.db.jdbc.ColumnConfig;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Ref:
 * https://stackoverflow.com/questions/48104672/spring-boot-configure-log4j2-to-use-spring-boot-datasource
 * http://smasue.github.io/log4j2-spring-database-appender
 * http://www.andreagirardi.it/blog/how-to-use-spring-datasource-bean-as-data-source-for-log4j-2-jdbc-appender/
 */
@Component
public class LoggerConfigInitializer {

    @Autowired
    private DataSource dataSource;

    public LoggerConfigInitializer() {
    }

    @PostConstruct
    private void init() {
        System.out.println("####### LogInitializer init() ########");
        final LoggerContext context = (LoggerContext) LogManager.getContext(false);
        final Configuration config = context.getConfiguration();

        // Here define the columns We want to log
        ColumnConfig[] columnConfigs = new ColumnConfig[] {
                ColumnConfig.newBuilder()
                    .setName("APPLICATION")
                    .setPattern("DEMO")
                    .setLiteral(null)
                    .setEventTimestamp(false)
                    .setUnicode(false)
                    .setClob(false).build(),
                ColumnConfig.newBuilder()
                    .setName("LOG_DATE")
                    .setPattern(null)
                    .setLiteral(null)
                    .setEventTimestamp(true)
                    .setUnicode(false)
                    .setClob(false).build(),
                ColumnConfig.newBuilder()
                    .setName("LOGGER")
                    .setPattern("%logger")
                    .setLiteral(null)
                    .setEventTimestamp(false)
                    .setUnicode(false)
                    .setClob(false).build(),
                ColumnConfig.newBuilder()
                    .setName("LOG_LEVEL")
                    .setPattern("%level")
                    .setLiteral(null)
                    .setEventTimestamp(false)
                    .setUnicode(false)
                    .setClob(false).build(),
                ColumnConfig.newBuilder()
                    .setName("MESSAGE")
                    .setPattern("%message")
                    .setLiteral(null)
                    .setEventTimestamp(false)
                    .setUnicode(false)
                    .setClob(false).build()
        };

        Appender appender = JdbcAppender.newBuilder()
            .setBufferSize(0)
            .setColumnConfigs(columnConfigs)
            .setColumnMappings(new ColumnMapping[] {})
            .setConnectionSource(new Log4jConnectionSource(dataSource))
            .setTableName("LOGS")
            .withName("databaseAppender")
            .withIgnoreExceptions(true)
            .withFilter(null)
            .build();

        appender.start();
        config.addAppender(appender);

        // Create an Appender reference.
        // @param ref The name of the Appender.
        // @param level The Level to filter against.
        // @param filter The filter(s) to use.
        // @return The name of the Appender.
        AppenderRef ref = AppenderRef.createAppenderRef("JDBC_Appender", null, null);
        AppenderRef[] refs = new AppenderRef[] { ref };

        // Factory method to create a LoggerConfig.
        // @param additivity true if additive, false otherwise.
        // @param level The Level to be associated with the Logger.
        // @param loggerName The name of the Logger.
        // @param includeLocation whether location should be passed downstream
        // @param refs An array of Appender names.
        // @param properties Properties to pass to the Logger.
        // @param config The Configuration.
        // @param filter A Filter.
        // @return A new LoggerConfig.
        // @since 2.6
        LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.DEBUG, "JDBC_Logger", null, refs, null, config, null);
        loggerConfig.addAppender(appender, null, null);

        config.addLogger("JDBC_Logger", loggerConfig);
        context.updateLoggers();

        System.out.println("####### LogInitializer init() - DONE ########");

    }

}