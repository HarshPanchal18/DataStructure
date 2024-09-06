/*
* Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Constraints:
1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lowercase English letters.
*/

fun main() {
    println(longestCommonPrefix(arrayOf("flower", "flow", "flight")))
}

fun longestCommonPrefix(strs: Array<String>): String {
    val first = strs[0]
    first.forEachIndexed { idx, ch ->
        if (strs.any { it.length == idx || it[idx] != ch })
            return first.substring(0, idx)
    }
    return first
}

class Solution {
    fun longestCommonPrefix(str: Array<String>): String {
        var first = str[0]

        for (i in 0 until str.lastIndex) {
            first = first.commonPrefixWith(str[i + 1])
        }

        return first
    }
}

class Solution {
    fun longestCommonPrefix(strs: Array<String>): String {

        if (strs.isEmpty()) return ""

        val commonPrefixBuilder = StringBuilder()
        var currIndex = 0

        while (true) {
            strs.mapIndexed { index, str ->
                if (currIndex >= str.length || index != 0 && strs[index][currIndex] != strs[index - 1][currIndex])
                    return commonPrefixBuilder.toString()

                if (index == strs.lastIndex) {
                    commonPrefixBuilder.append(str[currIndex])
                    currIndex++
                }
            }
        }

        return commonPrefixBuilder.toString()
    }
}

class Solution {
    fun longestCommonPrefix(str: Array<String>): String {
        if (str.all { it.isEmpty() })
            return ""

        if (str.size == 1)
            return str[0]

        val min = str.minBy { it.length }.length

        for (i in 0..min) {
            val ch = str[0][i]

            for (s in str) {
                if (s[i] == ch)
                    return s.substring(0, i)
            }
        }

        return str[0].substring(0, min)

    }
}

class Solution {
    fun longestCommonPrefix(ar: Array<String>): String {
        return StringBuilder().apply {
            ar.minBy { it.length }
                .forEachIndexed { i, c ->
                    if (ar.all { it[i] == c }) append(c)
                    else return toString()
                }
        }.toString()
    }
}