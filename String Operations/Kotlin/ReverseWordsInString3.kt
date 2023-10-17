/*
* Reverse Words in a String III
Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Example 2:
Input: s = "God Ding"
Output: "doG gniD"
*/

fun main(args: Array<String>) {
    val result = reverseWords("Let's take LeetCode contest")
    print(result)
}

fun reverseWords(s: String): String {
    return s.split(" ").map { it.reversed() }.joinToString(" ")
}

fun reverseWords(s: String): String {
    val res = s.toCharArray()
    val len = s.length
    var lastSpaceIndex = -1

    for (i in 0..len) {
        if (i == len || s[i] == ' ') {
            var start = lastSpaceIndex + 1
            var end = i - 1

            while (start < end) {
                val t = res[start]
                res[start++] = res[end]
                res[end--] = t
            }

            lastSpaceIndex = i
        }
    }

    return String(res)
}