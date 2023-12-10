package com.eskelinenantti.answers

import com.eskelinenantti.answers.Solution7A.cardValues
import com.eskelinenantti.answers.Solution7A.hands
import com.eskelinenantti.resources.inputForPuzzle


object Solution7A {
    val cardValues = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
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
            compareBy<Pair<String, Int>> { handByValue ->
                hands.indexOf(handByValue.first.groupingBy { it }.eachCount().values.sortedDescending())
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