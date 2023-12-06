package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle
import com.eskelinenantti.resources.testInputForPuzzle

fun main() {
    val input = inputForPuzzle(6).map { row ->
        Regex("""\d+""").findAll(row).map { it.value.toInt() }.toList()
    }
    val races = input.first().zip(input.last()) { time, distance -> time to distance}
    races.map { (time, distance) ->
        (0..<time).count { timePressed ->
            timePressed * (time - timePressed) > distance
        }
    }.reduce { acc, wins ->
        acc * wins
    }.run(::println)
}