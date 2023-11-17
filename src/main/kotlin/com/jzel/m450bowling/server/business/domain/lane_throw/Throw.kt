package com.jzel.m450bowling.server.business.domain.lane_throw

open class Throw(
    val throwNumber: UInt,
    val pinsHit: UInt,
    private val _id: UInt? = null
) {
    fun idValue() = _id ?: UInt.MIN_VALUE

    protected open var throwDisplay: ThrowDisplay = ThrowDisplay.fromPinsHit(pinsHit)
    val display: String
        get() = throwDisplay.displayString
}
