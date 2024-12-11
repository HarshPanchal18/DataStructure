/*
* Ransom Note
Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
Each letter in magazine can only be used once in ransomNote.

Example 1:
Input: ransomNote = "a", magazine = "b"
Output: false

Example 2:
Input: ransomNote = "aa", magazine = "ab"
Output: false

Example 3:
Input: ransomNote = "aa", magazine = "aab"
Output: true

Constraints:
1 <= ransomNote.length, magazine.length <= 105
ransomNote and magazine consist of lowercase English letters.
*/

fun main() {
    println(canConstruct("a","b"))
    println(canConstruct("aa","aab"))
}

fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val count = IntArray(26)

    for (c in magazine.toCharArray())
        count[c - 'a']++

    for (c in ransomNote.toCharArray()) {
        count[c - 'a']--
        if (count[c - 'a'] < 0)
            return false
    }

    return true
}

class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val map = magazine.asSequence()
            .groupingBy { it }
            .eachCount()
            .toMutableMap()

        for (c in ransomNote) {
            val v = map[c] ?: 0
            if (v == 0) return false
            map[c] = v - 1
        }

        return true
    }
}

class Solution {

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val magazineArray = magazine.toCharArray().toMutableList()
        val noteArray = ransomNote.toCharArray()

        for (i in noteArray)
            if (!magazineArray.remove(i))
                return false

        return true
    }
}

class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean = IntArray(26).apply {
        magazine.forEach { c -> this[c - 'a']++ }
        ransomNote.forEach { c ->
            if (--this[c - 'a'] < 0)
                return false
        }
    }.let { true }
}

class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val magazineMap = hashMapOf<Char, Int>()
        magazine.forEach {
            magazineMap[it] = magazineMap.getOrDefault(it, 0) + 1
        }

        ransomNote.forEach {
            if (magazineMap.contains(it) && magazineMap[it] != 0)
                magazineMap[it] = magazineMap[it]!! - 1
            else
                return false
        }

        return true
    }
}

class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        if (ransomNote.length > magazine.length) return false

        val rMap: MutableMap<Char, Int> = HashMap()

        for (element in magazine) {
            val c: Char = element
            val cc = if (rMap.contains(c)) rMap[c]!! + 1 else 1
            rMap[c] = cc
        }

        for (element in ransomNote) {
            val c: Char = element
            if (rMap.contains(c)) {
                var cc = rMap[c]!!
                if (cc > 0)
                    rMap[c] = --cc
                else
                    return false
            } else {
                return false
            }
        }
        return true
    }
}

class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        if (ransomNote.length > magazine.length)
            return false

        val magazineMap = mutableMapOf<Char, Int>()
        for (char in magazine)
            magazineMap[char] = magazineMap.getOrDefault(char, 0) + 1

        for (char in ransomNote) {
            val count = magazineMap[char] ?: 0
            if (count == 0)
                return false

            magazineMap[char] = count - 1
        }

        return true
    }
}

class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val hashmap = mutableMapOf<Char, Int>()

        ransomNote.forEach {
            if (hashmap.containsKey(it)) {
                hashmap[it] = hashmap[it]!! + 1
            } else {
                hashmap[it] = 1
            }
        }

        magazine.forEach {
            if (hashmap.containsKey(it))
                hashmap[it] = hashmap[it]!! - 1
        }

        for ((k, v) in hashmap)
            if (v > 0)
                return false

        return true
    }
}