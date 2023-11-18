package automated_readme_tests

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.jzel.m450bowling.server.M450BowlingApplication
import com.jzel.m450bowling.server.persistence.domain.game.GamePersistence
import com.jzel.m450bowling.server.webservice.adapter.rest.GameThrowController
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.testcontainers.junit.jupiter.Testcontainers


@ActiveProfiles("test")
@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [M450BowlingApplication::class, GameThrowController::class, GamePersistence::class]
)
@Transactional
class EmptyDBReadMeCasesTest {
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var controller: GameThrowController

    @Autowired
    private lateinit var persistence: GamePersistence

    @BeforeEach
    fun init() {
        persistence.deleteAll()
        JacksonTester.initFields(this, ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    // Testfall 1
    @Test
    fun emptyGame_strikeWithAdditionalThrows_scoreOfStrikeFrameIsCumulated() {
        val response = mvc.perform(MockMvcRequestBuilders.post("/throw/10").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn().response.contentAsString

        assertEquals(
            "X", ObjectMapper().registerKotlinModule().readTree(response)
                .path("frames")[0].path("throws")[0].path("display").asText()
        )
    }
}
