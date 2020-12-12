package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day4pt1 : DayExercise {
    private val criteria = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    override fun run() {
        val batch = File("src/main/resources/aoc2020/days/day4/Day4pt1.txt")
            .readText() // read file contents -> String
        val count = countValids(criteria = criteria, batchInput = batch)

        println("Valid documents found: $count")
    }
}

fun countValids(criteria: List<String>, batchInput : String) : Int {
    return batchInput
        .split("\n\n") // separate records -> List<String>
        .map { it.split("\n", " ") } // split on spaces and newlines -> List<List<String>>
        .map { pairs ->
            run {
                val map = HashMap<String, String>()
                pairs.forEach {
                    run {
                        val pair = it.split(":")
                        map[pair[0]] = pair[1]
                    }
                }
                map
            }
        } // each record's items to hashmap
        .map { isValidPPorNPC(criteria, it) }
        .filter { it }
        .count()
}

fun isValidPPorNPC(criteria : List<String>, items: HashMap<String, String>) : Boolean {
    return criteria.map { items.containsKey(it) }.reduce { acc, b -> acc && b }
}