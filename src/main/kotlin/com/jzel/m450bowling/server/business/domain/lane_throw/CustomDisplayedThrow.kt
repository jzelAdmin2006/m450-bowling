package com.jzel.m450bowling.server.business.domain.lane_throw

class CustomDisplayedThrow(
    id: UInt? = null,
    pinsHit: UInt,
    throwNumber: UInt,
    override var throwDisplay: ThrowDisplay,
) :
    Throw(throwNumber, pinsHit, id)
