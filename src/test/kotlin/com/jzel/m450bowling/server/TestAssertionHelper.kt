package com.jzel.m450bowling.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Assertions

fun hasPersistedGamesCount(n: Int, gamesAsJson: String) {
    Assertions.assertEquals(
        n, ObjectMapper().registerKotlinModule().readTree(gamesAsJson).size()
    )
}

fun nthGame(n: Int, gamesAsJson: String) =
    ObjectMapper().registerKotlinModule().readTree(gamesAsJson)[n - 1].toString()

fun gameHasFrames(gameAsJson: String, expectedFrames: Int) {
    Assertions.assertEquals(
        expectedFrames, ObjectMapper().registerKotlinModule().readTree(gameAsJson)
            .path("frames").size()
    )
}

fun totalScoreIs(gameAsJson: String, expectedScore: Int) {
    Assertions.assertEquals(
        expectedScore, ObjectMapper().registerKotlinModule().readTree(gameAsJson)
            .path("score").asInt()
    )
}

fun frameHasScore(frameNumber: Int, gameAsJson: String, expectedScore: Int) {
    Assertions.assertEquals(
        expectedScore, ObjectMapper().registerKotlinModule().readTree(gameAsJson)
            .path("frames")[frameNumber - 1].path("score").asInt()
    )
}

fun isStrikeFrame(frameNumber: Int, gameAsJson: String) {
    throwIsStrike(1, frameNumber, gameAsJson)
}

fun throwIsStrike(throwNumber: Int, frameNumber: Int, gameAsJson: String) {
    throwIsCustomDisplay(gameAsJson, frameNumber, throwNumber, "X")
}

fun throwIsSpare(throwNumber: Int, frameNumber: Int, gameAsJson: String) {
    throwIsCustomDisplay(gameAsJson, frameNumber, throwNumber, "/")
}

fun throwIsMiss(throwNumber: Int, frameNumber: Int, gameAsJson: String) {
    throwIsCustomDisplay(gameAsJson, frameNumber, throwNumber, "-")
}

private fun throwIsCustomDisplay(gameAsJson: String, frameNumber: Int, throwNumber: Int, display: String) {
    Assertions.assertEquals(
        display, ObjectMapper().registerKotlinModule().readTree(gameAsJson)
            .path("frames")[frameNumber - 1].path("throws")[throwNumber - 1]
            .path("display").asText()
    )
}
