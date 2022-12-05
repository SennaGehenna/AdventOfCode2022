package day4

import getResourceAsStream

import readAsOneLine

private typealias Section = IntRange

private class SectionPair private constructor(val firstSection: Section, val secondSection: Section) {

    companion object {
        fun makeSectionPair(input: String): SectionPair {

            //2-4,6-8

            val (first, second) = input.split(",").map {
                it.split("-")
            }.map {
                it[0].toInt()..it[1].toInt()
            }
            return SectionPair(first, second)
        }
    }
}

/**
 * count all sectionPairs where one pair fully contains the other
 */
private fun part1(input: List<SectionPair>): Any {
    return input.count { pair: SectionPair ->
        with(pair) {
            val intersect = firstSection.intersect(secondSection)
            val intersect1 = secondSection.intersect(firstSection)
            intersect == secondSection.toSet() || intersect1 == firstSection.toSet()
        }
    }
}

/**
 * count all sectionPairs where the sections overlap atleast partially
 */
private fun part2(input: List<SectionPair>): Any {
    return input.count { pair: SectionPair ->
        with(pair) {
            firstSection.intersect(secondSection).isNotEmpty() || secondSection.intersect(firstSection).isNotEmpty()
        }
    }
}

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val input = getResourceAsStream("day4/input.txt").readAsOneLine()
    val sectionPairs = input.split("\r\n").map {
        SectionPair.makeSectionPair(it)
    }

    println(
        """

       Solution for Part 1: ${part1(sectionPairs)}
       Solution for Part 2: ${part2(sectionPairs)}

    """.trimIndent()
    )

//    printSolution(
//        part1(sectionPairs),
//        part2(sectionPairs)
//    )
}

