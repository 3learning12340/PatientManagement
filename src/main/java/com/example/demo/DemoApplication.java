package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
//    second
    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
        System.out.println("hot fix change");
        System.out.println("second");
        System.out.println("pull test");
    }


}
