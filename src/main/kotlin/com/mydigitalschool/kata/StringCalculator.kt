package com.mydigitalschool.kata

class StringCalculator(val input: String, val delimiters: MutableList<String> = mutableListOf()) {

    init {
    }

    fun compute(input: String): Int {
        if (input == "") {
            return 0
        }
        var customInput = input
        val listDelimiter = mutableListOf(",", "\n")
        val customDelimiter = getDelimiter(customInput)
        if (customDelimiter != null) {
            listDelimiter.add(customDelimiter)
            customInput = if(customDelimiter.length > 1){
                input.drop(customDelimiter.length + 2)
            } else {
                customInput.removePrefix(customDelimiter)
            }
        }
        if (customInput.split(*listDelimiter.toTypedArray()).size >= 2) {
            return stringSplitter(customInput, listDelimiter)
        }
        return customInput.toInt()
    }

}

fun stringCalculator(input: String): Int = StringCalculator(input).compute(input)

private fun stringSplitter(input: String, delimiters: MutableList<String>): Int {
    val inputSplit = input.split(*delimiters.toTypedArray())
    return inputSplit
        .map { it.toInt() }
        .onEach {
            if (it < 0) {
                throw RuntimeException("Nombre négatif")
            }
        }
        .filter {
            it < 1000
        }
        .sumOf { it }
}

private fun getDelimiter(input: String, delimiters: MutableList<String> = mutableListOf()): String? {
    val firstChar = input.first()
    //guard
    if (firstChar.isDigit() || firstChar == '-') {
        return null
    }
    return if(firstChar == '['){
        input.substring(1, input.indexOf(']'))
    }
    else if (!firstChar.isDigit() && firstChar != '-') {
        firstChar.toString()
    } else {
        null
    }
}