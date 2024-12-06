fun main() {
    val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // Up, Right, Down, Left

    fun part1(input: List<String>): Int {
        var directionIndex = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        var x = input.indexOfFirst { it.contains('^') }
        var y = input[x].indexOf('^')
        var steps = 0

        while (x in input.indices && y in input[x].indices) {
            visited.add(Pair(x, y))
            val nextX = x + directions[directionIndex].first
            val nextY = y + directions[directionIndex].second

            if (nextX in input.indices && nextY in input[nextX].indices && input[nextX][nextY] == '#') {
                directionIndex = (directionIndex + 1) % 4
            } else {
                x = nextX
                y = nextY
            }
            steps++
        }

        return visited.size
    }

    fun isGuardStuck(input: List<String>): Boolean {
        var directionIndex = 0
        val visited = mutableSetOf<Triple<Int, Int, Int>>()
        var x = input.indexOfFirst { it.contains('^') }
        var y = input[x].indexOf('^')

        while (x in input.indices && y in input[x].indices) {
            if (!visited.add(Triple(x, y, directionIndex))) {
                return true
            }
            val nextX = x + directions[directionIndex].first
            val nextY = y + directions[directionIndex].second

            if (nextX in input.indices && nextY in input[nextX].indices && input[nextX][nextY] == '#') {
                directionIndex = (directionIndex + 1) % 4
            } else {
                x = nextX
                y = nextY
            }
        }
        return false
    }

    fun part2(input: List<String>): Int {
        val potentialObstacles = mutableSetOf<Pair<Int, Int>>()

        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '.') {
                    val modifiedInput = input.map { it.toCharArray() }.toMutableList()
                    modifiedInput[i][j] = '#'
                    if (isGuardStuck(modifiedInput.map { it.joinToString("") })) {
                        potentialObstacles.add(Pair(i, j))
                    }
                }
            }
        }
        return potentialObstacles.size
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
    check(part2(testInput) == 6)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}