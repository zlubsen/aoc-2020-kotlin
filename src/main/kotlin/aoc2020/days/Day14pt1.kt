package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day14pt1 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day14.txt")
            .readLines()

        val operations = parseDockOperations(lines)

        val computer = DockComputer(operations = operations)
        computer.run()
        println(computer.sumOfMem())
    }
}

fun parseDockOperations(input : List<String>) : List<DockOperation> {
    return input.map {
        when(it.substring(0..2)) {
            "mas" -> {
                SetMask(it.substring(7..it.lastIndex))
            }
            "mem" -> {
                val address = it.substring((it.indexOf('[') + 1) until it.indexOf(']')).toLong()
                val arg = it.substring((it.indexOf('=') + 2)..it.lastIndex).toLong()
                WriteMem(address = address, argument = arg)
            }
            else -> InvalidOp
        }
    }
}

sealed class DockOperation {
}

class SetMask(val mask: String) : DockOperation() {
    override fun toString(): String {
        return "mask = $mask"
    }
}

class WriteMem(val address : Long, val argument : Long) : DockOperation() {
    override fun toString(): String {
        return "mem[$address] = $argument"
    }
}

object InvalidOp : DockOperation() {
}

open class DockComputer(protected val operations: List<DockOperation>) {
    protected val mem = mutableMapOf<Long, Long>()
    protected var mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

    open fun run() {
        operations.forEach { operation ->
            when(operation) {
                is SetMask -> setMask(operation = operation)
                is WriteMem -> writeToMem(operation = operation)
                InvalidOp -> { }
            }
        }
    }

    fun setMask(operation : SetMask) {
        mask = operation.mask
    }

    open fun writeToMem(operation : WriteMem) {
        mem[operation.address] = applyMask(value = operation.argument, mask = mask)
    }

    protected fun applyMask(value : Long, mask: String) : Long {
        val binaryValue = valueToBitString(value)
        return mask.indices.map {
            if (mask[it] == 'X' )
                binaryValue[it]
            else
                mask[it]
        }.joinToString("")
            .toLong(2)
    }

    fun valueToBitString(value : Long) : String {
        return value.toString(2).padStart(mask.length,'0')
    }

    fun sumOfMem() : Long {
        return mem.values.sum()
    }
}
