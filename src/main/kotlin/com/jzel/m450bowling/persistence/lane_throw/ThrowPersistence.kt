package com.jzel.m450bowling.persistence.lane_throw

import org.springframework.data.repository.CrudRepository

interface ThrowPersistence : CrudRepository<ThrowEntity, UInt>
