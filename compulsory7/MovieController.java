package org.example.compulsory7;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class MovieController {

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        // returnam o lista de test
        return Arrays.asList(
                new Movie(1, "The Matrix"),
                new Movie(2, "Inception"),
                new Movie(3, "Interstellar")
        );
    }
}