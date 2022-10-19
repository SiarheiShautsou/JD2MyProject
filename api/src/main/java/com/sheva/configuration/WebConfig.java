package com.sheva.configuration;

import com.sheva.controller.converters.GymCreateConverter;
import com.sheva.controller.converters.UserCreateRequestConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {


    }
}
