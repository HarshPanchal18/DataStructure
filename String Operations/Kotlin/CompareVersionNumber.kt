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