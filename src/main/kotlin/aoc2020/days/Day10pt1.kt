package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File
import kotlin.properties.Delegates

class Day10pt1 : DayExercise {
    override fun run() {
        val ratings = File("src/main/resources/aoc2020/days/day10/Day10pt1.txt")
            .readLines().map { it.toInt() }.sorted()

//        val ratings = testInputDay10a.lines().map { it.toInt() }.sorted()
//        val ratings = testInputDay10b.lines().map { it.toInt() }.sorted()
        val device = ratings.last() + 3
        val diffs = (listOf(0) + ratings + device)
            .windowed(2).groupBy{it.last() - it.first()}

        val multiplied = diffs.map { it.value.size }.reduce{acc, i -> acc * i}
        println(multiplied)
    }
}

val testInputDay10a = """
    16
    10
    15
    5
    1
    11
    7
    19
    6
    12
    4
""".trimIndent()

val testInputDay10b = """
    28
    33
    18
    42
    31
    14
    46
    20
    48
    47
    24
    23
    49
    45
    19
    38
    39
    11
    1
    32
    25
    35
    8
    17
    7
    9
    4
    2
    34
    10
    3
""".trimIndent()