package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day2pt2 : DayExercise {
    override fun run() {
        val records = File("src/main/resources/aoc2020/days/day2/Day2pt1.txt").readLines()
        val validRecords = records.filter { isValidPassword(it) }.count()
        println(validRecords)
    }

    private fun isValidPassword(record: String): Boolean {
        val regex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")
        return if (regex.matches(record)) {
            val matchResult = regex.matchEntire(record)
            val (posOne, posTwo, char, password) = matchResult!!.destructured
            (charAtPos(password, posOne) == char[0]).xor(charAtPos(password, posTwo) == char[0])
        } else false
    }

    private fun charAtPos(pw: String, pos: String) : Char {
        return pw[pos.toInt()-1]
    }
}