package com.mydigitalschool.kata

class StringCalculator(private val delimiters: MutableList<String> = mutableListOf(",", "\n")) {

    fun compute(input: String): Int = if (input == "")
        0 else {
        val clearedInput = extractDelimiters(input)
        val inputParts = clearedInput.split(*delimiters.toTypedArray())
        if (inputParts.size >= 2)
            sumInputParts(inputParts)
        else
            clearedInput.toInt()
    }

    private fun sumInputParts(parts: List<String>) = parts
        .map { it.toInt() }
        .onEach { if (it < 0) throw RuntimeException("Nombre négatif") }
        .filter { it < 1000 }
        .sumOf { it }

    private fun extractDelimiters(input: String): String {
        val firstChar = input.first()
        //guard
        return if (firstChar.isDigit() || firstChar == '-') {
            input
        } else if (firstChar == '[') {
            delimiters.add(input.substring(1, input.indexOf(']')))
            extractDelimiters(input.drop(3))
        } else if (!firstChar.isDigit() && firstChar != '-') {
            delimiters.add(firstChar.toString())
            extractDelimiters(input.drop(1))
        } else {
            throw RuntimeException("Unknown delimiter: $firstChar")
        }
    }

}

fun stringCalculator(input: String): Int = StringCalculator().compute(input)
