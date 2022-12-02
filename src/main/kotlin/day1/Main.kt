package day1

import getResourceAsStream
import readAsOneLine

data class Elf(val totalCalories: Int) {

    companion object {
        fun makeElf(input: String): Elf {
            return Elf(input.split("\r\n").sumOf { it.toInt() })
        }
    }
}


fun part1(input: List<Elf>) = input.maxBy { it.totalCalories }

fun part2(input: List<Elf>) = input.sortedByDescending { it.totalCalories }.take(3).sumOf { it.totalCalories }


fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val elves = getResourceAsStream("day1/input.txt")
        .readAsOneLine()
        .split("\r\n\r\n")
        .filterNot { it.isEmpty() }
        .map { Elf.makeElf(it) }


    println(
        """

       Solution for Part 1: ${part1(elves).totalCalories}
       Solution for Part 2: ${part2(elves)}

    """.trimIndent()
    )
}