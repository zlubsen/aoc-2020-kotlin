package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day9pt2 : DayExercise {
    override fun run() {
        val numbers = File("src/main/resources/aoc2020/days/day9/Day9pt1.txt")
            .readLines().map { it.toLong() }
        val preamble = 25
//        val numbers = testInputDay9.lines().map { it.toLong() }
//        val preamble = 5

        val missingSum = findMissingSum(numbers = numbers, preamble = preamble)
        println("First missing sum number: $missingSum")
        val range = findContinousSum(sum = missingSum, numbers = numbers)
//        println(range)
        val sum = addSmallestAndLargest(range)
        println("Adds up to $sum")
    }
}

fun findContinousSum(sum : Long, numbers : List<Long>) : List<Long> {
    for(start in numbers.indices) {
        for(end in start..numbers.lastIndex) {
            val slice = numbers.slice(start..end)
            when {
                slice.sum() == sum -> return slice
                slice.sum() > sum -> break
                slice.sum() < sum -> continue
            }
        }
    }
    return listOf()
}

fun addSmallestAndLargest(list : List<Long>) : Long {
    val sorted = list.sorted()
    return sorted.first() + sorted.last()
}