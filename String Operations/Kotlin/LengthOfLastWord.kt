/*
* Length of Last Word

Given a string s consisting of words and spaces, return the length of the last word in the string.
A word is a maximal substring consisting of non-space characters only.

Example 1:
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.

Example 2:
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.

Example 3:
Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.

Constraints:
1 <= s.length <= 104
s consists of only English letters and spaces ' '.
There will be at least one word in s.
*/

fun main() {
	println(lengthOfLastWord("Hello World"))
	println(lengthOfLastWord("  fly me to the moon  "))
}

fun lengthOfLastWord(s: String): Int {
	return s.trim().run { length - lastIndexOf(" ") - 1 }
}

fun lengthOfLastWord(s: String): Int {
	val startIndex = s.trim().lastIndexOf(" ") - 1
	return s.trim().length - startIndex
}

class Solution {
	fun lengthOfLastWord(s: String): Int {
		var count = 0
		for (i in s.length - 1 downTo 0)
			if (s[i] != ' ')
				count++
			else if (count > 0)
				return count

		return count
	}
}

class Solution {
	fun lengthOfLastWord(s: String): Int {
		var count = 0
		var start = false

		for (i in s.length - 1 downTo 0) {
			if (s[i] == ' ' && start) {
				return count
			}
			if (start) {
				count++
			} else {
				if (s[i] != ' ' && !start) {
					start = true
					count++
				}
			}
		}
		return count
	}
}

class Solution {
	fun lengthOfLastWord(message: String): Int {
		val cleanMessage = message.trim()
		val messages = cleanMessage.split(" ").reversed()
		val lastWord = messages[0]
		return lastWord.length
	}
}

class Solution {
	fun lengthOfLastWord(s: String): Int {
		if (s.isEmpty()) return 0

		var rightWordIndex = s.length - 1

		// skip past trailing spaces
		while (rightWordIndex >= 0 && s[rightWordIndex] == ' ') {
			rightWordIndex--
		}

		var wordLen = 0
		while (rightWordIndex >= 0 && s[rightWordIndex] != ' ') {
			wordLen++
			rightWordIndex--
		}

		return wordLen
	}
}

class Solution {
	fun lengthOfLastWord(s: String): Int {
		val arr = s.trim().split(" ")
		return arr[arr.size - 1].length
	}
}

class Solution {
	fun lengthOfLastWord(s: String): Int {
		val stringArray = s.trim().split(" ")
		val finalWord = stringArray[stringArray.size - 1]
		return finalWord.length
	}
}

class Solution {
	fun lengthOfLastWord(s: String): Int {
		val wordsArray = s.split(" ")
		var j = wordsArray.size - 1
		var maxCount = 0

		while (maxCount <= 0) {
			if (wordsArray[j] != " ")
				maxCount = wordsArray[j].length
			j--
		}

		return maxCount
	}
}