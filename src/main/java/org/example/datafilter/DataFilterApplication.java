package org.example.datafilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DataFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataFilterApplication.class, args);
    }

}
