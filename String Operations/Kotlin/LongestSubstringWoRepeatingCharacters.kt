/*
* Longest Substring Without Repeating Characters

Given a string s, find the length of the longest substring without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Constraints:
0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
*/

fun lengthOfLongestSubstring(s: String): Int {
    var length = 0
    val container = Array(256) { -1 }
    var start = -1

    for (i in s.indices) {
        if (container[s[i].code] > start)
            start = container[s[i].code]

        container[s[i].code] = i

        length = max(length,i-start)
    }
    return length
}

fun lengthOfLongestSubstring(s: String): Int {
    val seen: HashSet<Char> = hashSetOf()
    var maxLength = 0
    var i = 0

    s.indices.forEach { index ->
        val c = s[index]

        while (seen.contains(c))
            seen.remove(s[i++])

        maxLength = max(maxLength, index - i + 1)
        seen.add(c)
    }

    return maxLength
}