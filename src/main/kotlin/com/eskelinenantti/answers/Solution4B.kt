package com.eskelinenantti.answers

import com.eskelinenantti.resources.inputForPuzzle


fun main() {
    val rows = inputForPuzzle(4)
    val scratchCards = rows.map { row ->
        Regex("""\d+""").findAll(parseScratchCardSide(row).first())
            .map { match -> match.value }.toSet() to
                Regex("""\d+""").findAll(parseScratchCardSide(row).last())
                    .map { match -> match.value }.toSet()
    }

    val winsByNumberOfCards = scratchCards.map { (winningNumbers, numbers) ->
        winningNumbers.intersect(numbers).size to 1
    }.toMutableList()

    winsByNumberOfCards.withIndex().map { (index, value) ->
        val wins = value.first
        val numberOfCards = value.second
        for (i in index + 1..index + wins) {
            if (winsByNumberOfCards.size <= i) {
                continue
            }
            val nextCard = winsByNumberOfCards[i]
            winsByNumberOfCards[i] = nextCard.first to nextCard.second + numberOfCards
        }
    }

    println(winsByNumberOfCards.sumOf {it.second})
}


private fun parseScratchCardSide(row: String) = row.split(": ").last().split(" | ")