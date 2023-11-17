package com.jzel.m450bowling.server.persistence.domain.frame

import com.jzel.m450bowling.server.business.domain.Frame
import com.jzel.m450bowling.server.persistence.domain.game.GameEntity
import com.jzel.m450bowling.server.persistence.domain.lane_throw.ThrowRepository
import com.jzel.m450bowling.server.persistence.service.PersistenceMapperService
import org.springframework.stereotype.Service

@Service
class FrameRepository(
    val persistence: FramePersistence,
    val mapper: PersistenceMapperService,
    val throwRepository: ThrowRepository
) {
    fun save(frames: List<Frame>, persistedGame: GameEntity) {
        val frameEntities = frames.map { mapper.toEntity(it, persistedGame) }
        persistence.saveAll(frameEntities)
        throwRepository.save(frameEntities.mapIndexed { index, frameEntity ->
            Pair(frameEntity, frames[index].throws)
        })
    }
}
