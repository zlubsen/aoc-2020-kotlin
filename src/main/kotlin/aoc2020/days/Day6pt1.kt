package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day6pt1 : DayExercise {
    override fun run() {
        val batch = File("src/main/resources/aoc2020/days/day6/Day6pt1.txt")
            .readText() // read file contents -> String
        val sum = countsPerGroup(batch).reduce { acc, i -> acc + i }
        println("Total sum: $sum")
    }
}

fun countsPerGroup(input : String) : List<Int> {
    return input
        .split("\n\n") // separate groups -> List<String>
//        .asSequence()
        .map { it.split("\n") } // split on newlines -> List<List<String>>
        .map { groupEntries -> run {
            groupEntriesToSet(groupEntries)
        } }
        .map { it.size }
}

fun groupEntriesToSet(entries: List<String>) : HashSet<Char> {
    val set = HashSet<Char>()
    entries.forEach { entry ->
        set.addAll(entry.asIterable())
    }
    return set
}