import kotlin.math.min

/*
* Restore IP Addresses

A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s.
You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

Example 1:
Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]

Example 2:
Input: s = "0000"
Output: ["0.0.0.0"]

Example 3:
Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

Constraints:
1 <= s.length <= 20
s consists of digits only.
*/

fun main() {
	print(Solution().restoreIpAddresses("25525511135"))
}

fun restoreIpAddresses(ip: String): ArrayList<String> {
	// replacing 4 with any number gives us that many subsections.
	return makeIP(ip, 4)
}

fun makeIP(A: String, n: Int): ArrayList<String> {
	val arr = ArrayList<String>()
	var sub: ArrayList<String>

	if (A.length > n * 3) return arr

	if (A.isEmpty()) return arr

	if (n == 1) {
		if (A[0] == '0' && A.length > 1) return arr

		if (A.toInt() > 255) return arr

		arr.add(A)
		return arr
	}

	if (A[0] == '0') {
		sub = makeIP(A.substring(1), n - 1)
		for (s in sub) arr.add("0.$s")

		return arr
	}

	for (i in 0..<min(3.0, A.length.toDouble()).toInt()) { //3 is max subsection digits
		val x = A.substring(0, i + 1)
		if (x.toInt() < 256) {
			sub = makeIP(A.substring(i + 1), n - 1)
			for (s in sub) arr.add("$x.$s")
		}
	}

	return arr
}

class Solution {
	fun restoreIpAddresses(s: String): List<String> {
		return explore(s, 0, 4, "")
	}

	private fun explore(s: String, start: Int, rem: Int, current: String): List<String> {
		if (start == s.length && rem == 0)
			return listOf(current)
		else if (start >= s.length || rem == 0)
			return listOf()

		val prepend = if (rem == 4) "" else "."
		if (s[start] == '0') {
			return explore(s, start + 1, rem - 1, current + prepend + "0")
		} else {
			val toReturn = mutableListOf<String>()
			for (i in 1..3) {
				if (start + i > s.length)
					break

				val tempS = s.substring(start, start + i)
				val temp = tempS.toInt()

				if (temp <= 255)
					toReturn.addAll(explore(s, start + i, rem - 1, current + prepend + tempS))

			}

			return toReturn
		}
	}
}

class Solution {
	fun restoreIpAddresses(s: String): List<String> {

		val ans = mutableListOf<String>()

		fun isValidIp(part: String): Boolean {
			val num = part.toInt()
			return num in 0..255 && num.toString() == part
		}

		if (s.length > 12)
			return ans

		for (i in 1..3) {
			for (j in i + 1..i + 3) {
				for (k in j + 1..j + 3) {

					if (k < s.length) {

						val p1 = s.substring(0, i)
						if (!isValidIp(p1))
							continue

						val p2 = s.substring(i, j)
						if (!isValidIp(p2))
							continue

						val p3 = s.substring(j, k)
						if (!isValidIp(p3))
							continue

						val p4 = s.substring(k)
						if (!isValidIp(p4))
							continue

						ans.add("$p1.$p2.$p3.$p4")
					}
				}
			}
		}
		return ans
	}
}

class Solution {
	fun restoreIpAddresses(s: String): List<String> {
		val result = mutableListOf<List<String>>()
		backtrack(s, 0, mutableListOf(), result)
		return result.map { list ->
			list.joinToString(".")
		}
	}

	fun backtrack(s: String, start: Int, current: MutableList<String>, total: MutableList<List<String>>) {
		// println("start=$start, current=$current, total=$total s.length=${s.length}")
		if (start == s.length && current.size == 4) {
			total.add(current.toList())
			return
		}

		if (start >= s.length || current.size >= 4)
			return

		for (i in start + 1..s.length) {
			if (i - start > 3)
				break
			if (s[start] == '0' && i - start > 1)
				break

			val num = s.substring(start, i)
			// println("check: $num, start=$start, i=$i")
			if (num.toInt() <= 255) {
				current.add(num)
				backtrack(s, i, current, total)
				current.removeAt(current.size - 1)
			}
		}
	}
}

