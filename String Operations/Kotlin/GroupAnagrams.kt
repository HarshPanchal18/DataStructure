/*
* Group Anagrams
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/

fun main() {
    val arr = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    val result = groupAnagrams(arr)
    result.forEach { res ->
        res.forEach { print("$it ") }
        println()
    }
}

fun groupAnagrams(str: Array<String>): List<List<String>> {
    if (str.isEmpty())
        return emptyList()

    val map = hashMapOf<String, MutableList<String>>()
    val result = mutableListOf<List<String>>()

    str.forEach { s ->
        val key = String(s.toCharArray().sortedArray())
        if (map.contains(key))
            map[key]?.add(s)
        else
            map[key] = mutableListOf<String>().apply { add(s) }
    }

    map.values.forEach { result.add(it) }

    return result
}

fun groupAnagrams(strs: Array<String>): List<List<String>> =
    strs.groupBy { str -> str.groupingBy { it }.eachCount() }
        .values
        .toList()

fun groupAnagrams(strs: Array<String>): List<List<String>> =
    mutableMapOf<String, MutableList<String>>().apply {
        strs.forEach {
            val key = String(it.toCharArray().sortedArray())
            if (!this.contains(key))
                this[key] = mutableListOf()
            this[key]?.add(it)
        }
    }.map { it.value }

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    return strs.groupBy { key -> key.toCharArray().sorted().joinToString() }.values.toList()
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()
    for (str in strs) {
        val key = buildKey(str)
        if (map.contains(key)) {
            map[key]!!.add(str)
        } else {
            map[key] = mutableListOf(str)
        }
    }
    return map.values.toList()
}

fun buildKey(str: String): String {
    val arr = IntArray(26, { 0 })
    for (ch in str) {
        arr[ch - 'a']++
    }
    return arr.map { it.toString() }.reduce { acc, s -> acc + "," + s }
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()
    val ans: MutableList<List<String>> = mutableListOf()

    for (str in strs) {
        val arr = str.toCharArray()
        arr.sort()
        map[String(arr)]?.add(str) ?: map.put(String(arr), mutableListOf(str))
    }

    for (i in map)
        ans.add(i.value)

    return ans
}

fun groupAnagrams(strs: Array<String>): List<List<String>> =
    strs.groupBy { str -> str.groupingBy { it }.eachCount() }.values.toList()

fun groupAnagrams(strs: Array<String>): List<List<String>> {

    //create a function that sort an elements of the string
    fun sorted(a: String): String {
        var c = ""
        val sorted: MutableList<String> = mutableListOf()
        val b: List<String> = a.split("")

        for (i in 1..b.count() - 2) sorted.add(b[i])
        sorted.sort()
        for (i in sorted) c += i

        return c
    }

    val result: MutableList<MutableList<String>> = mutableListOf(mutableListOf())
    result.clear()
    var k: String

    val temp: MutableList<String> = mutableListOf()
    val indexes: MutableList<Int> = mutableListOf()

    //create a list of sorted strings - in that way every anagram will look the same
    for (i in strs) temp.add(sorted(i))

    //create a list of indexes of the anagrams strings
    for (i in 0..<temp.size) {
        if (temp[i] == "0")
            continue
        k = temp[i]

        for (j in 0..<temp.size) {
            if ((k == temp[j]) and (temp[j] != "0")) {
                indexes.add(j)
                temp[j] = "0"
            }
        }
        indexes.add(-1)
    }


    var j = 0
    var counter = 0
    if (indexes.size > 0) result.add(mutableListOf())
// add to the result lists a lists with anagrams strings
    for (i in indexes) {

        if ((i == -1) and (counter <= strs.size)) {
            j += 1; result.add(mutableListOf())
        }
        if (i != -1) {
            result[j].add(strs[i]); counter += 1
        }

    }
    if (result[j].isEmpty()) result.removeAt(j)

    return result
}