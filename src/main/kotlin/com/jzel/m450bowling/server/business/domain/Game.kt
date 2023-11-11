package com.jzel.m450bowling.server.business.domain

import java.util.*

data class Game(
    val id: UInt? = null,
    val createDate: Date,
    val frames: List<Frame>,
) {
    val score: UInt
        get() = Random().nextInt(300).toUInt() // TODO: Implement score calculation
}
