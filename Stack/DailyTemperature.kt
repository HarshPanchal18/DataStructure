/*
* Daily Temperatures
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Example 2:
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]

Example 3:
Input: temperatures = [30,60,90]
Output: [1,1,0]

Constraints:
1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
*/

import java.util.*

fun dailyTemperatures(temperatures: IntArray): IntArray {
    val results = IntArray(temperatures.size)
    val stack = Stack<Int>()

    for (temp in temperatures.indices) {
        while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[temp])
            results[stack.peek()] = temp - stack.pop()

        stack.push(temp)
    }
    return results
}

fun main() {
    val temperatures = intArrayOf(73,74,75,71,69,72,76,73)
    val result = dailyTemperatures(temperatures)
    result.forEach { print("$it ") }
}

fun dailyTemperatures(temperatures: IntArray): IntArray {
    val n = temperatures.size
    val ans = IntArray(n)
    val stack = Stack<Int>()

    for (i in n - 1 downTo 0) {
        while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()])
            stack.pop()

        ans[i] = if (stack.isEmpty()) 0 else stack.peek() - i
        stack.push(i)
    }
    return ans
}

fun dailyTemperatures(temperatures: IntArray): IntArray {
    val stack = LinkedList<Pair<Int, Int>>()   // value to index

    for (i in temperatures.indices) {
        while (stack.isNotEmpty() && stack.first.first < temperatures[i])       // stack.peek
            stack.removeFirst().let { temperatures[it.second] = i - it.second } // stack.pop
        stack.addFirst(temperatures[i] to i)                                    // stack.push
    }
    stack.forEach { temperatures[it.second] = 0 }

    return temperatures
}

fun dailyTemperatures(temperatures: IntArray): IntArray {
    val stack = Vector<Pair<Int, Int>>()   // value to index

    for (i in temperatures.indices) {
        while (stack.isNotEmpty() && stack.lastElement().first < temperatures[i]) // stack.peek
            stack.removeLast().let { temperatures[it.second] = i - it.second } // stack.pop
        stack.add(temperatures[i] to i)                                        // stack.push
    }
    stack.forEach { temperatures[it.second] = 0 }

    return temperatures
}