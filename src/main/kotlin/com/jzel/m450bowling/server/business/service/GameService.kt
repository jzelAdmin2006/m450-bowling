package com.jzel.m450bowling.server.business.service

import com.jzel.m450bowling.server.business.domain.Frame
import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.business.domain.lane_throw.Throw
import com.jzel.m450bowling.server.persistence.domain.game.GameRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameService(val repository: GameRepository) {
    fun getPersistedGames(): List<Game> {
        return repository.findAll().sortedBy { it.createDate }.dropLast(1)
    }

    fun getActiveGame(): Game? {
        return repository.findAll().maxByOrNull { it.createDate }
    }

    fun laneThrow(pinsHit: UInt): Game? {
        var activeGame = getActiveGame()
        if (activeGame == null) {
            var newGame = Game(
                createDate = Date(),
                frames = mutableListOf()
            )
            newGame = repository.save(newGame)
            activeGame = newGame
        }
        laneThrow(activeGame, pinsHit)
        activeGame = repository.save(activeGame)
        return activeGame
    }

    fun laneThrow(game: Game, pinsHit: UInt): Game {
        var lastFrame = game.frames.lastOrNull()
        if (lastFrame == null || isCompleted(lastFrame)) {
            lastFrame = Frame(
                frameNumber = (lastFrame?.frameNumber ?: 0u) + 1u,
                _throws = mutableListOf()
            )
            game.frames.add(lastFrame)
        }
        lastFrame.addThrow(
            Throw(
                throwNumber = (lastFrame.throws.lastOrNull()?.throwNumber ?: 0u) + 1u,
                pinsHit = pinsHit
            )
        )
        return game
    }

    private fun isCompleted(frame: Frame): Boolean {
        return frame.throws.isNotEmpty() && (frame.throws.size == 2 || frame.throws[0].pinsHit == 10u) &&
                (frame.frameNumber != 10u || frame.throws.size == 2 &&
                        frame.throws[0].pinsHit + frame.throws[1].pinsHit < 10u || frame.throws.size == 3)
    }
}
