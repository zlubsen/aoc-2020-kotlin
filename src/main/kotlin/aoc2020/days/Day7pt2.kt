package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day7pt2 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day7/Day7pt1.txt")
            .readLines()
//        val lines = testInput.split("\n")
        val rules = lines.map { parseRule(it) }.associateBy { it.color }

        val sum = rules.getValue("shiny gold").countContaining(rules)
        println(sum)
    }
}

val testInput = """
    shiny gold bags contain 2 dark red bags.
    dark red bags contain 2 dark orange bags.
    dark orange bags contain 2 dark yellow bags.
    dark yellow bags contain 2 dark green bags.
    dark green bags contain 2 dark blue bags.
    dark blue bags contain 2 dark violet bags.
    dark violet bags contain no other bags.
""".trimIndent()