/*
* Word Break II
Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []

Constraints:
1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 105.
*/

fun main() {
	val result = wordBreak("catsanddog", listOf("cat", "cats", "and", "sand", "dog"))
	result.forEach { words ->
		println(words)
	}
}

fun wordBreak(s: String, wordDict: List<String>): List<String> = buildList {
	val set = wordDict.toSet()

    for (i in s.indices)
        if (s.take(i + 1) in wordSet)
            // If the index i reaches the last index of the string, add the entire string s to the list.
            if (i == s.lastIndex)
                add(s)
            else // Otherwise combine the valid prefix with the results from the recursive call and add to the list
                for (next in wordBreak(s.drop(i + 1), wordDict))
                    add("${s.take(i + 1)} $next")
}

class Solution {
	fun wordBreak(s: String, wordDict: List<String>): List<String> {
		val wordMap: MutableMap<Char, MutableSet<String>> = HashMap()
		for (word in wordDict) {
			wordMap.computeIfAbsent(word[0]) { mutableSetOf() }.add(word)
		}

		val results: MutableList<String> = ArrayList()

		backtrack(s, 0, ArrayList(), results, wordMap)
		return results
	}

	private fun backtrack(
		s: String,
		start: Int,
		path: MutableList<String>,
		results: MutableList<String>,
		wordMap: Map<Char, MutableSet<String>>
	) {
		if (start == s.length) {
			results.add(path.joinToString(" "))
			return
		}

		val startChar = s[start]
		if (wordMap.containsKey(startChar)) {
			for (word in wordMap[startChar] !!) {
				val end = start + word.length

				if (s.startsWith(word, start)) {
					path.add(word)
					backtrack(s, end, path, results, wordMap)
					path.removeLast()
				}

			}
		}
	}
}

class Solution {
	val map = mutableMapOf<String, Boolean>()

	//val cache = mutableMapOf<String, List<String>>()
	fun wordBreak(s: String, wordDict: List<String>): List<String> {
		for (word in wordDict)
			map[word] = true
		return fillWords(s, 0, StringBuilder(), StringBuilder())
	}

	fun fillWords(s: String, idx: Int, builder: StringBuilder, builderT: StringBuilder): List<String> {
		if (idx == s.length)
			return emptyList()

		// val key = getKey(builderT, idx)
		//  if(cache.containsKey(key))return cache[key]!!
		val res = mutableListOf<String>()
		builder.append(s[idx])

		val currentSplit = builder.toString()
		if (map.containsKey(currentSplit)) {

			val t = builderT.length
			builderT.append(currentSplit)

			if (idx == s.length - 1) {
				res.add(builderT.toString())
				return res
			}
			builderT.append(" ")
			val ls = fillWords(s, idx + 1, StringBuilder(), builderT)

			if (ls.isNotEmpty())
				res.addAll(ls)

			builderT.delete(t, builderT.length)
		}

		val ls = fillWords(s, idx + 1, builder, builderT)
		if (ls.isNotEmpty())
			res.addAll(ls)

		//   cache[key] = res
		return res
	}
}

class Solution {

	// Implement generic trie
	class Trie<E> {
		data class Node<E>(val links: MutableMap<E, Node<E>> = mutableMapOf(), var end: Boolean = false) {
			operator fun get(e: E) = links[e]
		}

		val root = Node<E>()
		fun add(item: Iterable<E>) {
			var n = root
			for (c in item)
				n = n.links.getOrPut(c) { Node() }

			n.end = true
		}
	}

	fun wordBreak(s: String, wordDict: List<String>): List<String> {
		val t = Trie<Char>()
		for (w in wordDict)
			t.add(w.asIterable())

		val result = mutableListOf<String>()

		fun walk(i: Int, n: Trie.Node<Char>, line: StringBuilder) {
			val c = s[i]
			// check if there is word with current letter, or abort otherwise
			val node = n[c] ?: return
			// append current char to current line
			line.append(c)
			when {
				// check, if we on lastIndex and current word is correct
				i == s.lastIndex && node.end -> {
					result += line.toString()
				}

				i < s.lastIndex -> {
					// continue to iterate thought s
					// if we on end of yhe word (node.end == true), then add separator and
					// continue to iterate looking for new word from dictionary
					if (node.end) {
						val bn = StringBuilder(line)
						bn.append(' ')
						walk(i + 1, t.root, bn)
					}
					// or just continue to iterate
					walk(i + 1, node, line)
				}
				// it's (i == s.lastIndex && !node.end) - last used word is not correct, so do not add line to result
				else -> Unit
			}
		}

		walk(0, t.root, StringBuilder())
		return result
	}
}

