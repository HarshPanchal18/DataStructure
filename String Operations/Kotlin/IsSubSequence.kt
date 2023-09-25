/*
* Is Subsequence
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
(i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:
Input: s = "axc", t = "ahbgdc"
Output: false
*/

fun main(args: Array<String>) {
    print(isSubSequence("abc","ahbgdc"))
}

fun isSubSequence1(s:String, t:String): Boolean {
    var index = -1
    return !s.any { ch ->
        index++
        while(index < t.length && t[index] != ch)
            index++
        index == t.length
    }
}

fun isSubsequence(s: String, t: String): Boolean {
    var n = s.length
    var m = t.length

    if(n == 0)  return true

    var c = 0
    for(i in 0 until m)
        if(t[i] == s[c])
            c++

    if(c == n)
        return true

    return false

}

fun isSubsequence(s: String, t: String): Boolean {
    var indexS = 0
    var indexT = 0
    var n = s.length
    var m = t.length

    while(indexS < n && indexT < m) {
        if(s[indexS] == t[indexT])
            indexS++
        indexT++
    }

    return indexS == n
}