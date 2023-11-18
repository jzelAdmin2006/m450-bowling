package com.jzel.m450bowling.server

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class TestHelper(private val mvc: MockMvc) {

    fun laneThrow(pins: UInt) = performThrow(pins.toString(), MockMvcResultMatchers.status().isOk)
    fun invalidThrow(pins: Int) = performThrow(pins.toString(), MockMvcResultMatchers.status().isBadRequest)
    fun invalidThrow(pins: String) = performThrow(pins, MockMvcResultMatchers.status().isBadRequest)

    private fun performThrow(pins: String, statusMatcher: ResultMatcher) =
        perform(MockMvcRequestBuilders.post("/throw/$pins"), statusMatcher)

    fun activeGame() = get("/game/active")
    fun getGames() = get("/game")

    fun endGame() = endGameWithStatus(MockMvcResultMatchers.status().isOk)
    fun endGameTooEarly() = endGameWithStatus(MockMvcResultMatchers.status().isBadRequest)

    private fun endGameWithStatus(statusMatcher: ResultMatcher) =
        perform(MockMvcRequestBuilders.put("/game"), statusMatcher)

    fun reset() = perform(MockMvcRequestBuilders.delete("/game"), MockMvcResultMatchers.status().isOk)

    private fun get(endpoint: String) =
        perform(MockMvcRequestBuilders.get(endpoint), MockMvcResultMatchers.status().isOk)

    private fun perform(requestBuilder: MockHttpServletRequestBuilder, statusMatcher: ResultMatcher) =
        mvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON))
            .andExpect(statusMatcher)
            .andReturn().response.contentAsString
}
