package com.jzel.m450bowling.server.business.domain

import com.jzel.m450bowling.server.business.domain.lane_throw.CustomDisplayedThrow
import com.jzel.m450bowling.server.business.domain.lane_throw.Throw
import com.jzel.m450bowling.server.business.domain.lane_throw.ThrowDisplay
import com.jzel.m450bowling.server.business.domain.lane_throw.ThrowDisplay.*
import java.util.*

open class Frame(
    val frameNumber: UInt,
    private val _throws: List<Throw>,
    private val followingFrame: Frame? = null
) {
    val throws: List<Throw>
        get() = _throws.map {
            CustomDisplayedThrow(
                customThrowDisplay(it),
                it.throwNumber,
                it.pinsHit
            )
        }

    private fun customThrowDisplay(it: Throw): ThrowDisplay {
        return if (it.pinsHit == 0u) {
            MISS
        } else if (isStrike(it)) {
            STRIKE
        } else if (it.throwNumber == 2u && _throws[0].pinsHit + it.pinsHit == 10u) {
            SPARE
        } else {
            ThrowDisplay.fromPinsHit(it.pinsHit)
        }
    }

    private fun isStrike(laneThrow: Throw): Boolean {
        return laneThrow.pinsHit == 10u && (laneThrow.throwNumber == 1u || frameNumber == 10u && isStrike(_throws[0]))
    }

    val score: UInt
        get() = Random().nextInt(300).toUInt() // TODO: Implement score calculation
}
