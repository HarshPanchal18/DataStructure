import java.util.*

/*
* Relative Ranks
You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.
The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on.

The placement of each athlete determines their rank:
* The 1st place athlete's rank is "Gold Medal".
* The 2nd place athlete's rank is "Silver Medal".
* The 3rd place athlete's rank is "Bronze Medal".
* For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").

Return an array answer of size n where answer[i] is the rank of the ith athlete.

Example 1:
Input: score = [5,4,3,2,1]
Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].

Example 2:
Input: score = [10,3,8,9,4]
Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].

Constraints:
n == score.length
1 <= n <= 104
0 <= score[i] <= 106
All the values in score are unique.
*/

fun main() {
	val result = findRelativeRanks(intArrayOf(5, 4, 3, 2, 1))
	result.forEach { print("$it ") }
}

fun findRelativeRanks(score: IntArray): Array<String> {
	val result = MutableList(score.size) { "" }
	val scoreMap: MutableMap<Int, Int> = mutableMapOf()
	for (i in score.indices) {
		scoreMap[score[i]] = i
	}

	score.sort()

	for (i in score.indices.reversed()) {
		when (val rank = score.size - i) {
			1 -> result[scoreMap[score[i]]!!] = "Gold Medal"
			2 -> result[scoreMap[score[i]]!!] = "Silver Medal"
			3 -> result[scoreMap[score[i]]!!] = "Bronze Medal"
			else -> result[scoreMap[score[i]]!!] = rank.toString()
		}
	}

	return result.toTypedArray()
}


class Solution {
	fun findRelativeRanks(score: IntArray): Array<String> {
		// Create a map to store each score's original index
		val scoreMap: MutableMap<Int, Int> = HashMap()
		for (i in score.indices) {
			scoreMap[score[i]] = i
		}

		// Sort the scores in descending order
		Arrays.sort(score)

		// Create a result array to store the ranks
		val result = arrayOf<String>()
		for (i in score.indices.reversed()) {
			// Assign ranks based on the position of each score in the sorted array
			when (val rank = score.size - i) {
				1 -> result[scoreMap[score[i]]!!] = "Gold Medal"
				2 -> result[scoreMap[score[i]]!!] = "Silver Medal"
				3 -> result[scoreMap[score[i]]!!] = "Bronze Medal"
				else -> result[scoreMap[score[i]]!!] = rank.toString()
			}
		}

		return result
	}
}

class Solution {
	fun findRelativeRanks(score: IntArray): Array<String?> {
		val n = score.size

		val sortedPairs = Array(n) { IntArray(2) }
		for (i in 0 until n)
			sortedPairs[i] = intArrayOf(i, score[i])

		Arrays.sort(
			sortedPairs
		) { x: IntArray, y: IntArray ->
			(y[1] - x[1])
		}

		val ans = arrayOfNulls<String>(n)

		for (i in 0 until n) {
			when (i) {
				0 -> ans[sortedPairs[i][0]] = "Gold Medal"
				1 -> ans[sortedPairs[i][0]] = "Silver Medal"
				2 -> ans[sortedPairs[i][0]] = "Bronze Medal"
				else -> ans[sortedPairs[i][0]] = (i + 1).toString()
			}
		}

		return ans
	}
}

class Solution {
	fun findRelativeRanks(score: IntArray): Array<String> {
		val maxHeap = PriorityQueue<AthleteIndexAndScore>(score.size) { a, b ->
			b.score.compareTo(a.score)
		}

		score.forEachIndexed { index, athleteScore ->
			maxHeap.add(AthleteIndexAndScore(index, athleteScore))
		}

		val result = Array(score.size) { "" }
		var currentPosition = 1

		while (maxHeap.size > 0) {
			val top = maxHeap.poll()
			result[top.index] = getPositionDescription(currentPosition)
			currentPosition++
		}

		return result
	}

	private fun getPositionDescription(position: Int): String {
		return when (position) {
			1 -> "Gold Medal"
			2 -> "Silver Medal"
			3 -> "Bronze Medal"
			else -> position.toString()
		}
	}

	data class AthleteIndexAndScore(val index: Int, val score: Int)
}

class Solution {
	fun findRelativeRanks(score: IntArray): Array<String> {
		val compareByValue: Comparator<Pair<Int, Int>> = compareByDescending { it.first }
		val queue = PriorityQueue(compareByValue)

		score.forEachIndexed { index, value -> queue.add(Pair(value, index)) }

		val result = Array(score.size) { "" }

		for (i in result.indices) {
			val item = queue.remove()
			val placement = getPlacement(i + 1)

			result[item.second] = placement
		}

		return result
	}

	fun getPlacement(index: Int): String {
		return when (index) {
			1 -> "Gold Medal"
			2 -> "Silver Medal"
			3 -> "Bronze Medal"
			else -> index.toString()
		}
	}
}

class Solution {
	fun findRelativeRanks(score: IntArray): Array<String> {
		val arr = Array(score.size) { "" }

		val sortedPos = score.sortedArrayDescending()
		var pos = 6

		for (i in sortedPos.indices) {
			val index = score.indexOf(sortedPos[i])
			arr[index] =
				when (i) {
					0 -> "Gold Medal"
					1 -> "Silver Medal"
					2 -> "Bronze Medal"
					3 -> "4"
					4 -> "5"
					else -> (pos++).toString()
				}
		}

		return arr
	}
}

class Solution {
	fun findRelativeRanks(score: IntArray): Array<String> {
		val scoreMap = mutableMapOf<Int, Int>()
		val result = Array(score.size) { "" }

		for (i in 0 until score.size)
			scoreMap[score[i]] = i

		score.sortDescending()

		if (score.isNotEmpty())
			result[scoreMap[score[0]]!!] = "Gold Medal"

		if (score.size >= 2)
			result[scoreMap[score[1]]!!] = "Silver Medal"

		if (score.size >= 3)
			result[scoreMap[score[2]]!!] = "Bronze Medal"

		for (i in 3 until score.size)
			result[scoreMap[score[i]]!!] = (i + 1).toString()

		return result
	}
}

class Solution {
	fun findRelativeRanks(score: IntArray): Array<String> {
		val ranked = score.indices.sortedBy { -score[it] }
		val res = Array(score.size) { "" }

		for (i in ranked.indices) {
			res[ranked[i]] = when (i) {
				0 -> "Gold Medal"
				1 -> "Silver Medal"
				2 -> "Bronze Medal"
				else -> (i + 1).toString()
			}
		}

		return res
	}
}