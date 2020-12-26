package aoc2020.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day13pt2KtTest {
    @Test
    fun day13pt2examples1() {
        assertEquals(3417, findEarliestToMatchList(findIdsAndIndices(listOf("17", "x", "13", "19")), 0))
    }

    @Test
    fun day13pt2examples2() {
        assertEquals(754018, findEarliestToMatchList(findIdsAndIndices(listOf("67","7","59","61")), 0))
    }

    @Test
    fun day13pt2examples3() {
        assertEquals(779210, findEarliestToMatchList(findIdsAndIndices(listOf("67","x","7","59","61")), 0))
    }

    @Test
    fun day13pt2examples4() {
        assertEquals(1261476, findEarliestToMatchList(findIdsAndIndices(listOf("67","7","x","59","61")), 0))
    }

    @Test
    fun day13pt2examples5() {
        assertEquals(1202161486, findEarliestToMatchList(findIdsAndIndices(listOf("1789","37","47","1889")), 0))
    }
}