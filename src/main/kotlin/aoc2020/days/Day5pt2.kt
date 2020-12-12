package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day5pt2 : DayExercise {
    override fun run() {
        val passes = File("src/main/resources/aoc2020/days/day5/Day5pt1.txt")
            .readLines()
        val seatIds = passes.map { findSeat(it) }.map { calcId(it) }.sorted()
        println(seatIds)
        val mySeat = findMissingSeat(seatIds)
        println("Seat: $mySeat")
    }
}

fun findMissingSeat(ids : List<Int>) : Int {
    var previous = -1

    for (id in ids) {
        if (previous == id - 2)
            return id - 1

        previous = id
    }

    return -1
}