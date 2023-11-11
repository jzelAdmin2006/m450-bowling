package com.jzel.m450bowling.server.persistence.domain.game

import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.persistence.service.PersistenceMapperService
import org.springframework.stereotype.Service

@Service
class GameRepository(val persistence: GamePersistence, val mapper: PersistenceMapperService) {
    fun findAll(): List<Game> {
        return persistence.findAll().map { mapper.fromEntity(it) }
    }
}
