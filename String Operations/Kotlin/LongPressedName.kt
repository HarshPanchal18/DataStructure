/*
* Long Pressed Name

Your friend is typing his name into a keyboard. Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
You examine the typed characters of the keyboard. Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

Example 1:
Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.

Example 2:
Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it was not in the typed output.

Constraints:
1 <= name.length, typed.length <= 1000
name and typed consist of only lowercase English letters.
*/

fun main(args: Array<String>) {
    println(isLongPressedName("alex", "aaleex"))
    println(isLongPressedName("saeed", "ssaaedd"))
    println(isLongPressedName("leelee", "lleeelee"))
}

fun isLongPressedName(name: String, typed: String): Boolean {
    var i = 0
    var lastChar: Char? = null

    for (char in typed) {
        if(i < name.length && char == name[i]) {
            i++
            lastChar = char
        } else if (char == lastChar) {
            lastChar = null
        } else {
            return false
        }
    }
    return i == name.length
}

class Solution {
    fun isLongPressedName(name: String, typed: String): Boolean {
        val map1 = lettersWithCount(name)
        val map2 = lettersWithCount(typed)

        if (map1.size == map2.size) {
            for (i in 0 until map1.size) {
                if (map1[i].first != map2[i].first || map1[i].second > map2[i].second) {
                    return false
                }
            }
            return true
        }
        return false
    }

    fun lettersWithCount(name: String): List<Pair<Char, Int>> {
        val ans = mutableListOf<Pair<Char, Int>>()
        var cr = name[0]
        var counter = 1
        for (i in 1 until name.length) {
            if (cr != name[i]) {
                ans.add(Pair(cr, counter))
                cr = name[i]
                counter = 1
            } else {
                counter++
            }
        }
        ans.add(Pair(cr, counter))
        return ans
    }
}

class Solution {
    fun isLongPressedName(name: String, typed: String): Boolean {
        var ni = 0
        var ti = 0

        while(ni < name.length || ti < typed.length) {
            if(ti == typed.length)
                return false

            if(ni == name.length) {
                if(name[ni - 1] == typed[ti]) {
                    ti++
                    continue
                } else
                    return false
            }

            if(name[ni] != typed[ti]) {
                if(ni == 0)
                    return false

                if(name[ni - 1] != typed[ti])
                    return false

                ti++
            } else {
                ni++
                ti++
            }
        }

        return true
    }
}

class Solution {
    fun isLongPressedName(name: String, typed: String): Boolean {
        var i = 0
        var j = 0
        val nameLen = name.length
        val typedLen = typed.length
        while(i < nameLen && j < typedLen) {
            val prev = i
            val prevj = j

            while(i < nameLen && j < typedLen && name[i] == typed[j]) {
                i++
                j++
            }

            while(j < typedLen && j > 0 && typed[j] == typed[j-1])
                j++

            if(i == prev && j == prevj)
                return false

        }
        return i == nameLen && j == typedLen
    }
}

/*fun isLongPressedName(name: String, typed: String): Boolean {
    var i = 0
    var j = 0

    while (i < name.length && j < typed.length) {
        if (name[i] != typed[j])
            return false

        while (j < typed.length && typed[j] == name[i])
            j++

        i++
    }
    return i == name.length
}*/