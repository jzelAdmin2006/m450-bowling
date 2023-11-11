package com.jzel.m450bowling.server.persistence.domain.lane_throw

import org.springframework.data.repository.CrudRepository

interface ThrowPersistence : CrudRepository<ThrowEntity, UInt>
