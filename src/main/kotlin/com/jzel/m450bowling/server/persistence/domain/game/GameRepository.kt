package com.jzel.m450bowling.server.persistence.domain.game

import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.persistence.domain.frame.FrameRepository
import com.jzel.m450bowling.server.persistence.service.PersistenceMapperService
import org.springframework.stereotype.Service

@Service
class GameRepository(
    val persistence: GamePersistence,
    val mapper: PersistenceMapperService,
    val frameRepository: FrameRepository
) {
    fun findAll(): List<Game> {
        return persistence.findAll().map { mapper.fromEntity(it) }
    }

    fun save(game: Game): Game {
        var gameEntity = mapper.toEntity(game)
        gameEntity = persistence.save(gameEntity)
        gameEntity.frames = frameRepository.save(game.frames, gameEntity)
        return mapper.fromEntity(gameEntity)
    }
}
