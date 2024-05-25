/*
* Word Break
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false

Constraints:
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/

fun main() {
    println(wordBreak("leetcode", listOf("leet", "code")))
}

val visited = HashSet<String>() // dead end

fun wordBreak(s: String, wordDict: List<String>): Boolean {
    if (s.isEmpty())
        return true
    else if (visited.contains(s))
        return false
    else {
        for (word in wordDict) {
            if (s.startsWith(word)) {
                if (wordBreak(s.substring(word.length), wordDict))
                    return true
            }
        }
        visited.add(s)
        return false
    }
}

class Solution {
    private lateinit var s: String
    private lateinit var wordDict: Set<String>
    private lateinit var memo: Array<MutableList<Boolean?>>

    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        this.s = s
        this.wordDict = wordDict.toHashSet()
        memo = Array(s.length + 1) { MutableList<Boolean?>(s.length + 1) { null } }
        memo[s.length][s.length] = true
        return dfs(0, s.length)
    }

    private fun dfs(i: Int, j: Int): Boolean {
        if (i > j)
            return false

        return memo[i][j] ?: run {
            memo[i][j] =
                if (!exists(s.substring(i, j))) dfs(i, j - 1)
                else dfs(j, s.length) || dfs(i, j - 1)
            memo[i][j] ?: false
        }
    }

    private fun exists(str: String): Boolean {
        return wordDict.contains(str)
    }
}

class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val n = s.length
        val dp = BooleanArray(n + 1) { false }
        dp[n] = true

        for (i in n downTo 0) {
            for (w in wordDict) {
                if (i + w.length <= n && w == s.substring(i, i + w.length))
                    dp[i] = dp[i + w.length]
                if (dp[i])
                    break
            }
        }

        return dp.first()
    }
}

class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val n = s.length
        val dp = BooleanArray(n + 1)
        dp[n] = true

        for (i in n - 1 downTo 0) {
            dp[i] = wordDict.any {
                (it.length + i <= n && s.substring(i, i + it.length) == it) && dp[it.length + i]
            }
        }

        return dp[0]
    }
}

class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val n = s.length
        val results = MutableList(n + 1) { false }
        results[n] = true

        for (index in n - 1 downTo 0) {
            for (word in wordDict) {
                if (index + word.length <= results.lastIndex) {
                    if (s.startsWith(word, index)) {
                        println("match found at $index with word $word")
                        results[index] = results[index + word.length]
                        if (results[index])
                            break
                    }
                }
            }
        }
        //results.printWithIndex()
        return results[0]
    }
}

class Solution {
    fun wordBreak(s: String, wordDict: List<String>, start: Int = 0, memo: MutableMap<Int, Boolean> = mutableMapOf()): Boolean {
        if (start == s.length)
            return true

        if (memo.containsKey(start))
            return memo[start]!!

        for (i in start until s.length) {
            val substring = s.substring(start, i + 1)
            if (wordDict.contains(substring) && wordBreak(s, wordDict, i + 1, memo))
                return true
        }
        memo[start] = false
        return false
    }
}

class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val dp = MutableList(s.length + 1) { it == s.length }
        var i = s.length
        while (i >= 0) {
            if (!dp[i]) {
                i--
                continue
            }

            for (word in wordDict) {
                var chs = i-1
                var chw = word.lastIndex

                while(chw >= 0 && chs >= 0 && word[chw] == s[chs]) {
                    chw--
                    chs--
                }
                if (chw < 0) {
                    println("$chs $word")
                    dp[chs + 1] = dp[chs + 1 + word.length]
                }
            }
            i--
        }
        print(dp)
        return dp[0]
    }
}

class Solution {
    val seen = mutableMapOf<String,Boolean>()
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        return wordConstructed(s,0,wordDict.toSet())
    }

    private fun wordConstructed(word:String, start:Int, dict: Set<String>): Boolean {
    //    println("seen: $seen")
        if(start >= word.length)
            return true

        var current = ""

        for(i in start until word.length) {

            current += word[i]
            val remaining = word.substring(i+1)

            if(seen.contains(remaining)){
                if(seen[remaining]!!) return true
                continue
            }
            val matchFound = dict.contains(remaining)
            if(dict.contains(current) && matchFound) {
                return true
            }
            val checkComplete = if(dict.contains(current)){
                val remain = wordConstructed(word,i+1,dict)
                seen[remaining] = remain
                remain
            }  else false

            if(checkComplete) return true
        }

        return false
    }
}

// [leet, le, ee, etcode, eet]
// for i in 0 until size
//  check for each char word and see if match found
//      if match found, record the word
//      else

class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {

        if(s.isEmpty())
            return false

        val len = s.length
        val wordSet = HashSet(wordDict)

        val dp = BooleanArray(len + 1)
        dp[0] = true

        for(hi in 1..len)
            for(lo in 0..hi)
                if(dp[lo] && wordSet.contains(s.substring(lo, hi))) {
                    dp[hi] = true
                    break
                }

        return dp[len]
    }
}