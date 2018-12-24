package com.wilderpereira.utils

import com.wilderpereira.domain.Coordinate

private const val ALPHA = 0.2f

/**
 * Algorithm to remove the noise from the accelerometer data.
 * https://en.wikipedia.org/wiki/Low-pass_filter
 */
fun lowPassFilter(input: Coordinate, output: Coordinate?): Coordinate {

    if (output == null) return input.copy(input.x, input.y, input.z)

    output.x = output.x + ALPHA * (input.x - output.x)
    output.y = output.y + ALPHA * (input.y - output.y)
    output.z = output.z + ALPHA * (input.z - output.z)

    println(output)
    return output
}