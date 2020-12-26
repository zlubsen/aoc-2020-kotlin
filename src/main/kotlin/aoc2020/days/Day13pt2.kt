package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File
import kotlin.math.abs

class Day13pt2 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day13.txt")
            .readLines()

//        val lines = testInputDay13.lines()

        val entries = lines[1].split(",")
        val ids = findIdsAndIndices(entries)
        println(ids)
//        println(findEarliestToMatchList(ids, 100000000000000L)) //100000000000000L
    }
}

fun findIdsAndIndices(input : List<String>) : List<Pair<Int, Int>> {
    val ids = mutableListOf<Pair<Int,Int>>()
    for (idx in input.indices) {
        if (input[idx] != "x")
            ids.add(Pair(input[idx].toInt(), idx))
    }
    return ids
}

fun findEarliestToMatchList(ids : List<Pair<Int, Int>>, initial_t : Long) : Long {
    var found = false
    var t = initial_t
    val increment = ids.first().first
    while(!found) {
        var fail = false
        for (bus in ids) {
            if ((t + bus.second.toLong()) % bus.first.toLong() != 0L) {
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

fun solveChineseRemainderTheorem(ids : List<Pair<Int, Int>>) : Long {
    val b_offsets = ids.map { it.second }
    val bigN = ids.map { it.first }.reduce{ acc, it -> acc * it }
//    val n_coefficients = ids.map { bigN / it.first }
    val x_coefficients = ids.map { solveXi(bigN) }af

    return 0L
}

fun solveXi(a : Int, mod : Int) : Int {
    // solves xi for 'a'*xi ~ 1 (mod 'mod')
    val remainder = a % mod
    val xi = 1 /
}