class Solution {
	val mem = hashMapOf<String, List<String>>()

	fun wordBreak(s: String, wordDict: List<String>): List<String> {
		if (mem.contains(s))
			return mem[s] ?: listOf()

		if (s == "")
			return mutableListOf("")

		val res = mutableListOf<String>()
		for (v in wordDict) {
			if (s.startsWith(v)) {
				for (comp in wordBreak(s.substring(v.length, s.length), wordDict)) {
					res.add(("$v $comp").trim())
				}
			}
		}
		mem[s] = res
		return res
	}
}

class Solution {
	private class TrieNode(
		val children: MutableMap<Char, TrieNode> = mutableMapOf(),
		var isWord: Boolean = false
	)

	private fun buildTrie(wordDict: List<String>): TrieNode {
		val root = TrieNode()
		wordDict.forEach { w ->
			var node = root
			w.forEach { c ->
				node = node.children.getOrPut(c) { TrieNode() }
			}
			node.isWord = true
		}

		return root
	}

	private fun helper(
		s: String,
		trieRoot: TrieNode,
		index: Int,
		currentPrefix: StringBuilder,
		result: MutableList<String>
	) {
		if (index == s.length)
			result.add(currentPrefix.toString().trimEnd())

		var node = trieRoot
		val originalLength = currentPrefix.length
		for (i in index until s.length) {
			node = node.children[s[i]] ?: break
			currentPrefix.append(s[i])
			if (node.isWord) {
				currentPrefix.append(" ")
				helper(s, trieRoot, i + 1, currentPrefix, result)
				currentPrefix.setLength(currentPrefix.length - 1)
			}
		}
		currentPrefix.setLength(originalLength)
	}

	fun wordBreak(s: String, wordDict: List<String>): List<String> {
		val result = mutableListOf<String>()
		val root = buildTrie(wordDict)
		helper(s, root, 0, StringBuilder(), result)

		return result
	}
}

data class TrieNode(val letter: Char, val children: Array<TrieNode?>, var isEnd: Boolean = false) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrieNode

        if (letter != other.letter) return false
        if (!children.contentEquals(other.children)) return false
        if (isEnd != other.isEnd) return false

        return true
    }

    override fun hashCode(): Int {
        var result = letter.hashCode()
        result = 31 * result + children.contentHashCode()
        result = 31 * result + isEnd.hashCode()
        return result
    }
}

class Trie {
    private val root: TrieNode = TrieNode('#', Array(26) { null })

    fun add(word: String) {
        var current = root
        for (letter in word) {
            val index = letter - 'a'
            if (current.children[index] == null) {
                current.children[index] = TrieNode(letter, Array(26) { null })
            }
            current = current.children[index]!!
        }
        current.isEnd = true
    }

    fun search(word: String): Boolean {
        var current = root
        for (letter in word) {
            val index = letter - 'a'
            if (current.children[index] == null) {
                return false
            }
            current = current.children[index]!!
        }
        return current.isEnd
    }
}

class Solution {
    fun wordBreak(s: String, wordDict: List<String>): List<String> {
        val trie = Trie()
        val n = s.length

        for (word in wordDict)
            trie.add(word)

        val dp = Array(n + 1) { mutableListOf<String>() }
        dp[0].add("")

        for (i in 1..n) {
            for (j in 0 until i) {
                if (dp[j].isNotEmpty() && trie.search(s.substring(j, i))) {
                    for (word in dp[j])
                        dp[i].add(
                            if (word.isEmpty()) s.substring(j, i)
                            else "$word ${s.substring(j, i)}"
                        )
                }
            }
        }

        return dp[n]
    }
}

class Solution {
    fun wordBreak(s: String, wordDict: List<String>): List<String> {

        val cache = mutableMapOf<Int, List<String>>()

        fun dfs(start: Int): List<String> {

            if (cache.containsKey(start))
                return cache[start]!!

            val result = mutableListOf<String>()
            for (word in wordDict) {
                val end = start + word.length

                if (end > s.length)
                    continue

                val sub = s.substring(start, end)
                if (sub == word) {
                    if (end == s.length)
                        result.add(sub)
                    else
                        result.addAll(dfs(end).map { "$sub $it" })
                }
            }

            cache[start] = result
            return result
        }

        return dfs(0)
    }
}