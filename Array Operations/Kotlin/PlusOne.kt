import java.math.BigDecimal

/*
* Plus One
You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer.
The digits are ordered from most significant to least significant in left-to-right order.
The large integer does not contain any leading 0's.
Increment the large integer by one and return the resulting array of digits.

Example 1:
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].

Example 2:
Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].

Example 3:
Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].

Constraints:
1 <= digits.length <= 100
0 <= digits[i] <= 9
digits does not contain any leading 0's.
*/

fun main() {
	plusOne(intArrayOf(1, 2, 3))
}

fun plusOne(digits: IntArray): IntArray {
	for (i in digits.indices.reversed()) {
		if (digits[i] < 9) {
			digits[i]++
			return digits
		}

		digits[i] = 0
	}

	val digits = IntArray(digits.size + 1)
	digits[0] = 1
	return digits
}

class Solution {
	fun plusOne(digits: IntArray): IntArray {

		// [1,2,3] -> [1,2,4]
		// [4,3,2,1] -> [4,3,2,2]
		// [9] -> [1,0]

		// Algorithm
		// 1- Loop from the last position
		// 2- Add 1 to the value of the current item
		// 3- If the value is 10, set the value to 0 and go to the previous item and repeat (2)
		// 3- If the value is less than 10, increment the value by 1 and return the array
		// 5- If reached the first item and still it is the case (3),that means all the items were 9 -> overflow the first digit,
		//      create a new array with the size of (digits.size + 1) and set the first element to 1 and the rest to 0

		for (i in (digits.size - 1) downTo 0) {
			if (digits[i] + 1 == 10) {
				digits[i] = 0
			} else {
				digits[i]++
				return digits
			}
		}

		return IntArray(digits.size + 1) { index ->
			if (index == 0) 1 else 0
		}
	}
}

class Solution {
	fun plusOne(digits: IntArray): IntArray {
		val listTemp: MutableList<Int> = digits.toMutableList()
		listTemp.add(0)

		var i = 1
		while (true) {
			if (listTemp[listTemp.size - i] != 9) {
				listTemp[listTemp.size - i] += 1
				break
			} else {
				listTemp[listTemp.size - i] = 0
				i++
			}
		}

		if (listTemp.first() == 0)
			listTemp.removeFirst()

		return listTemp.toIntArray()
	}
}

class Solution {
	fun plusOne(digits: IntArray): IntArray {
		for (i in digits.lastIndex downTo 0) {
			if (digits[i] == 9) {
				digits[i] = 0
				if (i == 0)
					return intArrayOf(1, *digits)
			} else {
				digits[i] = digits[i] + 1
				return digits
			}
		}

		return digits
	}
}

class Solution {
	fun plusOne(digits: IntArray): IntArray {
		val number = digits.joinToString("").toBigDecimal()
		val incrementOne = number.plus(BigDecimal.ONE)
		val res = incrementOne.toString().map { it.digitToInt() }.toIntArray()

		//println(incrementOne)
		return res
	}
}