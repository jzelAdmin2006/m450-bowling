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
    fun fromEntity(entity: GameEntity) = Game(
        entity.id,
        entity.createDate,
        entity.frames.mapIndexed { index, frameEntity ->
            val followingFrameEntity = entity.frames.getOrNull(index + 1)
            val followingfollowingFrameEntity = entity.frames.getOrNull(index + 2)
            fromEntity(frameEntity, followingFrameEntity, followingfollowingFrameEntity)
        }.toMutableList()
    )

    private fun fromEntity(
        entity: FrameEntity,
        followingFrameEntity: FrameEntity?,
        followingfollowingFrameEntity: FrameEntity?
    ): Frame {
        return Frame(
            entity.frameNumber,
            entity.throws.map { fromEntity(it) }.toMutableList(),
            followingFrameEntity?.let { fromEntity(it, followingfollowingFrameEntity, null) },
            entity.id
        )
    }

    private fun fromEntity(entity: ThrowEntity) = Throw(
        entity.throwNumber,
        entity.pinsHit,
        entity.id
    )

    fun toEntity(game: Game): GameEntity {
        return GameEntity(
            game.id ?: UInt.MIN_VALUE,
            game.createDate,
            listOf()
        )
    }

    fun toEntity(frame: Frame, game: GameEntity): FrameEntity {
        val frameEntity = FrameEntity(
            frame.idValue(),
            frame.frameNumber,
            listOf(),
        )
        frameEntity.game = game
        return frameEntity
    }

    fun toEntity(laneThrow: Throw, frame: FrameEntity): ThrowEntity {
        val throwEntity = ThrowEntity(
            laneThrow.idValue(),
            laneThrow.throwNumber,
            laneThrow.pinsHit
        )
        throwEntity.frame = frame
        return throwEntity
    }
}
