package de.sivgin.coding_challenge.trainings.api;

import de.sivgin.coding_challenge.trainings.api.io.CreateTraining;
import de.sivgin.coding_challenge.trainings.api.io.TrainingResource;
import de.sivgin.coding_challenge.trainings.api.service.TrainingsCreationService;
import jakarta.json.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring MockMvc test for {@link TrainingsPostController}
 *
 * @author fatih
 * @since 14/09/2025
 */
@SpringBootTest
@AutoConfigureMockMvc
class TrainingsPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TrainingsCreationService trainingsCreationService;

    @Test
    void shouldSaveTraining() throws Exception {
        // given
        String body = Json.createObjectBuilder()
                .add("description", "Java Design Patterns")
                .add("price", 49.50)
                .add("speaker", "Dr. Chuck Norris")
                .build()
                .toString();

        UUID mockId = UUID.randomUUID();
        TrainingResource resource = new TrainingResource(mockId, "Java Design Patterns", 49.5f, "Dr. Chuck Norris", Collections.emptyList());

        when(trainingsCreationService.createTraining(any(CreateTraining.class))).thenReturn(resource);

        // test and then
        mockMvc.perform(post("/api/v1/trainings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())                  // ✅ 201 Created
                .andExpect(header().exists("Location"))           // ✅ Location header present
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockId.toString()))           // ✅ ID is returned
                .andExpect(jsonPath("$.description").value("Java Design Patterns"))
                .andExpect(jsonPath("$.price").value(49.5));
    }

    @Test
    void shouldNotSaveTraining() throws Exception {
        // given
        String body = Json.createObjectBuilder()
                .add("description", "Java Design Patterns")
                .add("speaker", "Dr. Chuck Norris")
                .build()
                .toString();

        // test and then
        mockMvc.perform(post("/api/v1/trainings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

}