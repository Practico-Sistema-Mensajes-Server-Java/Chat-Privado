package org.main_java.chatprivado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ChatPrivadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatPrivadoApplication.class, args);
    }

}
