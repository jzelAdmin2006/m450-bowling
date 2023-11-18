package com.jzel.m450bowling.server.business.domain.lane_throw

enum class ThrowDisplay(val displayString: String) {

    EIGHT("8"),

    FIVE("5"),

    FOUR("4"),

    MISS("-"),

    NINE("9"),

    ONE("1"),

    SEVEN("7"),

    SIX("6"),

    SPARE("/"),

    STRIKE("X"),

    TEN("10"),

    THREE("3"),

    TWO("2");

    companion object {
        fun fromPinsHit(pinsHit: UInt): ThrowDisplay {
            return entries.first { it.displayString == pinsHit.toString() }
        }
    }

}
