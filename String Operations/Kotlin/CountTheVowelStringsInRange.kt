import java.util.Arrays

/*
* Count the Number of Vowel Strings in Range
You are given a 0-indexed array of string words and two integers left and right.
A string is called a vowel string if it starts with a vowel character and ends with a vowel character where vowel characters are 'a', 'e', 'i', 'o', and 'u'.
Return the number of vowel strings words[i] where i belongs to the inclusive range [left, right].

Example 1:
Input: words = ["are","amy","u"], left = 0, right = 2
Output: 2
Explanation:
- "are" is a vowel string because it starts with 'a' and ends with 'e'.
- "amy" is not a vowel string because it does not end with a vowel.
- "u" is a vowel string because it starts with 'u' and ends with 'u'.
The number of vowel strings in the mentioned range is 2.

Example 2:
Input: words = ["hey","aeo","mu","ooo","artro"], left = 1, right = 4
Output: 3
Explanation:
- "aeo" is a vowel string because it starts with 'a' and ends with 'o'.
- "mu" is not a vowel string because it does not start with a vowel.
- "ooo" is a vowel string because it starts with 'o' and ends with 'o'.
- "artro" is a vowel string because it starts with 'a' and ends with 'o'.
The number of vowel strings in the mentioned range is 3.

Constraints:
1 <= words.length <= 1000
1 <= words[i].length <= 10
words[i] consists of only lowercase English letters.
0 <= left <= right < words.length
*/

fun main() {
	vowelStrings(arrayOf("are", "amy", "u"), 0, 2)
}

fun vowelStrings(words: Array<String>, left: Int, right: Int): Int {
	return Arrays.stream(words, left, right + 1)
		.filter { word -> "aeiou".indexOf(word.first()) >= 0 && "aeiou".indexOf(word.last()) >= 0 }.count().toInt()
}

class Solution {
	fun vowelStrings(words: Array<String>, left: Int, right: Int): Int {
		var counter = 0

		for (i in left..right) {
			val length = words[i].length - 1
			val word = words[i]

			if (isCharVowel(word.first()) && isCharVowel(word[length]))
				counter++
		}

		return counter
	}

	private fun isCharVowel(digit: Char): Boolean {
		val vowels = mutableListOf('a', 'e', 'i', 'o', 'u')
		return vowels.any { it == digit }
		//return vowels.contains(digit)
	}
}

class Solution {
	fun vowelStrings(words: Array<String>, left: Int, right: Int): Int {
		val vowels = setOf('a', 'e', 'i', 'o', 'u')
		var count = 0

		for (i in left..right) {
			val word = words[i]

			if (vowels.contains(word.first()) && vowels.contains(word.last()))
				count++
		}

		return count
	}
}

class Solution {
	fun vowelStrings(words: Array<String>, left: Int, right: Int): Int {
		val vowels = "aeiou"
		var count = 0

		for (i in left..right) {
			if (words[i].first() in vowels && words[i].last() in vowels)
				count++
		}

		return count
	}
}