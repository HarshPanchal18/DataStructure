/*
* Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true

Constraints:
1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
*/

fun main(args: Array<String>) {
    print(isIsomorphic("paper", "title"))
}

fun isIsomorphic(s: String, t: String): Boolean {
    return s.zip(t).toSet().size.run {
        equals(s.toSet().size) && equals(t.toSet().size )
    }
}

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        val sCount = IntArray(256)
        val tCount = IntArray(256)

        for (i in 0..s.length-1) {
            if (sCount[s[i].toInt()] != tCount[t[i].toInt()]) return false
            sCount[s[i].toInt()] = i + 1
            tCount[t[i].toInt()] = i + 1
        }

        return true
    }
}

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        val sa = IntArray(255)
        val ta = IntArray(255)
        for (i in s.indices) {
            val sc = s[i]
            val tc = t[i]

            if (sa[sc.code] != ta[tc.code]) {
                return false
            }
            sa[sc.code] = i + 1
            ta[tc.code] = i + 1
        }
        return true
    }
}

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        val mappingTable = mutableMapOf<Char, Char>()
        var result = true

        for (index in s.indices) {
            val sourceChar = s[index]
            val destChar = t[index]
            val current = mappingTable[sourceChar]

            if (current == null) {
                if (mappingTable.containsValue(destChar)) {
                    result = false
                    break
                } else {
                    mappingTable[sourceChar] = destChar
                }
            } else if (current != destChar) {
                result = false
                break
            }
        }

        return result
    }
}

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.isEmpty() || t.isEmpty()) {
            return false
        }
        if (s.length != t.length) {
            return false
        }
        val map = mutableMapOf<Char, Char>()
        val map2 = mutableMapOf<Char, Char>()
        for (i in 0..s.length - 1) {
            val mappedChar = map.get(s[i])
            if (mappedChar == null) {
                if (map2.get(t[i]) != null) {
                    return false
                }
                map.put(s[i], t[i])
                map2.put(t[i], s[i])
            } else {
                if (mappedChar != t[i]) {
                    return false
                }
            }
        }
        return true

    }
}

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val map = mutableMapOf<Char, Char>()

        for (i in s.indices) {
            val sChar = s[i]
            val tChar = t[i]

            if (!map.containsKey(sChar) && !map.containsValue(tChar)) {
                map[sChar] = tChar
            } else if (map.containsKey(sChar)) {
                if(map.getValue(sChar)!=tChar)
                    return false
            } else {
                return false
            }
        }

        return true
    }
}

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        if(s.length != t.length) return false
        val sMap = mutableMapOf<Char,IntArray>()
        val tMap = mutableMapOf<Char,IntArray>()
        var index = 0
        var isIsomorphic = true

        while(index < t.length) {
            // s= foo t = bar
            // index = 0
            // cs = f sMap =  ('f',0)
            // ct = b tMap = ('b',0)
            // index = 1
            // cs = o sMap = (('f',[0]),('o',[1]))
            // ct = a tMap = (('b',[0]),('a',[1]))
            // index = 2
            // cs = o  sMap = (('f',[0]),('o',[1,2]))
            // ct = r tMap = (('b',[0]),('a',[1]),('r',[2]))
            val cs = s[index] // f
            val ct = t[index] // b
            var csOccurrences: IntArray = sMap.get(cs) ?: intArrayOf() // []
            var ctOccurrences : IntArray = tMap.get(ct) ?: intArrayOf() // []
            val sOccurrencesUpdated =csOccurrences+index // [1]
            val tOccurrencesUpdated = ctOccurrences+index // [1]

            sMap.put(cs,sOccurrencesUpdated) // (f,[0])
            tMap.put(ct,tOccurrencesUpdated) // (b,[0])

            if(sOccurrencesUpdated.contentEquals(tOccurrencesUpdated).not()) {
                isIsomorphic = false
                break
            }
            index++
        }
        return isIsomorphic
    }
}

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        val sDict = mutableMapOf<Char, Int>()
        var counter = 0

        var resultS = Array(s.length) { 0 }
        s.forEachIndexed { index, value ->
            val key = sDict[value] ?: ++counter
            sDict[value] = key
            resultS[index] = key
        }

        sDict.clear()
        counter = 0

        var resultT = Array(t.length) { 0 }
        t.forEachIndexed { index, value ->
            val key = sDict[value] ?: ++counter
            sDict[value] = key
            resultT[index] = key
        }

        return resultS.joinToString() == resultT.joinToString()
    }
}

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        val mappings = hashMapOf<String,String>()
        val sLetters = s.split("")
        val tLetters = t.split("")

        if(sLetters.size != tLetters.size)
            return false

        for(i in 0 until sLetters.size) {
            val sLetter = sLetters[i]
            val tLetter = tLetters[i]
            var mappingOrNot = mappings[sLetter]
            if(mappingOrNot == null) {
                if(mappings.values.contains(tLetter))
                    return false

                mappings[sLetter] = tLetter
            }
            if(mappings[sLetter]!! != tLetter) return false
        }
        return true
    }
}

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        var sMap = HashMap<Char, String>()
        var tMap = HashMap<Char, String>()

        for (i in 0 until s.length) {
            var fingerPrint = sMap.getOrDefault(s[i], "")

            fingerPrint += i.toString()
            sMap[s[i]] = fingerPrint
            fingerPrint = tMap.getOrDefault(t[i], "")
            fingerPrint += i.toString()
            tMap[t[i]] = fingerPrint
        }

        if (sMap.keys.size != tMap.keys.size)
            return false

        for (i in 0 until s.length) {
            var first = sMap[s[i]]!!
            var second = tMap[t[i]]!!

            if (first.length != second.length || first != second)
                return false

        }

        return true
    }
}

fun isIsomorphic(s: String, t: String): Boolean {
    if (s.length != t.length)
        return false

    val map = mutableMapOf<Char, Char>()
    for (i in s.indices) {
        if (!map.containsKey(s[i])) {
            map[s[i]] = t[i]
        } else {
            if (map[s[i]] != t[i]) {
                return false
            }
        }
    }
    return true
}
