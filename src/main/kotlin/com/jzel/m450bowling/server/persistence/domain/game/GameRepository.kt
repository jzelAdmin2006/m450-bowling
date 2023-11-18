package com.jzel.m450bowling.server.persistence.domain.game

import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.persistence.domain.frame.FrameRepository
import com.jzel.m450bowling.server.persistence.service.PersistenceMapperService
import org.springframework.stereotype.Service

@Service
class GameRepository(
    val frameRepository: FrameRepository,
    val mapper: PersistenceMapperService,
    val persistence: GamePersistence,
) {

    fun delete(game: Game) = persistence.delete(mapper.toEntity(game))

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
