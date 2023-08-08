/*
* Remove duplicates from sorted array
Given an integer array nums sorted in non-decreasing order,
remove the duplicates in-place such that each unique element appears only once.
The relative order of the elements should be kept the same.
Then return the number of unique elements in nums.
Consider the number of unique elements of nums to be j, to get accepted, you need to do the following things:
Change the array nums such that the first j elements of nums contain the unique elements in the order they were present in nums initially.
The remaining elements of nums are not important as well as the size of nums.
Return j.
*/

fun main(args:Array<String>) {
    val arr = intArrayOf(4,5,7,7,7,8,8,9)
    println(removeDuplicates(arr))
    println(removeDuplicates0(arr))
}

fun removeDuplicates(nums: IntArray): Int {
    var j = 1
    for(i in 1 until nums.size) {
        if(nums[i] != nums[i-1]) { // Comparing with the predecessor
            nums[j] = nums[i] // Store if unidentical and increment the Jth index
            j+=1
        }
    }
    return j
}

fun removeDuplicates0(nums:IntArray): Int {
    nums.distinct().let {
        it.forEachIndexed { i, v -> nums[i] = v }
        return it.size
    }
}