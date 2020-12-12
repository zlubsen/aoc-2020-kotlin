package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File
import kotlin.math.ceil
import kotlin.math.floor

val initialRows = IntRange(0,127)
val initialColumns = IntRange(0,7)

class Day5pt1 : DayExercise {
    override fun run() {
        val passes = File("src/main/resources/aoc2020/days/day5/Day5pt1.txt")
            .readLines()
        val highest = passes.map { findSeat(it) }.map { calcId(it) }.sorted().last()
        println("Highest ID: $highest")
    }
}

fun findSeat(pass: String) : Pair<Int, Int> {
    return Pair(
        findRow(initialRows, pass.substring(0..6)),
        findColumn(initialColumns, pass.substring(7..9)))
}

fun findRow(rows: IntRange, pass : String) : Int {
    return if(pass.isEmpty()) {
        rows.first
    } else {
        when (pass.first()) {
            'F' -> findRow(rows.first..midFloor(rows), pass.substring(1))
            'B' -> findRow(midCeil(rows)..rows.last, pass.substring(1))
            else -> 0
        }
    }
}

fun midFloor(range: IntRange) : Int {
    return range.first + floor((range.last-range.first)/2.0).toInt()
}

fun midCeil(range: IntRange) : Int {
    return range.first + ceil((range.last-range.first)/2.0).toInt()
}

fun findColumn(columns: IntRange, pass: String) : Int {
    return if(pass.isEmpty()) {
        columns.first
    } else {
        when (pass.first()) {
            'L' -> findColumn(columns.first..midFloor(columns), pass.substring(1))
            'R' -> findColumn(midCeil(columns)..columns.last, pass.substring(1))
            else -> 0
        }
    }
}

fun calcId(seat: Pair<Int, Int>) : Int {
    return seat.first * 8 + seat.second
}

//enum class Region {
//    FRONT,
//    BACK,
//    LEFT,
//    RIGHT;
//
//    fun parseRegion(input : String) : Region {
//        return when(input) {
//            "F" -> Region.FRONT
//            "B" -> Region.BACK
//            "L" -> Region.LEFT
//            "R" -> Region.RIGHT
//            else -> Region.FRONT
//        }
//    }
//}