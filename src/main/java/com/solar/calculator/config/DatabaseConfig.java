package com.solar.calculator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.solar.calculator")
public class DatabaseConfig {

    @Value("${database.url}")
    String databaseURL;
    @Value("${database.username}")
    String username;
    @Value("${database.password}")
    String password;

    GlobalDataSource getGlobalDataSource(){
        return new GlobalDataSource(databaseURL,username,password);
    }
}
