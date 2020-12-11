package aoc2020.utils

import com.xenomachina.argparser.ArgParser

class CliArguments(parser:ArgParser) {
    val dayPart by parser.storing("-D", "--day", help = "The day exercise to run in format 'dayXptY")
}