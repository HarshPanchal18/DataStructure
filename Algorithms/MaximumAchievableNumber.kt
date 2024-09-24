/*
* Find the Maximum Achievable Number

Given two integers, num and t.
A number is achievable if it can become equal to num after applying the following operation:
Increase or decrease the number by 1, and simultaneously increase or decrease num by 1.
Return the maximum achievable number after applying the operation at most t times.

Example 1:
Input: num = 4, t = 1
Output: 6
Explanation:
Apply the following operation once to make the maximum achievable number equal to num:
Decrease the maximum achievable number by 1, and increase num by 1.

Example 2:
Input: num = 3, t = 2
Output: 7
Explanation:
Apply the following operation twice to make the maximum achievable number equal to num:
Decrease the maximum achievable number by 1, and increase num by 1.

Constraints:
1 <= num, t <= 50
*/

fun main() {
    println(theMaximumAchievableX(4, 1))
}

fun theMaximumAchievableX(num: Int, t: Int): Int {
    return num + (t * 2)
}