package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle


fun main() {
    val rows = inputForPuzzle(5)
    val seeds = rows.first().removePrefix("seeds: ").split(" ").map(String::toLong)

    val maps = mutableListOf<MutableMap<Long, Long>>()
    rows.filter { it.isNotBlank() }.drop(1).forEach { row ->
        if (row.first().isDigit()) {
            val map = row.split(" ").map(String::toLong)
            val (target, source, range) = map
            repeat(range.toInt()) { index ->
                maps.last()[source + index] = target + index
            }
        } else {
            maps.add(mutableMapOf())
        }
    }

    val result = seeds.minOf { seed ->
        maps.fold(seed) { acc, map ->
            map.getOrElse(acc) { acc }
        }
    }

    println(result)
}