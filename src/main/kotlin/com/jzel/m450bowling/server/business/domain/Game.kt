package com.jzel.m450bowling.server.business.domain

import java.util.*

data class Game(
    val createDate: Date,
    val frames: MutableList<Frame>,
    val id: UInt? = null,
) {
    val score: UInt
        get() = frames.sumOf { it.score }

}
