/*
* Redistribute Characters to Make All Strings Equal
You are given an array of strings words (0-indexed).
In one operation, pick two distinct indices i and j, where words[i] is a non-empty string, and move any character from words[i] to any position in words[j].
Return true if you can make every string in words equal using any number of operations, and false otherwise.

Example 1:
Input: words = ["abc","aabc","bc"]
Output: true
Explanation: Move the first 'a' in words[1] to the front of words[2],
to make words[1] = "abc" and words[2] = "abc".
All the strings are now equal to "abc", so return true.

Example 2:
Input: words = ["ab","a"]
Output: false
Explanation: It is impossible to make all the strings equal using the operation.

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
*/

fun main(args: Array<String>) {
    val words = arrayOf("abc", "aabc", "bc")
    println(makeEqual(words))
}

fun makeEqual(words: Array<String>): Boolean {
    val charCounter = IntArray(26)

    for(word in words) {
        for(character in word) {
            charCounter[character - 'a']++ // Add 1 to position of char in int-array
        }
    }

    for(count in charCounter)
        if(count % words.size != 0) // If count is not multiple of numbers of row in array
            return false

    return true
}

fun makeEqual(words: Array<String>): Boolean {
    if (words.size == 1) {
        return true
    }

    val n = words.size

    var totalCharCount = 0
    words.forEach { s ->
        totalCharCount += s.length
    }
    if (totalCharCount % n != 0) {
        return false
    }

    val freq = IntArray(26)
    words.forEach { str ->
        for (j in 0 until str.length) {
            val ch = str[j]
            freq[ch - 'a']++
        }
    }

    for (i in 0 until 26) {
        if (freq[i] % n != 0) {
            return false
        }
    }

    return true
}

fun makeEqual(words: Array<String>): Boolean {
    return words
        .fold(IntArray(128)) { arr, word -> arr.also { word.forEach { arr[it.code]++ } } }
        .find { v -> v%words.size != 0 } == null
}

fun makeEqual(words: Array<String>) =
IntArray(26).apply {
    for (w in words) for (c in w) this[c.toInt() - 'a'.toInt()]++
}.all { it % words.size == 0 }

class Solution {
    fun makeEqual(words: Array<String>): Boolean {
        var i = 'a'.code
        while (i < counters.size) {
            counters[i] = 0
            i++
        }

        i = 0
        var j: Int
        while (i < words.size) {
            val word = words[i]
            j = 0
            while (j < word.length) {
                counters[word[j].code]++
                j++
            }
            i++
        }

        i = 'a'.code
        while (i < counters.size) {
            val c = counters[i]
            if (c > 0) {
                if (c < words.size || c % words.size != 0) {
                    return false
                }
            }
            i++
        }

        return true
    }

    companion object {
        val counters = IntArray('z'.code + 1)
    }
}

/* Complexity:
    * Time O(N) and Space O(1) where N is the flattened length of words;
    */
fun makeEqual(words: Array<String>): Boolean {
    val charFrequency = IntArray(26)
    for (word in words) {
        for (char in word) {
            charFrequency[char - 'a']++
        }
    }
    return charFrequency.all { it % words.size == 0 }
}

fun makeEqual(words: Array<String>): Boolean {
    val charCount = IntArray(26)
    val n = words.size
    for(w in words){
        for(c in w)
            charCount[c-'a']++
    }
    return charCount.all{ it % n == 0 }
}

fun makeEqual(words: Array<String>): Boolean =
    words.asSequence()
        .flatMap { it.asSequence() }
        .groupingBy { it }
        .eachCount()
        .values
        .all { it % words.size == 0 }

fun makeEqual(words: Array<String>): Boolean =
    words.fold(mutableMapOf<Char, Int>()) { acc, s ->
        s.groupingBy { it }.eachCountTo(acc)
    }.values.all {
        it % words.size == 0
    }

fun makeEqual(words: Array<String>): Boolean {
    val s = mutableMapOf<Char, Int>()

    // Count the frequency of each character in all strings
    for (it in words) {
        for (c in it)
            s[c] = s.getOrDefault(c, 0) + 1
    }

    // Check if the frequency of each character is divisible by the total number of strings
    for ((_, value) in s) {
        if (value % words.size != 0)
            return false
    }

    return true
}