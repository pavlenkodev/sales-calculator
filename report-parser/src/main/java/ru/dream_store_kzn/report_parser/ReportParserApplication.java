package ru.dream_store_kzn.report_parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.dream_store_kzn.report_parser.properties.SalesReportProperties;

@SpringBootApplication
@EnableConfigurationProperties(SalesReportProperties.class)
public class ReportParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportParserApplication.class, args);
    }

}
