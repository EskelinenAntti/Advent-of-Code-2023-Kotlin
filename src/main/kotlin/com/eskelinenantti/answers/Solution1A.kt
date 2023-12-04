package com.eskelinenantti.answers

import com.eskelinenantti.resources.readResource

fun main() {
    val data = readResource("/inputs/1.txt")

    val rows = data.split("\n")
    val sum =
        rows.sumOf { row ->
            "${row.first { char -> char.isDigit() }}${row.last { char -> char.isDigit() }}".toInt()
        }
    println(sum)
}


