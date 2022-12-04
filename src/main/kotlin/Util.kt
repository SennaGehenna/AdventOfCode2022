import java.io.InputStream

fun getResourceAsStream(path: String): InputStream? =
    object {}.javaClass.getResourceAsStream(path)

fun InputStream?.readAsOneLine() = this?.bufferedReader()?.readText()?.trimEnd('\r', '\n') ?: ""


fun printSolution(part1Solution:Any, part2Solution: Any) {
    println(
        """

       Solution for Part 1: $part1Solution
       Solution for Part 2: $part2Solution

    """.trimIndent()
    )
}