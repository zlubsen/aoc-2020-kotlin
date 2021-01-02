package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day16pt2 : Day16pt1() {
    override fun run() {
        val input = File("src/main/resources/aoc2020/days/day16.txt")
            .readText()
//        val input = testInputDay16pt2
        val blocks = input.split("\n\n")

        val ranges = parseRanges(block = blocks[0])
        val myTicket = parseTicket(block = blocks[1])
        val nearbyTickets = parseNearbyTickets(block = blocks[2])

        val validTickets = nearbyTickets
            .filter { ticket ->
                ticket.fold(true) { ticketAcc, num ->
                    (ranges.fold(false) { acc, rng -> (num in rng) || acc }) && ticketAcc
                }
            }

        val mappedRanges = parseRangesV2(block = blocks[0])

        val positions = solvePositions(ranges = mappedRanges, tickets = validTickets)
        val departureFields = positions
            .filter { it.first.startsWith("departure") }
        val multiplication = departureFields
            .map { myTicket[it.second].toLong() }
            .reduce { a, b -> a * b }
        println(multiplication)
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
        val (from, to) = input.split("-").map { it.toInt() }
        return (from..to)
    }

    private fun solvePositions(ranges: Map<String, List<IntRange>>, tickets : List<List<Int>>) : List<Pair<String,Int>> {
        // bouw een lijst met geldige indices per field (ranges.keys)

        val options = ranges.keys.map { key ->
            val positions = ranges.keys.indices
                .filter { index ->
                    tickets.all { ticket ->
                        ranges[key]!!.any { ticket[index] in it }
                    }
                }
            key to positions.toMutableList()
        }.toMutableList()

        // reduceer de lijst zodat er per field maar 1 indice overblijft
        while (options.any { it.second.size > 1 }) {
            val singles = options.filter { it.second.size == 1 }.flatMap { it.second }
            for ((_, possibleColumns) in options.filter { it.second.size > 1 }) {
                possibleColumns.removeAll(singles)
            }
        }
        return options.map { it.first to it.second.first() }
    }
}

val testInputDay16pt2 = """
    class: 0-1 or 4-19
    row: 0-5 or 8-19
    seat: 0-13 or 16-19

    your ticket:
    11,12,13

    nearby tickets:
    3,9,18
    15,1,5
    5,14,9
""".trimIndent()