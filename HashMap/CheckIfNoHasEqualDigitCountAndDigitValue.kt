/*
* Check if Number Has Equal Digit Count and Digit Value
You are given a 0-indexed string num of length n consisting of digits.
Return true if for every index i in the range 0 <= i < n, the digit i occurs num[i] times in num, otherwise return false.

Example 1:
Input: num = "1210"
Output: true
Explanation:
num[0] = '1'. The digit 0 occurs once in num.
num[1] = '2'. The digit 1 occurs twice in num.
num[2] = '1'. The digit 2 occurs once in num.
num[3] = '0'. The digit 3 occurs zero times in num.
The condition holds true for every index in "1210", so return true.

Example 2:
Input: num = "030"
Output: false
Explanation:
num[0] = '0'. The digit 0 should occur zero times, but actually occurs twice in num.
num[1] = '3'. The digit 1 should occur three times, but actually occurs zero times in num.
num[2] = '0'. The digit 2 occurs zero times in num.
The indices 0 and 1 both violate the condition, so return false.

Constraints:
n == num.length
1 <= n <= 10
num consists of digits.
*/

fun main() {
	print(digitCount("1210"))
}

class Solution {
	fun digitCount(num: String): Boolean {
		for (i in num.indices) {
			val count = counts(num, i)
			if ((count + '0'.code).toChar() != num[i])
				return false
		}

		return true
	}

	fun counts(s: String, x: Int): Int {
		var count = 0
		val c = (x + '0'.code).toChar()

		for (element in s) {
			if (element == c)
				count++
		}

		return count
	}
}

class Solution {
	fun digitCount(num: String): Boolean {
		val map = HashMap<Int, Int>()

		for (element in num) {
			val ch = element.code - '0'.code
			map[ch] = map.getOrDefault(ch, 0) + 1
		}

		// StringBuilder sb=new StringBuilder();
		var count: Int
		for (i in num.indices) {
			count = if (map.containsKey(i)) {
				map[i]!!
			} else {
				0
			}
			val x = num[i].code - '0'.code
			if (x != count)
				return false

		}
		return true
	}
}

internal class Solution {
	fun digitCount(num: String): Boolean {
		val temp = CharArray(11)
		var temp1: Int
		var temp2: Int

		for (element in num) {
			temp1 = element.code - '0'.code
			temp[temp1]++
		}

		for (i in num.indices) {
			val ch = num[i]
			temp2 = ch.code - '0'.code
			if (temp[i].code != temp2)
				return false
		}

		return true
	}
}

class Solution {
	fun digitCount(num: String): Boolean {
		val map: MutableMap<Int, Int> = HashMap()

		for (c in num.toCharArray()) {
			val n = c.code - '0'.code
			map[n] = 1 + map.getOrDefault(n, 0)
		}

		for (i in num.indices) {
			val n = num[i].code - '0'.code
			if (n != map.getOrDefault(i, 0))
				return false
		}

		return true
	}
}

fun digitCount(s: String) = s.withIndex().all { it.value - '0' == s.count { c -> c == '0' + it.index } }

class Solution {
	fun digitCount(num: String): Boolean {
		for (i in num.indices) {
			val countI = num.count { it == '0' + i }
			if (countI != (num[i] - '0'))
				return false

		}
		return true
	}
}

class Solution {
	fun digitCount(num: String): Boolean {
		val arr = IntArray(10)

		for (char in num)
			arr[char - '0']++

		for (i in num.indices)
			if (arr[i] != num[i] - '0')
				return false

		return true
	}
}

class Solution {
	fun digitCount(num: String): Boolean {
		val frequencies = IntArray(10)

		for (digit in num)
			frequencies[digit.digitToInt()]++

		for (index in num.indices) {
			if (frequencies[index] != num[index].digitToInt())
				return false
		}

		return true
	}
}

class Solution {
	fun digitCount(num: String): Boolean {
		val digitCounts: Map<Char, Int> = num.groupingBy { it }.eachCount()

		return num.withIndex().all { (index: Int, digit: Char) ->
			// println(index.toString() + " " + digit)
			(digitCounts[index.digitToChar()] ?: 0) == digit.digitToInt()
		}
	}
}

class Solution {
	fun digitCount(num: String): Boolean {
		val count = IntArray(10)

		for (n in num)
			count[n - '0']++

		for (i in num.indices) {
			if ((num[i] - '0') != count[i])
				return false
		}

		return true
	}
}