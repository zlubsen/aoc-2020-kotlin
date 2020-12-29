package aoc2020.utils

import aoc2020.days.*

enum class Days {
    Unknown,
    Day1pt1,
    Day1pt2,
    Day2pt1,
    Day2pt2,
    Day3pt1,
    Day3pt2,
    Day4pt1,
    Day4pt2,
    Day5pt1,
    Day5pt2,
    Day6pt1,
    Day6pt2,
    Day7pt1,
    Day7pt2,
    Day8pt1,
    Day8pt2,
    Day9pt1,
    Day9pt2,
    Day10pt1,
    Day10pt2,
    Day11pt1,
    Day11pt2,
    Day12pt1,
    Day12pt2,
    Day13pt1,
    Day13pt2,
    Day14pt1,
    Day14pt2,
    Day15pt1,
    Day15pt2,
}

fun parseDay(input : String) : Days {
    return if (enumValues<Days>().map { it.name }.contains(input))
        Days.valueOf(input)
    else
        Days.Unknown
}

fun dayTypeToObject(type: Days) : DayExercise {
    return when(type) {
        Days.Unknown -> UnknownDay()
        Days.Day1pt1 -> Day1pt1()
        Days.Day1pt2 -> Day1pt2()
        Days.Day2pt1 -> Day2pt1()
        Days.Day2pt2 -> Day2pt2()
        Days.Day3pt1 -> Day3pt1()
        Days.Day3pt2 -> Day3pt2()
        Days.Day4pt1 -> Day4pt1()
        Days.Day4pt2 -> Day4pt2()
        Days.Day5pt1 -> Day5pt1()
        Days.Day5pt2 -> Day5pt2()
        Days.Day6pt1 -> Day6pt1()
        Days.Day6pt2 -> Day6pt2()
        Days.Day7pt1 -> Day7pt1()
        Days.Day7pt2 -> Day7pt2()
        Days.Day8pt1 -> Day8pt1()
        Days.Day8pt2 -> Day8pt2()
        Days.Day9pt1 -> Day9pt1()
        Days.Day9pt2 -> Day9pt2()
        Days.Day10pt1 -> Day10pt1()
        Days.Day10pt2 -> Day10pt2()
        Days.Day11pt1 -> Day11pt1()
        Days.Day11pt2 -> Day11pt2()
        Days.Day12pt1 -> Day12pt1()
        Days.Day12pt2 -> Day12pt2()
        Days.Day13pt1 -> Day13pt1()
        Days.Day13pt2 -> Day13pt2()
        Days.Day14pt1 -> Day14pt1()
        Days.Day14pt2 -> Day14pt2()
        Days.Day15pt1 -> Day15pt1()
        Days.Day15pt2 -> Day15pt2()
    }
}