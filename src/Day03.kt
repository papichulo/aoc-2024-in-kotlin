fun main() {

    fun String.sumMultiplyMatches(): Int {
        val regex = Regex("""mul\(\d+,\d+\)""")
        return regex.findAll(this).map { it.value }.toList()
            .map { it.replace("mul(", "").replace(")", "") }
            .map { it.split(",").map { it.toInt() } }
            .sumOf { (a, b) -> a * b }
    }

    fun part1(input: List<String>): Int {
        return input.joinToString("").sumMultiplyMatches()
    }

    fun splitAndSumPart2(input: String): Int {
        val regex = Regex("""do\(\)|don't\(\)|mul\(\d+,\d+\)""")
        val matches = regex.findAll(input).map { it.value }.toList()
        var sum = 0
        var addValues = true

        for (match in matches) {
            when {
                match == "do()" -> addValues = true
                match == "don't()" -> addValues = false
                match.startsWith("mul(") && addValues -> {
                    val values = match.removePrefix("mul(").removeSuffix(")").split(",").map { it.toInt() }
                    sum += values[0] * values[1]
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        return splitAndSumPart2(input.joinToString(""))
    }


    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    val testInput2 = readInput("Day03_test2")
    check(part2(testInput2) == 48)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
