package day2

import getResourceAsStream
import printSolution
import readAsOneLine

private enum class ThrowType(val throwValue: Int) {
    Rock(1),        // X
    Paper(2),       // Y
    Scissors(3);    // Z

    /**
     * determine required result based on the throw dictated by the strategy guide
     * X -> loss
     * Y -> draw
     * Z -> win
     *
     * for ease of use, we reuse the ThrowType enum
     */
    fun toOutcomePart2(): Outcome {
        return when (this) {
            Rock -> Outcome.Loss
            Paper -> Outcome.Draw
            Scissors -> Outcome.Win
        }
    }

}

private enum class Outcome(val points: Int) {
    Loss(0), Draw(3), Win(6);


    companion object {
        fun determineOutcome(thro: Throw): Outcome {
            return when (thro.yourThrow) {
                ThrowType.Rock -> when (thro.myThrow) {
                    ThrowType.Rock -> Draw
                    ThrowType.Paper -> Win
                    ThrowType.Scissors -> Loss
                }

                ThrowType.Paper -> when (thro.myThrow) {
                    ThrowType.Rock -> Loss
                    ThrowType.Paper -> Draw
                    ThrowType.Scissors -> Win
                }

                ThrowType.Scissors -> when (thro.myThrow) {
                    ThrowType.Rock -> Win
                    ThrowType.Paper -> Loss
                    ThrowType.Scissors -> Draw
                }
            }
        }

        fun determineRequiredResult(thro: Throw): Int {
            return with(thro) {
                val desiredOutcome = myThrow.toOutcomePart2()
                val myActualThrow = when (yourThrow) {
                    ThrowType.Rock -> when (desiredOutcome) {
                        Loss -> ThrowType.Scissors
                        Draw -> ThrowType.Rock
                        Win -> ThrowType.Paper
                    }

                    ThrowType.Paper -> when (desiredOutcome) {
                        Loss -> ThrowType.Rock
                        Draw -> ThrowType.Paper
                        Win -> ThrowType.Scissors
                    }

                    ThrowType.Scissors -> when (desiredOutcome) {
                        Loss -> ThrowType.Paper
                        Draw -> ThrowType.Scissors
                        Win -> ThrowType.Rock
                    }
                }
                myActualThrow.throwValue + desiredOutcome.points
            }
        }
    }
}

private class Throw private constructor(val yourThrow: ThrowType, val myThrow: ThrowType) {

    val outcome: Outcome = Outcome.determineOutcome(this)

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

/**
 * determine result of throw (outcome + value of throw)
 */
private fun part1(throws: List<Throw>) = throws.sumOf { it.outcome.points + it.myThrow.throwValue }

/**
 * determine result of throw (manipulated outcome + value of throw)
 */
private fun part2(throws: List<Throw>) = throws.sumOf { Outcome.determineRequiredResult(it) }

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val input = getResourceAsStream("day2/input.txt").readAsOneLine()
    val throws = input.split("\r\n").map {
        Throw.makeThrow(it)
    }



    printSolution(
        part1(throws),
        part2(throws)
    )
}