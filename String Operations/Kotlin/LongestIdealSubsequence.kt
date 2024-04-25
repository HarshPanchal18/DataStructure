import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/*
* Longest Ideal Subsequence (Hashing, Dynamic Programming)
You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:
* t is a subsequence of the string s.
* The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
Return the length of the longest ideal string.
A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.

Example 1:
Input: s = "acfgbd", k = 2
Output: 4
Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.

Example 2:
Input: s = "abcd", k = 3
Output: 4
Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.

Constraints:
1 <= s.length <= 105
0 <= k <= 25
s consists of lowercase English letters.
*/

fun main() {
	longestIdealString("abcd",3)
}

fun longestIdealString(s: String, k: Int): Int {
	var res = 0
	val n = s.length
	val dp = IntArray(150)

	for (ci in 0 until n) {
		val i = s[ci].code
		for (j in i - k..i + k)
			dp[i] = max(dp[i], dp[j])
		res = max(res, ++dp[i])
	}
	return res
}

class Solution {
	fun longestIdealString(s: String, k: Int): Int {
		val dp = IntArray(26)
		var ans = 1
		for (i in 0 until s.length) {
			val ele = s[i].code - 'a'.code
			run {
				var j = ele
				while (j >= 0 && j >= ele - k) {
					dp[ele] = max(dp[ele].toDouble(), (dp[j] + 1).toDouble()).toInt()
					j--
				}
			}
			var j = ele + 1
			while (j < 26 && j <= ele + k) {
				dp[ele] = max(dp[ele].toDouble(), (dp[j] + 1).toDouble()).toInt()
				j++
			}
			ans = max(ans.toDouble(), dp[ele].toDouble()).toInt()
		}
		return ans
	}
}

class Solution {
	private fun solveMemo(s: String, idx: Int, k: Int, dp: Array<IntArray>, ch: Char): Int {
		// Base Case
		if (idx >= s.length) {
			return 0
		}

		// step-2 => if already calculated just return it
		if (dp[idx][ch.code] != -1) {
			return dp[idx][ch.code]
		}

		// step-3 => if not calculated yet just calculate it and return
		var take = 0
		var nontake = 0
		// Case of take it
		if (abs((ch.code - s[idx].code).toDouble()) <= k || ch == '#') {
			take = 1 + solveMemo(s, idx + 1, k, dp, s[idx])
		}
		// case of leave it
		nontake = solveMemo(s, idx + 1, k, dp, ch)

		return max(take.toDouble(), nontake.toDouble()).also { dp[idx][ch.code] = it.toInt() }.toInt().toInt()
	}


	fun longestIdealString(s: String, k: Int): Int {
		// DP + Memoization
		val dp = Array(s.length + 1) { IntArray(130) }
		for (d in dp) {
			Arrays.fill(d, -1)
		}
		return solveMemo(s, 0, k, dp, '#')
	}
} // DP + Tabulation
// class Solution {
//     public int longestIdealString(String s, int k) {
//         int res = 0;
//         int[] dp = new int[150];
//         for (int i = 0; i < s.length(); i++) {
//             int asci = s.charAt(i);
//             for (int j = asci - k; j <= asci + k; j++) {
//                 dp[asci] = Math.max(dp[asci], dp[j]);
//             }
//             res = Math.max(res, ++dp[asci]);
//         }
//         return res;
//     }
// }

class Solution {
	fun longestIdealString(s: String, k: Int): Int {
		val dp = IntArray(27)
		val n = s.length
		for (i in n - 1 downTo 0) {
			val c = s[i]
			val idx = c.code - 'a'.code
			var max = Int.MIN_VALUE
			val left = max((idx - k), 0)
			val right = min((idx + k), 26)
			for (j in left..right)
				max = max(max.toDouble(), dp[j].toDouble()).toInt()

			dp[idx] = max + 1
		}
		var max = Int.MIN_VALUE
		for (ele in dp)
			max = max(ele, max)

		return max
	}
}

class Solution {
	fun longestIdealString(s: String, k: Int): Int {
		val DP = IntArray(26)
		var ans = 1

		var ch = 0
		val n = s.length
		while (ch < n) {
			val i = s[ch].code - 'a'.code
			DP[i] = DP[i] + 1

			for (j in max(0, i - k)..minOf(25, i + k))
				if (j != i) DP[i] = max(DP[i].toDouble(), (DP[j] + 1).toDouble()).toInt()

			ans = max(ans, DP[i])
			ch++
		}

		return ans
	}
}

class Solution {
	fun longestIdealString(s: String, k: Int): Int {
		val dp = IntArray(26)
		for (i in 0 until s.length) {
			val a = s[i].code - 'a'.code
			var mx = 0

			for (b in max(0, (a - k))..min(25, (a + k)))
				mx = max(mx, dp[b])

			dp[a] = 1 + mx
		}
		return Arrays.stream(dp).max().asInt
	}
}