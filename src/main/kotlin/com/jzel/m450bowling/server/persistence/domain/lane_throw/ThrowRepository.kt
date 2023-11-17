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
    fun save(persistedFramesWithThrows: Map<FrameEntity, List<Throw>>): Map<FrameEntity, List<ThrowEntity>> {
        val persistedFramesWithMappedThrows = persistedFramesWithThrows.map { (frameEntity, throws) ->
            frameEntity to throws.map { mapper.toEntity(it, frameEntity) }
        }.toMap()
        val result: MutableMap<FrameEntity, MutableList<ThrowEntity>> = mutableMapOf()
        persistence.saveAll(persistedFramesWithMappedThrows.map { it.value }.flatten())
            .forEach { throwEntity ->
                result.getOrPut(persistedFramesWithMappedThrows.keys.find { it.id == throwEntity.frame.id }!!) { mutableListOf() }
                    .add(throwEntity)
            }
        return result
    }
}
