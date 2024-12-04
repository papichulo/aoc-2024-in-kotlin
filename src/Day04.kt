fun main() {

    fun findWord(word: String, grid: List<String>): Int {
        val directions = listOf(
            Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(1, -1),
            Pair(0, -1), Pair(-1, 0), Pair(-1, -1), Pair(-1, 1)
        )
        val wordLength = word.length
        return grid.indices.flatMap { i ->
            grid[i].indices.flatMap { j ->
                directions.mapNotNull { direction ->
                    var k = 0
                    var x = i
                    var y = j
                    while (k < wordLength && x in grid.indices && y in grid[x].indices && grid[x][y] == word[k]) {
                        k++
                        x += direction.first
                        y += direction.second
                    }
                    if (k == wordLength) Pair(i, j) else null
                }
            }
        }.count()
    }

    fun findCompleteXPatternWithCenterA(grid: List<String>): Int {
        return grid.indices.flatMap { i ->
            grid[i].indices.mapNotNull { j ->
                if (grid[i][j] == 'A' &&
                    i - 1 >= 0 && i + 1 < grid.size && j - 1 >= 0 && j + 1 < grid[i].length &&
                    ((grid[i - 1][j - 1] == 'M' && grid[i + 1][j + 1] == 'S' &&
                            grid[i - 1][j + 1] == 'M' && grid[i + 1][j - 1] == 'S') ||
                            (grid[i - 1][j - 1] == 'S' && grid[i + 1][j + 1] == 'M' &&
                                    grid[i - 1][j + 1] == 'S' && grid[i + 1][j - 1] == 'M') ||
                            (grid[i - 1][j - 1] == 'S' && grid[i + 1][j + 1] == 'M' &&
                                    grid[i - 1][j + 1] == 'M' && grid[i + 1][j - 1] == 'S') ||
                            (grid[i - 1][j - 1] == 'M' && grid[i + 1][j + 1] == 'S' &&
                                    grid[i - 1][j + 1] == 'S' && grid[i + 1][j - 1] == 'M'))
                ) 1 else null
            }
        }.count()
    }

    fun part1(input: List<String>): Int {
        return findWord("XMAS", input)
    }

    fun part2(input: List<String>): Int {
        return findCompleteXPatternWithCenterA(input)
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
