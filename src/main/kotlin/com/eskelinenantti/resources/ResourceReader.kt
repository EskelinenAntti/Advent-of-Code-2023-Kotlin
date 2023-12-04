package com.eskelinenantti.resources

import java.lang.IllegalArgumentException

fun inputForPuzzle(day: Int): List<String> =
    readResource("/inputs/$day.txt")

fun testInputForPuzzle(day: Int): List<String> =
    readResource("/test_inputs/$day.txt")


private fun readResource(resource: String) =
    object {}::class.java.getResource(resource)?.readText()?.split("\n")
    ?: throw IllegalArgumentException("File not found")