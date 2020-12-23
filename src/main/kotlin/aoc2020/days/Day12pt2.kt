package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day12pt2 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day12/Day12pt1.txt")
            .readLines()
        val instructions = parseNavInstructions(lines)

        val origin = Position(0,0,Heading.E)
        val destination = navigateWaypoint(
            start = Pair(origin, Position(x = 10, y = 1, Heading.N)),
            instructions = instructions)
        println(destination.first.calculateManhattan())
    }
}

fun navigateWaypoint(start: Pair<Position, Position>, instructions: List<NavInstruction>) : Pair<Position, Position> {
    return instructions.fold(start) { acc, inst ->
        val shipPosition = acc.first
        val waypoint = acc.second
        when(inst.action) {
            Action.N -> Pair(shipPosition, Position(x = waypoint.x, y = waypoint.y+inst.value, heading = waypoint.heading))
            Action.S -> Pair(shipPosition, Position(x = waypoint.x, y = waypoint.y-inst.value, heading = waypoint.heading))
            Action.E -> Pair(shipPosition, Position(x = waypoint.x+inst.value, y = waypoint.y, heading = waypoint.heading))
            Action.W -> Pair(shipPosition, Position(x = waypoint.x-inst.value, y = waypoint.y, heading = waypoint.heading))
            Action.L -> Pair(shipPosition, rotateWaypoint(waypoint = waypoint, instruction = inst))
            Action.R -> Pair(shipPosition, rotateWaypoint(waypoint = waypoint, instruction = inst))
            Action.F -> moveTowardsWaypoint(shipPosition = shipPosition, waypoint = waypoint, instruction = inst)
        }
    }
}

fun rotateWaypoint(waypoint : Position, instruction: NavInstruction) : Position {
    val stepsOf90 = (instruction.value / 90)
    val xSign = if(instruction.action == Action.L) -1 else 1
    val ySign = if(instruction.action == Action.R) -1 else 1

    return (1..stepsOf90).toList().fold(waypoint) { acc, _ ->
        Position(acc.y * xSign, acc.x * ySign, acc.heading)
    }
}

fun moveTowardsWaypoint(shipPosition: Position, waypoint: Position, instruction: NavInstruction) : Pair<Position, Position> {
    val magnitude = instruction.value
    return Pair(
        first = Position(
            x = shipPosition.x + (magnitude * (waypoint.x)),
            y = shipPosition.y + (magnitude * (waypoint.y)), heading = shipPosition.heading),
        second = waypoint
    )
}