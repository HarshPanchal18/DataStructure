import kotlin.math.max

/*
* Compare Version Numbers - https://www.interviewbit.com/problems/compare-version-numbers/
Asked In: Amazon

* Problem Description
Compare two version numbers version1 and version2.
If version1 > version2 return 1,
If version1 < version2 return -1,
otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences. For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Note: Here is an example of version numbers ordering:
0.1 < 1.1 < 1.2 < 1.13 < 1.13.4

Problem Constraints
1 <= |A|, |B| <= 5000


Input Format
The first argument is a string A representing version1.
The first argument is a string B representing version2.

Output Format
Return an integer.

Example Input
A = "1.13"
B = "1.13.4"

Example Output
-1

Example Explanation
Version1 = "1.13"
Version2 = "1.13.4"
Version1 < version2, hence return -1

* Compare Version Numbers - https://leetcode.com/problems/compare-version-numbers/
Given two version numbers, version1 and version2, compare them.
Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.
To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.

Return the following:
If version1 < version2, return -1.
If version1 > version2, return 1.
Otherwise, return 0.

Example 1:
Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".

Example 2:
Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: version1 does not specify revision 2, which means it is treated as "0".

Example 3:
Input: version1 = "0.1", version2 = "1.1"
Output: -1
Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.

Constraints:
1 <= version1.length, version2.length <= 500
version1 and version2 only contain digits and '.'.
version1 and version2 are valid version numbers.
All the given revisions in version1 and version2 can be stored in a 32-bit integer.
*/

fun compareVersion(A: String?, B: String?): Int {
	if (A == null || B == null) return 0

	val v1: Array<String> = A.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
	val v2: Array<String> = B.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
	var i = 0
	var j = 0

	while (i < v1.size || j < v2.size) {
		val x: Double = if (i < v1.size) v1[i].toDouble() else 0.0
		val y: Double = if (j < v2.size) v2[i].toDouble() else 0.0

		if (x > y) return 1
		else if (x < y) return -1
		i++
		j++
	}

	return 0
}

fun main() {
	println(compareVersion("4444371174137455", "5.168"))
}

class Solution {
    fun compareVersion(version1: String, version2: String): Int {
        val one = version1.split(".").map { it.toInt() }
        val two = version2.split(".").map { it.toInt() }
        val till = maxOf(one.size, two.size)

        for (i in 0 until till) {
            val a = if (i < one.size) one[i] else 0
            val b = if (i < two.size) two[i] else 0

            if (a < b) return -1
            else if (a > b) return 1

        }

        return 0
    }
}

class Solution {
    fun compareVersion(version1: String, version2: String): Int {
        var length1 = 0
        var length2 = 0

        while(length1 < version1.length || length2 < version2.length) {
            var sb = StringBuilder()

            var first = "0"
            if(length1 < version1.length) {
				while(length1 < version1.length && version1[length1] != '.') {
					sb.append(version1[length1])
					length1++
				}

				first = sb.toString()
				sb.clear()
				length1++
            }

            var second = "0"
            if(length2 < version2.length){
				while(length2 < version2.length && version2[length2] != '.') {
					sb.append(version2[length2])
					length2++
				}

				second = sb.toString()
				sb.clear()
				length2++
            }

            if(first.toInt() < second.toInt())
                return -1
            else if(first.toInt() > second.toInt())
                return 1
        }

        return 0
    }
}

class Solution {
    fun compareVersion(version1: String, version2: String): Int {

        val arr1 = version1.split(".")
        val arr2 = version2.split(".")

        val maxlen = maxOf(arr1.size, arr2.size)

        for(i in 0 until maxlen) {
            val num1 = if(i < arr1.size) arr1[i].toInt() else 0
            val num2 = if(i < arr2.size) arr2[i].toInt() else 0

            if(num1.compareTo(num2) != 0)
                return num1.compareTo(num2)

        }

        return 0
    }
}

class Solution {
    fun compareVersion(version1: String, version2: String) = Version(version1).compareTo(Version(version2))

    private class Version(string: String): Comparable<Version> {
        private val revisions = string.split('.').map { it.toInt() }
        val size get() = revisions.size

        operator fun get(i: Int) = revisions.getOrNull(i) ?: 0

        override fun compareTo(other: Version): Int {
            val comparisons = max(size, other.size)

            for (i in 0 until comparisons) {
                if (this[i] < other[i])
                    return -1
                else if (this[i] > other[i])
                    return 1
            }

            return 0
        }
    }
}

class Solution {
    fun compareVersion(version1: String, version2: String): Int {
        val lst1 = convertVersionToList(version1)
        val lst2 = convertVersionToList(version2)
        var idx = 0

        while (idx < lst1.size && idx < lst2.size) {
            if (lst1[idx] < lst2[idx]) return -1
            else if (lst1[idx] > lst2[idx]) return 1
            idx++
        }

        while (idx < lst1.size) {
            if (lst1[idx] > 0) return 1
            idx++
        }

        while(idx < lst2.size) {
            if (lst2[idx] > 0) return -1
            idx++
        }

        return 0
    }

    private fun convertVersionToList(version: String): List<Int> {
        return version.split(".").map { it.toInt() }
    }

}