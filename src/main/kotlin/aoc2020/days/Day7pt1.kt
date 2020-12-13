package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day7pt1 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day7/Day7pt1.txt")
            .readLines()
        val rules = lines.map { parseRule(it) }.associateBy { it.color }

        val count = rules.values
            .count { it.contains(targetColor = "shiny gold", rules = rules) }
        println(count)
    }
}

data class BagRule(val color: String, val containment:Map<String, Int>) {
    fun contains(targetColor:String, rules: Map<String, BagRule>) : Boolean {
        return containment.keys
            .any { it == targetColor || rules.getValue(it).contains(targetColor, rules) }
    }

    fun countContaining(rules: Map<String, BagRule>) : Int {
        return containment.entries.sumBy { it.value + rules.getValue(it.key).countContaining(rules) * it.value }
    }
}

fun parseRule(rule : String) : BagRule {
    val color = rule.substringBefore(" bags")
    val contents = rule.substringAfter("contain ")
        .substringBefore(".")
        .split(", ")
        .filter { it != "no other bags" }
        .map {
            val parts = it.split(" ")
            "${parts[1]} ${parts[2]}" to parts[0].toInt()
        }
        .toMap()
    return BagRule(color = color, containment = contents)

    //    val regex = Regex("""([a-z ]+) bags contain (((\d+) ([a-z ]+) bag(s)?(,|.)( )?)+|no other bags.)""")
    // (?<type>[a-z ]+) bags contain ((?<rule>(?<amount>\d+) (?<rulename>[a-z ]+) bag(?:s)?(?:, |\.))+|(?<none>no other bags).)
    //    val matches = regex.matchEntire(rules)
    //    matches!!.groupValues.forEach { println(it) }
    //    println("match: ${matches!!.groupValues}")
}