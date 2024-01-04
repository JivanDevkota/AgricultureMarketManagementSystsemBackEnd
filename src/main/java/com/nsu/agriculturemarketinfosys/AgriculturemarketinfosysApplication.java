package com.nsu.agriculturemarketinfosys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication

//@EntityScan(basePackages = "com.nsu.agriculturemarketinfosys.entity")
@ServletComponentScan
public class AgriculturemarketinfosysApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgriculturemarketinfosysApplication.class, args);
    }

}
