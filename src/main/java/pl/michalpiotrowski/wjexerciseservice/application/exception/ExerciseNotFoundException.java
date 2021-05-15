package pl.michalpiotrowski.wjexerciseservice.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Exercise does not exist")
public class ExerciseNotFoundException extends RuntimeException {
}
