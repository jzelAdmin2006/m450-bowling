package com.jzel.m450bowling.server

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class TestHelper(private val mvc: MockMvc) {
    fun laneThrow(pins: UInt) = performThrow(pins, MockMvcResultMatchers.status().isOk)

    fun invalidThrow(pins: UInt) = performThrow(pins, MockMvcResultMatchers.status().isBadRequest)

    private fun performThrow(pins: UInt, statusMatcher: ResultMatcher) =
        mvc.perform(MockMvcRequestBuilders.post("/throw/$pins").accept(MediaType.APPLICATION_JSON))
            .andExpect(statusMatcher)
            .andReturn().response.contentAsString
}
