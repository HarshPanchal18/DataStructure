/*
* Minimum Operations to Make the Integer Zero
You are given two integers num1 and num2.
In one operation, you can choose integer i in the range [0, 60] and subtract 2i + num2 from num1.
Return the integer denoting the minimum number of operations needed to make num1 equal to 0.
If it is impossible to make num1 equal to 0, return -1.

Example 1:
Input: num1 = 3, num2 = -2
Output: 3
Explanation: We can make 3 equal to 0 with the following operations:
- We choose i = 2 and substract 22 + (-2) from 3, 3 - (4 + (-2)) = 1.
- We choose i = 2 and substract 22 + (-2) from 1, 1 - (4 + (-2)) = -1.
- We choose i = 0 and substract 20 + (-2) from -1, (-1) - (1 + (-2)) = 0.
It can be proven, that 3 is the minimum number of operations that we need to perform.

Example 2:
Input: num1 = 5, num2 = 7
Output: -1
Explanation: It can be proven, that it is impossible to make 5 equal to 0 with the given operation.

Constraints:
1 <= num1 <= 109
-109 <= num2 <= 109
*/

fun main() {
	print(makeTheIntegerZero(3, -2))
}

fun makeTheIntegerZero(num1: Int, num2: Int): Int {
	var answer = num1

	for (i in 0..59) {
		answer -= num2
		if (answer < 0)
			return -1

		var count = 0
		var c = 0

		for (j in 0..59) {
			if (answer.shl(j.and(1)) == 1) {
				count += j + 1
				c++
			}
		}

		if(i in c..count)
			return i
	}

	return -1
}

internal class Solution {
	fun makeTheIntegerZero(num1: Int, n2: Int): Int {
		var n1 = num1.toLong()

		for (x in 1..63) {
			n1 -= n2.toLong()

			if (n1 <= 0)
				return -1


			// case b and c
			if (n1 > 0) {
				val cnt = cntBit(n1)
				if (cnt <= x) {
					return if (n1 >= x) x
					else -1
				}
			}
		}
		return -1
	}

	fun cntBit(num: Long): Int {
		var cnt = 0
		for (i in 0..63) {
			var x: Long = 1
			x = x shl i
			if ((x and num) != 0L)
				cnt++
		}
		return cnt
	}
}

class Solution {
	fun makeTheIntegerZero(num1: Int, num2: Int): Int {
		if (num1 < num2)
			return -1

		for (i in 0..100) {
			val diff = num1 - num2.toLong() * i
			val bits = countBits(diff)

			if (i in bits..diff)
				return i

		}

		return -1
	}

	private fun countBits(num: Long): Int {
		var num = num
		var counter = 0
		while (num > 0) {
			counter += (num and 1).toInt()
			num = num shr 1
		}
		return counter
	}
}