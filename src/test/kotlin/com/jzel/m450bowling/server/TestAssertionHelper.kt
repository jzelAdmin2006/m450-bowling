package com.jzel.m450bowling.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Assertions

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
    Assertions.assertEquals(
        "X", ObjectMapper().registerKotlinModule().readTree(gameAsJson)
            .path("frames")[frameNumber - 1].path("throws")[throwNumber - 1]
            .path("display").asText()
    )
}
