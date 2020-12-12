package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day2pt1 : DayExercise {
    override fun run() {
        val records = File("src/main/resources/aoc2020/days/day2/Day2pt1.txt").readLines()
        val validRecords = records.filter { isValidPassword(it) }.count()
        println(validRecords)
    }

    private fun isValidPassword(record : String) : Boolean {
        val regex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")
        return if (regex.matches(record)) {
            val matchResult = regex.matchEntire(record)
            val (min, max, char, password) = matchResult!!.destructured
            val occurrences = password.count { it == char[0] }
            (occurrences in min.toInt()..max.toInt())
        } else false
    }
}