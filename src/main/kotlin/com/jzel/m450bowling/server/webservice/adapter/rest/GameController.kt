package com.jzel.m450bowling.server.webservice.adapter.rest

import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.business.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game")
class GameController(val service: GameService) {

    @PutMapping
    fun finishActiveGame(): ResponseEntity<Game> =
        service.getActive()?.let { activeGame ->
            if (service.isCompleted(activeGame)) {
                service.init()
                ResponseEntity.ok(activeGame)
            } else {
                ResponseEntity.badRequest().build()
            }
        } ?: ResponseEntity.notFound().build()

    @GetMapping("/active")
    fun getActiveGame(): ResponseEntity<Game> =
        service.getActive()?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.noContent().build()

    @GetMapping
    fun getGames(): ResponseEntity<List<Game>> {
        return ResponseEntity.ok(service.getPersistedGames())
    }

    @DeleteMapping
    fun resetActiveGame(): ResponseEntity<Game> =
        service.getActive()?.let { ResponseEntity.ok(service.reset(it)) }
            ?: ResponseEntity.notFound().build()

}
