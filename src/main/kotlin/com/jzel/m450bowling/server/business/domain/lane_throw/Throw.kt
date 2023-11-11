package com.jzel.m450bowling.server.business.domain.lane_throw

open class Throw(
    val throwNumber: UInt,
    val pinsHit: UInt
) {
    protected open var throwDisplay: ThrowDisplay = ThrowDisplay.fromPinsHit(pinsHit)
    val display: String
        get() = throwDisplay.displayString
}
