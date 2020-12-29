package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day15pt1 : DayExercise {
    override fun run() {
        val input = listOf(1,0,16,5,17,4)

        val outcome = turns(input = input, numTurns = 2020)
        println("Last number spoken is: ${outcome.last()}")
    }
}

fun turns(input : List<Int>, numTurns : Int) : List<Int> {
    return (input.size+1..numTurns).fold(input) { acc, _ -> playTurn(acc) }
}

fun playTurn(numbers : List<Int>) : List<Int> {
    val last = numbers.last()

    return when(numbers.count { it == last }) {
        1 -> numbers + 0
        else -> {
            val sublist = numbers.subList(0,(numbers.lastIndex))
            val previousIndex = sublist.lastIndexOf(last)
            numbers + (numbers.lastIndex - previousIndex)
        }
    }
}