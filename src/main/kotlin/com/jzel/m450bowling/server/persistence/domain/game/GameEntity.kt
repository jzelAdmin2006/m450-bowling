package com.jzel.m450bowling.server.persistence.domain.game

import com.jzel.m450bowling.server.persistence.domain.frame.FrameEntity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
@Table(name = "game")
data class GameEntity(
    @NotNull
    val createDate: Date,
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: UInt,
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    var frames: List<FrameEntity>,
)
