package com.e_commerce.monolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;
import java.util.Properties;
import java.lang.*;

@SpringBootApplication
@EnableJpaAuditing
public class MonolithApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonolithApplication.class, args);
        Properties properties = System.getProperties();
        System.out.println("Java Library Path: " + System.getProperty("java.library.path"));
        System.gc();
        for (Map.Entry<Object, Object> objectObjectEntry : properties.entrySet()) {
            //System.out.println("key - " + objectObjectEntry.getKey() + " , value - "+ objectObjectEntry.getValue());
        }


    }

}
