package com.jzel.m450bowling.persistence.game

import org.springframework.data.repository.CrudRepository

interface GamePersistence : CrudRepository<GameEntity, UInt>
