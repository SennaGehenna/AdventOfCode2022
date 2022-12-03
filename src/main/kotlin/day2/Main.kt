package day2

import getResourceAsStream
import readAsOneLine

enum class ThrowType(val throwValue: Int) {
    Rock(1),
    Paper(2),
    Scissors(3);

    fun toOutcomePart2(): Outcome {
        return when (this) {
            Rock -> Outcome.Loss
            Paper -> Outcome.Draw
            Scissors -> Outcome.Win
        }
    }

    companion object {

        fun determineRequiredResult(thro: Throw): Int {
            return with(thro) {
                val desiredOutcome = myThrow.toOutcomePart2()
                val myActualThrow = when (yourThrow) {
                    Rock -> when (desiredOutcome) {
                        Outcome.Loss -> Scissors
                        Outcome.Draw -> Rock
                        Outcome.Win -> Paper
                    }

                    Paper -> when (desiredOutcome) {
                        Outcome.Loss -> Rock
                        Outcome.Draw -> Paper
                        Outcome.Win -> Scissors
                    }

                    Scissors -> when (desiredOutcome) {
                        Outcome.Loss -> Paper
                        Outcome.Draw -> Scissors
                        Outcome.Win -> Rock
                    }
                }
                myActualThrow.throwValue + desiredOutcome.points
            }
        }
    }
}

enum class Outcome(val points: Int) {
    Loss(0), Draw(3), Win(6);


    companion object {
        fun determineOutcome(yourThrow: ThrowType, myThrow: ThrowType): Outcome {
            return when (yourThrow) {
                ThrowType.Rock -> when (myThrow) {
                    ThrowType.Rock -> Draw
                    ThrowType.Paper -> Win
                    ThrowType.Scissors -> Loss
                }

                ThrowType.Paper -> when (myThrow) {
                    ThrowType.Rock -> Loss
                    ThrowType.Paper -> Draw
                    ThrowType.Scissors -> Win
                }

                ThrowType.Scissors -> when (myThrow) {
                    ThrowType.Rock -> Win
                    ThrowType.Paper -> Loss
                    ThrowType.Scissors -> Draw
                }
            }
        }
    }
}

class Throw private constructor(val yourThrow: ThrowType, val myThrow: ThrowType) {

    val outcome: Outcome = Outcome.determineOutcome(yourThrow, myThrow)

    companion object {
        fun makeThrow(input: String): Throw {
            val (you, me) = input.split(" ")
            val yourThrow = when (you) {
                "A" -> ThrowType.Rock
                "B" -> ThrowType.Paper
                "C" -> ThrowType.Scissors
                else -> throw IllegalArgumentException("Found illegal input argument for 'yourThrow' = \"$you\"")
            }
            val myThrow = when (me) {
                "X" -> ThrowType.Rock
                "Y" -> ThrowType.Paper
                "Z" -> ThrowType.Scissors
                else -> throw IllegalArgumentException("Found illegal input argument for 'myThrow' = \"$me\"")
            }

            return Throw(yourThrow, myThrow)
        }
    }

    override fun toString(): String {
        return "Throw(yourThrow=$yourThrow,myThrow=$myThrow,outcome=$outcome)"
    }
}

fun part1(throws: List<Throw>) = throws.sumOf { it.outcome.points + it.myThrow.throwValue }

fun part2(throws: List<Throw>) = throws.sumOf { ThrowType.determineRequiredResult(it) }

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val input = getResourceAsStream("day2/input.txt").readAsOneLine()
    val throws = input.split("\r\n").map {
        Throw.makeThrow(it)
    }



    println(
        """

       Solution for Part 1: ${part1(throws)}
       Solution for Part 2: ${part2(throws)}

    """.trimIndent()
    )
}