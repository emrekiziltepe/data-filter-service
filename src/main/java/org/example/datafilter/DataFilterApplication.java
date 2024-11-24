package org.example.datafilter;

import lombok.RequiredArgsConstructor;
import org.example.datafilter.service.SocketConsumerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class DataFilterApplication {

    private final SocketConsumerService socketConsumerService;

    public static void main(String[] args) {
        SpringApplication.run(DataFilterApplication.class, args);
    }

    @Bean
    public CommandLineRunner startConsumer() {
        return args -> socketConsumerService.startConsumer();
    }

}
