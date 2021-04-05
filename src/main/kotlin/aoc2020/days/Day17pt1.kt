package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day17pt1 : DayExercise {
    override fun run() {
        val input = File("src/main/resources/aoc2020/days/day17.txt")
            .readText()

        val cube = Cube(input)
        println(cube.points)
    }
}

data class Point(val coordinates : List<Int>)

data class Cube(val input: String) {
    val points = mutableSetOf<Point>()
    
    init {
        input.lines()
            .forEachIndexed { x, line ->
                line.toCharArray()
                    .forEachIndexed { y, c ->
                        if (c == '#')
                            points.add(
                                Point(listOf(x,y,0)))
                    }
            }
    }
}

//fun cycleCube(cube : List<List<List<Boolean>>>) : List<List<List<Boolean>>> {
//    val size = cube.size
//
//}

fun parseInitialPlane(input : String) : List<List<Boolean>> {
    return input.split("\n").map { line ->
        line.toCharArray().map { parseCubeState(it) }
    }
}

fun parseCubeState(input : Char) : Boolean {
    return input == '#'
}