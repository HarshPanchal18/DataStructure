/*
* Reverse Words in a String
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

Output: "blue is sky the"

Example 2:
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

Constraints:
1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.
*/

fun main(args: Array<String>) {
    println(reverseWords("a good   example"))
    println(reverseWords("the sky is blue"))
}

fun reverseWords(s:String):String {
    return s.split(Regex("\\s+")).reversed().joinToString(" ")
}

fun reverseWords(s:String): String {
    val words = s.trim().split(Regex("\\s+"))
    val reversedWords = words.reversed()
    return reversedWords.joinToString(" ")
}

fun reverseWords(s: String): String {
    var words = s.trim().replace("\\s+".toRegex(), " ").split(" ").toTypedArray()
    var (i,j) = Pair(0, words.size-1)
    while(i < j) {
        words[i] = words[j].also{ words[j--] = words[i++] }
    }
    return words.joinToString(" ")
}

fun reverseWords(s: String): String {
    val split = s.trim().split(" ").reversed()
    var res = ""

    split.forEach {
        if (it != "") {
            res = "$res $it"
        }
    }

    res = res.trim()

    return res
}

fun reverseWords(s: String): String {
        return s.split(" ").filter { it.isNotEmpty() }.reversed().joinToString(separator = " ")
}

fun reverseWords(s: String): String {
    // Create a StringBuilder to hold the reversed result
    var result = StringBuilder()

    var index = 0
    while (index < s.length) {
        // Keep shifting index if blank space appears
        if (s[index] == ' ') {
            index++
            continue
        }
        // right is used to find the end of the current word
        var right = index
        while (right < s.length && s[right] != ' ') {
            right++
        }

        // Append the word to the beginning of the result, along
        // with a space. Inserting at start automatically reverse it
        result.insert(0, s.substring(index, right))
        result.insert(0, " ")

        // Move the index to the start of the next word
        index = right
    }

    // Remove the extra space at the beginning and return the reversed string
    return result.substring(1, result.length).toString()
}

class Solution {
    fun reverseWords(s: String): String {
        val ans = StringBuilder()
        var i = s.length -1
        var j = i
        while(i>=0){
           if(s[i] == ' '){
               if(i!=j){
                   ans.append(s.substring(i+1,j+1))
                   ans.append(' ')
               }
                i--
                j=i
           }else{
               i--
           }
        }

        if(i!=j){ // incase the string does not start with space the first word will be left out
            ans.append(s.substring(i+1,j+1))
        }

        return ans.toString().trim()
    }
}

fun reverseWords(s: String): String {

    var ls: List<String> = s.split("\\s+".toRegex())
    var ret: String = ""

    for (i in (ls.size - 1) downTo 0)
        ret += " " + ls[i]

    return ret.trim()
}

fun reverseWords(s: String) = s.split(" ").filter(String::isNotBlank).reversed().joinToString(" ")