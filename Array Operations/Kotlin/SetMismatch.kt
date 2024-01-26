/*
* Set Mismatch
You have a set of integers s, which originally contains all the numbers from 1 to n.
Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set,which results in repetition of one number and loss of another number.
You are given an integer array nums representing the data status of this set after the error.
Find the number that occurs twice and the number that is missing and return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]

Example 2:
Input: nums = [1,1]
Output: [1,2]

Constraints:
2 <= nums.length <= 104
1 <= nums[i] <= 104
*/

fun main() {
    val arr = intArrayOf(1,2,2,4)
    val result = findErrorNums(arr)
    result.forEach{ println(it) }
}

fun findErrorNums(nums: IntArray): IntArray {
    val set = mutableSetOf<Int>()
    var duplicate = 0
    var missing = 0

    nums.forEach {
        if(set.contains(it))
            duplicate = it
        else
            set.add(it)
    }

    for(i in 1..nums.size) {
        if(!set.contains(i)) {
            missing = i
            break
        }
    }

    return intArrayOf(duplicate, missing)
}

fun findErrorNums(nums: IntArray): IntArray {
    val arr = IntArray(nums.size) { 0 }
    var repeatedNum = 0
    var missedNum = 0

    nums.forEach {
        nums.forEach {
            if (arr[it - 1] == 1)  repeatedNum = it else arr[it - 1] += 1
        }
    }
    missedNum = arr.indexOf(0) + 1

    return intArrayOf(repeatedNum, missedNum)
}

fun findErrorNums(nums: IntArray): IntArray {
    var duplicate = 0
    val flags = BooleanArray(nums.size + 1)

    for (n in nums) {
        if (flags[n])
            duplicate = n
        else
            flags[n] = true
    }

    val missing = flags.indexOfLast { it == false }

    return intArrayOf(duplicate, missing)
}


fun findErrorNums(nums: IntArray): IntArray {
    val expectedSum = (1 + nums.size) * (nums.size) shr 1
    val (double, sum) = findDoubleAndSum(nums)
    return intArrayOf(double, double + (expectedSum - sum))
}

private fun findDoubleAndSum(nums: IntArray): Pair<Int, Int> {
    val met = BooleanArray(nums.size + 1)
    var sum = 0
    var double = 0
    for (ni in nums) {
        if (met[ni]) double = ni
        else met[ni] = true
        sum += ni
    }
    return double to sum
}

fun findErrorNums(nums: IntArray): IntArray {
    val len = nums.size
    var xor_res = 0
    var a = 0
    var b = 0
    var rez = IntArray(2)

    for (i in 1..len) {
        xor_res = xor_res xor i
        xor_res = xor_res xor nums[i-1]
    }

    val bit_mask = xor_res and -xor_res // 1 in one of the bits that differ between A and B
    for (i in 1..len) {
        if (i and bit_mask == 0) a = a xor i
        else b = b xor i

        if (nums[i-1] and bit_mask == 0) a = a xor nums[i-1]
        else b = b xor nums[i-1]
    }
    if (nums.indexOf(a)>=0) {
        rez[0]=a
        rez[1]=b
    } else {
        rez[1]=a
        rez[0]=b
    }

    return rez
}

fun findErrorNums(nums: IntArray) = with(nums) {
    val missing = ((1..size) - toSet()).first()
    val delta = sum() - (size + 1) * size / 2
    intArrayOf(missing + delta, missing)
}

fun findErrorNums(nums: IntArray): IntArray {
    val n = nums.size
    var totalNum = n * (n + 1) / 2
    var duplicateNum = 0
    val numberSet = hashSetOf<Int>()

    nums.forEach { num ->
        if (numberSet.add(num))
            totalNum -= num
        else
            duplicateNum = num
    }

    return intArrayOf(duplicateNum, totalNum)
}


fun findErrorNums(nums: IntArray): IntArray {
    val counts = IntArray(nums.size + 1)
    nums.forEach { num ->
        counts[num]++
    }

    val result = IntArray(2) // duplicated, missing
    for (i in 1 until counts.size) {
        if (counts[i] == 0) {
            result[1] = i
            if (result[0] != 0) break
        } else if (counts[i] == 2) {
            result[0] = i
            if (result[1] != 0) break
        }
    }

    return result
}

fun findErrorNums(nums: IntArray): IntArray {
    Arrays.sort(nums)

    var curr = 1
    var x1 = 0
    var x2 = 0
    var repeat = -1
    val n = nums.size

    for (i in nums.indices) {
        var num = nums[i]
        if (i < n - 1 && num == nums[i + 1])
            repeat = num
         else
            x1 = x1 xor num

        x2 = x2 xor curr
        curr += 1
    }

    return intArrayOf(repeat, x1 xor x2)
}