package pl.michalpiotrowski.wjexerciseservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michalpiotrowski.wjapispecification.dto.exercise.ExerciseDtoRequest;
import pl.michalpiotrowski.wjapispecification.dto.exercise.ExerciseDto;
import pl.michalpiotrowski.wjexerciseservice.api.converter.ExerciseConverter;
import pl.michalpiotrowski.wjexerciseservice.application.service.ExerciseService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> findOne(@PathVariable UUID id) {
        return ResponseEntity.ok(ExerciseConverter.exerciseDto(exerciseService.findOne(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody ExerciseDtoRequest exerciseDtoRequest) {
        exerciseService.updateExercise(id, exerciseDtoRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<ExerciseDto> create(@Valid @RequestBody ExerciseDtoRequest exerciseDtoRequest) {
        return ResponseEntity.ok(
                ExerciseConverter.exerciseDto(exerciseService.createExercise(exerciseDtoRequest))
        );
    }

    @GetMapping("")
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        return ResponseEntity.ok(
                ExerciseConverter.exerciseDtoList(exerciseService.getAllExercises())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeExercise(@PathVariable UUID id) {
        exerciseService.removeExercise(id);
        return ResponseEntity.noContent().build();
    }
}
