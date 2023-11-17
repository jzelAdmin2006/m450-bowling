package com.jzel.m450bowling.server.webservice.adapter.rest

import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.business.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/game")
class GameController(val service: GameService) {
    @GetMapping
    fun getGames(): ResponseEntity<List<Game>> {
        return ResponseEntity.ok(service.getPersistedGames())
    }

    @GetMapping("/active")
    fun getActiveGame(): ResponseEntity<Game> {
        return ResponseEntity.ok(service.getActiveGame())
    }
    // TODO: Implement endpoints POST /game for finishing games (validate if game is finished), DELETE /game for resetting active game
}
