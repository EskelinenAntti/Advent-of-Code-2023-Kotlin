package com.eskelinenantti.answers

import com.eskelinenantti.answers.Solution8A.END
import com.eskelinenantti.answers.Solution8A.LEFT
import com.eskelinenantti.answers.Solution8A.START
import com.eskelinenantti.resources.inputForPuzzle
import com.eskelinenantti.resources.testInputForPuzzle

object Solution8A {
    const val LEFT = 'L'
    const val RIGHT = 'R'
    const val START = "AAA"
    const val END = "ZZZ"

    data class Node(
        val value: String,
        val left: String,
        val right: String,
    )
}

fun main() {
    val input = inputForPuzzle(8)
    val instructions = input.first()
    val nodes = input.drop(2).associate { row ->
        val current = row.split(" = ").first()
        val (left, right) = row.substringAfter("(").substringBefore(")").split(", ")
        current to (left to right)
    }

    var count = 0
    var currentNode = START
    while (currentNode != END) {
        currentNode = if (instructions[count.mod(instructions.length)] == LEFT) {
            nodes.getValue(currentNode).first
        } else {
            nodes.getValue(currentNode).second
        }
        count++
    }
    println(count)

}