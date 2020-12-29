package aoc2020.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day15pt2Test {
    @Test
    fun testExampleInText() {
        val a = Day15pt2()
        assertEquals(175594, a.turnsPt2(listOf(0,3,6), 30000000))
    }

    @Test
    fun testExample1() {
        val a = Day15pt2()
        assertEquals(2578, a.turnsPt2(listOf(1,3,2), 30000000))
    }

    @Test
    fun testExample2() {
        val a = Day15pt2()
        assertEquals(3544142, a.turnsPt2(listOf(2,1,3), 30000000))
    }

    @Test
    fun testExample3() {
        val a = Day15pt2()
        assertEquals(261214, a.turnsPt2(listOf(1,2,3), 30000000))
    }

    @Test
    fun testExample4() {
        val a = Day15pt2()
        assertEquals(6895259, a.turnsPt2(listOf(2,3,1), 30000000))
    }

    @Test
    fun testExample5() {
        val a = Day15pt2()
        assertEquals(18, a.turnsPt2(listOf(3,2,1), 30000000))
    }

    @Test
    fun testExample6() {
        val a = Day15pt2()
        assertEquals(362, a.turnsPt2(listOf(3,1,2), 30000000))
    }
}