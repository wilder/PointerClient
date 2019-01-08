package com.wilderpereira.domain

data class Coordinate(
    var x: Float = 0f,
    var y: Float = 0f,
    var z: Float = 0f,
    var mode: String = "",
    var state: Int = 0
)