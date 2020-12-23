package aoc2020.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day12pt2KtTest {
    @Test
    fun testRotateWaypoint() {
        val initial = Position(2,1,Heading.N)
        val l90 = rotateWaypoint(waypoint = initial, instruction = NavInstruction(action = Action.L,value = 90))
        val l180 = rotateWaypoint(waypoint = initial, instruction = NavInstruction(action = Action.L,value = 180))
        val l270 = rotateWaypoint(waypoint = initial, instruction = NavInstruction(action = Action.L,value = 270))
        val r90 = rotateWaypoint(waypoint = initial, instruction = NavInstruction(action = Action.R,value = 90))
        val r180 = rotateWaypoint(waypoint = initial, instruction = NavInstruction(action = Action.R,value = 180))
        val r270 = rotateWaypoint(waypoint = initial, instruction = NavInstruction(action = Action.R,value = 270))

        assertEquals(Position(-1, 2, Heading.N), l90)
        assertEquals(Position(-2, -1, Heading.N), l180)
        assertEquals(Position(1, -2, Heading.N), l270)
        assertEquals(Position(1, -2, Heading.N), r90)
        assertEquals(Position(-2, -1, Heading.N), r180)
        assertEquals(Position(-1, 2, Heading.N), r270)
    }
}