class Solution {
	fun restoreIpAddresses(s: String): List<String> {
		val result = mutableListOf<String>()

		// 27 = 3*3*3
		for (variant in 0..<27) {
			val lengths: List<Int> = mutableListOf(
				variant % 3 + 1,
				(variant / 3) % 3 + 1,
				(variant / 9) % 9 + 1
			).apply { add(s.length - sum()) }.toList()

			if (lengths[3] !in 1..3) continue

			val indices = lengths.fold(listOf(0)) { acc, x ->
				acc + (x + acc.last())
			}

			val chunks = indices
				.take(4)
				.zip(indices.drop(1))
				.map { s.substring(it.first, it.second) }

			// leading zeros?
			if (
				chunks.any { it.length > 1 && it[0] == '0' }
			) continue

			// is int in valid range?
			if (
				chunks
					.map { it.toIntOrNull() }
					.any { it == null || it !in 0..255 }
			)
				continue

			result.add(chunks.joinToString("."))
		}

		return result
	}
}

class Solution {
	// hint:  return all possible valid IP addresses
	// This indicates that we can use backtrack or at least attempt to.
	fun restoreIpAddresses(s: String): List<String> {
		val result = ArrayList<String>()
		backtrack(0, s, result, "", 0)
		return result
	}

	fun backtrack(index: Int, s: String, result: ArrayList<String>, cur: String, /* track man dots */dots: Int) {
		// base-case
		// We check for `4` dots, because we count the last one
		// after the last number, e.g. 255.255.255.255.
		// Also, we check against the length of string because
		// we assume that now we have traversed the whole string.
		if (dots == 4 && index == s.length) {
			// Remove last dot.
			result.add(cur.substring(0, cur.length - 1)) // O(n)
			return
		}

		if (dots > 4) return
		// Here we actually want to iterate only 3 digits,
		// but we have to check for bounds in case we are off.
		val minBound = minOf(index + 3, s.length)
		for (j in index..<minBound) {

			val seg = s.substring(index, j + 1)
			val int = seg.toInt()
			// Check for leading zero:
			// `index == j`: we check if the length of this digit
			// is equal to one, that means it is oke to be any number plus zero.
			// In case it is not one, then we check if the last one was zero.
			if (int < 256 && (index == j || s[index] != '0')) {
				backtrack(j + 1, s, result, "$cur$seg.", dots + 1)
			}
		}
	}
}

class Solution {
	fun restoreIpAddresses(s: String): List<String> {
		val validIPAddresses = mutableListOf<String>()

		if (s.length > 12)
			return listOf()

		fun backtrack(i: Int, dotNum: Int, currIP: String) {
			if (dotNum == 4 && i == s.length) {
				validIPAddresses.add(currIP.substring(0, currIP.length - 1))
				return
			}

			if (dotNum > 4)
				return

			for (j in i..<min(i + 3, s.length)) {
				if (s.substring(i, j + 1).toInt() <= 255 && (i == j || s[i] != '0')) {
					backtrack(j + 1, dotNum + 1, currIP + s.substring(i, j + 1) + '.')
				}
			}
		}

		backtrack(0, 0, "")
		return validIPAddresses
	}
}

class Solution {
	fun restoreIpAddresses(s: String): List<String> {
		val answer = mutableListOf<String>()
		val length = s.length

		fun isValidForm(part: String): Boolean {
			if (part.isEmpty() || part.length > 3)
				return false
			if (part.length > 1 && part[0] == '0')
				return false
			return part.toInt() in 0..255
		}

		for (i in 1..length - 2) { // first segment, e.g. if s is "25525511135", the first segment could be "2", "25", or "255".
			for (j in i + 1..<length - 1) { // second segment, e.g. if the first segment is "25", the second segment could be "2", "25", or "255".
				for (k in j + 1..<length) { // third segment, e.g. if the first segment is "25" and the second segment is "25", the third segment could be "1", "11", or "111".
					println("$i-$j-$k")
					// Preventing segments longer than 3 characters.
					if (j >= i + 4 || k >= j + 4)
						break

					val a = s.substring(0, i)
					val b = s.substring(i, j)
					val c = s.substring(j, k)
					val d = s.substring(k, length)

					if (isValidForm(a) &&
						isValidForm(b) &&
						isValidForm(c) &&
						isValidForm(d)
					)
						answer.add("$a.$b.$c.$d")
				}

			}
		}

		return answer
	}
}