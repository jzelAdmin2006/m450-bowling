package com.jzel.m450bowling.server.business.service

import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.persistence.domain.game.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(val repository: GameRepository) {
    fun getPersistedGames(): List<Game> {
        return repository.findAll().sortedByDescending { it.createDate }.dropLast(1)
    }
}
