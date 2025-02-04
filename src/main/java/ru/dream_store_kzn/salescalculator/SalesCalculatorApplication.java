package ru.dream_store_kzn.salescalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.dream_store_kzn.salescalculator.properties.SalesReportProperties;

@SpringBootApplication
@EnableConfigurationProperties(SalesReportProperties.class)
public class SalesCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesCalculatorApplication.class, args);
    }

}
