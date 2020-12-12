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
    }
}