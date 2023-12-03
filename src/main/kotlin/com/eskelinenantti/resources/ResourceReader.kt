package com.eskelinenantti.resources

import java.lang.IllegalArgumentException

fun readResource(file: String): String = object {}::class.java.getResource(file)?.readText()
    ?: throw IllegalArgumentException("File not found")
