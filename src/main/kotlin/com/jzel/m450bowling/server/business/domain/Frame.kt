package com.jzel.m450bowling.server.business.domain

import com.jzel.m450bowling.server.business.domain.lane_throw.CustomDisplayedThrow
import com.jzel.m450bowling.server.business.domain.lane_throw.Throw
import com.jzel.m450bowling.server.business.domain.lane_throw.ThrowDisplay
import com.jzel.m450bowling.server.business.domain.lane_throw.ThrowDisplay.*

open class Frame(
    val frameNumber: UInt,
    private val _id: UInt? = null,
    private val _throws: MutableList<Throw>,
    private val followingFrame: Frame? = null,
) {

    val score: UInt
        get() = _throws.sumOf { it.pinsHit } + bonusScore()

    val throws: List<Throw>
        get() = _throws.map {
            CustomDisplayedThrow(
                it.idValue(),
                it.throwNumber,
                it.pinsHit,
                customThrowDisplay(it)
            )
        }

    fun addThrow(laneThrow: Throw) {
        _throws.add(laneThrow)
    }

    fun idValue() = _id ?: UInt.MIN_VALUE

    private fun bonusScore(): UInt {
        return when {
            frameNumber == 10u -> 0u
            _throws.isEmpty() -> 0u
            isStrike(_throws[0]) -> {
                followingFrame?.let { following ->
                    val followingPinsHit = following._throws[0].pinsHit
                    if (isStrike(following._throws[0]) && following.frameNumber < 10u) {
                        followingPinsHit + (following.followingFrame?._throws?.get(0)?.pinsHit ?: 0u)
                    } else {
                        followingPinsHit + (following._throws.getOrNull(1)?.pinsHit ?: 0u)
                    }
                } ?: 0u
            }

            isSpare(_throws.getOrNull(1)) -> followingFrame?.let { it._throws[0].pinsHit } ?: 0u
            else -> 0u
        }
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

    private fun isSpare(laneThrow: Throw?) =
        laneThrow?.throwNumber == 2u && _throws[0].pinsHit + laneThrow.pinsHit == 10u

    private fun isStrike(laneThrow: Throw?): Boolean =
        laneThrow?.pinsHit == 10u && (laneThrow.throwNumber == 1u || laneThrow.throwNumber == 3u || frameNumber == 10u && isStrike(
            _throws[0]
        ))

}
