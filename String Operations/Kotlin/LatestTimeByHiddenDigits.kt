/*
* Latest Time by Replacing Hidden Digits
You are given a string time in the form of  hh:mm, where some of the digits in the string are hidden (represented by ?).
The valid times are those inclusively between 00:00 and 23:59.
Return the latest valid time you can get from time by replacing the hidden digits.

Example 1:
Input: time = "2?:?0"
Output: "23:50"
Explanation: The latest hour beginning with the digit '2' is 23 and the latest minute ending with the digit '0' is 50.

Example 2:
Input: time = "0?:3?"
Output: "09:39"

Example 3:
Input: time = "1?:22"
Output: "19:22"

Constraints:
time is in the format hh:mm.
It is guaranteed that you can produce a valid time from the given string.
*/

fun main() {
	maximumTime("2?:?0")
	maximumTime("0?:3?")
	maximumTime("00:??")
}

fun maximumTime(time: String): String {
	val sb = StringBuilder()

	sb.append(
		if (time[0] == '?') {
			if (time[1] == '?' || time[1] <= '3') '2'
			else '1'
		} else {
			time[0]
		}
	)

	sb.append(
		if (time[1] == '?') {
			if (sb[0] == '2') '3'
			else '9'
		} else {
			time[1]
		}
	)

	sb.append(':')

	sb.append(if (time[3] == '?') '5' else time[3])

	sb.append(if (time[4] == '?') '9' else time[4])

	return sb.toString()
}

class Solution {
	fun maximumTime(time: String): String {
		val times = time.toCharArray()
		if (times[0] == '?') {
			times[0] = if (times[1] == '?' || times[1] <= '3') '2' else '1'
		}
		if (times[1] == '?') {
			times[1] = if (times[0] == '2') '3' else '9'
		}
		if (times[3] == '?') {
			times[3] = '5'
		}
		if (times[4] == '?') {
			times[4] = '9'
		}
		return String(times)
	}
}

class Solution {
	fun maximumTime(time: String): String {
		val result = StringBuilder()

		time.forEachIndexed { index, c ->
			if (c == '?') {
				when (index) {
					0 -> {
						if (time[1] == '?' || time[1].digitToInt() < 4) result.append(2)
						else result.append(1)
					}

					1 -> {
						if (time[0] == '?' || time[0].digitToInt() == 2) result.append(3)
						else result.append(9)

					}

					3 -> result.append(5)
					4 -> result.append(9)
				}
			} else {
				result.append(c)
			}
		}
		return result.toString()
	}
}

class Solution {
	fun maximumTime(s: String): String {
		var isPastTwenty = s[0] == '2'
		val isPreTwenty = s[1].isDigit() && s[1].digitToInt() > 3
		val sb = StringBuilder(5)

		s.forEachIndexed { id, c ->
			if (c == '?') {
				when (id) {
					0 -> {
						if (isPreTwenty) {
							sb.append('1')
						} else {
							sb.append('2')
							isPastTwenty = true
						}
					}

					1 -> if (isPastTwenty) sb.append('3') else sb.append('9')

					2 -> sb.append(':')
					3 -> sb.append('5')
					else -> sb.append('9')
				}
			} else {
				sb.append(c)
			}
		}
		return sb.toString()
	}
}