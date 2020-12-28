package aoc2020.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day13pt2KtTest {
    @Test
    fun day13pt2examples1() {
        assertEquals(3417L, findEarliestToMatchList(findIdsAndIndices(listOf("17", "x", "13", "19")), 0))

        assertEquals(3417L, findEarliestEfficient(findIdsAndIndices(listOf("17", "x", "13", "19"))))
    }

    @Test
    fun day13pt2examples2() {
        assertEquals(754018L, findEarliestToMatchList(findIdsAndIndices(listOf("67","7","59","61")), 0))

        assertEquals(754018L, findEarliestEfficient(findIdsAndIndices(listOf("67","7","59","61"))))
    }

    @Test
    fun day13pt2examples3() {
        assertEquals(779210L, findEarliestToMatchList(findIdsAndIndices(listOf("67","x","7","59","61")), 0))

        assertEquals(779210L, findEarliestEfficient(findIdsAndIndices(listOf("67","x","7","59","61"))))
    }

    @Test
    fun day13pt2examples4() {
        assertEquals(1261476L, findEarliestToMatchList(findIdsAndIndices(listOf("67","7","x","59","61")), 0))

        assertEquals(1261476L, findEarliestEfficient(findIdsAndIndices(listOf("67","7","x","59","61"))))
    }

    @Test
    fun day13pt2examples5() {
        assertEquals(1202161486L, findEarliestToMatchList(findIdsAndIndices(listOf("1789","37","47","1889")), 0))

        assertEquals(1202161486L, findEarliestEfficient(findIdsAndIndices(listOf("1789","37","47","1889"))))
    }
}