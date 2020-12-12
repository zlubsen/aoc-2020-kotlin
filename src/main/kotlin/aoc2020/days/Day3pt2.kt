package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day3pt2 : DayExercise {
    override fun run() {
        val grid = File("src/main/resources/aoc2020/days/day3/Day3pt1.txt")
            .readLines()
            .map { it.toCharArray() }

        val slopes = listOf(
            Slope(1,1),
            Slope(3,1),
            Slope(5,1),
            Slope(7,1),
            Slope(1,2)
        )

        val result = slopes.map { countTreesOnSlope(grid, it) }.reduce { acc, it -> acc * it }

        println("Multiplication: $result")
    }
}