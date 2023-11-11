package com.jzel.m450bowling.server.persistence.service

import com.jzel.m450bowling.server.business.domain.Frame
import com.jzel.m450bowling.server.business.domain.Game
import com.jzel.m450bowling.server.business.domain.lane_throw.Throw
import com.jzel.m450bowling.server.persistence.domain.frame.FrameEntity
import com.jzel.m450bowling.server.persistence.domain.game.GameEntity
import com.jzel.m450bowling.server.persistence.domain.lane_throw.ThrowEntity
import org.springframework.stereotype.Service

@Service
class PersistenceMapperService {
    fun fromEntity(entity: GameEntity): Game {
        return Game(
            entity.id,
            entity.createDate,
            entity.frames.map { frameEntity ->
                fromEntity(frameEntity)
            }
        )
    }

    private fun PersistenceMapperService.fromEntity(entity: FrameEntity) =
        Frame(
            entity.frameNumber,
            entity.throws.map { throwEntity ->
                fromEntity(throwEntity)
            }
        )

    private fun fromEntity(entity: ThrowEntity) = Throw(
        entity.throwNumber,
        entity.pinsHit
    )

    fun toEntity(game: Game): GameEntity {
        return GameEntity(
            game.id ?: UInt.MIN_VALUE,
            game.createDate,
            game.frames.map { frame ->
                toEntity(frame)
            }
        )
    }

    private fun toEntity(frame: Frame) = FrameEntity(
        UInt.MIN_VALUE,
        frame.frameNumber,
        frame.throws.map { laneThrow ->
            ToEntity(laneThrow)
        }
    )

    private fun ToEntity(laneThrow: Throw) = ThrowEntity(
        UInt.MIN_VALUE,
        laneThrow.throwNumber,
        laneThrow.pinsHit
    )
}
