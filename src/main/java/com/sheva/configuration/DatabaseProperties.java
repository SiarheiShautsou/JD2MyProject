package com.sheva.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@PropertySource("classpath:database.properties")
public class DatabaseProperties {

    @Value("${POSTRGES_DRIVER_NAME}")
    private String driverName;

    @Value("${DATABASE_URL}")
    private String url;

    @Value("${DATABASE_LOGIN}")
    private String login;

    @Value("${DATABASE_PASSWORD}")
    private String password;

}
