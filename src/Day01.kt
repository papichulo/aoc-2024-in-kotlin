import kotlin.math.absoluteValue

fun main() {
    fun getListsOfIntegersDescending(input: List<String>): Pair<List<Int>, List<Int>> {
        val list = input.map { it.split("  ").map { it.replace(" ", "").toInt(10) } }
        return Pair(list.map { it.first() }.sortedDescending(), list.map { it.last() }.sortedDescending())
    }

    fun part1(input: List<String>): Int {
        val (firstList, secondList) = getListsOfIntegersDescending(input)
        val differences = firstList.zip(secondList) { a, b -> (a - b).absoluteValue }
        return differences.sum()
    }

    fun part2(input: List<String>): Int {
        val (firstList, secondList) = getListsOfIntegersDescending(input)
        return firstList.sumOf { value -> value * secondList.count { it == value } }
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("1  2", "3  4")) == 2)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
