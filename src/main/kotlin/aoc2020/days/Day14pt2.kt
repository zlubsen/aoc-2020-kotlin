package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day14pt2 : DayExercise {
    override fun run() {
        val lines = File("src/main/resources/aoc2020/days/day14.txt")
            .readLines()

        val operations = parseDockOperations(lines)
//        val operations = parseDockOperations(listOf("mask = 000000000000000000000000000000X1001X","mem[42] = 100","mask = 00000000000000000000000000000000X0XX","mem[26] = 1"))

        val computerV2 = DockComputerV2(operations)
        computerV2.run()
        println("Sum of memory values: ${computerV2.sumOfMem()}")
    }
}

class DockComputerV2(ops : List<DockOperation>) : DockComputer(operations = ops) {
    override fun run() {
        operations.forEach { operation ->
            when(operation) {
                is SetMask -> setMask(operation = operation)
                is WriteMem -> writeToMem(operation = operation)
                InvalidOp -> { }
            }
        }
    }

    override fun writeToMem(operation : WriteMem) {
        val addresses = findAddresses(value = operation.address, mask = mask)
        addresses.forEach { mem[it] = operation.argument }
//        mem[operation.address] = applyMask(value = operation.argument, mask = mask)
    }

    fun findAddresses(value : Long, mask : String) : List<Long> {
        val address = applyMaskV2(value = value, mask = mask)
        return generateAddress(address = address).map { it.toLong(2) }
    }

    fun generateAddress(address : String) : MutableList<String> {
        if (address.count { it == 'X' } == 0)
            return mutableListOf(address)

        val it = listOf('0','1').map { address.replaceFirst('X',it) }
            .fold(mutableListOf<String>()) { acc, s ->
                acc.addAll(generateAddress(s))
                acc
            }
        return it
    }

    fun applyMaskV2(value : Long, mask: String) : String {
        val binaryValue = valueToBitString(value)
        return mask.indices.map {
            when(mask[it]) {
                'X' -> 'X'
                '1' -> mask[it]
                else -> binaryValue[it]
            }
        }.joinToString("")
    }
}