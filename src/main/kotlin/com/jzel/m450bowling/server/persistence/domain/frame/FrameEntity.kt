package com.jzel.m450bowling.server.persistence.domain.frame

import com.jzel.m450bowling.server.persistence.domain.game.GameEntity
import com.jzel.m450bowling.server.persistence.domain.lane_throw.ThrowEntity
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "frame")
data class FrameEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UInt,

    @NotNull
    @Column(unique = true)
    val frameNumber: UInt,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, mappedBy = "frame")
    val throws: List<ThrowEntity>
) {
    @ManyToOne
    @NotNull
    lateinit var game: GameEntity
}
