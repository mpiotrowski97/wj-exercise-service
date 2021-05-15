package pl.michalpiotrowski.wjexerciseservice.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.michalpiotrowski.wjapispecification.dto.exercise.ExerciseDtoRequest;
import pl.michalpiotrowski.wjapispecification.dto.exercise.ExerciseDto;
import pl.michalpiotrowski.wjexerciseservice.ExerciseTestFixture;
import pl.michalpiotrowski.wjexerciseservice.application.service.UserService;
import pl.michalpiotrowski.wjexerciseservice.domain.Exercise;
import pl.michalpiotrowski.wjexerciseservice.domain.ExerciseCategory;
import pl.michalpiotrowski.wjexerciseservice.domain.ExerciseRepository;
import pl.michalpiotrowski.wjexerciseservice.domain.ExerciseType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
public class ExerciseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ExerciseRepository exerciseRepository;

    @MockBean
    UserService userService;

    @Before
    public void setUp() {
        Mockito.when(userService.getCurrentUserName()).thenReturn(ExerciseTestFixture.EXERCISE_USER);
    }

    @Test
    public void createExerciseTest() throws Exception {
        val newExerciseDto = ExerciseDtoRequest
                .builder()
                .name(ExerciseTestFixture.EXERCISE_NAME)
                .type(ExerciseTestFixture.EXERCISE_TYPE)
                .category(ExerciseTestFixture.EXERCISE_CATEGORY)
                .build();

        val result = mockMvc
                .perform(
                        post("/api/v1/exercises")
                                .content(objectMapper.writeValueAsString(newExerciseDto))
                                .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andReturn();

        val response = objectMapper
                .readValue(result.getResponse().getContentAsString(), ExerciseDto.class);

        assertThat(response.getName()).isEqualTo(ExerciseTestFixture.EXERCISE_NAME);
        assertThat(response.getId()).isNotNull();
    }

    @Test
    public void getExercisesTest() throws Exception {
        val exercise = exerciseRepository.save(
                Exercise
                        .builder()
                        .name(ExerciseTestFixture.EXERCISE_NAME)
                        .category(ExerciseCategory.BICEPS)
                        .type(ExerciseType.TIME)
                        .user(ExerciseTestFixture.EXERCISE_USER)
                        .build()
        );

        val result = mockMvc
                .perform(
                        get("/api/v1/exercises")
                                .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains(exercise.getId().toString());
    }
}
