/*
* Valid Palindrome
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.

Constraints:
1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
*/

fun main() {
	println(isPalindrome("A man, a plan, a canal: Panama"))
	println(isPalindrome("race a car"))
	println(isPalindrome("0P"))
}

fun isPalindrome(s: String): Boolean {
	var left = 0
	var right = s.lastIndex

	while (left < right) {
		while (!s[left].isLetterOrDigit())
			left++

		while (!s[right].isLetterOrDigit())
			--right

		if (s[left].lowercaseChar() != s[right].lowercaseChar())
			return false

		left++
		right--
	}

	return true
}

class Solution {
	fun isPalindrome(s: String): Boolean {
		var left = 0
		var right = s.lastIndex

		var l = s[left]
		var r = s[right]

		while (left < right) {
			if (!l.isLetter() && !l.isDigit()) {
				l = s[++left]
				continue
			}

			if (!r.isLetter() && !r.isDigit()) {
				r = s[--right]
				continue
			}

			if (l.lowercaseChar() != r.lowercaseChar())
				return false

			l = s[++left]
			r = s[--right]
		}

		return true
	}
}

class Solution {
	fun isPalindrome(s: String): Boolean {
		var j = s.lastIndex
		var ch: Char
		var ch2 = ' '

		for (i in s.indices) {
			ch = s[i]

			if (!(ch in 'a'..'z' ||
						ch in 'A'..'Z' ||
						ch in '0'..'9')
			)
				continue

			if (ch in 'A'..'Z')
				ch = ch.lowercaseChar()

			while (i <= j) {
				ch2 = s[j]
				if (!(ch2 in 'a'..'z' ||
							ch2 in 'A'..'Z' ||
							ch2 in '0'..'9')
				) {
					j--
					continue
				}

				if (ch2 in 'A'..'Z')
					ch2 = ch2.lowercaseChar()

				j--
				break
			}
			// println("i=$i,ch=$ch,j=$j,ch2=$ch2")
			if (i > j && ch == ch2)
				return true
			if (ch != ch2)
				return false
		}

		return true
	}
}

class Solution {
	fun isPalindrome(s: String): Boolean {

		val str = s.filter {
			!it.isWhitespace()
			it.isLetterOrDigit()
		}.lowercase()

		if (str.isBlank() || str.length == 1)
			return true

		var left = 0
		var right = str.lastIndex

		while (left < right) {
			if (str[left] != str[right])
				return false
			left++
			right--
		}

		return true
	}
}

class Solution {
	fun isPalindrome(s: String): Boolean {
		val allChars = "abcdefghijklmnopqrstuvxyz0123456789".toSet()
		var normalizedStr = ""

		s.forEach {
			if (allChars.contains(it.lowercaseChar()))
				normalizedStr += it
		}

		var lPointer = 0
		var rPointer = normalizedStr.lastIndex

		print(normalizedStr)

		while (lPointer < normalizedStr.length / 2) {
			if (normalizedStr[lPointer].lowercaseChar() != normalizedStr[rPointer].lowercaseChar())
				return false

			lPointer++
			rPointer--
		}

		return true
	}
}