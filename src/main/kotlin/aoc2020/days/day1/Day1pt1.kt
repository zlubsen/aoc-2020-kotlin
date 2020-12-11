package aoc2020.days.day1

import aoc2020.utils.DayExercise
import java.io.File

class Day1pt1 : DayExercise {
    override fun run() {
        val numbers = File("src/main/resources/aoc2020/days/day1/Day1pt1.txt").useLines { it.map { i -> i.toInt() }.toList() }
        val numElements = numbers.lastIndex
        for (leftIndex in 0..numElements) {
            val leftNum = numbers[leftIndex]
            for (rightIndex in (leftIndex+1)..numElements) {
                val rightNum = numbers[rightIndex]
                if (leftNum + rightNum == 2020)
                    println("Sum is 2020: $leftNum * $rightNum = ${leftNum * rightNum}")
            }
        }
    }
}