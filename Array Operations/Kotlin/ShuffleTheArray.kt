/*
* Shuffle the Array
Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
Return the array in the form [x1,y1,x2,y2,...,xn,yn].

Example 1:
Input: nums = [2,5,1,3,4,7], n = 3
Output: [2,3,5,4,1,7]
Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].

Example 2:
Input: nums = [1,2,3,4,4,3,2,1], n = 4
Output: [1,4,2,3,3,2,4,1]

Example 3:
Input: nums = [1,1,2,2], n = 2
Output: [1,2,1,2]
*/

fun shuffle(nums: IntArray, n: Int): IntArray {
    val arr = ArrayList<Int>()
    for(i in 0 until nums.size/2) {
    //for(i in 0 until n) {
        arr.add(nums[i])
        arr.add(nums[i+n])
    }
    return arr.toIntArray()
}

fun shuffle(nums: IntArray, n: Int) = nums.zip(
    nums.drop(n)
).flatMap { it.toList() }

fun shuffle(nums: IntArray, n: Int): IntArray {
    return IntArray(2*n) {i -> if (i % 2 == 0) nums[i/2] else nums[i/2 + n]}
}

fun shuffle(nums: IntArray, n: Int): IntArray {
    var res = IntArray(nums.size)
    for (i in 0 until n)
        res[i * 2] = nums[i]
    for (i in n until nums.size)
        res[(i - n) * 2 + 1] = nums[i]
    return res
}

fun shuffle(nums: IntArray, n: Int): IntArray {
    val result = IntArray(nums.size)
    for(i in 0 until n){
        result[2*i] = nums[i]
        result[2*i+1] = nums[n+i]
    }
    return result
}

fun shuffle(nums: IntArray, n: Int): IntArray {
    val result = IntArray(2 * n)

    for(i in 0 until 2 * n) {
        result[i] = nums[i / 2 + if (i % 2 == 0) 0 else n]
    }

    return result
}