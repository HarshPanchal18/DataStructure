/*
* Number of Segments in a String

Given a string s, return the number of segments in the string.
A segment is defined to be a contiguous sequence of non-space characters.

Example 1:
Input: s = "Hello, my name is John"
Output: 5
Explanation: The five segments are ["Hello,", "my", "name", "is", "John"]

Example 2:
Input: s = "Hello"
Output: 1

Constraints:
0 <= s.length <= 300
s consists of lowercase and uppercase English letters, digits, or one of the following characters "!@#$%^&*()_+-=',.:".
The only space character in s is ' '.
*/

fun main() {
    println(countSegments("                "))
    println(countSegments(", , , ,        a, eaefa"))
}

fun countSegments(s: String): Int {
    return s.split(Regex("\\s++")).count { it.isNotEmpty() }
}

class Solution {
    fun countSegments(s: String): Int {
        var shouldCount = true
        var count = 0

        s.forEach {
            if (it != ' ') {
                if (shouldCount) {
                    count++
                    shouldCount = false
                }
            } else {
                shouldCount = true
            }
        }
        return count

    }
}

class Solution {
    fun countSegments(s: String): Int {
        val trimmedString = s.trim() // Remove leading and trailing spaces
        if (trimmedString.isEmpty()) return 0
        return trimmedString.split("\\s+".toRegex()).size // Split the string by one or more spaces and return size
    }
}

class Solution {
    fun countSegments(s: String): Int {
        var currentSegment = ""
        var count = 0

        for (c in s) {
            if (c != ' ') currentSegment += c
            else {
                if (currentSegment != "") {
                    println(currentSegment)
                    count += 1
                    currentSegment = ""
                }
            }
        }
        if (currentSegment.length > 0) count++
        return count
    }
}