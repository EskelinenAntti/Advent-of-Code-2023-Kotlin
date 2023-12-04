package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle
import com.eskelinenantti.resources.testInputForPuzzle
import com.sun.org.apache.xalan.internal.lib.ExsltMath.power
import java.lang.Math.pow
import kotlin.math.pow

object Solution4A {}


fun main() {
    val rows = inputForPuzzle(4)
    val scratchCards = rows.map { row ->
        Regex("""\d+""").findAll(parseScratchCardSide(row).first())
            .map { match -> match.value }.toSet() to
                Regex("""\d+""").findAll(parseScratchCardSide(row).last())
                    .map { match -> match.value }.toSet()
    }
    val sum = scratchCards.map { (winningNumbers, numbers) ->
        winningNumbers.intersect(numbers).size
    }.filter { it > 0 }.sumOf { 2.toDouble().pow(it.toDouble() - 1 ).toInt()}
    println(sum)
}

private fun parseScratchCardSide(row: String) = row.split(": ").last().split(" | ")