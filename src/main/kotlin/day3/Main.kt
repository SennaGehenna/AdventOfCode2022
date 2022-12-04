package day3

import getResourceAsStream
import printSolution
import readAsOneLine

private data class Rucksack(val allContents: String) {

    val leftCompartment = allContents.substring(startIndex = 0, endIndex = allContents.length / 2)
    val rightCompartment = allContents.substring(startIndex = allContents.length / 2, endIndex = allContents.length)

}

/**
 * turn characters 'a'..'z' into 1..26 and 'A'..'Z' into 27..52
 */
private fun getItemValue(item: Char): Int {
    return if (item.isLowerCase()) {
        item.code - 96 //'a' is code 97
    } else {
        item.code - 38 //'A' is code 65
    }
}

/**
Day 1: find the item present in both compartments of a rucksack and return that items value
 */
private fun part1(rucksacks: List<Rucksack>): Any {

    val res = rucksacks.mapNotNull { rucksack ->
        rucksack.leftCompartment.find { rucksack.rightCompartment.contains(it) }
    }.sumOf {
        getItemValue(it)
    }

    return res
}

/**
 * group the rucksacks into groups of 3, find the item present in all 3 rucksacks and return that items value
 */
private fun part2(rucksacks: List<Rucksack>): Any {
    val res = rucksacks.windowed(size = 3, step = 3) { rucksack ->
        rucksack[0].allContents.find {
            rucksack[1].allContents.contains(it) && rucksack[2].allContents.contains(it)
        }
    }.filterNotNull().sumOf {
        getItemValue(it)
    }

    return res
}

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val input = getResourceAsStream("day3/input.txt").readAsOneLine()
    val rucksacks = input.split("\r\n").map {
        Rucksack(it)
    }

    printSolution(
        part1(rucksacks),
        part2(rucksacks)
    )
}