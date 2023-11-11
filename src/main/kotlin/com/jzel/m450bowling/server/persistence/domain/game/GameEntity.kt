package com.jzel.m450bowling.server.persistence.domain.game

import com.jzel.m450bowling.server.persistence.domain.frame.FrameEntity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
@Table(name = "game")
data class GameEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private val id: UInt,

    @NotNull
    val createDate: Date,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, mappedBy = "game")
    val frames: List<FrameEntity>
)
