package com.jzel.m450bowling.server.webservice.adapter.rest

import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.business.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game")
class GameController(val service: GameService) {
    @GetMapping
    fun getGames(): ResponseEntity<List<Game>> {
        return ResponseEntity.ok(service.getPersistedGames())
    }

    @GetMapping("/active")
    fun getActiveGame(): ResponseEntity<Game> {
        return ResponseEntity.ok(service.getActive())
    }

    @DeleteMapping
    fun resetActiveGame(): ResponseEntity<Game> {
        return ResponseEntity.ok(service.resetActive())
    }

    @PutMapping
    fun finishActiveGame(): ResponseEntity<Game> {
        val activeGame = service.getActive()
        if (activeGame == null) {
            return ResponseEntity.notFound().build()
        } else if (service.isCompleted(activeGame)) {
            service.init()
            return ResponseEntity.ok(activeGame)
        } else {
            return ResponseEntity.badRequest().build()
        }
    }
}
