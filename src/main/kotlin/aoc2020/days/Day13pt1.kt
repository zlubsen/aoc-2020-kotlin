package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day13pt1 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day13.txt")
            .readLines()

//        val lines = testInputDay13.lines()

        val timestamp = lines[0].toInt()
        val ids = parseBusIDs(lines[1])

        val nextDepartures = ids.map { findNextDepartureAfterTimestamp(id = it, timestamp = timestamp) }

        val earliest = nextDepartures.sorted().first()
        val earliestId = ids[nextDepartures.indexOf(earliest)]
        val waitingTime = earliest - timestamp
        println(earliestId * waitingTime)
    }
}

fun parseBusIDs(line : String) : List<Int> {
    return line.split(',').filterNot { it == "x" }.map { it.toInt() }
}

fun findNextDepartureAfterTimestamp(id : Int, timestamp : Int) : Int {
    val remainder = timestamp % id
    return timestamp - remainder + id
}

val testInputDay13 = """
    939
    7,13,x,x,59,x,31,19
""".trimIndent()