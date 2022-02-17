package com.mydigitalschool.kata

fun stringCalculator(input: String): Int {
    if (input == "") {
        return 0
    }
    var customInput = input
    val listDelimiter = mutableListOf(",", "\n")
    val customDelimiter = getDelimiter(customInput)
    if (customDelimiter != null) {
        listDelimiter.add(customDelimiter)
        if(customDelimiter.length > 1){
        customInput = input.drop(customDelimiter.length + 2)
        } else {
            customInput = customInput.removePrefix(customDelimiter)
        }
    }
    if (customInput.split(*listDelimiter.toTypedArray()).size >= 2) {
        return stringSplitter(customInput, listDelimiter)
    }
    return customInput.toInt()
}

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

private fun getDelimiter(input: String, delimiters: MutableList<String> = mutableListOf()): List<String> {
    val firstChar = input.first()
    //guard
    if (firstChar.isDigit() || firstChar == '-') {
        return delimiters
    }
    if(firstChar == '['){
        delimiters.add(input.substring(1, input.indexOf(']')))
        return getDelimiter(input,delimiters)
    }
    else if (!firstChar.isDigit() && firstChar != '-') {
         return firstChar.toString()
    } else {
        return null
     }
}