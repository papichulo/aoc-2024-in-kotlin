typealias Operator = Long.(Long) -> Long

fun main() {

    fun Long.concat(other: Long): Long {
        return (this.toString() + other.toString()).toLong()
    }

    fun isValid(numbers: List<Long>, sum: Long, target: Long, operators: List<Operator>): Boolean {
        if (numbers.isEmpty() || sum > target) return false
        if (numbers.size == 1) return operators.any { op -> sum.op(numbers[0]) == target }

        return operators.any { op ->
            isValid(numbers.drop(1), sum.op(numbers[0]), target, operators)
        }
    }

    fun solve(input: List<String>, operations: List<Operator>): Long {
        return input.map { line ->
            val (target, numbers) = line.split(": ").let {
                it[0].toLong() to it[1].trim().split(" ").map(String::toLong)
            }
            target to numbers
        }.sumOf { (target, numbers) ->
            if (isValid(numbers.drop(1), numbers.first(), target, operations)) target else 0L
        }
    }

    fun part1(input: List<String>): Long {
        return solve(input, listOf(Long::plus, Long::times))
    }

    fun part2(input: List<String>): Long {
        return solve(input, listOf(Long::plus, Long::times, Long::concat))
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 3749L)
    check(part2(testInput) == 11387L)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}