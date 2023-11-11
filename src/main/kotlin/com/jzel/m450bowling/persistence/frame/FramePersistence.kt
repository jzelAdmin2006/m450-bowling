package com.jzel.m450bowling.persistence.frame

import org.springframework.data.repository.CrudRepository

interface FramePersistence : CrudRepository<FrameEntity, UInt>
