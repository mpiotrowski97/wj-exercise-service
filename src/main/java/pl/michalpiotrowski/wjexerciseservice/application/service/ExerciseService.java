package pl.michalpiotrowski.wjexerciseservice.application.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.michalpiotrowski.wjapispecification.dto.exercise.ExerciseDtoRequest;
import pl.michalpiotrowski.wjexerciseservice.application.exception.ExerciseNotFoundException;
import pl.michalpiotrowski.wjexerciseservice.domain.Exercise;
import pl.michalpiotrowski.wjexerciseservice.domain.ExerciseCategory;
import pl.michalpiotrowski.wjexerciseservice.domain.ExerciseRepository;
import pl.michalpiotrowski.wjexerciseservice.domain.ExerciseType;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final UserService userService;
    private final ExerciseRepository exerciseRepository;

    public Exercise createExercise(final ExerciseDtoRequest exerciseDtoRequest) {
        return exerciseRepository.save(
                Exercise
                        .builder()
                        .name(exerciseDtoRequest.getName())
                        .category(ExerciseCategory.valueOf(exerciseDtoRequest.getCategory()))
                        .type(ExerciseType.valueOf(exerciseDtoRequest.getType()))
                        .description(exerciseDtoRequest.getDescription())
                        .videoUrl(exerciseDtoRequest.getVideoUrl())
                        .user(userService.getCurrentUserName())
                        .build()
        );
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAllByUser(userService.getCurrentUserName());
    }

    public void removeExercise(UUID id) {
        exerciseRepository.findByIdAndUser(id, userService.getCurrentUserName())
                .ifPresent(exerciseRepository::delete);
    }

    public Exercise findOne(UUID id) {
        return exerciseRepository.findByIdAndUser(id, userService.getCurrentUserName())
                .orElseThrow(ExerciseNotFoundException::new);
    }

    public void updateExercise(UUID id, ExerciseDtoRequest exerciseDtoRequest) {
        val exercise = exerciseRepository.findByIdAndUser(id, userService.getCurrentUserName())
                .orElseThrow(ExerciseNotFoundException::new);
        exercise.setName(exerciseDtoRequest.getName());
        exercise.setDescription(exercise.getDescription());
        exercise.setCategory(ExerciseCategory.valueOf(exerciseDtoRequest.getCategory()));
        exercise.setType(ExerciseType.valueOf(exerciseDtoRequest.getType()));
        exercise.setVideoUrl(exerciseDtoRequest.getVideoUrl());

        exerciseRepository.save(exercise);
    }
}
