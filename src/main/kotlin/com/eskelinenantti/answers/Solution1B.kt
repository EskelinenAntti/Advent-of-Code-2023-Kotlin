package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle

fun String.replaceWithAnyOf(
    strings: Map<String, String>,
    findFunction: CharSequence.(Collection<String>) -> Pair<Int, String>?,
): String {
    val hit = this.findFunction(strings.keys) ?: return this
    return this.replaceRange(hit.first, hit.first + hit.second.length, strings.getValue(hit.second))
}

fun String.replaceWithAnyOf(
    strings: Map<String, String>,
): String = replaceWithAnyOf(strings) { findAnyOf(it) }

fun String.replaceLastWithAnyOf(
    strings: Map<String, String>,
): String = replaceWithAnyOf(strings) { findLastAnyOf(it) }

fun main() {
    val rows = inputForPuzzle(1)

    val digitsByWords = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    val sum = rows.sumOf { row ->
        val first = row.replaceWithAnyOf(digitsByWords).first { it.isDigit() }
        val last = row.replaceLastWithAnyOf(digitsByWords).last { it.isDigit() }
        "$first$last".toInt()
    }

    println(sum)
}

