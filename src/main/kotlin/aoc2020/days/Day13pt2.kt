package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day13pt2 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day13.txt")
            .readLines()

        val entries = lines[1].split(",")

        val ids = findIdsAndIndices(entries)
        println(ids)

        // First attempt - brute force
//        println(findEarliestToMatchList(ids, 100000000000000L)) //100000000000000L

        // Second attempt - efficient
        println(findEarliestEfficient(ids))
    }
}

fun findIdsAndIndices(input : List<String>) : List<Pair<Long, Long>> {
    return input.map { if (it == "x") null else it.toLong() }
        .mapIndexed { index, id -> if (id == null) null else id to index.toLong() }
        .filterNotNull()
}

// START OF First attempt - brute force
fun findEarliestToMatchList(ids : List<Pair<Long, Long>>, initial_t : Long) : Long {
    var found = false
    var t = initial_t
    val increment = ids.first().first
    while(!found) {
        var fail = false
        for (bus in ids) {
            if ((t + bus.second) % bus.first != 0L) {
                fail = true
                break;
            }
        }
        if (fail)
            t += increment
        else
            found = true
    }
    return t
}

// END OF First attempt - brute force

// See: https://github.com/jmerle/advent-of-code-2020/blob/master/src/main/kotlin/com/jaspervanmerle/aoc2020/day/Day13.kt
fun findEarliestEfficient(ids: List<Pair<Long, Long>>): Any {
    return ids.fold(0L to 0L) { (currentOffset, multiplier), (busId, busOffset) ->
        if (multiplier == 0L) {
            return@fold 0L to busId
        }

        var newOffset = currentOffset
        while ((newOffset + busOffset) % busId != 0L) {
            newOffset += multiplier
        }
        val acc = newOffset to multiplier * busId
//        println("(${currentOffset}, ${multiplier}), (${busId}, ${busOffset}) -> (${acc.first}, ${acc.second})")
        acc
    }.first
}