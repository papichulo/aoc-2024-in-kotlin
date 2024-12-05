fun main() {
    fun getPrecedence(input: List<String>): List<Pair<Int, Int>> {
        return input.takeWhile { it.isNotBlank() }
            .map { it.split("|").map(String::toInt) }
            .map { it[0] to it[1] }
    }

    fun isInRightOrder(row: List<Int>, precedence: List<Pair<Int, Int>>): Boolean {
        val indexMap = row.withIndex().associate { it.value to it.index }
        return precedence.all { (a, b) ->
            indexMap[a] == null || indexMap[b] == null ||
            indexMap[a] != null && indexMap[b] != null && indexMap[a]!! < indexMap[b]!!
        }
    }

    fun findRowsInRightOrder(input: List<String>): List<List<Int>> {
        val precedence = getPrecedence(input)
        return input.dropWhile { it.isNotBlank() }.drop(1)
            .map { it.split(",").map(String::toInt) }
            .filter { isInRightOrder(it, precedence) }
    }

    fun sortRowAccordingToPrecedence(row: List<Int>, precedence: List<Pair<Int, Int>>): List<Int> {
        return row.sortedWith { a, b ->
            when {
                precedence.contains(Pair(a, b)) -> -1
                precedence.contains(Pair(b, a)) -> 1
                else -> 0
            }
        }
    }

    fun findRowsInWrongOrderAndSort(input: List<String>): List<List<Int>> {
        val precedence = getPrecedence(input)
        return input.asSequence().dropWhile { it.isNotBlank() }.drop(1)
            .map { it.split(",").map(String::toInt) }
            .filter { !isInRightOrder(it, precedence) }
            .map { sortRowAccordingToPrecedence(it, precedence) }
            .toList()
    }

    fun List<List<Int>>.sumMiddleValues(): Int {
        return this.sumOf { row ->
            val middleIndex = row.size / 2
            if (row.size % 2 == 0) {
                (row[middleIndex - 1] + row[middleIndex]) / 2
            } else {
                row[middleIndex]
            }
        }
    }

    fun part1(input: List<String>): Int {
        return findRowsInRightOrder(input).sumMiddleValues()
    }

    fun part2(input: List<String>): Int {
        return findRowsInWrongOrderAndSort(input).sumMiddleValues()
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
