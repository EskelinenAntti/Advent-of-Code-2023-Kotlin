package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle

fun main() {
    val rows = inputForPuzzle(5)
    val seeds = rows.first().removePrefix("seeds: ").split(" ").map(String::toLong)

    // Just parse the string input to sane format
    val maps = mutableListOf<MutableList<Triple<Long, Long, Long>>>()
    rows.filter { it.isNotBlank() }.drop(1).forEach { row ->
        if (row.first().isDigit()) {
            val (target, source, range) = row.split(" ").map(String::toLong)
            maps.last().add(Triple(target, source, range))
        } else {
            maps.add(mutableListOf())
        }
    }

    val result = seeds.minOf { seed ->
        maps.fold(seed) { acc, map ->
            map.firstOrNull {
                (it.second..<it.second + it.third).contains(acc)
            }?.run {
                first + (acc - second)
            } ?: acc
        }
    }
    println(result)
}