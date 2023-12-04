package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle
import kotlin.math.abs

object Solution3A {
    data class Coordinate(val x: Int, val y: Int) {
        fun isNextTo(coordinate: Coordinate) =
            (abs(this.x - coordinate.x) <= 1 && abs(this.y - coordinate.y) <= 1)

    }

    data class NumberLocation(val number: Int, val location: Collection<Coordinate>)

}

fun main() {
    val inputData = inputForPuzzle(3)

    val numberLocations = inputData.withIndex().map {
        val yCoord = it.index
        Regex("""\d+""").findAll(it.value).map { match ->
            Solution3A.NumberLocation(match.value.toInt(), (match.range.start..match.range.endInclusive).map { xCoord ->
                Solution3A.Coordinate(xCoord, yCoord)
            })
        }.toList()
    }.flatten()

    val symbolLocations = inputData.withIndex().map {
        val yCoord = it.index
        it.value.withIndex().filter { char ->
            char.value.isDigit().not() && char.value != '.'
        }.map { Solution3A.Coordinate(it.index, yCoord) }.toList()
    }.flatten()

    val sumOfNumbers = numberLocations.filter {
        it.location.any { number -> symbolLocations.any { symbol -> number.isNextTo(symbol) } }
    }.sumOf { it.number }
    println(sumOfNumbers)

}
