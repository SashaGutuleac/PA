package org.example.homework7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping
    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return repository.save(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movieDetails) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filmul nu exista!"));
        movie.setTitle(movieDetails.getTitle());
        movie.setScore(movieDetails.getScore());
        return repository.save(movie);
    }

    @PatchMapping("/{id}/score")
    public Movie updateScore(@PathVariable Integer id, @RequestParam Double score) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filmul nu exista!"));
        movie.setScore(score);
        return repository.save(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Filmul nu exista!");
        }
        repository.deleteById(id);
        return ResponseEntity.ok("Sters cu succes!");
    }
}