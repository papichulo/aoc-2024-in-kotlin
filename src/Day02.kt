import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            it.split(" ").map(String::toInt) }
            .filter { it == it.sorted() || it == it.sortedDescending() }
            .filter { it.zipWithNext().all { (a, b) -> (b - a).absoluteValue in 1..3 } }.size
    }

    fun isValidWithOneRemoval(list: List<Int>): Boolean {
        for (i in list.indices) {
            val sublist = list.toMutableList().apply { removeAt(i) }
            if (
                sublist.zipWithNext().all { (a, b) -> (b - a).absoluteValue in 1..3 } &&
                (sublist == sublist.sorted() || sublist == sublist.sortedDescending())
                ) {
                return true
            }
        }
        return false
    }

    fun part2(input: List<String>): Int {
        return input.map {
            it.split(" ").map(String::toInt) }
            .filter { isValidWithOneRemoval(it) }.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("1 2", "3 4", "5 2")) == 3)
    check(part2(listOf("7 6 4 2 1", "1 2 7 8 9", "9 7 6 2 1")) == 1)

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
