package com.eskelinenantti.answers

import com.eskelinenantti.answers.Solution7B.JOKER
import com.eskelinenantti.answers.Solution7B.cardValues
import com.eskelinenantti.answers.Solution7B.hands
import com.eskelinenantti.resources.inputForPuzzle

object Solution7B {
    const val JOKER = 'J'
    val cardValues = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', JOKER)
    val hands = listOf(
        listOf(5),
        listOf(4, 1),
        listOf(3, 2),
        listOf(3, 1, 1),
        listOf(2, 2, 1),
        listOf(2, 1, 1, 1),
        listOf(1, 1, 1, 1, 1),
    )
}

fun main() {
    val handsByValues = inputForPuzzle(7)
        .map {
            it.split(" ")
        }.map { (hand, value) ->
            hand to value.toInt()
        }.sortedWith(
            compareBy<Pair<String, Int>> { (cards, _) ->
                val hand =
                    cards.filterNot { it == JOKER }.groupingBy { it }.eachCount().values.sortedDescending().toMutableList()

                if (hand.isEmpty()) {
                    hand.add(cards.count { it == JOKER })
                } else {
                    hand[0] += cards.count { it == JOKER }
                }

                hands.indexOf(hand)
            }.thenComparator { card1, card2 ->
                (0..<5).map {
                    cardValues.indexOf(card1.first[it])
                        .compareTo(cardValues.indexOf(card2.first[it]))
                }.firstOrNull { it != 0 } ?: 0
            }
        ).reversed()
        .withIndex()
        .sumOf {
            (it.index + 1) * it.value.second
        }

    println(handsByValues)
}