package pl.michalpiotrowski.wjexerciseservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michalpiotrowski.wjapispecification.dto.exercise.NewExerciseDto;
import pl.michalpiotrowski.wjapispecification.dto.exercise.ExerciseDto;
import pl.michalpiotrowski.wjexerciseservice.api.converter.ExerciseConverter;
import pl.michalpiotrowski.wjexerciseservice.application.service.ExerciseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("")
    public ResponseEntity<ExerciseDto> create(@Valid @RequestBody NewExerciseDto newExerciseDto) {
        return ResponseEntity.ok(
                ExerciseConverter.exerciseDto(exerciseService.createExercise(newExerciseDto))
        );
    }

    @GetMapping("")
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        return ResponseEntity.ok(
                ExerciseConverter.exerciseDtoList(exerciseService.getAllExercises())
        );
    }
}
