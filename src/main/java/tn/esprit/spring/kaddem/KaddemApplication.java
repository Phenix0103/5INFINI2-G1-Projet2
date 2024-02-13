package tn.esprit.spring.kaddem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KaddemApplication {
    //test jenkinsfile 2
    public static void main(String[] args) {
        SpringApplication.run(KaddemApplication.class, args);
    }

}
