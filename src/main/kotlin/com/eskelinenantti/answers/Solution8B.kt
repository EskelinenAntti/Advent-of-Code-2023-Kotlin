package com.eskelinenantti.answers

import com.eskelinenantti.answers.Solution8B.LEFT
import com.eskelinenantti.resources.inputForPuzzle
import kotlin.math.max

object Solution8B {
    const val LEFT = 'L'
}

fun solve(startNode: String, instructions: String, nodes: Map<String, Pair<String, String>>): Long {
    var count = 0L
    var currentNode = startNode
    while (currentNode.endsWith('Z').not()) {
        currentNode = if (instructions[count.mod(instructions.length)] == Solution8A.LEFT) {
            nodes.getValue(currentNode).first
        } else {
            nodes.getValue(currentNode).second
        }
        count++
    }
    return count
}

fun main() {
    val input = inputForPuzzle(8)
    val instructions = input.first()
    val nodes = input.drop(2).associate { row ->
        val current = row.split(" = ").first()
        val (left, right) = row.substringAfter("(").substringBefore(")").split(", ")
        current to (left to right)
    }

    val startNodes = nodes.keys.filter { it.endsWith('A') }
    val result = startNodes.drop(1).fold(solve(startNodes.first(), instructions, nodes)) { acc, node ->
        lcm(acc, solve(node, instructions, nodes))
    }
    println(result)

}

fun lcm(a: Long, b: Long): Long {
    val larger = max(a,b)
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}
