package com.veryitman.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class MSAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MSAdminApplication.class, args);
    }

}
