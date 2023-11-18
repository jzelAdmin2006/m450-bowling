package com.jzel.m450bowling.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.jzel.m450bowling.server.persistence.domain.game.GamePersistence
import com.jzel.m450bowling.server.webservice.adapter.rest.GameController
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
    classes = [
        M450BowlingApplication::class,
        GameController::class,
        GameThrowController::class,
        GamePersistence::class
    ]
)
@Transactional
class AutomatedReadMeCasesTest {
    private lateinit var mvc: MockMvc

    private lateinit var helper: TestHelper

    @Autowired
    private lateinit var gameController: GameController

    @Autowired
    private lateinit var throwController: GameThrowController

    @Autowired
    private lateinit var persistence: GamePersistence

    @BeforeEach
    fun init() {
        persistence.deleteAll()
        JacksonTester.initFields(this, ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(gameController, throwController).build()
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
    fun emptyGame_playPerfectGame_scoreIs300() {
        for (n in 1..11) {
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
    fun emptyGame_invalidThrowTooManyPins_isIgnored() {
        val response = helper.invalidThrow(11)

        totalScoreIs(response, 0)
    }

    /**
     * test case #4 from ReadMe.md
     */
    @Test
    fun emptyGame_invalidThrowNegativePins_isIgnored() {
        val response = helper.invalidThrow(-1)

        totalScoreIs(response, 0)
    }

    /**
     * test case #5 from ReadMe.md
     */
    @Test
    fun emptyGame_invalidThrowTooManyPinsInFrame_isIgnored() {
        helper.laneThrow(5u)
        helper.invalidThrow(6)
        val response = helper.activeGame()

        totalScoreIs(response, 5)
    }

    /**
     * test case #6 from ReadMe.md
     */
    @Test
    fun emptyGame_doubleGutterBall_scoreIsZero() {
        helper.laneThrow(0u)
        val response = helper.laneThrow(0u)

        totalScoreIs(response, 0)
        throwIsMiss(1, 1, response)
        throwIsMiss(2, 1, response)
    }

    /**
     * test case #7 from ReadMe.md
     */
    @Test
    fun emptyGame_fullGameWithAlways4Pins_totalScoreIs80() {
        for (n in 1..19) {
            helper.laneThrow(4u)
        }
        val response = helper.laneThrow(4u)

        totalScoreIs(response, 80)
        for (n in 1..10) {
            frameHasScore(n, response, 8)
        }
    }

    /**
     * test case #8 from ReadMe.md
     */
    @Test
    fun emptyGame_fullGameWithAlways4Pins5PinsTwiceEach_totalScoreIs110() {
        for (n in 1..5) {
            helper.laneThrow(5u)
            helper.laneThrow(5u)
            helper.laneThrow(4u)
            helper.laneThrow(4u)
        }
        val response = helper.activeGame()

        totalScoreIs(response, 110)
        for (n in 1..5) {
            frameHasScore(n * 2 - 1, response, 14)
            frameHasScore(n * 2, response, 8)
        }
    }

    /**
     * test case #9 from ReadMe.md
     */
    @Test
    fun emptyGame_fullGameWithAlwaysStrikesAndGutterFrames_totalScoreIs50() {
        for (n in 1..5) {
            helper.laneThrow(10u)
            helper.laneThrow(0u)
            helper.laneThrow(0u)
        }
        val response = helper.activeGame()

        totalScoreIs(response, 50)
        for (n in 1..5) {
            frameHasScore(n * 2 - 1, response, 10)
            isStrikeFrame(n * 2 - 1, response)
            frameHasScore(n * 2, response, 0)
            throwIsMiss(1, n * 2, response)
            throwIsMiss(2, n * 2, response)
        }
    }

    /**
     * test case #10 from ReadMe.md
     */
    @Test
    fun emptyGame_fullGameWithStrikeInLastFrame_lastFrameHasOneBonusThrow() {
        for (n in 1..18) {
            helper.laneThrow(1u)
        }
        helper.laneThrow(10u)
        helper.laneThrow(5u)
        val response = helper.laneThrow(3u)

        totalScoreIs(response, 36)
        throwIsStrike(1, 10, response)
        frameHasScore(10, response, 18)
    }
}
