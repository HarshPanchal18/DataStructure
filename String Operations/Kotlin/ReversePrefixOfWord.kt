/*
* Reverse Prefix of Word
Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.
For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
Return the resulting string.

Example 1:
Input: word = "abcdefd", ch = "d"
Output: "dcbaefd"
Explanation: The first occurrence of "d" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".

Example 2:
Input: word = "xyxzxe", ch = "z"
Output: "zxyxxe"
Explanation: The first and only occurrence of "z" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".

Example 3:
Input: word = "abcd", ch = "z"
Output: "abcd"
Explanation: "z" does not exist in word.
You should not do any reverse operation, the resulting string is "abcd".
*/

fun main() {
    print(reversePrefix("xyxzxe","z"))
}

fun reversePrefix(word: String, ch: Char): String {
	var left = 0
	var right = word.indexOf(ch)
	if (right == -1)
		return word

	val arr = word.toCharArray()

	while (left < right) {
		// Swap characters at left and right
		val temp = arr[left]
		arr[left] = arr[right]
		arr[right] = temp
		left++
		right--
	}

	// Convert CharArray back to String and return
	return String(arr)
}

class Solution {
	fun reversePrefix(word: String, ch: Char): String {
		val length = word.length
		var index = 0
		while (index < word.length) {
			val char = word[index]
			if (char == ch) break
			index++
		}

		if (index == length) return word

		val stringBuilder = StringBuilder()
		val prefixIndex = index + 1
		while (index >= 0) {
			val char = word[index]
			stringBuilder.append(char)
			index--
		}

		if (prefixIndex < length) {
			val substring = word.substring(prefixIndex, word.length)
			stringBuilder.append(substring)
		}

		return stringBuilder.toString()
	}
}

class Solution {
	fun reversePrefix(word: String, ch: Char): String {
		val arr = word.toCharArray()
		var right = 0
		while (right < word.length && word[right] != ch) right++
		if (right == word.length) return word
		var tmp: Char
		for (i in 0..right / 2) {
			tmp = arr[i]
			arr[i] = arr[right - i]
			arr[right - i] = tmp
		}
		return String(arr)
	}
}

class Solution {
	fun reversePrefix(word: String, ch: Char): String {
		if (word.isEmpty()) return ""

		return buildString {
			for (i in word.indices) {
				if (word[i] == ch) {
					append(word[i]).reverse()
					append(word.substring(i + 1))
					return@buildString
				} else {
					append(word[i])
				}
			}
		}
	}
}

class Solution {
	fun reversePrefix(word: String, ch: Char): String {
		val result = word.toCharArray()
		var left = 0
		var right = 0 //word.indexOf(ch)
		while (right < word.length && word[right] != ch) {
			right++
		}

		if (right == word.length || word[right] != ch) {
			return word
		}

		while (left < right) {
			val ch = result[left]
			result[left] = result[right]
			result[right] = ch
			left++
			right--
		}

		return String(result)
	}
}