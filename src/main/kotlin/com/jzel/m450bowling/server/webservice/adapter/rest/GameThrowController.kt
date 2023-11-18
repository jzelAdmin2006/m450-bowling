package com.jzel.m450bowling.server.webservice.adapter.rest

import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.business.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/throw")
class GameThrowController(val service: GameService) {
    @PostMapping("/{pinsHit}")
    fun laneThrow(@PathVariable pinsHit: UInt): ResponseEntity<Game> =
        if (service.prevalidateThrow(pinsHit)) {
            ResponseEntity.ok(service.laneThrow(pinsHit))
        } else {
            ResponseEntity.badRequest().build()
        }

}
