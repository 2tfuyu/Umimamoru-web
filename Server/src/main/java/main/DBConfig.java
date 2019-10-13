package main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource driverManagerDataSource = new BasicDataSource();
        driverManagerDataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/umimamoru");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("Hkkgeanvty930/");
        //driverManagerDataSource.setPassword("");
        return driverManagerDataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}