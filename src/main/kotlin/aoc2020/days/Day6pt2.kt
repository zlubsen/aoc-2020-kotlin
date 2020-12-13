package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day6pt2 : DayExercise {
    override fun run() {
        val batch = File("src/main/resources/aoc2020/days/day6/Day6pt1.txt")
            .readText() // read file contents -> String
        val sum = setCountsPerGroup(batch)
            .map { it.count() }
            .reduce { acc, i -> acc + i }
        println("Total sum: $sum")
    }
}

fun setCountsPerGroup(input: String) : List<Set<Char>> {
    return input
        .split("\n\n") // separate groups -> List<String>
        .map { it.split("\n") } // entries per group -> List<List<String>>
        .map { groupEntries -> run {
            setsPerGroup(groupEntries)
        } } // set per entry per group -> List<List<Set<Char>>>
        .map { groupIntersection(it) } // intersection of set per group -> List<Set<Char>>
}

fun setsPerGroup(groupEntries: List<String>) : List<Set<Char>> {
    return groupEntries.map { it.toSet() }
}

fun groupIntersection(sets: List<Set<Char>>) : Set<Char> {
    return sets.fold(sets.first()) { acc, it -> acc.intersect(it) }
}