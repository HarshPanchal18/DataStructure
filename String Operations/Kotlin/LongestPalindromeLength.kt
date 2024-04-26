/*
 * Longest Palindromic Substring

 Given a string s, return the longest palindromic substring in s.
 A string is called a palindrome string if the reverse of that string is the same as the original string.

 Example 1:
 Input: s = "babad"
 Output: "bab"
 Explanation: "aba" is also a valid answer.

 Example 2:
 Input: s = "cbbd"
 Output: "bb"
 */

fun main(args: Array<String>) {
    println(longestPalindromeLength("babad"))
}

fun expandAroundCenter(s: String, left: Int, right: Int): Int {
    var _left = left
    var _right = right

    while(_left >= 0 && _right < s.length && s[_left] == s[_right]) {
        _left--
        _right++
    }
    return _right - _left - 1
}

fun longestPalindromeLength(str: String): String {
    var start = 0
    var end = 0

    for(i in 0..str.length) {
        val len1 = expandAroundCenter(str, i, i)
        val len2 = expandAroundCenter(str, i, i + 1)
        val len = Math.max(len1, len2)

        if(len > end) {
            start = i - (len - 1) / 2
            end = i + len / 2
        }
    }
    return str.substring(start, end + 1)
}