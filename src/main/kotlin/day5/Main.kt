package day5

import getResourceAsStream
import printSolution
import readAsOneLine
import java.util.*

private class Command(val amount: Int, val from: String, val to: String) {


    companion object {

        fun makeCommand(command: String): Command {
            val (_, amount, from, to) = command.split(Regex("\\D+"))
            return Command(amount.toInt(), from, to)
        }
    }

    override fun toString(): String {
        return "Command(amount=$amount, from='$from', to='$to')"
    }


}

private fun buildStack(stackString: List<String>): Map<String, Stack<String>> {
    val stacks = buildMap {
        stackString.first().split(Regex(" +")).filterNot { it.isEmpty() }.forEach {
            put(it, Stack<String>())
        }
    }


    stackString.drop(1).map {
        it.chunked(4)
    }.forEach { strings ->
        strings.forEachIndexed { index, s ->
            if (s.isNotEmpty() && s.isNotBlank())
                stacks[(index + 1).toString()]!!.push(s.replace("[", "").replace("]", "").replace(" ", ""))
        }
    }
    return stacks
}

/**
 * move items from one stack to the other one by one
 */
private fun part1(stackString: List<String>, commands: List<Command>): Any {

    val stacks = buildStack(stackString)

    //move 1 from 2 to 1
    commands.forEach {
        repeat(it.amount) { _ ->
            stacks[it.to]!!.push(
                stacks[it.from]!!.pop()
            )
        }
    }

    return stacks.map {
        it.value.peek()
    }.joinToString(separator = "")

}

/**
 * move multiple items from one stack to the other
 */
private fun part2(stackString: List<String>, commands: List<Command>): Any {

    val stacks = buildStack(stackString)

    //move 3 from 2 to 1
    commands.forEach {

        val queue = mutableListOf<String>()
        repeat(it.amount) { _ ->
            queue += stacks[it.from]!!.pop()
        }
        queue.reversed().forEach { items ->
            stacks[it.to]!!.push(items)
        }
    }

    return stacks.map {
        it.value.peek()
    }.joinToString(separator = "")

}

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val input = getResourceAsStream("day5/input.txt").readAsOneLine()

    val doubleNewLine = "\r\n\r\n"
    val stackString = input.substring(0, input.indexOf(doubleNewLine)).split("\r\n").asReversed()
    val commandsString = input.substring(input.indexOf("move"))


    val commands = commandsString.split("\r\n").map {
        Command.makeCommand(it)
    }

    printSolution(part1(stackString, commands),
            part2(stackString, commands))
}

