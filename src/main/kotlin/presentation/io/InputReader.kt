package org.example.presentation.io

interface InputReader {
    fun readString (): String
    fun readIntOrNull (): Int?
    fun readDoubleOrNull (): Double?
}