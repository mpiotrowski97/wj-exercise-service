package pl.michalpiotrowski.wjexerciseservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "exercises")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Exercise {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    private String name;
    private String description;
    private String videoUrl;
    private ExerciseType type;
    private ExerciseCategory category;
}
