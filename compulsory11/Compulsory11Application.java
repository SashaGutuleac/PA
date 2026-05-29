package org.example.compulsory11;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Compulsory11Application {

    public static void main(String[] args) {
        SpringApplication.run(Compulsory11Application.class, args);
    }

    @Bean
    public CommandLineRunner testApp(StudentRepository repo) {
        return args -> {
            System.out.println("\n--- INCEPEM TESTAREA JPA ---");

            repo.save(new Student("Alexandru Popescu"));
            repo.save(new Student("Maria Ionescu"));

            for (Student s : repo.findAll()) {
                System.out.println("ID: " + s.getId() + " | Nume: " + s.getName());
            }

            System.out.println("--- TEST TERMINAT CU SUCCES ---\n");
        };
    }
}