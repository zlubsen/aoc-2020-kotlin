package aoc2020.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day15pt1KtTest {
    @Test
    fun testExampleInText() {
        assertEquals(0, turns(listOf(0,3,6), 10).last())
    }

    @Test
    fun testExample1() {
        assertEquals(1, turns(listOf(1,3,2), 2020).last())
    }

    @Test
    fun testExample2() {
        assertEquals(10, turns(listOf(2,1,3), 2020).last())
    }

    @Test
    fun testExample3() {
        assertEquals(27, turns(listOf(1,2,3), 2020).last())
    }

    @Test
    fun testExample4() {
        assertEquals(78, turns(listOf(2,3,1), 2020).last())
    }

    @Test
    fun testExample5() {
        assertEquals(438, turns(listOf(3,2,1), 2020).last())
    }

    @Test
    fun testExample6() {
        assertEquals(1836, turns(listOf(3,1,2), 2020).last())
    }
}