package com.jzel.m450bowling.server.persistence.domain.frame

import org.springframework.data.repository.CrudRepository

interface FramePersistence : CrudRepository<FrameEntity, UInt>
