/*
* Stone Game
Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.
Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.
Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

Example 1:
Input: piles = [5,3,4,5]
Output: true
Explanation:
Alice starts first, and can only take the first 5 or the last 5.
Say she takes the first 5, so that the row becomes [3, 4, 5].
If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alice, so we return true.

Example 2:
Input: piles = [3,7,2,3]
Output: true

Constraints:
2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles[i]) is odd.
*/

fun main() {
	println(stoneGame(intArrayOf(5, 3, 4, 5)))
}

fun stoneGame(piles: IntArray): Boolean {
	var alice = 0
	var bob = 0

	for (i in piles.indices) {
		// Finding first and last positives
		val first = piles.find { it > 0 } ?: 0
		val last = piles.findLast { it > 0 } ?: 0

		if (first > last) {
			if (i % 2 == 0) // Alice turn
				alice += first
			else
				bob += last
			piles[piles.indexOfFirst { it > 0 }] = 0
		} else {
			if (i % 2 == 0) // Alice turn
				alice += last
			else
				bob += first

			piles[piles.indexOfLast { it > 0 }] = 0
		}
	}

	return alice > bob
}

class Solution {
	/**
	 * key in map is left and right borders
	 * example: piles = [1,2,3,4,5,6,7,8] with key(2,5) == [3,4,5,6]
	 */
	private val memo = mutableMapOf<Pair<Int, Int>, Int>()

	fun stoneGame(piles: IntArray): Boolean {
		val sum = piles.sum()
		//Alice score
		val sumA = dfs(0 to piles.lastIndex, piles, sum)
		//Bob score
		val sumB = sum - sumA
		//Tie is not an option, so
		return sumA > sumB
	}

	fun dfs(key: Pair<Int, Int>, piles: IntArray, sum: Int): Int {
		//if already exist
		memo[key]?.let { return it }
		val (l, r) = key
		//if only 2 piles left
		if (r - l == 1) return maxOf(piles[l], piles[r])
		//take element form head + sum of other elements - best chooses opponent
		val head = piles[l] + (sum - piles[l]) - dfs(l + 1 to r, piles, sum - piles[l])
		//take element form tail + sum of other elements - best chooses opponent
		val tail = piles[r] + (sum - piles[r]) - dfs(l to r - 1, piles, sum - piles[r])
		//save to map and return best choose
		return maxOf(head, tail).also { memo[key] = it }
	}
}

class Solution {
	fun stoneGame(piles: IntArray): Boolean {
		val n = piles.size
		val dp = Array(2) { Array(n) { IntArray(n) { -1 } } }

		fun dfs(turn: Int, left: Int, right: Int): Int {
			if (right <= left) return 0
			if (dp[turn][left][right] > 0) return dp[turn][left][right]

			var result = if (turn == 0) 0 else Int.MAX_VALUE
			if (turn == 0) {
				result = maxOf(result, piles[left] + dfs(1, left + 1, right))
				result = maxOf(result, piles[right] + dfs(1, left, right - 1))
			} else {
				result = minOf(result, dfs(0, left + 1, right))
				result = minOf(result, dfs(0, left, right - 1))
			}

			dp[turn][left][right] = result
			return result
		}

		dfs(0, 0, n - 1)

		val sum = piles.sum()
		return dp[0][0][n - 1] > (sum / 2)
	}
}

class Solution {
	fun stoneGame(piles: IntArray): Boolean {
		val dp = Array(piles.size) { IntArray(piles.size) { -1 } }

		fun dfs(left: Int, right: Int): Int {
			if (left > right)
				return 0
			if (dp[left][right] != -1)
				return dp[left][right]

			val isEven = (right - left) % 2 == 0
			dp[left][right] = maxOf(
				dfs(left + 1, right) + if (isEven) piles[left] else 0,
				dfs(left, right - 1) + if (isEven) piles[right] else 0
			)

			return dp[left][right]
		}

		return dfs(0, piles.lastIndex) > (piles.sum()) / 2
	}
}

class Solution {
	fun stoneGame(piles: IntArray): Boolean {
		var a = 0
		var b = 0
		var res = 1

		for (i in piles.indices) {
			if (res % 2 != 0)
				a += maxOf(piles[i], piles[piles.lastIndex - i])
			else
				b += minOf(piles[i], piles[piles.lastIndex - i])
			res++
		}
		return b < a
	}
}

class Solution {
	fun stoneGame(piles: IntArray): Boolean {
		val memo = hashMapOf<String, Int>()
		val r = stoneGameHelper(piles, 0, piles.size - 1, true, memo)

		return (piles.sum() / 2) <= r
	}

	private fun stoneGameHelper(
		piles: IntArray,
		l: Int, r: Int,
		aliceTurn: Boolean,
		memo: HashMap<String, Int>
	): Int {
		val key = "$l $r $aliceTurn"
		// println("${piles} ${alice} ${bob} ${aliceTurn}")

		if (memo.contains(key))
			return memo[key]!!

		if (l >= r)
			return 0

		if (aliceTurn) {
			var ret = 0
			ret = maxOf(ret, stoneGameHelper(piles, l + 1, r, false, memo) + piles[l])
			ret = maxOf(ret, stoneGameHelper(piles, l, r - 1, false, memo) + piles[r])
			memo[key] = ret

			return ret
		} else {
			var ret = 0
			ret = maxOf(ret, stoneGameHelper(piles, l + 1, r, true, memo) + piles[l])
			ret = maxOf(ret, stoneGameHelper(piles, l, r - 1, true, memo) + piles[r])
			memo[key] = ret

			return ret
		}
	}
}

class Solution {
	fun stoneGame(piles: IntArray) = true
}