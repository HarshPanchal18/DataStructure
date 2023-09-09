/*
* Find the Index of the First Occurrence in a String
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
or -1 if needle is not part of haystack.

Example 1:
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.

Example 2:
Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.

*/

fun main(args: Array<String>) {
    print(strStr("sadbutsad","sad"))
    print(strStr1("sadbutsad","sad"))
    print(strStr2("sadbutsed","sed"))
}

fun strStr(haystack: String, needle: String): Int {
    return haystack.indexOf(needle)
}

// Brute Force - O(n*n)
fun strStr1(haystack:String, needle:String): Int {

    var hLength = haystack.length
    var nLength = needle.length

    for(i in 0 until hLength+1 - nLength) {
        for(j in 0 until nLength) {
            if(haystack[i+j] != needle[j])
                break
            if(j == nLength - 1)
                return i
        }
    }

    return -1
}

// Sliding Window - O(n)
fun strStr2(haystack:String, needle:String): Int {

    var hLength = haystack.length
    var nLength = needle.length - 1

    for(i in 0 until hLength - nLength) { // Do not go forward if `<nLength` char is left at the end
        val subString = haystack.substring(i .. i + nLength)
        if(subString == needle) return i
    }

    return -1
}