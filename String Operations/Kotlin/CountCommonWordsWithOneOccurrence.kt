/*
* Count Common Words With One Occurrence

Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.

Example 1:
Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
Output: 2
Explanation:
- "leetcode" appears exactly once in each of the two arrays. We count this string.
- "amazing" appears exactly once in each of the two arrays. We count this string.
- "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
- "as" appears once in words1, but does not appear in words2. We do not count this string.
Thus, there are 2 strings that appear exactly once in each of the two arrays.

Example 2:
Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
Output: 0
Explanation: There are no strings that appear in each of the two arrays.

Example 3:
Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
Output: 1
Explanation: The only string that appears exactly once in each of the two arrays is "ab".

Constraints:
1 <= words1.length, words2.length <= 1000
1 <= words1[i].length, words2[j].length <= 30
words1[i] and words2[j] consists only of lowercase English letters.
*/

fun main() {
    println(countWords(arrayOf("leetcode", "is", "amazing", "as", "is"), arrayOf("amazing", "leetcode", "is")))
}

fun countWords(words1: Array<String>, words2: Array<String>): Int {

    val freq1 = words1.groupingBy { it }.eachCount()
    val freq2 = words2.groupingBy { it }.eachCount()
    val commons = freq1.keys intersect freq2.keys

    return commons.count {
        freq1[it] == 1 && freq1[it] == freq2[it]
    }

}

class Solution {
    fun countWords(words1: Array<String>, words2: Array<String>): Int {
        var h1 = HashMap<String, Int>()
        var h2 = HashMap<String, Int>()
        var count = 0

        for(i in words1.indices) {
            h1.put(words1[i], h1.getOrDefault(words1[i], 0) + 1)
        }

        for(i in words2.indices) {
            h2.put(words2[i], h2.getOrDefault(words2[i], 0) + 1)
        }

        for(i in words1.indices) {
            if(h1[words1[i]] == 1 && h2.contains(words1[i]) && h2[words1[i]] == 1) {
                count++
            }
        }

        return count
    }
}

class Solution {

    fun countWords(words1: Array<String>, words2: Array<String>): Int {

        val map1 = mutableMapOf<String,Int>()
        val map2 = mutableMapOf<String,Int>()

        words1.forEach{str->
                map1[str] = map1.getOrDefault(str,0)+1
        }

        words2.forEach{str->
                map2[str] = map2.getOrDefault(str,0)+1
        }

        var count = map1.count{ (k,v) ->
            map2[k] == v && map2[k] ==1
        }

        return count
    }
}

fun countWords2(words1: Array<String>, words2: Array<String>): Int {
    return (words1 + words2).toSet()
            .count{ word ->
                words1.count{it == word} == 1 && words2.count{it == word} == 1
            }
}

class Solution {
    fun countWords(words1: Array<String>, words2: Array<String>): Int {
        val frequencies1Map = hashMapOf<String, Int>()
        for (word in words1) {
            frequencies1Map[word] = frequencies1Map.getOrDefault(word, 0) + 1
        }

        val frequencies2Map = hashMapOf<String, Int>()
        for (word in words2) {
            frequencies2Map[word] = frequencies2Map.getOrDefault(word, 0) + 1
        }

        var count = 0
        for ((key, value) in frequencies1Map) {
            if (value == 1 && frequencies2Map.getOrDefault(key, 0) == 1) {
                count++
            }
        }

        return count
    }
}