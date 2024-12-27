import java.util.*

/*
* Rearrange Words in a Sentence

Given a sentence text (A sentence is a string of space-separated words) in the following format:
* First letter is in upper case.
* Each word in text are separated by a single space.
* Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths. If two words have the same length, arrange them in their original order.

Return the new text following the format shown above.

Example 1:
Input: text = "Leetcode is cool"
Output: "Is cool leetcode"
Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
Output is ordered by length and the new first word starts with capital letter.

Example 2:
Input: text = "Keep calm and code on"
Output: "On and keep calm code"
Explanation: Output is ordered as follows:
"On" 2 letters.
"and" 3 letters.
"keep" 4 letters in case of tie order by position in original text.
"calm" 4 letters.
"code" 4 letters.

Example 3:
Input: text = "To be or not to be"
Output: "To be or to be not"

Constraints:
text begins with a capital letter and then contains lowercase letters and single space between words.
1 <= text.length <= 10^5
*/

fun main() {
    println(arrangeWords("Leetcode is cool"))
}

fun arrangeWords(text: String): String {
    return text.split(" ")
        .sortedBy { it.length }
        .joinToString(" ")
        .lowercase()
//        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        .replaceFirstChar { it.titlecase() }
}

class Solution {
    fun arrangeWords(text: String): String {
        return text.lowercase()
            .split(" ")
            .sortedBy { it.length }
            .joinToString(" ")
            .replaceFirstChar { it.uppercaseChar() }
    }
}

class Solution {
    fun arrangeWords(text: String): String {
        return text.replaceFirstChar { it.lowercaseChar() }
            .split(" ")
            .sortedBy { it.length }
            .joinToString(" ")
            .replaceFirstChar { it.uppercaseChar() }
    }
}

class Solution {
    fun arrangeWords(s: String): String {
        return s.split(" ")
            .sortedBy { it.length }
            .mapIndexed { i, w ->
                if (i == 0) "${w[0].uppercaseChar()}${w.drop(1)}"
                else "${w[0].lowercaseChar()}${w.drop(1)}"
            }
            .joinToString(" ")
    }
}

class Solution {
    fun arrangeWords(text: String): String {
        return text.lowercase()
            .split(" ")
            .toTypedArray()
            .apply { sortBy { it.length } }
            .joinToString(" ")
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}

fun arrangeWords(text: String): String {
    val map = hashMapOf<Int, ArrayList<String>>()
    val result = java.lang.StringBuilder()

    text.split(" ").forEach {
        map.computeIfAbsent(it.length) { ArrayList() }.add(it)
    }

    var isFirstStr = false
    map.keys.forEach {
        val value = map[it]!!

        for (str in value) {
            if (isFirstStr.not()) { // For making first char of first word caps
                result.append(str[0].uppercaseChar())
                result.append(str.subSequence(1, str.length))
                isFirstStr = true
            } else {
                result.append(str.lowercase(Locale.getDefault()))
            }
            result.append(" ")
        }
    }

    result.deleteCharAt(result.lastIndex) // delete last space
    return result.toString()
}