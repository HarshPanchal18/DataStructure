/*
* Count Items Matching a Rule

You are given an array items, where each items[i] = [typei, colori, namei] describes the type, color, and name of the ith item.
You are also given a rule represented by two strings, ruleKey and ruleValue.

The ith item is said to match the rule if one of the following is true:
* ruleKey == "type" and ruleValue == typei.
* ruleKey == "color" and ruleValue == colori.
* ruleKey == "name" and ruleValue == namei.

Return the number of items that match the given rule.

Example 1:
Input: items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
Output: 1
Explanation: There is only one item matching the given rule, which is ["computer","silver","lenovo"].

Example 2:
Input: items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
Output: 2
Explanation: There are only two items matching the given rule, which are ["phone","blue","pixel"] and ["phone","gold","iphone"]. Note that the item ["computer","silver","phone"] does not match.

Constraints:
1 <= items.length <= 104
1 <= typei.length, colori.length, namei.length, ruleValue.length <= 10
ruleKey is equal to either "type", "color", or "name".
All strings consist only of lowercase letters.
*/

fun main() {
    val items = listOf(
        listOf("phone", "blue", "pixel"),
        listOf("computer", "silver", "lenovo"),
        listOf("phone", "gold", "iphone")
    )
    println(countMatches(items, "color", "silver"))
}

fun countMatches(items: List<List<String>>, ruleKey: String, ruleValue: String): Int {
    return items.count { item ->
        when (ruleKey) {
            "type" -> item[0] == ruleValue
            "color" -> item[1] == ruleValue
            else -> item[2] == ruleValue
        }
    }
}

class Solution {
    fun countMatches(items: List<List<String>>, ruleKey: String, ruleValue: String): Int {
        var result = 0
        for (list in items) {
            if (ruleKey == "type") {
                if (list[0] == ruleValue) {
                    result++
                }
            } else if (ruleKey == "color") {
                if (list[1] == ruleValue) {
                    result++
                }
            } else {
                if (list[2] == ruleValue) {
                    result++
                }
            }
        }
        return result
    }
}

class Solution {
    fun countMatches(items: List<List<String>>, ruleKey: String, ruleValue: String): Int {
        val ruleIndex = when (ruleKey) {
            "type" -> 0
            "color" -> 1
            "name" -> 2
            else -> -1
        }
        return items.count {
            it[ruleIndex] == ruleValue
        }
    }
}

class Solution {
    fun countMatches(items: List<List<String>>, ruleKey: String, ruleValue: String): Int {
        val index = when (ruleKey) {
            "type" -> 0
            "color" -> 1
            else -> 2
        }

        var res = 0

        for (element in items) {
            for (j in 0 until items[0].size) {
                if (j == index && element[j] == ruleValue) res++
            }
        }

        return res
    }
}

class Solution {
    fun countMatches(items: List<List<String>>, ruleKey: String, ruleValue: String): Int {
        var count = 0
        val ruleKeys = listOf("type", "color", "name")
        val indexToMatch = ruleKeys.indexOf(ruleKey)

        for (item in items) {
            if (item[indexToMatch] == ruleValue) count++
        }

        return count
    }
}

class Solution {
    fun countMatches(items: List<List<String>>, ruleKey: String, ruleValue: String): Int {
        return when (ruleKey) {
            "type" -> items.count { it[0] == ruleValue }
            "color" -> items.count { it[1] == ruleValue }
            "name" -> items.count { it[2] == ruleValue }
            else -> 0
        }
    }
}