package com.jzel.m450bowling.server.persistence.domain.lane_throw

import com.jzel.m450bowling.server.persistence.domain.frame.FrameEntity
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "throw")
data class ThrowEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UInt,

    @NotNull
    @Column(unique = true)
    val throwNumber: UInt,

    @NotNull
    val pinsHit: UInt
) {
    @ManyToOne
    @NotNull
    lateinit var frame: FrameEntity
}
