package aoc2020.days

import aoc2020.utils.DayExercise
import java.io.File

class Day4pt2 : DayExercise {
    override fun run() {
        val batch = File("src/main/resources/aoc2020/days/day4/Day4pt1.txt")
            .readText()
        val count = countValidRecords(batchInput = batch)
        println("Valid documents found: $count")

//        tests()
    }

    private fun countValidRecords(batchInput : String) : Int {
        return batchInput
            .split("\n\n") // separate records -> List<String>
            .map { it.split("\n", " ") } // split on spaces and newlines -> List<List<String>>
            .map { pairs ->
                run {
                    val map = HashMap<String, String>()
                    pairs.forEach {
                        run {
                            val pair = it.split(":")
                            map[pair[0]] = pair[1]
                        }
                    }
                    map
                }
            } // each record's items to hashmap
            .map { isValidRecord(it) }
            .filter { it }
            .count()
    }

    fun isValidRecord(record: HashMap<String, String>) : Boolean {
        return hasValidBYR(record) &&
                hasValidECL(record) &&
                hasValidEYR(record) &&
                hasValidHCL(record) &&
                hasValidHGT(record) &&
                hasValidIYR(record) &&
                hasValidPID(record)
    }

    fun hasValidBYR(items: HashMap<String, String>) : Boolean {
        return if(items.containsKey("byr")) {
            return items["byr"]!!.toInt() in 1920..2002
        } else
            false
    }

    fun hasValidIYR(items: HashMap<String, String>) : Boolean {
        return if(items.containsKey("iyr")) {
            return items["iyr"]!!.toInt() in 2010..2020
        } else
            false
    }

    fun hasValidEYR(items: HashMap<String, String>) : Boolean {
        return if(items.containsKey("eyr")) {
            return items["eyr"]!!.toInt() in 2020..2030
        } else
            false
    }

    fun hasValidHGT(items: HashMap<String, String>) : Boolean {
        val regex = Regex("""(\d+)(cm|in)""")
        return if(items.containsKey("hgt")) {
            val hgt = items["hgt"]!!
            if (regex.matches(hgt)) {
                val matchResult = regex.matchEntire(hgt)
                val (value, unit) = matchResult!!.destructured
                when (unit) {
                    "cm" -> value.toInt() in 150..193
                    "in" -> value.toInt() in 59..76
                    else -> false
                }
            } else
                false
        } else
            false
    }

    fun hasValidHCL(items: HashMap<String, String>) : Boolean {
        val regex = Regex("""#([0-9a-f]{6})""")
        return if(items.containsKey("hcl")) {
            val hcl = items["hcl"]!!
            return regex.matches(hcl)
        } else false
    }

    fun hasValidECL(items: HashMap<String, String>) : Boolean {
        val colors = listOf("amb","blu","brn","gry","grn","hzl","oth")
        return if (items.containsKey("ecl")){
            colors.contains(items["ecl"])
        } else false
    }

    fun hasValidPID(items: HashMap<String, String>) : Boolean {
        val regex = Regex("""([0-9]{9})""")
        return if (items.containsKey("pid")) {
            regex.matches(items["pid"]!!)
        } else false
    }

    private fun tests() {
        val testInvalidPPs = """
            eyr:1972 cid:100
            hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

            iyr:2019
            hcl:#602927 eyr:1967 hgt:170cm
            ecl:grn pid:012533040 byr:1946

            hcl:dab227 iyr:2012
            ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

            hgt:59cm ecl:zzz
            eyr:2038 hcl:74454a iyr:2023
            pid:3556412378 byr:2007
        """.trimIndent()
        val nonvalidTest = countValidRecords(batchInput = testInvalidPPs)
        println("nonvalid tested (0): $nonvalidTest")

        val testValidPPs = """
            pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
            hcl:#623a2f

            eyr:2029 ecl:blu cid:129 byr:1989
            iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

            hcl:#888785
            hgt:164cm byr:2001 iyr:2015 cid:88
            pid:545766238 ecl:hzl
            eyr:2022

            iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
        """.trimIndent()
        val validTest = countValidRecords(batchInput = testValidPPs)
        println("valid tested (4): $validTest")
    }
}