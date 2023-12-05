package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle
import com.eskelinenantti.resources.testInputForPuzzle

fun main() {
    val rows = inputForPuzzle(5)

    val maps = mutableListOf<MutableList<Triple<Long, Long, Long>>>()
    rows.filter { it.isNotBlank() }.drop(1).forEach { row ->
        if (row.first().isDigit()) {
            val (target, source, range) = row.split(" ").map(String::toLong)
            maps.last().add(Triple(target, source, range))
        } else {
            maps.add(mutableListOf())
        }
    }

    val seedRanges = rows.first().removePrefix("seeds: ").split(" ").map(String::toLong).chunked(2)
    val total = seedRanges.fold(0.toLong()) { acc, range -> acc + range.last() }
    var i = 0.toLong()
    val result = seedRanges.minOf { (start, range) ->
        (start..<start + range).minOf { seed ->
            if (i++.mod(100000) == 0) {
                print("processed ${String.format("%.2f", (i.toFloat().div(total) * 100))} %\r")
            }
            maps.fold(seed) { acc, map ->
                map.firstOrNull {
                    it.second <= acc && acc < it.second + it.third
                }?.run {
                    first + (acc - second)
                } ?: acc
            }
        }
    }
    println(result)
}