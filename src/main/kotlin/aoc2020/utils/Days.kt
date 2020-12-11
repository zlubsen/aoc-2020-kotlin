package aoc2020.utils

import aoc2020.days.UnknownDay
import aoc2020.days.day1.Day1pt1
import aoc2020.days.day1.Day1pt2
import aoc2020.days.day1.Day2pt1
import aoc2020.days.day1.Day2pt2

enum class Days {
    Unknown,
    Day1pt1,
    Day1pt2,
    Day2pt1,
    Day2pt2,
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
    }
}