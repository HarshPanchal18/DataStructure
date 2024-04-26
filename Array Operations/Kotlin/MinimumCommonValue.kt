/*
* Minimum Common Value

Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.
Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.

Example 1:
Input: nums1 = [1,2,3], nums2 = [2,4]
Output: 2
Explanation: The smallest element common to both arrays is 2, so we return 2.

Example 2:
Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
Output: 2
Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.

Constraints:
1 <= nums1.length, nums2.length <= 105
1 <= nums1[i], nums2[j] <= 109

Both nums1 and nums2 are sorted in non-decreasing order.
*/

fun getCommon(nums1: IntArray, nums2: IntArray): Int {
    nums1.sorted().forEach {
        if (nums2.contains(it))
            return it
    }
    return -1
}

fun getCommon(nums1: IntArray, nums2: IntArray): Int {
    var i = 0
    var j = 0

    while (i < nums1.size && j < nums2.size) {
        if (nums1[i] == nums2[j]) {
            return nums1[i]
        } else {
            if (nums1[i] < nums2[j]) {
                i++
            } else {
                j++
            }
        }
    }
    while (i < nums1.size) {
        if (nums1[i] == nums2[nums2.size - 1])
            return nums1[i]
        i++
    }
    while (j < nums2.size) {
        if (nums2[j] == nums1[nums1.size - 1])
            return nums2[j]
        j++
    }

    return -1
}

fun getCommon(nums1: IntArray, nums2: IntArray): Int {
    // Figure out which array is bigger
    val (small, large) =
        if (nums1.size < nums2.size) nums1 to nums2 else nums2 to nums1

    // If the expected number of comparisons in a binary search doesn't
    // beat the max number of comparisons in two-pointers, just use that.
    if (small.size * log2(large.size.toDouble()) > large.size) {
        var i = 0
        var j = 0
        while (i < small.size && j < large.size) {
            when {
                small[i] == large[j] -> return small[i]
                small[i] < large[j] -> i++
                else -> j++
            }
        }
    } else {
        // Otherwise binary search for each element of the small array
        // in the large array.
        small.forEach { x ->
            var low = 0
            var high = large.size - 1
            while (low <= high) {
                val mid = low + (high - low) / 2
                when {
                    x > large[mid] -> low = mid + 1
                    x == large[mid] -> return x
                    x < large[mid] -> high = mid - 1
                }
            }
        }
    }

    // Didn't find any common elements. Return -1
    return -1
}

fun getCommon(nums1: IntArray, nums2: IntArray): Int {
    var i = 0
    var j = 0

    while (i < nums1.size && j < nums2.size) {
        if (nums1[i] == nums2[j]) {
            return nums1[i]
        } else {
            if (nums1[i] < nums2[j]) i++
            else j++
        }
    }
    for (ind in i..<nums1.size) {
        if (nums1[ind] == nums2[nums2.size - 1]) {
            return nums1[ind]
        }
    }
    for (ind in j..<nums2.size) {
        if (nums2[ind] == nums1[nums1.size - 1]) {
            return nums2[ind]
        }
    }

    return -1
}

fun getCommon(nums1: IntArray, nums2: IntArray): Int {
    var i = 0
    var j = 0
    val n = nums1.size
    val m = nums2.size

    while (i < n && j < m) {
        when {
            nums1[i] == nums2[j] -> return nums1[i]
            nums1[i] > nums2[j] -> j++
            else -> i++
        }
    }

    return -1
}

fun getCommon(nums1: IntArray, nums2: IntArray): Int {
    var i1 = 0
    var i2 = 0
    while (i1 < nums1.size && i2 < nums2.size) {
        val diff = nums2[i2] - nums1[i1]
        when {
            diff == 0 -> return nums1[i1]
            diff > 0 -> i1++
            else -> i2++
        }
    }
    return -1
}