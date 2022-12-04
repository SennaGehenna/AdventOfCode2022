package day1

import getResourceAsStream
import printSolution
import readAsOneLine

private class Elf private constructor(val totalCalories: Int) {

    companion object {
        fun makeElf(input: String): Elf {
            return Elf(input.split("\r\n").sumOf { it.toInt() })
        }
    }

    override fun toString(): String {
        return "Elf(totalCalories=$totalCalories"
    }
}


/**
 * find the elf with the highest total calories
 */
private fun part1(input: List<Elf>) = input.maxBy { it.totalCalories }

/**
 * find the 3 elfs with the highest total calories
 */
private fun part2(input: List<Elf>) = input.sortedByDescending { it.totalCalories }.take(3).sumOf { it.totalCalories }


fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val elves = getResourceAsStream("day1/input.txt")
        .readAsOneLine()
        .split("\r\n\r\n")
        .filterNot { it.isEmpty() }
        .map { Elf.makeElf(it) }


    printSolution(
        part1(elves).totalCalories,
        part2(elves)
    )
}