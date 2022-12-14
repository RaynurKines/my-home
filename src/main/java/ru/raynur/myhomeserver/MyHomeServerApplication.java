package ru.raynur.myhomeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.raynur.myhomeserver")
public class MyHomeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyHomeServerApplication.class, args);
    }

}
