package com.jzel.m450bowling.server.business.domain

import com.jzel.m450bowling.server.business.domain.lane_throw.CustomDisplayedThrow
import com.jzel.m450bowling.server.business.domain.lane_throw.Throw
import com.jzel.m450bowling.server.business.domain.lane_throw.ThrowDisplay
import com.jzel.m450bowling.server.business.domain.lane_throw.ThrowDisplay.*

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
        } else if (isSpare(it)) {
            SPARE
        } else {
            ThrowDisplay.fromPinsHit(it.pinsHit)
        }
    }

    private fun isStrike(laneThrow: Throw?): Boolean =
        laneThrow?.pinsHit == 10u && (laneThrow.throwNumber == 1u || laneThrow.throwNumber == 3u || frameNumber == 10u && isStrike(
            _throws[0]
        ))

    private fun isSpare(laneThrow: Throw?) =
        laneThrow?.throwNumber == 2u && _throws[0].pinsHit + laneThrow.pinsHit == 10u

    val score: UInt
        get() = _throws.sumOf { it.pinsHit } + bonusScore()

    private fun bonusScore(): UInt {
        return if (frameNumber == 10u) {
            0u
        } else if (isStrike(_throws[0])) {
            followingFrame?.let { it._throws[0].pinsHit + (it._throws.getOrNull(1)?.pinsHit ?: 0u) }
                ?: 0u
        } else if (isSpare(_throws[0])) {
            followingFrame?.let { it._throws[0].pinsHit } ?: 0u
        } else {
            0u
        }
    }
}
