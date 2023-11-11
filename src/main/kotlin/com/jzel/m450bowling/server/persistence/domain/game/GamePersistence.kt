package com.jzel.m450bowling.server.persistence.domain.game

import org.springframework.data.repository.CrudRepository

interface GamePersistence : CrudRepository<GameEntity, UInt>
