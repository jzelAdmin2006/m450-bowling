package com.jzel.m450bowling.server.business.domain.lane_throw

open class Throw(
    val pinsHit: UInt,
    val throwNumber: UInt,
    private val _id: UInt? = null,
) {

    protected open var throwDisplay: ThrowDisplay = ThrowDisplay.fromPinsHit(pinsHit)

    val display: String
        get() = throwDisplay.displayString

    fun idValue() = _id ?: UInt.MIN_VALUE

}
