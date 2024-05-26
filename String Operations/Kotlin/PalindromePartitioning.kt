import java.util.*
import kotlin.collections.ArrayList

/*
* Palindrome Partitioning
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:
Input: s = "a"
Output: [["a"]]

Constraints:
1 <= s.length <= 16
s contains only lowercase English letters.
*/

fun main() {
    partition("aab")
    partition("abcaa")
}

// aab -> [a,a,b] [aa,b]
fun subsets(s: String): List<List<String>> {
    val answer = ArrayList<List<String>>()
    var left = 0

    while (left < s.length) {
        val substrings = s.slice(0..left)
        println(substrings)
        left++
    }

    return answer
}

fun partition(s: String): List<List<String>> {
    val result = ArrayList<List<String>>()
    backtrack(s, 0, ArrayList(), result)
    return result
}

fun backtrack(s: String, start: Int, path: ArrayList<String>, result: ArrayList<List<String>>) {
    if (start == s.length) {
        result.add(ArrayList(path))
        return
    }

    for (end in start + 1..s.length) {
        if (isPalindrome(s, start, end - 1)) {
            path.add(s.substring(start, end))
            backtrack(s, end, path, result)
            path.removeLast()
        }
    }
}

fun isPalindrome(s: String, left: Int = 0, right: Int = 0): Boolean {
    var left = left
    var right = right

    while (left < right)
        if (s[left++] != s[right--])
            return false

    return true
}

class Solution {

    fun partition(s: String): List<List<String>> {
        val n = s.length
        val dp = Array(n) { BooleanArray(n) }

        // Initialize the DP table for single characters and pairs
        for (i in 0 until n)
            dp[i][i] = true

        for (length in 2..n) {
            for (i in 0..n - length) {
                val j = i + length - 1

                if (s[i] == s[j] && (length == 2 || dp[i + 1][j - 1]))
                    dp[i][j] = true
            }
        }

        val result: MutableList<List<String>> = ArrayList()
        backtrack(s, 0, ArrayList(), result, dp)
        return result
    }

    private fun backtrack(
        s: String,
        start: Int,
        path: MutableList<String>,
        result: MutableList<List<String>>,
        dp: Array<BooleanArray>
    ) {
        // If we've reached the end of the string, add the current partition to the result list
        if (start == s.length) {
            result.add(ArrayList(path))
            return
        }
        // Explore all possible partitions
        for (end in start until s.length) {
            // Use the DP table to check if the substring s[start:end+1] is a palindrome
            if (dp[start][end]) {
                path.add(s.substring(start, end + 1))
                // Recur to find other partitions
                backtrack(s, end + 1, path, result, dp)
                // Backtrack to explore other partitions
                path.removeLast()
            }
        }
    }
}

class Solution {

    fun partition(s: String): List<List<String>> {
        val result = mutableListOf<List<String>>()
        val curr = mutableListOf<String>()
        val lenS = s.length

        fun explore(index: Int) {

            if (index >= lenS) {
                result.add(curr.toList())
                return
            }

            for (i in index until lenS) {
                val subStr = s.substring(index, i + 1)

                if (isPalindrome(subStr)) {
                    curr.add(subStr)
                    explore(i + 1)
                    curr.removeAt(curr.size - 1)
                }
            }
        }

        explore(0)
        return result
    }

    private fun isPalindrome(s: String): Boolean {
        var left = 0
        var right = s.lastIndex

        while (left < right) {

            if (s[left] != s[right])
                return false

            left++
            right--
        }

        return true
    }
}

class Solution {

    private val partitions = LinkedList<LinkedList<String>>()
    private lateinit var text: String
    private val currentPartition = LinkedList<String>()

    fun partition(s: String): List<List<String>> {
        text = s
        inner()
        return partitions
    }

    private fun inner(position: Int = 0) {
        for (i in position..<text.length) {

            if (text.isPalindrome(position, i)) {
                currentPartition.add(text.substring(position, i + 1))

                if (i == text.lastIndex)
                    partitions.add(LinkedList(currentPartition))
                else
                    inner(i + 1)

                currentPartition.removeLast()
            }
        }
    }

    private fun String.isPalindrome(startIndex: Int, endIndex: Int): Boolean {
        if (startIndex == endIndex)
            return true

        for (i in 0..(endIndex - startIndex) / 2)
            if (get(startIndex + i) != get(endIndex - i))
                return false

        return true
    }

}

class Solution {

    val results = HashMap<Int, List<List<String>>>()

    fun partition(s: String): List<List<String>> {
        val palindromeMatrix = Array(s.length) { BooleanArray(s.length) }

        for (i in s.indices) {
            palindromeMatrix[i][i] = true
            if (i < s.length - 1)
                palindromeMatrix[i][i + 1] = s[i] == s[i + 1]
        }

        for (i in s.length - 3 downTo 0) {
            for (j in i + 2..s.lastIndex)
                palindromeMatrix[i][j] = s[i] == s[j] && palindromeMatrix[i + 1][j - 1]
        }

        return findAll(palindromeMatrix, s, 0)
    }

    fun findAll(palindromeMatrix: Array<BooleanArray>, s: String, start: Int): List<MutableList<String>> {
        val partitionings = mutableListOf<MutableList<String>>()
        if (results.contains(start)) {
            results.getOrDefault(start, partitionings).forEach {
                partitionings.add(it.toMutableList())
            }
            return partitionings
        }

        for (i in start..s.lastIndex) {
            if (palindromeMatrix[start][i]) {
                if (i == s.length - 1) {
                    partitionings.add(mutableListOf(s.substring(start)))
                } else {
                    val result = findAll(palindromeMatrix, s, i + 1)
                    result.forEach { it.add(0, s.substring(start, i + 1)) }
                    partitionings += result
                }
            }
        }

        val toAdd = mutableListOf<List<String>>()
        partitionings.forEach { toAdd.add(it.toList()) }
        results[start] = toAdd
        return partitionings
    }
}

class Solution {

    var result: ArrayList<List<String>> = ArrayList<List<String>>()

    fun partition(s: String): List<List<String>> {
        process(s.map { it.toString() })
        return result
    }

    fun process(subs: List<String>, prefix: List<String>? = null) {
        if (subs.size == 1) {
            if (subs[0] == subs[0].reversed())
                result.add(prefix?.plus(subs) ?: subs)
            return
        }

        if (subs[0] == subs[0].reversed())
            process(subs.subList(1, subs.size), prefix?.plus(subs[0]) ?: listOf(subs[0]))

        process(
            subs.toMutableList().let {
                it[1] = it[0] + it[1]
                it.removeFirst()
                it
            },
            prefix
        )
    }
}