package com.jzel.m450bowling.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.jzel.m450bowling.server.persistence.domain.game.GamePersistence
import com.jzel.m450bowling.server.webservice.adapter.rest.GameThrowController
import jakarta.transaction.Transactional
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [M450BowlingApplication::class, GameThrowController::class, GamePersistence::class]
)
@Transactional
class AutomatedReadMeCasesTest {
    private lateinit var mvc: MockMvc

    private lateinit var helper: TestHelper

    @Autowired
    private lateinit var controller: GameThrowController

    @Autowired
    private lateinit var persistence: GamePersistence

    @BeforeEach
    fun init() {
        persistence.deleteAll()
        JacksonTester.initFields(this, ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        helper = TestHelper(mvc)
    }

    /**
     * test case #1 from ReadMe.md
     */
    @Test
    fun emptyGame_strikeWithAdditionalThrows_scoreOfStrikeFrameIsCumulated() {
        val strikeResponse = helper.laneThrow(10u)
        helper.laneThrow(5u)
        val secondResponse = helper.laneThrow(3u)

        firstFrameIsStrike(strikeResponse)
        frameHasScore(1, strikeResponse, 10)
        firstFrameIsStrike(secondResponse)
        frameHasScore(1, secondResponse, 18)
        frameHasScore(2, secondResponse, 8)
        totalScoreIs(secondResponse, 26)
    }
}
