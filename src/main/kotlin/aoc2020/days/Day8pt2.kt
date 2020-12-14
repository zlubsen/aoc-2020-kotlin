package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day8pt2 : DayExercise {
    override fun run() {
        val instructions = File("src/main/resources/aoc2020/days/day8/Day8pt1.txt")
            .readLines().map { parseInstruction(it) }

        for (index in 0..instructions.lastIndex) {
            var newProgram = ArrayList(instructions.map{it.copy()})

            when(instructions[index].op) {
                Operation.acc -> {}
                Operation.jmp -> newProgram[index].op = Operation.nop
                Operation.nop -> newProgram[index].op = Operation.jmp
            }

            val proc = Processor(instructions = newProgram)
            val result = proc.run()

            when(proc.state) {
                ProgramState.Finished -> {
                    println("${proc.state} with value $result")
                    break
                }
                else -> {}
            }
        }
    }
}