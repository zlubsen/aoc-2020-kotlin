package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File
import kotlin.properties.Delegates

class Day10pt2 : DayExercise {
    override fun run() {
        val ratings = File("src/main/resources/aoc2020/days/day10/Day10pt1.txt")
            .readLines().map { it.toInt() }.sorted()


    }
}

// patterns
// 113
// 1113
// 11113