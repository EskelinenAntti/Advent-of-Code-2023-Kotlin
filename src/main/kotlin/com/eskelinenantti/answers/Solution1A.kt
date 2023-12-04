package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle

fun main() {
    val rows = inputForPuzzle(1)

    val sum =
        rows.sumOf { row ->
            "${row.first { char -> char.isDigit() }}${row.last { char -> char.isDigit() }}".toInt()
        }
    println(sum)
}


