package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({ "com.backend.controller", "com.backend.service", "com.backend.config" })
@EntityScan("com.backend.model")
@EnableJpaRepositories("com.backend.repository")
@SpringBootApplication
public class ServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class, args);
  }

}
