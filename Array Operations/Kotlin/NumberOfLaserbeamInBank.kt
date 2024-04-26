/*
* Number of Laser Beams in a Bank https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
Anti-theft security devices are activated inside a bank. You are given a 0-indexed binary string array bank representing the floor plan of the bank, which is an m x n 2D matrix. bank[i] represents the ith row, consisting of '0's and '1's. '0' means the cell is empty, while'1' means the cell has a security device.
There is one laser beam between any two security devices if both conditions are met:
The two devices are located on two different rows: r1 and r2, where r1 < r2.
For each row i where r1 < i < r2, there are no security devices in the ith row.
Laser beams are independent, i.e., one beam does not interfere nor join with another.

Return the total number of laser beams in the bank.

Example 1:
Input: bank = ["011001","000000","010100","001000"]
Output: 8
Explanation: Between each of the following device pairs, there is one beam. In total, there are 8 beams:
 * bank[0][1] -- bank[2][1]
 * bank[0][1] -- bank[2][3]
 * bank[0][2] -- bank[2][1]
 * bank[0][2] -- bank[2][3]
 * bank[0][5] -- bank[2][1]
 * bank[0][5] -- bank[2][3]
 * bank[2][1] -- bank[3][2]
 * bank[2][3] -- bank[3][2]
Note that there is no beam between any device on the 0th row with any on the 3rd row.
This is because the 2nd row contains security devices, which breaks the second condition.

Example 2:
Input: bank = ["000","111","000"]
Output: 0
Explanation: There does not exist two devices located on two different rows.
*/

fun main() {
    println(numberOfBeams(arrayOf("011001","000000","010100","001000")))
    println(numberOfBeams(arrayOf("000","111","000")))
}

fun numberOfBeams(bank: Array<String>): Int {
    return bank
            .map { row -> row.count{ it == '1' }}
            .filterNot{ it == 0 }
            .zipWithNext { current, next -> current * next} // 6, 2
            .sum()
}

class Solution {
    data class Accumulator(val prevValue : Int, val accumulatedValue : Int)

    fun numberOfBeams(bank: Array<String>): Int {
        return  bank
                .map {it.count {char -> char == '1'}}
                .filter {it > 0}
                .fold(Accumulator(0,0)) { acc,count ->
                    Accumulator(count,acc.accumulatedValue + acc.prevValue*count)
                }.accumulatedValue
    }
}

fun numberOfBeams(bank: Array<String>): Int =
    bank.map { it.count{ it == '1' } }
        .filter { it > 0 }
        .windowed(2) { it.first() * it.last() }
        .sum()

fun numberOfBeams(bank: Array<String>): Int {
    var beams = 0
    var (r1, r2) = listOf(0, 0)
    for (path in bank) {
        if (r2 != 0) { r1 = r2 }
        r2 = path.count { it == '1' }
        beams += r1 * r2
    }
    return beams
}

class Solution {
    fun numberOfBeams(bank: Array<String>): Int {
        var beams = 0
        var lastDevices = 0
        var i = bank.size - 1
        while (i >= 0) {
            val devices = numberOfDevices(bank[i])
            if (devices > 0) {
                beams += lastDevices * devices
                lastDevices = devices
            }
            i--
        }
        return beams
    }

    private fun numberOfDevices(s: String): Int {
        var result = 0
        var i = s.length - 1
        while (i >= 0) {
            if (s[i] == '1') result++
            i--
        }
        return result
    }
}