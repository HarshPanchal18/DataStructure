/*
* Maximize Happiness of Selected Children
You are given an array happiness of length n, and a positive integer k.
There are n children standing in a queue, where the ith child has happiness value happiness[i]. You want to select k children from these n children in k turns.
In each turn, when you select a child, the happiness value of all the children that have not been selected till now decreases by 1. Note that the happiness value cannot become negative and gets decremented only if it is positive.
Return the maximum sum of the happiness values of the selected children you can achieve by selecting k children.

Example 1:
Input: happiness = [1,2,3], k = 2
Output: 4
Explanation: We can pick 2 children in the following way:
- Pick the child with the happiness value == 3. The happiness value of the remaining children becomes [0,1].
- Pick the child with the happiness value == 1. The happiness value of the remaining child becomes [0]. Note that the happiness value cannot become less than 0.
The sum of the happiness values of the selected children is 3 + 1 = 4.

Example 2:
Input: happiness = [1,1,1,1], k = 2
Output: 1
Explanation: We can pick 2 children in the following way:
- Pick any child with the happiness value == 1. The happiness value of the remaining children becomes [0,0,0].
- Pick the child with the happiness value == 0. The happiness value of the remaining child becomes [0,0].
The sum of the happiness values of the selected children is 1 + 0 = 1.

Example 3:
Input: happiness = [2,3,4,5], k = 1
Output: 5
Explanation: We can pick 1 child in the following way:
- Pick the child with the happiness value == 5. The happiness value of the remaining children becomes [1,2,3].
The sum of the happiness values of the selected children is 5.

Constraints:
1 <= n == happiness.length <= 2 * 105
1 <= happiness[i] <= 108
1 <= k <= n
*/

fun main() {
	println(maximumHappinessSum(intArrayOf(1, 1, 1, 1), 2))
	println(maximumHappinessSum(intArrayOf(1, 2, 3), 1))
}

fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
	happiness.sortDescending()

	var sum = 0L
	for (i in 0 until k) {
		if (happiness[i] > i) {
			sum += happiness[i] - i
		}
	}

	return sum
}

class Solution {

	fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
		var k = k
		var ans = 0L
		var min = 0
		var i = happiness.lastIndex

		happiness.sort()

		while (i >= 0 && k > 0) {
			k--

			if (min < happiness[i])
				ans += (happiness[i] - min).toLong()

			min++
			i--
		}

		return ans
	}
}

class Solution {
	fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
		happiness.sortDescending()

		var tot = 0L

		for (i in 0 until k) {
			val v = happiness[i] - i
			if (v <= 0)
				break
			tot += v
		}

		return tot
	}
}

class Solution {
	fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
		happiness.sortDescending()
		var answer = 0L
		var remain = 0

		while (remain < k) {
			if (remain == 0) {
				answer += happiness[0]
			} else {
				var next = happiness[remain] - remain
				if (next < 0) next = 0
				answer += next
			}
			remain++
		}

		return answer
	}
}

class Solution {
	fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
		happiness.sort()

		var p = happiness.lastIndex
		var dec = 0
		var total = 0L

		repeat(k) {
			total += (happiness[p] - dec).coerceAtLeast(0)
			p--
			dec++
		}

		return total
	}
}

class Solution {
	fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
		happiness.sort()
		var sum = 0L

		for (i in happiness.lastIndex downTo happiness.size - k) {
			happiness[i] = maxOf(0, (happiness[i] - (happiness.lastIndex - i)))
			sum += happiness[i].toLong()
		}

		return sum
	}
}

class Solution {
	fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
		happiness.sort()
		val n = happiness.size
		var i = n - 1
		var sum = 0L

		while (i >= 0) {
			val offset = n - 1 - i

			val score = maxOf(0, (happiness[i] - offset))
			sum += score

			val collected = n - i

			if (score == 0 || collected == k)
				break //we cannot score more, lower elements will be 0 only

			i--
		}

		return sum
	}
}