package com.jzel.m450bowling.server.persistence.domain.lane_throw

import com.jzel.m450bowling.server.business.domain.lane_throw.Throw
import com.jzel.m450bowling.server.persistence.domain.frame.FrameEntity
import com.jzel.m450bowling.server.persistence.service.PersistenceMapperService
import org.springframework.stereotype.Service

@Service
class ThrowRepository(
    val persistence: ThrowPersistence,
    val mapper: PersistenceMapperService,
) {
    fun save(throwsWithPersistedFrames: List<Pair<FrameEntity, List<Throw>>>) {
        persistence.saveAll(throwsWithPersistedFrames.map { (frameEntity, throws) ->
            throws.map { mapper.toEntity(it, frameEntity) }
        }.flatten())
    }
}
