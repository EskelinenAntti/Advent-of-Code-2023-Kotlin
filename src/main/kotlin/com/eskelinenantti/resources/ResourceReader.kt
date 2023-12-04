package com.eskelinenantti.resources

import java.lang.IllegalArgumentException

fun inputForPuzzle(day: Int): List<String> =
    object {}::class.java.getResource("/inputs/$day.txt")?.readText()?.split("\n")
        ?: throw IllegalArgumentException("File not found")
