package day6

import getResourceAsStream
import printSolution
import readAsOneLine

/**
 * find the first combination of 4 characters where every character is unique and return the index of the item AFTER said combination
 */
private fun part1(input: String): Any {

    val window = 4
    val result = input.windowed(window).indexOfFirst { string ->
        string.map { s ->
            s to string.count { it == s }
        }.none {
            it.second != 1
        }
    }

    return result + window
}

/**
 * find the first combination of 14 characters where every character is unique and return the index of the item AFTER said combination
 */
private fun part2(input: String): Any {

    val window = 14
    val result = input.windowed(window).indexOfFirst { string ->
        string.map { s ->
            s to string.count { it == s }
        }.none {
            it.second != 1
        }
    }

    return result + window
}

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val input = getResourceAsStream("day6/input.txt").readAsOneLine()

    printSolution(
        part1(input),
        part2(input)
    )
}