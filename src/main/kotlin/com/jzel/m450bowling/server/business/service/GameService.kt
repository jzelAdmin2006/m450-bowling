package com.jzel.m450bowling.server.business.service

import com.jzel.m450bowling.server.business.domain.Frame
import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.business.domain.lane_throw.Throw
import com.jzel.m450bowling.server.persistence.domain.game.GameRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameService(val repository: GameRepository) {

    fun getActive(): Game? {
        return repository.findAll().maxByOrNull { it.createDate }
    }

    fun getPersistedGames(): List<Game> {
        return repository.findAll().sortedBy { it.createDate }.dropLast(1)
    }

    fun init(): Game {
        return repository.save(
            Game(
                createDate = Date(),
                frames = mutableListOf()
            )
        )
    }

    fun isCompleted(game: Game): Boolean {
        return game.frames.size == 10 && isCompleted(game.frames.last())
    }

    fun laneThrow(pinsHit: UInt): Game? {
        var activeGame = getActiveOrInit()
        laneThrow(activeGame, pinsHit)
        activeGame = repository.save(activeGame)
        return activeGame
    }

    fun laneThrow(game: Game, pinsHit: UInt): Game {
        var lastFrame = game.frames.lastOrNull()
        if (newFrameIsRequired(lastFrame)) {
            lastFrame = Frame(
                frameNumber = (lastFrame?.frameNumber ?: 0u) + 1u,
                _throws = mutableListOf()
            )
            game.frames.add(lastFrame)
        }
        lastFrame?.addThrow(
            Throw(
                throwNumber = (lastFrame.throws.lastOrNull()?.throwNumber ?: 0u) + 1u,
                pinsHit = pinsHit
            )
        )
        return game
    }

    fun prevalidateThrow(pinsHit: UInt): Boolean {
        val activeGame = getActive()
        val lastFrame = activeGame?.frames?.lastOrNull()
        val gameIsNotCompleted = activeGame == null || !isCompleted(activeGame)
        val framePinsHitIsValid =
            newFrameIsRequired(lastFrame) || lastFrame?.frameNumber == 10u && pinsSum(lastFrame) >= 10u ||
                    lastFrame?.let { frame ->
                        pinsSum(frame) + pinsHit <= 10u
                    } ?: true
        val pinsHitIsValid = pinsHit <= 10u
        return gameIsNotCompleted && framePinsHitIsValid && pinsHitIsValid
    }

    private fun pinsSum(frame: Frame) = frame.throws.sumOf { it.pinsHit }

    fun reset(game: Game): Game? {
        repository.delete(game)
        return init()
    }

    private fun getActiveOrInit(): Game = getActive() ?: init()

    private fun isCompleted(frame: Frame): Boolean {
        return frame.throws.isNotEmpty() && (frame.throws.size == 2 || frame.throws[0].pinsHit == 10u) &&
                (frame.frameNumber != 10u || frame.throws.size == 2 &&
                        frame.throws[0].pinsHit + frame.throws[1].pinsHit < 10u || frame.throws.size == 3)
    }

    private fun newFrameIsRequired(lastFrame: Frame?) =
        lastFrame == null || isCompleted(lastFrame)

}
