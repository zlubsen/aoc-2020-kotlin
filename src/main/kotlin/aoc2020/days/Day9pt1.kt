package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File
import kotlin.properties.Delegates

class Day9pt1 : DayExercise {
    override fun run() {
        val numbers = File("src/main/resources/aoc2020/days/day9/Day9pt1.txt")
            .readLines().map { it.toLong() }
        val preamble = 25
//        val numbers = testInputDay9.lines().map { it.toLong() }
//        val preamble = 5

        val missingSum = findMissingSum(numbers = numbers, preamble = preamble)
        println("First missing sum number: $missingSum")
    }
}

fun findMissingSum(numbers: List<Long>, preamble : Int) : Long {
    var finger = preamble
    var missing = 0L

    for(item in numbers.slice(preamble..numbers.lastIndex)) {
        val sums = calcSums(numbers.slice((finger-preamble) until finger))
        if (numbers[finger] !in sums) {
            missing = numbers[finger]
            break
        }
        finger++
    }
    return missing
}

fun calcSums(slice : List<Long>) : Set<Long> {
    val sums = mutableListOf<Long>()
    for (leftIndex in slice.indices) {
        val leftNum = slice[leftIndex]
        for (rightIndex in (leftIndex+1) until slice.size) {
            val rightNum = slice[rightIndex]
            sums.add(leftNum + rightNum)
        }
    }
    return sums.toSet()
}

val testInputDay9 = """
    35
    20
    15
    25
    47
    40
    62
    55
    65
    95
    102
    117
    150
    182
    127
    219
    299
    277
    309
    576
""".trimIndent()