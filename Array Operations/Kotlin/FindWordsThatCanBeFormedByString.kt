/*
* Find Words That Can Be Formed by Characters

You are given an array of strings words and a string chars.
A string is good if it can be formed by characters from chars (each character can only be used once).
Return the sum of lengths of all good strings in words.

Example 1:
Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.

Example 2:
Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.

Constraints:
1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
words[i] and chars consist of lowercase English letters.

*/

fun countCharacters(words: Array<String>, chars: String): Int {

    var sum = 0
    val arr = IntArray(26)

    for (ch in chars) arr[ch - 'a']++

    words.forEach { word ->
        var flag = true
        val current = IntArray(26)

        for (ch in word) {
            if (++current[ch - 'a'] > arr[ch - 'a']) {
                flag = false
                break
            }
        }

        if (flag)
            sum += word.length
    }

    return sum
}

fun countCharacters(words: Array<String>, chars: String): Int {
    var sum = 0
    words.forEach /*loop@*/ { word ->
        for(ch in word) {
            if (word.count { it == ch } > chars.count { it == ch }) {
                //return@loop
                continue
            }
        }
        sum += word.length
    }
    return sum
}