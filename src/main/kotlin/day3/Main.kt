package day3

import getResourceAsStream
import readAsOneLine

data class Item(val allContents: String) {

    val firstItem = allContents.substring(startIndex = 0, endIndex = allContents.length / 2)
    val secondItem = allContents.substring(startIndex = allContents.length / 2, endIndex = allContents.length)

}

/**
 * turn characters 'a'..'z' into 1..26 and 'A'..'Z' into 27..52
 */
fun getItemValue(item: Char): Int {
    return if (item.isLowerCase()) {
        item.code - 96 //'a' is code 97
    } else {
        item.code - 38 //'A' is code 65
    }
}

fun part1(items: List<Item>): Any {

    val res = items.mapNotNull { pair ->
        pair.firstItem.find { pair.secondItem.contains(it) }
    }.sumOf {
        getItemValue(it)
    }

    return res
}


fun part2(items: List<Item>): Any {
    val res = items.windowed(size = 3, step = 3) { item ->
        item[0].allContents.find {
            item[1].allContents.contains(it) && item[2].allContents.contains(it)
        }
    }.filterNotNull().sumOf {
        getItemValue(it)
    }

    return res
}

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val input = getResourceAsStream("day3/input.txt").readAsOneLine()
    val items = input.split("\r\n").map {
        Item(it)
    }

    println(
        """

       Solution for Part 1: ${part1(items)}
       Solution for Part 2: ${part2(items)}

    """.trimIndent()
    )
}