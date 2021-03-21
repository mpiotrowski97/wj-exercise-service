package pl.michalpiotrowski.wjexerciseservice.api.converter;

import pl.michalpiotrowski.wjapispecification.dto.exercise.ExerciseDto;
import pl.michalpiotrowski.wjexerciseservice.domain.Exercise;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseConverter {

    public static List<ExerciseDto> exerciseDtoList(final List<Exercise> exercises) {
        return exercises.stream().map(ExerciseConverter::exerciseDto).collect(Collectors.toList());
    }

    public static ExerciseDto exerciseDto(final Exercise exercise) {
        return ExerciseDto
                .builder()
                .name(exercise.getName())
                .category(exercise.getCategory().toString())
                .type(exercise.getType().toString())
                .description(exercise.getDescription())
                .videoUrl(exercise.getVideoUrl())
                .id(exercise.getId())
                .build();
    }
}
