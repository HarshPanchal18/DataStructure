/*
* Kth Largest element in an Array
Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.
Can you solve it without sorting?

Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
*/

import java.util.PriorityQueue

fun main(args: Array<String>) {
    val arr = intArrayOf(3,2,3,1,2,4,5,5,6,8)
    //val arr = intArrayOf(3,2,1,5,6,4)
    val target = findKthLargestElement(arr,2)
    println("$target")
}
fun findKthLargestElement(nums: IntArray, k: Int): Int {
    val prioQ = PriorityQueue<Int>()
    nums.forEach { num ->
        prioQ.add(num)
        println("Before: "+prioQ)
        prioQ.removeIf({prioQ.size>k})
        println("After: "+prioQ)
    }
    return prioQ.remove() // return the first element
    //return nums.sorted()[nums.size-k]
}
/* Iteration
Before: [3]
After: [3]
Before: [2, 3]
After: [2, 3]
Before: [2, 3, 3]
After: [3, 3]
Before: [1, 3, 3]
After: [3, 3]
Before: [2, 3, 3]
After: [3, 3]
Before: [3, 3, 4]
After: [3, 4]
Before: [3, 4, 5]
After: [4, 5]
Before: [4, 5, 5]
After: [5, 5]
Before: [5, 5, 6]
After: [5, 6]
Before: [5, 6, 8]
After: [6, 8]
*/