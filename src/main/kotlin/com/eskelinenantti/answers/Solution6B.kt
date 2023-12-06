package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle
import com.eskelinenantti.resources.testInputForPuzzle

fun main() {
    val (time, distance) = inputForPuzzle(6).map { row ->
        row.filter(Char::isDigit).toLong()
    }.run {
        first() to last()
    }
    (0..<time).count { timePressed ->
        timePressed * (time - timePressed) > distance
    }.run(::println)
}