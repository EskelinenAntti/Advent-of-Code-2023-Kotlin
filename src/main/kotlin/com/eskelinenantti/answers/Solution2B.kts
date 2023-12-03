package com.eskelinenantti.answers

import com.eskelinenantti.resources.readResource

data class Game(val id: Int, val maxRed: Int, val maxBlue: Int, val maxGreen: Int) {
    companion object {
        fun from(row: String): Game {
            val id = row.split(": ").first().removePrefix("Game ").toInt()
            val colors = row
                .split(": ")
                .last()
                .replace("; ", ", ")
                .split(", ")
            val maxRed = colors.filter { it.contains(" red") }.maxOfOrNull { it.removeSuffix(" red").toInt() } ?: 0
            val maxBlue = colors.filter { it.contains(" blue") }.maxOfOrNull { it.removeSuffix(" blue").toInt() } ?: 0
            val maxGreen = colors.filter { it.contains(" green") }.maxOfOrNull { it.removeSuffix(" green").toInt() } ?: 0
            return Game(id, maxRed, maxBlue, maxGreen)
        }
    }
}

val dataset = readResource("/inputs/2.txt")

val sum = dataset.split("\n").map(Game.Companion::from).sumOf {
    it.maxRed * it.maxBlue * it.maxGreen
}
sum
