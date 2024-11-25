package org.example.datafilter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocketConsumerService {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Value("${socket.port}")
    private int port;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void startConsumer() {
        try (Socket socket = new Socket("localhost", port);
             var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var fileWriter = new FileWriter("output.txt", true)) {

            String line;
            while ((line = reader.readLine()) != null) {
                handleData(line, fileWriter);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void handleData(String line, FileWriter fileWriter) throws IOException {
        var parts = line.split(",");
        var value = Integer.parseInt(parts[1]);
        if (value > 90) {
            System.out.println("Data send to kafka" + value);
            kafkaTemplate.send(topic, line);
        } else {
            System.out.println("Data written to output.txt" + value);
            fileWriter.append(line).append(System.lineSeparator());
            fileWriter.flush();
        }
    }
}
