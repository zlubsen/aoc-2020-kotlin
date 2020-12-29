package aoc2020.days

import aoc2020.utils.DayExercise

class Day15pt2 : DayExercise {
    var lookup = mutableMapOf<Int, Int>()

    override fun run() {
        val input = listOf(1,0,16,5,17,4)
        val numberOfTurns = 30000000

        val outcome = turnsPt2(input = input, numTurns = numberOfTurns)
        println("Last number spoken is: $outcome")
    }

    fun turnsPt2(input : List<Int>, numTurns : Int) : Int {
        // fill lookup with the input list
        input.forEachIndexed { turn, number -> lookup[number] = turn + 1 }

        // Run through the number of turns
        var lastItem = input.last()
        for (turn in input.size until numTurns) {
            val nextItem = when(lastItem) {
                in lookup -> turn - lookup[lastItem]!!
                else -> 0
            }
            lookup[lastItem] = turn
            lastItem = nextItem
        }

        return lastItem
    }
}

