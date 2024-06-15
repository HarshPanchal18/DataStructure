/*
* Word Pattern
Given a pattern and a string s, find if s follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

Example 1:
Input: pattern = "abba", s = "dog cat cat dog"
Output: true

Example 2:
Input: pattern = "abba", s = "dog cat cat fish"
Output: false

Example 3:
Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false

Constraints:
1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
*/

fun main() {
	println(wordPattern("abba", "dog cat cat dog"))
	println(wordPattern("abba", "dog cat cat fish"))
}

fun wordPattern(pattern: String, str: String): Boolean {
	val words = str.split(" ")//.dropLastWhile { it.isEmpty() }.toTypedArray()

	if (words.size != pattern.length)
		return false

	val charIndexMap: MutableMap<Char, Int> = HashMap()
	val wordIndexMap: MutableMap<String, Int> = HashMap()

	for (i in words.indices) {
		val char = pattern[i]
		val charIndex = charIndexMap.put(char, i)

		val word = words[i]
		val wordIndex = wordIndexMap.put(word, i)

		if (charIndex != wordIndex)
			return false
	}

	return true
}

class Solution {
	fun wordPattern(pattern: String, s: String): Boolean {
		val words = s.split(" ")

		if (pattern.length != words.size)
			return false

		val hashMap = hashMapOf<Char, String>()

		for (index in pattern.indices) {
			if (!hashMap.containsKey(pattern[index]) && !hashMap.containsValue(words[index]))
				hashMap[pattern[index]] = words[index]
			else if (hashMap[pattern[index]] != words[index])
				return false

		}

		return true
	}
}

class Solution {
	fun wordPattern(pattern: String, s: String): Boolean {
		val words = s.split(" ")
		if (words.size != pattern.length)
			return false

		val matchers = mutableMapOf<Char, String>()
		for (i in pattern.indices)
			if (matchers.getOrPut(pattern[i]) { words[i] } != words[i])
				return false

		return matchers.values.toSet().size == matchers.size
	}
}

class Solution {
	fun wordPattern(pattern: String, s: String): Boolean {
		val map = mutableMapOf<Char, String>() // key - char from pattern, value - word from s
		val set = mutableSetOf<String>() // set of all the words
		var index = 0 // offset in string s to find next word

		for (i in pattern.indices) {
			var word = "" // find next word
			// index - start of new word
			for (r in index..s.length) {
				// end of string or space - end of word
				if (r == s.length || s[r] == ' ') {
					word = s.substring(index, r)
					index = r + 1
					break
				}
			}

			if (word == "")
				return false

			// check map not contains current char
			if (!map.contains(pattern[i])) {
				if (!set.contains(word)) { // check set not contains current word
					map[pattern[i]] = word // update map and set
					set.add(word)
				} // else check has faied, pattern is not correct
				else {
					return false
				}
			} else {
				if (map[pattern[i]] != word) // if map contains current char check the value
					return false
			}
		}
		// in the end check all words in string have been checked
		return index >= s.length
	}
}

class Solution {
	fun wordPattern(pattern: String, s: String): Boolean {
		val words = s.split(" ")
		val letters = pattern.toCharArray()

		val map = mutableMapOf<Char, String>()

		for (i in words.indices) {
			try {
				val word = words[i]
				val letter = letters[i]
				if (!map.containsValue(word))
					map[letter] = word
			} catch (e: ArrayIndexOutOfBoundsException) {
				return false
			}
		}

		return s == letters.map { map[it] }.joinToString(separator = " ")
	}
}

class Solution {
	fun wordPattern(pattern: String, string: String): Boolean {
		val chars = pattern.withIndex()
			.groupBy({ it.value }) { it.index }
		val words = string.split(' ')
			.withIndex()
			.groupBy({ it.value }) { it.index }

		return chars.size == words.size &&
				chars.values
					.zip(words.values)
					.fold(true) { acc, p ->
						acc && p.first == p.second
					}
	}
}

class Solution {
	fun wordPattern(pattern: String, s: String): Boolean {
		val map = mutableMapOf<Char, String>()
		var words = s
		val builder = StringBuilder()

		for (char in pattern) {
			val substring = words.substringBefore(" ")

			if (map[char] == null && !map.containsValue(substring))
				map[char] = substring

			words = words.drop(substring.length + 1)
			builder.append(" ${map[char]}")
		}
		return builder.toString().trimStart() == s
	}
}

class Solution {
	fun wordPattern(pattern: String, s: String): Boolean {
		val words = s.split(" ")

		if (pattern.length != words.size)
			return false

		val patternMapping = mutableMapOf<Char, String>()

		for (i in pattern.indices) {
			if (patternMapping[pattern[i]] == null) {
				val mapThatShareSameValue = patternMapping.filterValues { it == words[i] }

				if (mapThatShareSameValue.isEmpty()) {
					patternMapping[pattern[i]] = words[i]
				} else {
					return false
				}
			} else {
				if (patternMapping[pattern[i]] != words[i]) return false
			}
		}
		return true
	}
}