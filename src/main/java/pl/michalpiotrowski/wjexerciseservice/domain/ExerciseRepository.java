package pl.michalpiotrowski.wjexerciseservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

    public List<Exercise> findAllByUser(String user);
    public Optional<Exercise> findByIdAndUser(UUID id, String user);
}
