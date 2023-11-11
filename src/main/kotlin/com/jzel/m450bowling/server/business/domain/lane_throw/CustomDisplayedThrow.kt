package com.jzel.m450bowling.server.business.domain.lane_throw

class CustomDisplayedThrow(override var throwDisplay: ThrowDisplay, throwNumber: UInt, pinsHit: UInt) :
    Throw(throwNumber, pinsHit)
