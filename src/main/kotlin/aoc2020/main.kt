package aoc2020

import com.xenomachina.argparser.ArgParser
import aoc2020.utils.CliArguments
import aoc2020.utils.dayTypeToObject
import aoc2020.utils.parseDay

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::CliArguments).run {
        val start = System.currentTimeMillis()
        dayTypeToObject(parseDay(dayPart)).run()
        val stop = System.currentTimeMillis()
        val time = stop - start

        println("Run took $time ms")
    }
}