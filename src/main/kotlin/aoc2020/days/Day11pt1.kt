package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day11pt1 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day11/Day11pt1.txt")
            .readLines()

//        val lines = testInputDay11pt1.lines()

        val board = parseBoard(lines)
        val stable = iterateToStableBoard(board, ::doRoundPt1)
        val numOccupied = countOccupieds(stable)
        println(numOccupied)
    }
}

fun iterateToStableBoard(board : Array<Array<State>>, iterateFunction : (Array<Array<State>>) -> Array<Array<State>>) : Array<Array<State>> {
    var previousBoard = board
    var counter = 0
    while(true) {
        var newBoard = iterateFunction(previousBoard)
        if (boardsAreEqual(newBoard,previousBoard))
            return newBoard
        previousBoard = newBoard
        counter++
    }
}

fun doRoundPt1(board : Array<Array<State>>) : Array<Array<State>> {
    var newBoard = Array(board.size) { Array(board.size) { State.Empty} }
    for (row in board.indices) {
        for (col in board[row].indices) {
            newBoard[row][col] = when(board[row][col]) {
                State.Empty -> {
                    if (adjacentsOccupied(board, row, col) == 0)
                        State.Occupied
                    else
                        State.Empty
                }
                State.Occupied -> {
                    if (adjacentsOccupied(board, row, col) >= 4)
                        State.Empty
                    else
                        State.Occupied
                }
                State.Floor -> {
                    State.Floor
                }
            }
//            println("($row, $col) : ${board[row][col]} , ${adjacentsOccupied(board, row, col)} > ${newBoard[row][col]}")
        }
    }
    return newBoard
}

fun boardsAreEqual(one : Array<Array<State>>, other : Array<Array<State>>) : Boolean {
    for (row in one.indices) {
        for (col in one[row].indices) {
            val a = one[row][col]
            val b = other[row][col]
            if (a != b)
                return false
        }
    }
    return true
}

fun adjacentsOccupied(board : Array<Array<State>>, row : Int, col : Int) : Int {
    return directions.keys.map { positionIsOccupied(board, row, col, it) }.map { if(it) 1 else 0 }.sum()
}

fun positionIsOccupied(board : Array<Array<State>>, row : Int, col : Int, direction: Direction) : Boolean {
    val deltas = directions[direction]
    val newRow = row + (deltas?.first ?: 0)
    val newCol = col + (deltas?.second ?: 0)

    return if (newRow in board.indices && newCol in board.indices) {
        when (board[newRow][newCol]) {
            State.Occupied -> true
            else -> false
        }
    } else
        false
}

fun countOccupieds(board : Array<Array<State>>) : Int {
    var count = 0
    for (row in board.indices) {
        for (col in board[row].indices) {
            if (board[row][col] == State.Occupied)
                count++
        }
    }
    return count
}

val directions = mapOf(
    Direction.Left to Pair(-1,0),
    Direction.Right to Pair(1,0),
    Direction.Up to Pair(0,-1),
    Direction.Down to Pair(0,1),
    Direction.UpperLeft to Pair(-1,-1),
    Direction.UpperRight to Pair(1,-1),
    Direction.LowerLeft to Pair(-1,1),
    Direction.LowerRight to Pair(1,1),
)

enum class Direction {
    Left,
    Right,
    Up,
    Down,
    UpperLeft,
    UpperRight,
    LowerLeft,
    LowerRight,
}

enum class State {
    Empty,
    Occupied,
    Floor;

    override fun toString() : String {
        return when(this) {
            Empty -> "L"
            Occupied -> "#"
            Floor -> "."
        }
    }
}

fun parseState(input : Char) : State {
    return when(input) {
        'L' -> State.Empty
        '#' -> State.Occupied
        '.' -> State.Floor
        else -> State.Floor
    }
}

fun parseBoard(lines : List<String>) : Array<Array<State>> {
    var board = Array(lines.size) { Array(lines.first().length) { State.Empty} }
    for (row in lines.indices) {
        for(col in lines[row].indices) {
            val cell = parseState(lines[row][col])
            board[row][col] = cell
        }
    }
    return board
}

fun printBoard(board : Array<Array<State>>) {
    for (row in board.indices) {
        for (col in board[row].indices) {
            print(board[row][col])
        }
        println()
    }
    println()
}

val testInputDay11pt1 = """
    L.LL.LL.LL
    LLLLLLL.LL
    L.L.L..L..
    LLLL.LL.LL
    L.LL.LL.LL
    L.LLLLL.LL
    ..L.L.....
    LLLLLLLLLL
    L.LLLLLL.L
    L.LLLLL.LL
""".trimIndent()