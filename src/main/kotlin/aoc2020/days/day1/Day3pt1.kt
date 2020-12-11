package aoc2020.days.day1

import aoc2020.utils.DayExercise
import java.io.File

class Day3pt1 : DayExercise {
    override fun run() {
        val grid = File("src/main/resources/aoc2020/days/day3/Day3pt1.txt")
            .readLines()
            .map { it.toCharArray() }

        val numTrees = countTreesOnSlope(grid, Slope(3,1))

        println("Encountered trees: $numTrees")
    }
}

data class Slope(val right : Int, val down: Int)

fun countTreesOnSlope(grid : List<CharArray>, slope: Slope) : Long {
    val width = grid[0].size
    var numTrees = 0L
    var x = 0
    var y = 0

    while (y < grid.size) {
        if (grid[y][x % width] == '#') {
            numTrees++
        }
        x += slope.right
        y += slope.down
    }

    return numTrees
}