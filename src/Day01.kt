import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var dial = 50
        var result = 0
        val  maxDial = 100
        for (s in input) {
            result += if (dial == 0) 1 else 0
            val operation = if (s.startsWith("L")) -1 else 1
            val value = operation * s.drop(1).toInt()
            dial = (dial + value) % maxDial
            if (dial < 0) dial += maxDial
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var dial = 50
        val maxDial = 100
        var zeroCounter = 0
        for (s in input) {
            val operation = if (s.startsWith("L")) -1 else 1
            val value = operation * s.drop(1).toInt();
            val prevDial = dial

            val fullTurns = abs(value) / maxDial
            zeroCounter += fullTurns

            val partialMove = value % maxDial
            val newDial = (dial + partialMove).mod(maxDial)

            val crossedZero = if (operation > 0) {
                prevDial > newDial
            } else {
                prevDial < newDial
            }

            if (partialMove != 0 && crossedZero) {
                zeroCounter++
            }
            dial = newDial
        }
        return zeroCounter
    }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("Day01")) == 1)

    // Or read a large test input from the `src/Day01.txt` file:
    val testInput = readInput("Day01")
    //check(part1(testInput) == 3)
    //check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
    //part2(input).println()
}
