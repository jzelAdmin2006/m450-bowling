package com.jzel.m450bowling.server.business.domain.lane_throw

enum class ThrowDisplay(val displayString: String) {

    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    MISS("-"),
    SPARE("/"),
    STRIKE("X");

    companion object {
        fun fromPinsHit(pinsHit: UInt): ThrowDisplay {
            return entries.first { it.displayString == pinsHit.toString() }
        }
    }

}
