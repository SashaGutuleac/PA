package org.example.homework11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Homework11Application {

    private static final Logger logger = LoggerFactory.getLogger(Homework11Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Homework11Application.class, args);
    }

    @Bean
    public CommandLineRunner runHomework(StudentHwRepository studentRepo, SchoolRepository schoolRepo) {
        return args -> {
            logger.info("=== START HOMEWORK 11 ===");

            School scoala = new School("Universitatea din Iasi");
            schoolRepo.save(scoala);

            Student s1 = new Student("Alexandru", scoala);
            Course c1 = new Course("Programare Avansata");
            s1.getCourses().add(c1);
            studentRepo.save(s1);

            long startRead = System.currentTimeMillis();
            studentRepo.findStudentsBySchoolName("Universitatea din Iasi");
            long endRead = System.currentTimeMillis();
            logger.info("JPQL READ a durat: " + (endRead - startRead) + " ms");

            long startUpdate = System.currentTimeMillis();
            studentRepo.updateStudentName(s1.getId(), "Alexandru-Marian");
            long endUpdate = System.currentTimeMillis();
            logger.info("JPQL UPDATE a durat: " + (endUpdate - startUpdate) + " ms");

            try {
                logger.info("Incercam sa salvam un student fara nume (fortam o eroare)...");
                Student invalidStudent = new Student(null, scoala);
                studentRepo.save(invalidStudent);
            } catch (Exception e) {
                logger.error("Exceptie prinsa: Nu se poate salva un student cu nume null!", e);
            }

            logger.info("=== SUCCES: Verifica baza de date si fisierul homework11.log ===");
            System.exit(0);
        };
    }
}