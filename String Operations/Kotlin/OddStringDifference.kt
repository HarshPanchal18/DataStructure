/*
* Odd String Difference

You are given an array of equal-length strings words. Assume that the length of each string is n.
Each string words[i] can be converted into a difference integer array difference[i] of length n - 1 where difference[i][j] = words[i][j+1] - words[i][j] where 0 <= j <= n - 2. Note that the difference between two letters is the difference between their positions in the alphabet i.e. the position of 'a' is 0, 'b' is 1, and 'z' is 25.
For example, for the string "acb", the difference integer array is [2 - 0, 1 - 2] = [2, -1].
All the strings in words have the same difference integer array, except one. You should find that string.

Return the string in words that has different difference integer array.

Example 1:
Input: words = ["adc","wzy","abc"]
Output: "abc"
Explanation:
- The difference integer array of "adc" is [3 - 0, 2 - 3] = [3, -1].
- The difference integer array of "wzy" is [25 - 22, 24 - 25]= [3, -1].
- The difference integer array of "abc" is [1 - 0, 2 - 1] = [1, 1].
The odd array out is [1, 1], so we return the corresponding string, "abc".

Example 2:
Input: words = ["aaa","bob","ccc","ddd"]
Output: "bob"
Explanation: All the integer arrays are [0, 0] except for "bob", which corresponds to [13, -13].

Constraints:
3 <= words.length <= 100
n == words[i].length
2 <= n <= 20
words[i] consists of lowercase English letters.
*/

fun main() {
    oddString(arrayOf("adc", "wzy", "abc"))
    oddString(arrayOf("aaa", "bob", "ccc", "ddd"))
}

fun oddString(words: Array<String>): String {
    val n = words.first().length
    val difference = mutableListOf<MutableList<Int>>()

    words.forEach { word ->
        val diffs = mutableListOf<Int>()
        for (i in 1..<n)
            diffs.add(word[i] - word[i - 1])

        difference.add(diffs)
        println(diffs)
    }

    val distinct = difference.groupingBy { it }.eachCount().filter { it.value == 1 }.keys
    return words[difference.indexOf(distinct.toList().first())]
}

class Solution {
    fun oddString(words: Array<String>): String {
        val key = toKey(words[0])
        var i = 1
        while (key == toKey(words[i])) {
            i++
        }
        return if (i > 1) {
            words[i]
        } else {
            if (toKey(words[i + 1]) == key) words[i] else words[0]
        }

    }

    fun toKey(w: String): String {
        val sh = w[0].code - 'a'.code
        val sb = StringBuilder()
        for (element in w) {
            sb.append((element.code - sh).toChar())
        }
        return sb.toString()
    }
}

class Solution {
    fun oddString(words: Array<String>): String {
        var i = 0

        while (i < words.size) {
            val oddWord = findOddWord(words[i], words[i + 1], words[i + 2])
            if (oddWord != "") return oddWord
            i++
        }

        return ""
    }

    private fun findOddWord(word1: String, word2: String, word3: String): String {
        var i = 1

        while (i < word1.length) {
            val diff1 = word1[i] - word1[i - 1]
            val diff2 = word2[i] - word2[i - 1]
            val diff3 = word3[i] - word3[i - 1]

            if (diff1 == diff2 && diff2 == diff3)
                i++
            else return when (diff1) {
                diff2 -> word3
                diff3 -> word2
                else -> word1
            }
        }

        return ""
    }
}

class Solution {
    fun oddString(words: Array<String>): String {
        val diffMap = mutableMapOf<String, Int>()
        words.forEach { word ->
            val diffArray = getDiffArray(word)
            diffMap[diffArray] = diffMap.getOrDefault(diffArray, 0) + 1
        }
        return words.first { word -> diffMap[getDiffArray(word)] == 1 }
    }

    fun getDiffArray(word: String): String {
        val diffArray = StringBuilder()
        for (i in 1 until word.length) {
            diffArray.append(word[i] - word[i - 1])
            diffArray.append(',')
        }
        return diffArray.toString()
    }
}

class Solution {
    fun oddString(words: Array<String>): String {
        val diffMap = mutableMapOf<String, Int>()
        words.forEach { word ->
            val diffArray = getDiffArray(word)
            diffMap[diffArray] = diffMap.getOrDefault(diffArray, 0) + 1
        }
        return words.first { word -> diffMap[getDiffArray(word)] == 1 }
    }

    fun getDiffArray(word: String): String {
        val diffArray = StringBuilder()
        for (i in 1 until word.length) {
            diffArray.append(word[i] - word[i - 1])
            diffArray.append(',')
        }
        return diffArray.toString()
    }
}

class Solution {
    fun oddString(words: Array<String>): String {
        val map = hashMapOf<ArrayList<Int>, Pair<Int, Int>>()

        words.forEachIndexed { index, s ->
            val list = arrayListOf<Int>()
            for (i in 1 until s.length) {
                list.add((s[i] - 'a') - (s[i - 1] - 'a'))
            }
            map[list] = Pair(index, (map[list]?.second ?: 0) + 1)
        }

        return words[map.entries.find { it.value.second == 1 }!!.value.first]
    }
}

class Solution {
    fun oddString(words: Array<String>): String {
        val diffs = words.map { diff(it) }
        val i = diffs.findUnique()
        return words[i]
    }

    fun <T> List<T>.findUnique(): Int {
        val start = first()
        val second = get(1)
        return if (start == second) {
            indexOfFirst { it != start }
        } else {
            // The single elem is one of start or second, to decide, we use the third
            val third = get(2)
            if (start == third) 1
            else 0
        }
    }

    fun diff(word: String) = word.toCharArray(1)
        .zip(word.toCharArray())
        .map { it.first - it.second }
}