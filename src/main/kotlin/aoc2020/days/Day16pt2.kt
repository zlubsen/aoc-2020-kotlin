package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day16pt2 : Day16pt1() {
    override fun run() {
//        val input = File("src/main/resources/aoc2020/days/day16.txt")
//            .readText()
        val input = testInputDay16pt1
        val blocks = input.split("\n\n")

        val ranges = parseRanges(blocks[0])
        val myTicket = parseTicket(blocks[1])
        val nearbyTickets = parseNearbyTickets(blocks[2])

        val validTickets = nearbyTickets
            .filter { ticket ->
                ticket.fold(true) { ticketAcc, num ->
                    (ranges.fold(false) { acc, rng -> (num in rng) || acc }) && ticketAcc
                }
            }
        println(validTickets)

        val mappedRanges = parseRangesV2(blocks[0])
        println(mappedRanges)
    }

    private fun parseRangesV2(block: String): Map<String, List<IntRange>> {
        return block.split("\n")
            .map { it.split(":") }
            .fold(mutableMapOf<String, List<IntRange>>()) { lineAcc, line ->
                lineAcc[line[0]] = line[1].trim().split(" or ").map { parseSingleRange(it) }
                lineAcc
            }
    }

    private fun parseSingleRange(input : String) : IntRange {
        val nums = input.split("-")
        return (nums[0].toInt()..nums[1].toInt())
    }
}

