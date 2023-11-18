package com.jzel.m450bowling.server

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class TestHelper(private val mvc: MockMvc) {
    fun laneThrow(pins: UInt) = performThrow(pins.toInt(), MockMvcResultMatchers.status().isOk)

    fun invalidThrow(pins: Int) = performThrow(pins, MockMvcResultMatchers.status().isBadRequest)

    private fun performThrow(pins: Int, statusMatcher: ResultMatcher) =
        mvc.perform(MockMvcRequestBuilders.post("/throw/$pins").accept(MediaType.APPLICATION_JSON))
            .andExpect(statusMatcher)
            .andReturn().response.contentAsString

    fun activeGame(): String =
        mvc.perform(MockMvcRequestBuilders.get("/game/active").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString
}
