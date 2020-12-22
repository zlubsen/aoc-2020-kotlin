package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File
import kotlin.math.abs

class Day12pt1 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day12/Day12pt1.txt")
            .readLines()
        val instructions = parseNavInstructions(lines)

//        val lines = testInputDay12pt1.lines()
//        val instructions = parseNavInstructions(lines)

        val origin = Position(0,0,Heading.E)
        val destination = navigate(start = origin, instructions = instructions)
        println(destination.calculateManhattan())
    }
}

fun navigate(start: Position, instructions: List<NavInstruction>) : Position {
    return instructions.fold(start) { acc, inst ->
        when(inst.action) {
            Action.N -> Position(x = acc.x, y = acc.y+inst.value, heading = acc.heading)
            Action.S -> Position(x = acc.x, y = acc.y-inst.value, heading = acc.heading)
            Action.E -> Position(x = acc.x+inst.value, y = acc.y, heading = acc.heading)
            Action.W -> Position(x = acc.x-inst.value, y = acc.y, heading = acc.heading)
            Action.L -> Position(x = acc.x, y = acc.y, heading = acc.heading.rotate(inst))
            Action.R -> Position(x = acc.x, y = acc.y, heading = acc.heading.rotate(inst))
            Action.F -> {
                when (acc.heading) {
                    Heading.N -> Position(x = acc.x, y = acc.y+inst.value, heading = acc.heading)
                    Heading.E -> Position(x = acc.x+inst.value, y = acc.y, heading = acc.heading)
                    Heading.S -> Position(x = acc.x, y =acc.y-inst.value, heading = acc.heading)
                    Heading.W -> Position(x = acc.x-inst.value, y = acc.y, heading = acc.heading)
                }
            }
        }
    }
}

data class Position(val x : Int, val y : Int, val heading : Heading) {
    fun calculateManhattan() : Int {
        return abs(x) + abs(y)
    }
}

data class NavInstruction(val action: Action, val value : Int)

fun parseNavInstructions(lines : List<String>) : List<NavInstruction> {
    return lines.map {
        NavInstruction(
            action = Action.valueOf(it.substring(0,1)),
            value = it.substring(1..it.lastIndex).toInt()) }
}

enum class Action {
    N,
    S,
    E,
    W,
    L,
    R,
    F,
}

enum class Heading(private val degrees : Int) {
    N(0),       // +y
    S(180),     // -y
    E(90),      // +x
    W(270);     // -x

    private fun fromDegrees(degrees: Int) : Heading {
        return when (degrees%360) {
            0 -> N
            90 -> E
            180 -> S
            270 -> W
            else -> N
        }
    }

    fun rotate(instruction: NavInstruction) : Heading {
        val delta = instruction.value
        return when(instruction.action) {
            Action.L -> fromDegrees(((this.degrees - delta) + 360) % 360)
            Action.R -> fromDegrees((this.degrees + delta) % 360)
            else -> this
        }
    }
}

val testInputDay12pt1 = """
    F10
    N3
    F7
    R90
    F11
""".trimIndent()