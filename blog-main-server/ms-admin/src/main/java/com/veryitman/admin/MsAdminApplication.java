package com.veryitman.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class MsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAdminApplication.class, args);
    }

}
