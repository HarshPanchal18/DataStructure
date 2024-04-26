import java.util.Stack

/*
* NEXT-GREATER
Given an array, find the next greater element G[i] for every element A[i] in the array.  The Next greater Element for an element A[i] is the first greater element on the right side of A[i] in array.
More formally,
G[i] for an element A[i] = an element A[j] such that
    j is minimum possible AND
    j > i AND
    A[j] > A[i]
Elements for which no greater element exist, consider next greater element as -1.

Example 1:
Input : A : [4, 5, 2, 10]
Output : [5, 10, 10, -1]

Example 2:
Input : A : [3, 2, 1]
Output : [-1, -1, -1]
*/

fun main() {
	val res = nextGreater(intArrayOf(4, 5, 2, 10)) // 5, 10, 10, -1
	res.forEach { print("$it ") }
}

fun nextGreater(nums: IntArray): IntArray {
	val stack = Stack<Int>()
	val result: MutableList<Int> = mutableListOf()

	var i = nums.lastIndex
	while (i >= 0) {
		if (stack.isEmpty()) {
			result.add(-1)
			stack.push(nums[i--])
		} else if (stack.peek() > nums[i]) {
			result.add(stack.peek())
			stack.push(nums[i--])
		} else
			stack.pop()
	}

	return result.toIntArray().reversedArray()
}