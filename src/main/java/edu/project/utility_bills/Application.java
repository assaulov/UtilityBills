package edu.project.utility_bills;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

@SpringBootApplication(exclude={JacksonAutoConfiguration.class, JmxAutoConfiguration.class})

public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
