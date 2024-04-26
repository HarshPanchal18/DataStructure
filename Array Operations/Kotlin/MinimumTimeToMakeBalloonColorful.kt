/*
* Minimum Time to Make Rope Colorful
Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.
Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.
Return the minimum time Bob needs to make the rope colorful.

Example 1:
Input: colors = "abaac", neededTime = [1,2,3,4,5]
Output: 3
Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
Bob can remove the blue balloon at index 2. This takes 3 seconds.
There are no longer two consecutive balloons of the same color. Total time = 3.

Example 2:
Input: colors = "abc", neededTime = [1,2,3]
Output: 0
Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.

Example 3:
Input: colors = "aabaa", neededTime = [1,2,3,4,1]
Output: 2
Explanation: Bob will remove the ballons at indices 0 and 4. Each ballon takes 1 second to remove.
There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.

Constraints:
n == colors.length == neededTime.length
1 <= n <= 105
1 <= neededTime[i] <= 104
colors contains only lowercase English letters.
*/

fun main() {
    println(minCost("aabaa",intArrayOf(1,2,3,4,1)))
}

fun minCost(colors: String, neededTime: IntArray): Int {
    var minTime = neededTime.first()
    var maxTime = neededTime.first()

    for (i in 1..<colors.length) {
        if (colors[i] == colors[i - 1]) {
            minTime += neededTime[i]
            if (neededTime[i] > maxTime)
                maxTime = neededTime[i]
        } else {
            minTime -= maxTime
            maxTime = neededTime[i]
            minTime += neededTime[i]
        }
    }
    minTime -= maxTime
    return minTime
}

fun minCost(colors: String, neededTime: IntArray): Int {
    var ans = 0
    var i = 0
    while(i < colors.length) {
        var j = i+1
        var sum = neededTime[i]
        var max = neededTime[i]
        while(j < colors.length && colors[j] == colors[i]) {
            if(max < neededTime[j]) max = neededTime[j]
            sum += neededTime[j]
            j++
        }
        if(j >= i+1) {
            ans += sum - max
        }
        i = j
    }
    return ans
}

fun minCost(colors: String, neededTime: IntArray): Int {
    var ans = 0
    var max = 0
    for(i in 0 until colors.length) {
        if(i > 0 && colors[i] == colors[i-1]) {
            ans += Math.min(max, neededTime[i]);
            max = Math.max(max, neededTime[i]);
        } else {
            max = neededTime[i]
        }
    }
    return ans
}

fun minCost(colors: String, neededTime: IntArray): Int =
    neededTime.sum() - colors.toCharArray().zip(neededTime.toList()).zipWithNext()
        .fold(mutableListOf<Int>(neededTime.first())) { acc, (left, right) ->
            if (!acc.isEmpty() && left.first == right.first) {
                acc[acc.lastIndex] = maxOf(acc.last(), right.second)
            } else {
                acc.add(right.second)
            }
            acc
        }.sum()

fun minCost(colors: String, neededTime: IntArray): Int {
    var ans = 0
    var last = 0
    for (i in 1..<colors.length) {
        if (colors[i] == colors[last]) {
            ans += min(neededTime[last], neededTime[i])
            last = listOf(last, i).maxBy { neededTime[it] }
        } else last = i
    }
    return ans
}

fun minCost(colors: String, neededTime: IntArray): Int {
    var sum = 0
    var max = 0
    var prev = '.'

    for ((i, c) in colors.withIndex()) {
        sum += neededTime[i]
        if (prev != c) sum -= max.also { max = 0 }
        max = max(max, neededTime[i])
        prev = c
    }

    return sum - max
}