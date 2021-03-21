package pl.michalpiotrowski.wjexerciseservice.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalpiotrowski.wjapispecification.dto.exercise.NewExerciseDto;
import pl.michalpiotrowski.wjexerciseservice.domain.Exercise;
import pl.michalpiotrowski.wjexerciseservice.domain.ExerciseCategory;
import pl.michalpiotrowski.wjexerciseservice.domain.ExerciseRepository;
import pl.michalpiotrowski.wjexerciseservice.domain.ExerciseType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public Exercise createExercise(final NewExerciseDto newExerciseDto) {
        return exerciseRepository.save(
                Exercise
                        .builder()
                        .name(newExerciseDto.getName())
                        .category(ExerciseCategory.valueOf(newExerciseDto.getCategory()))
                        .type(ExerciseType.valueOf(newExerciseDto.getType()))
                        .description(newExerciseDto.getDescription())
                        .videoUrl(newExerciseDto.getVideoUrl())
                        .build()
        );
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }
}
