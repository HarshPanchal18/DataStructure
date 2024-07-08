/*
* Find the Encrypted String
You are given a string s and an integer k. Encrypt the string using the following algorithm:
For each character c in s, replace c with the kth character after c in the string (in a cyclic manner).
Return the encrypted string.

Example 1:
Input: s = "dart", k = 3
Output: "tdar"
Explanation:
For i = 0, the 3rd character after 'd' is 't'.
For i = 1, the 3rd character after 'a' is 'd'.
For i = 2, the 3rd character after 'r' is 'a'.
For i = 3, the 3rd character after 't' is 'r'.

Example 2:
Input: s = "aaa", k = 1
Output: "aaa"
Explanation:
As all the characters are the same, the encrypted string will also be the same.

Constraints:
1 <= s.length <= 100
1 <= k <= 104
s consists only of lowercase English letters.
*/

fun main() {
	val result = getEncryptedString("dart", 3)
	println(result)
}

fun getEncryptedString(s: String, k: Int): String {
	val encryptedString = mutableListOf<Char>()
	val n = s.length

	for (i in 1..n) {
		val j = (i + k).mod(n)
		encryptedString.add(s[j])
		println(encryptedString)
	}

	return encryptedString.joinToString("")
}

class Solution {
	fun getEncryptedString(s: String, k: Int): String {
		return (s + s).substring(k % s.length, k % s.length + s.length)
	}
}

class Solution {
	fun getEncryptedString(s: String, k: Int): String {
		var k = k
		val n = s.length
		var res = ""

		k %= n

		for (i in k until n)
			res += s[i]

		for (i in 0 until k)
			res += s[i]

		return res
	}
}