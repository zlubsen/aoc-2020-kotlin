package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day10pt2 : DayExercise {
    override fun run() {
        val ratings = File("src/main/resources/aoc2020/days/day10/Day10pt1.txt")
            .readLines().map { it.toInt() }.sorted()

//        val ratings = testInputDay10a.lines().map { it.toInt() }.sorted()
//        val ratings = testInputDay10b.lines().map { it.toInt() }.sorted()

        val device = ratings.last() + 3
        val diffs = (listOf(0) + ratings + device)
            .windowed(2).map { it.last() - it.first() }

        val arrangements = findArrangements(diffs.asSequence())
        println("Nr of arrangements: $arrangements")
    }
}

fun findArrangements(all: Sequence<Int>): Long {//: List<List<Int>> {
    val regex = "(1{2,4}3)".toRegex()
    val diffsAsString = all.joinToString("")
    val matches = regex.findAll(diffsAsString)
    val lengths = matches.map {
        when (it.value.length) {
            5 -> {
                7L
            }
            4 -> {
                4L
            }
            3 -> {
                2L
            }
            else -> {
                1L
            }
        }
    }.toList()

    return lengths.reduce { acc, it -> acc * it }
}