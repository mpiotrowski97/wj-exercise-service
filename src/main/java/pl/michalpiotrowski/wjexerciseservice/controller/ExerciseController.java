package pl.michalpiotrowski.wjexerciseservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {

    @GetMapping("/api/v1/exercises")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("EXERCISES");
    }
}
