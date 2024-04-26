/*
* Repeated DNA Sequences
The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.
Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.

Example 1:
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]

Example 2:
Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]

Constraints:
1 <= s.length <= 105
s[i] is either 'A', 'C', 'G', or 'T'.
*/

fun findRepeatedDnaSequences(s: String): List<String?> {
	val seen: MutableSet<String> = HashSet()
	val repeated: MutableSet<String> = HashSet()
	var i = 0
	while (i + 9 < s.length) {
		val ten: String = s.substring(i, i + 10)
		if (!seen.add(ten))
			repeated.add(ten)
		i++
	}
	return ArrayList(repeated)
}

class Solution {
	fun findRepeatedDnaSequences(s: String): List<String?> {
		val ans = HashSet<String>()
		val encounter = HashSet<String>()

		for (i in 0 until s.length - 9) {
			val dna = s.substring(i, i + 10)
			if (encounter.contains(dna))
				ans.add(dna)
			encounter.add(dna)
		}
		return ArrayList(ans)
	}
}

class Solution {
	fun findRepeatedDnaSequences(s: String): List<String> {
		if (s.length < 10)
			return emptyList()

		var hash = 0L
		var pow = 1L

		for (index in 0 until 10) {
			hash = hash * 26 + (s[index] - 'a' + 1)
			pow *= 26
		}

		val result = ArrayList<String>()

		val seenMap = HashMap<Long, Int>()
		seenMap[hash] = 1

		for (index in 10 until s.length) {
			hash = hash * 26 + (s[index] - 'a' + 1)
			hash -= ((s[index - 10] - 'a' + 1) * pow)

			val count = seenMap[hash] ?: 0
			if (count == 1)
				result.add(s.substring(index - 10 + 1, index + 1))

			seenMap[hash] = count + 1
		}

		return result
	}
}

class Solution {
	fun findRepeatedDnaSequences(s: String): List<String> {
		val dic: MutableMap<String, Int> = mutableMapOf()
		val pushed: MutableMap<String, Int> = mutableMapOf()
		val answer: MutableList<String> = mutableListOf()

		var i = 0
		var j = 9
		while (j < s.length) {
			val current = s.substring(i, j + 1)

			if (current in dic && current !in pushed) {
				answer.add(current)
				pushed[current] = 1
			} else {
				dic[current] = 1
			}

			i++
			j++
		}

		return answer

	}
}

class Solution {
	fun findRepeatedDnaSequences(s: String): List<String> {
		if (s.length < 10)
			return listOf()

		val dnaSet = hashSetOf<String>()
		val dnaMap = mutableMapOf<String, Int>()
		var dna: String

		for (i in 0..s.length - 10) {
			dna = s.substring(i, i + 10)
			if (dnaMap[dna] != null)
				dnaSet.add(dna)
			dnaMap[dna] = 1
			// println(dna)
		}
		return dnaSet.toList()
	}
}

class Solution {
	fun findRepeatedDnaSequences(s: String): List<String> {
		val hashes = HashMap<String, Int>()
		var currentString: String
		var currentValue: Int
		val strings = mutableListOf<String>()

		for (i in 0 .. s.count() - s.length) {
			currentString = s.substring(i, i + s.length)
			currentValue = hashes.getOrPut(currentString) { 0 }

			if (currentValue == 1)
				strings.add(currentString)

			hashes[currentString] = currentValue + 1
		}

		return strings
	}
}