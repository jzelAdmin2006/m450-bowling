package com.jzel.m450bowling.persistence.lane_throw

import com.jzel.m450bowling.persistence.frame.FrameEntity
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "throw")
data class ThrowEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: UInt,

    @ManyToOne
    @NotNull
    private val frame: FrameEntity,

    @NotNull
    @Column(unique = true)
    val throwNumber: UInt,

    @NotNull
    val pinsHit: UInt
)
