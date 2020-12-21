package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day11pt2 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day11/Day11pt1.txt")
            .readLines()

//        val linesTest1 = testInputDay11pt2SeeEightOccupied.lines()
//        val testBoard1 = parseBoard(linesTest1)
//        println(canSeeOccupieds(testBoard1, 4, 3))
//        val linesTest2 = testInputDay11pt2SeeOneEmpty.lines()
//        val testBoard2 = parseBoard(linesTest2)
//        println(canSeeOccupieds(testBoard2, 1, 1))
//        val linesTest3 = testInputDay11pt2SeeNoOccupied.lines()
//        val testBoard3 = parseBoard(linesTest3)
//        println(canSeeOccupieds(testBoard3, 3, 3))

        val board = parseBoard(lines)
        val stable = iterateToStableBoard(board, ::doRoundPt2)
        val numOccupied = countOccupieds(stable)
        println(numOccupied)
    }
}

fun doRoundPt2(board : Array<Array<State>>) : Array<Array<State>> {
    var newBoard = Array(board.size) { Array(board.size) { State.Empty} }
    for (row in board.indices) {
        for (col in board[row].indices) {
            newBoard[row][col] = when(board[row][col]) {
                State.Empty -> {
                    if (canSeeOccupieds(board, row, col) == 0)
                        State.Occupied
                    else
                        State.Empty
                }
                State.Occupied -> {
                    if (canSeeOccupieds(board, row, col) >= 5)
                        State.Empty
                    else
                        State.Occupied
                }
                State.Floor -> State.Floor
            }
        }
    }
    return newBoard
}

fun canSeeOccupieds(board : Array<Array<State>>, row : Int, col : Int) : Int {
    return directions.keys.map { lineOfSightIsFree(board, row, col, it) }.map { if(it) 0 else 1 }.sum()
}

fun lineOfSightIsFree(board : Array<Array<State>>, row : Int, col : Int, direction: Direction) : Boolean {
    val deltas = directions[direction]
    val newRow = row + (deltas?.first ?: 0)
    val newCol = col + (deltas?.second ?: 0)

    return if (newRow in board.indices && newCol in board.indices) {
        when(board[newRow][newCol]) {
            State.Occupied -> false
            State.Empty -> true
            State.Floor -> lineOfSightIsFree(board, newRow, newCol, direction)
        }
    } else true
}

val testInputDay11pt2SeeEightOccupied = """
    .......#.
    ...#.....
    .#.......
    .........
    ..#L....#
    ....#....
    .........
    #........
    ...#.....
""".trimIndent()

val testInputDay11pt2SeeOneEmpty = """
    .............
    .L.L.#.#.#.#.
    .............
""".trimIndent()

val testInputDay11pt2SeeNoOccupied = """
    .##.##.
    #.#.#.#
    ##...##
    ...L...
    ##...##
    #.#.#.#
    .##.##.
""".trimIndent()