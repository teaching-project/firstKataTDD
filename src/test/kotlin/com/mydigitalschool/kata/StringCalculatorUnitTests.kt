package com.mydigitalschool.kata

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringCalculatorUnitTests {

    @Test
    fun `An empty string returns zero`() {
        //GIVEN

        //WHEN
        val actual = stringCalculator("")
        //THEN
        Assertions.assertEquals(0, actual)
    }

    @Test
    fun `A single number returns the value`() {

        //WHEN
        val actual = stringCalculator("5")

        //THEN
        Assertions.assertEquals(5, actual)
    }

    @Test
    fun `Two numbers, comma delimited, returns the sum`() {

        //WHEN
        val actual = stringCalculator("5,6")

        //THEN
        Assertions.assertEquals(11, actual)
    }

    @Test
    fun `Two numbers, newline delimited, returns the sum`() {

        //WHEN
        val actual = stringCalculator("3\n7")

        //THEN
        Assertions.assertEquals(10, actual)
    }

    @Test
    fun `Three numbers, delimited either way, returns the sum`() {

        //WHEN
        val actual = stringCalculator("3\n7,4")

        //THEN
        Assertions.assertEquals(14, actual)
    }

    @Test
    fun `Negative numbers throw an exception`() {

        //WHEN
        val actual: () -> Unit = { stringCalculator("-3\n7,4") }

        //THEN
        Assertions.assertThrows(RuntimeException::class.java, actual)
    }

    @Test
    fun `Numbers greater than 1000 are ignored`() {

        //WHEN
        val actual= stringCalculator("10001\n7,4")

        //THEN
        Assertions.assertEquals(11, actual)
    }

    @Test
    fun `A single char delimiter can be defined on the first line`() {

        //WHEN
        val actual= stringCalculator("#10#7,4")

        //THEN
        Assertions.assertEquals(21, actual)
    }
    @Test
    fun `A multi char delimiter can be defined on the first line`() {

        //WHEN
        val actual= stringCalculator("[###]10###7,4")

        //THEN
        Assertions.assertEquals(21, actual)
    }

    @Test
    fun `Many single or multi-char delimiters can be defined`() {

        //WHEN
        val actual= stringCalculator("[###][;;;]|^10;;;15|5^6###7,4")

        //THEN
        Assertions.assertEquals(47, actual)
    }

}