package com.jzel.m450bowling.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Assertions

class TestAssertionHelper

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

fun firstFrameIsStrike(gameAsJson: String) {
    Assertions.assertEquals(
        "X", ObjectMapper().registerKotlinModule().readTree(gameAsJson)
            .path("frames")[0].path("throws")[0].path("display").asText()
    )
}
