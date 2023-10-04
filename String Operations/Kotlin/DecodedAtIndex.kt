/*
* Decoded String at Index
You are given an encoded string s.
To decode the string to a tape, the encoded string is read one character at a time and the following steps are taken:
* If the character read is a letter, that letter is written onto the tape.
* If the character read is a digit d, the entire current tape is repeatedly written d - 1 more times in total.
* Given an integer k, return the kth letter (1-indexed) in the decoded string.

* Example 1:
Input: s = "leet2code3", k = 10
Output: "o"
Explanation: The decoded string is "leetleetcodeleetleetcodeleetleetcode".
The 10th letter in the string is "o".

* Example 2:
Input: s = "ha22", k = 5
Output: "h"
Explanation: The decoded string is "hahahaha".
The 5th letter is "h".

* Example 3:
Input: s = "a2345678999999999999999", k = 1
Output: "a"
Explanation: The decoded string is "a" repeated 8301530446056247680 times.
The 1st letter is "a".
*/

// Not solved yet :'

fun main(args: Array<String>) {
    val res = decodeAtIndex("ha22",5)
    print(res)
}

fun decodeAtIndex(s: String, k: Int): String {

    val result = StringBuilder()

    for(i in s.indices) {
        when {
            s[i].isDigit() -> {
                for(j in 0..i-1) {
                    result.append(s[j])
                }
            }
        }
    }

    return result.toString()
}