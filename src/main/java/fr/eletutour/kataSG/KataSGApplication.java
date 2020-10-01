package fr.eletutour.kataSG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class KataSGApplication {
    public static void main(String[] args) {
        SpringApplication.run(KataSGApplication.class, args);
    }
}
