package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle

fun main() {
    val historyList = inputForPuzzle(9).map { it.split(" ").map(String::toInt) }

    val derivativesList = historyList.map { history ->
        generateSequence(history) { derivative ->
            derivative.windowed(size = 2, step = 1).map { (first, second) ->
                second - first
            }
        }.takeWhile { derivative -> derivative.any { it != 0 } }.toList()
    }

    val sum = derivativesList.sumOf { derivatives ->
        derivatives.fold(0) { acc: Int, derivative ->
            acc + derivative.last()
        }
    }

    println(sum)
}