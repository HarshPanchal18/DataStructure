import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

/*
* Find Players With Zero or One Losses
You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.

Return a list answer of size 2 where:
* answer[0] is a list of all players that have not lost any matches.
* answer[1] is a list of all players that have lost exactly one match.

The values in the two lists should be returned in increasing order.

Note:
* You should only consider the players that have played at least one match.
* The testcases will be generated such that no two matches will have the same outcome.

Example 1:
Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
Output: [[1,2,10],[4,5,7,8]]
Explanation:
Players 1, 2, and 10 have not lost any matches.
Players 4, 5, 7, and 8 each have lost one match.
Players 3, 6, and 9 each have lost two matches.
Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].

Example 2:
Input: matches = [[2,3],[1,3],[5,4],[6,4]]
Output: [[1,2,5,6],[]]
Explanation:
Players 1, 2, 5, and 6 have not lost any matches.
Players 3 and 4 each have lost two matches.
Thus, answer[0] = [1,2,5,6] and answer[1] = [].

Constraints:
1 <= matches.length <= 105
matches[i].length == 2
1 <= winneri, loseri <= 105
winneri != loseri
All matches[i] are unique.
*/

fun main() {
	val result = findWinners(
		arrayOf(
			intArrayOf(1, 3), intArrayOf(2, 3), intArrayOf(3, 6), intArrayOf(5, 6), intArrayOf(5, 7),
			intArrayOf(4, 5), intArrayOf(4, 8), intArrayOf(4, 9), intArrayOf(10, 4), intArrayOf(10, 9)
		)
	)
}

fun findWinners(matches: Array<IntArray>): List<List<Int>> {
	val oneWinners = mutableListOf<Int>()
	val lostFreqs = hashMapOf<Int, Int>()
	val winFreqs = hashMapOf<Int, Int>()

	for (match in matches) {
		lostFreqs[match[1]] = lostFreqs.getOrDefault(match[1], 0) + 1
		winFreqs[match[0]] = winFreqs.getOrDefault(match[0], 0) + 1
	}

	lostFreqs.forEach {
		if (it.value == 1)
			oneWinners.add(it.key)
	}

	val zeroWinners = (winFreqs.keys.toSet().minus(lostFreqs.keys.toSet())).toList()
	println(zeroWinners)
	println(oneWinners)

	return listOf(zeroWinners.sorted(), oneWinners.sorted())
}

class Solution {
	fun findWinners(matches: Array<IntArray>): List<List<Int>> {
		val losses = Array<Int>(100_001) { -1 }
		for (m in matches) {
			val win = m[0]
			val loose = m[1]
			if (losses[win] == -1) losses[win] = 0
			if (losses[loose] == -1) losses[loose] = 0
			losses[loose]++
		}

		val playersWithoutLosses = mutableListOf<Int>()
		val playersWithOneLoss = mutableListOf<Int>()
		for ((index, lossesCount) in losses.withIndex()) {
			if (lossesCount == 1)
				playersWithOneLoss.add(index)

			if (lossesCount == 0)
				playersWithoutLosses.add(index)

		}
		return listOf(playersWithoutLosses, playersWithOneLoss)
	}
}

class Solution {
	fun findWinners(matches: Array<IntArray>): List<List<Int>> {


		val resultArray = IntArray(1000001)
		Arrays.fill(resultArray, -1)

		for (i in 0..matches.lastIndex) {
			val winner = matches[i][0]
			val looser = matches[i][1]

			if (resultArray[winner] == -1)
				resultArray[winner] = 0

			if (resultArray[looser] == -1)
				resultArray[looser] = 1
			else
				resultArray[looser]++

		}

		val winner = ArrayList<Int>()
		val looser = ArrayList<Int>()

		resultArray.forEachIndexed { index, value ->
			if (value == 0) {
				winner.add(index)
			} else if (value == 1) {
				looser.add(index)
			}
		}

		val resultList = ArrayList<List<Int>>()

		resultList.add(winner)
		resultList.add(looser)

		return resultList
	}
}

class Solution {
	fun findWinners(matches: Array<IntArray>): List<List<Int>> {
		val loseCounts = mutableMapOf<Int, Int>()
		matches.forEach {
			val winner = it[0]
			val loser = it[1]
			loseCounts[winner] = max(loseCounts.getOrDefault(winner, 0), 0)
			loseCounts[loser] = loseCounts.getOrDefault(loser, 0) + 1
		}

		return listOf(
			loseCounts.filterValues { it -> it == 0 }.keys.toList().sorted(),
			loseCounts.filterValues { it == 1 }.keys.toList().sorted()
		)
	}
}

class Solution {
	fun findWinners(matches: Array<IntArray>): List<List<Int>> {
		val losesCnt = HashMap<Int, Int>()
		for (match in matches) {
			val winner = match[0]
			val loser = match[1]
			losesCnt[winner] = losesCnt.getOrDefault(winner, 0)
			losesCnt[loser] = losesCnt.getOrDefault(loser, 0) + 1
		}
		return listOf(
			losesCnt.filter { it.value == 0 }.map { it.key }.sorted(),
			losesCnt.filter { it.value == 1 }.map { it.key }.sorted()
		)
	}
}