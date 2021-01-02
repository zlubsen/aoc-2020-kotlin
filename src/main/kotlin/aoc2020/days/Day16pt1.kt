package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

open class Day16pt1 : DayExercise {
    override fun run() {
        val input = File("src/main/resources/aoc2020/days/day16.txt")
            .readText()
//        val input = testInputDay16pt1
        val blocks = input.split("\n\n")

        val ranges = parseRanges(blocks[0])
        val myTicket = parseTicket(blocks[1])
        val nearbyTickets = parseNearbyTickets(blocks[2])

        val numbers = nearbyTickets.fold(mutableListOf<Int>()) { acc, it ->
            acc.addAll(it)
            acc
        }
        val notInRanges = numbers
            .filter { num ->
                !(ranges.fold(false) {acc, rng -> (num in rng) || acc}) }
        println(notInRanges.sum())
    }

    fun parseRanges(block : String) : List<IntRange> {
        return block.split("\n")
            .map { it.split(":")[1] }
            .fold(mutableListOf<String>()) { acc, it ->
                acc.addAll(it.split(" or "))
                acc
            }
            .map { it.trim() }
            .map {
                val nums = it.split("-")
                (nums[0].toInt()..nums[1].toInt())
            }
    }

    fun parseTicket(block : String) : List<Int> {
        return block.split("\n")[1]
            .split(",").map { it.toInt() }
    }

    fun parseNearbyTickets(block : String) : List<List<Int>> {
        return block.split("\n")
            .drop(1)
            .map { line -> line.split(",")
                .map { inner -> inner.toInt() } }
    }
}

val testInputDay16pt1 = """
    class: 1-3 or 5-7
    row: 6-11 or 33-44
    seat: 13-40 or 45-50

    your ticket:
    7,1,14

    nearby tickets:
    7,3,47
    40,4,50
    55,2,20
    38,6,12
""".trimIndent()
