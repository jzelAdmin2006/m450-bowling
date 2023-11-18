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

        isStrikeFrame(1, strikeResponse)
        frameHasScore(1, strikeResponse, 10)
        isStrikeFrame(1, secondResponse)
        frameHasScore(1, secondResponse, 18)
        frameHasScore(2, secondResponse, 8)
        totalScoreIs(secondResponse, 26)
    }


    /**
     * test case #2 from ReadMe.md
     */
    @Test
    fun emptyGame_playPerfectGame_scoreIsCorrect() {
        for (i in 1..11) {
            helper.laneThrow(10u)
        }
        val response = helper.laneThrow(10u)

        totalScoreIs(response, 300)
        for (n in 1..10) {
            isStrikeFrame(n, response)
        }
        throwIsStrike(2, 10, response)
        throwIsStrike(3, 10, response)
    }

    /**
     * test case #3 from ReadMe.md
     */
    @Test
    fun emptyGame_invalidThrow_isIgnored() {
        val response = helper.invalidThrow(11u)

        totalScoreIs(response, 0)
    }
}
