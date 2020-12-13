package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day8pt1 : DayExercise {
    override fun run() {
        val instructions = File("src/main/resources/aoc2020/days/day8/Day8pt1.txt")
            .readLines()
//        val instructions = testProgram.lines()

        val proc = Processor(instructions = instructions.map { parseInstruction(it) })
        val result = proc.run()
        println(result)
    }
}

fun parseInstruction(line : String) : Instruction {
    val parts = line.split(" ")
    return Instruction(Operation.valueOf(parts[0]), parts[1].toInt())
}

class Processor(val instructions : List<Instruction>) {
    private var accu : Int = 0
    private var pc : Int = 0

    private var history = hashSetOf<Int>()
//    private var hold = false

    fun run() : Int {
        while (true) {
            if (history.contains(pc))
                break
            history.add(pc)
            val inst = instructions[pc]
//            println("op: ${inst.op} arg: ${inst.arg}")
            when(inst.op) {
                Operation.acc -> {
                    accu += inst.arg
                    pc += 1
                }
                Operation.jmp -> { pc += inst.arg }
                Operation.nop -> { pc += 1 }
            }
//            println("pc: $pc accu: $accu")
        }
        return accu
    }
}

data class Instruction(val op: Operation, val arg: Int)

enum class Operation {
    acc,
    jmp,
    nop
}

val testProgram = """
    nop +0
    acc +1
    jmp +4
    acc +3
    jmp -3
    acc -99
    acc +1
    jmp -4
    acc +6
""".trimIndent()