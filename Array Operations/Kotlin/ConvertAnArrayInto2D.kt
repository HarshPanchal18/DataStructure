/*
* Convert an Array Into a 2D Array With Conditions

You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
The 2D array should contain only the elements of the array nums.
Each row in the 2D array contains distinct integers.
The number of rows in the 2D array should be minimal.

Return the resulting array. If there are multiple answers, return any of them.
Note that the 2D array can have a different number of elements on each row.

Example 1:
Input: nums = [1,3,4,1,2,3,1]
Output: [[1,3,4,2],[1,3],[1]]
Explanation: We can create a 2D array that contains the following rows:
- 1,3,4,2
- 1,3
- 1
All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
It can be shown that we cannot have less than 3 rows in a valid array.

Example 2:
Input: nums = [1,2,3,4]
Output: [[4,3,2,1]]
Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.

Constraints:
1 <= nums.length <= 200
1 <= nums[i] <= nums.length
*/

fun main() {
    val nums = intArrayOf(1,3,4,1,2,3,1)
    println(findMatrix(nums))
}

fun findMatrix(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<MutableSet<Int>>()

    for(n in nums) {
        var inserted = false

        for(row in result) {
            // If there is no row with this number, add the number to its own row.
            if(!row.contains(n)) {
                row.add(n)
                inserted = true
                break
            }
        }

        if(!inserted) {
            result.add(mutableSetOf(n)) // Add new row with adding n.
        }
    }

    return result.map { it.toList() }
}

fun findMatrix(nums: IntArray): List<List<Int>> {
    val map = HashMap<Int,Int>()
    nums.forEach{ num ->
        map[num] = map.getOrDefault(num,0) + 1
    }
    return map.toList().map { (key, value) -> List(value) {key} }
}

fun findMatrix(nums: IntArray): List<List<Int>> {
    // Sorting the input list to get all duplicates together and ascending order
    nums.sort()

    var i = 0
    var j = 0
    var k = 0
    val result = mutableListOf<List<Int>>()

    while(i < nums.size) {
        while(j < nums.size && nums[j]==nums[i]) {
            j++
        }
        result.add(listOf(nums[i],j-i))
        i = j
    }
    return result
}

fun findMatrix(nums: IntArray): List<List<Int>> {
    val actual = mutableListOf<MutableList<Int>>()
    // using map to store frequency of num
    val freqMap = mutableMapOf<Int, Int>() // num -> freq

    for (n in nums) {
        freqMap[n] = freqMap.getOrDefault(n, 0) + 1
    }

    // since each row has distinct number count max frequency to be the size of the list then initialize empty list for each row
    val max = freqMap.values.maxOf { it }
    for (i in 0 .. max - 1) {
        actual.add(mutableListOf())
    }

    // add the key (num) into list which list index based on the value
    freqMap.forEach { (key, value) ->
        for (i in 0 .. value - 1) {
            val list = actual[i]
            list.add(key)
        }
    }

    return actual
}

fun findMatrix(nums: IntArray): List<List<Int>> {
    val map = mutableMapOf<Int, Int>()
    nums.forEach {
        if (map.containsKey(it)) {
            map[it] = map[it]!! + 1
        } else {
            map[it] = 1
        }
    }
    val result = mutableListOf<List<Int>>()
    while (true) {
        val list = mutableListOf<Int>()
        map.forEach {
            if (it.value > 0) {
                list.add(it.key)
                map[it.key] = map[it.key]!! - 1
            }
        }
        if (list.size > 0){
            result.add(list)
        } else break
    }
    return result
}

fun findMatrix(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<MutableList<Int>>()
    val frequency = IntArray(nums.size+1)
    for (c in nums) {
        val listNumber = ++frequency[c]
        if (result.size < listNumber)
            result += mutableListOf<Int>()
        result.get(listNumber-1) += c
    }
    return result
}

fun findMatrix(nums: IntArray): List<List<Int>> {
    val freq = nums.toList().groupingBy { it }.eachCount()
    val layers = mutableMapOf<Int, MutableList<Int>>()

    for ((num, count) in freq) for (i in 1..count) layers.getOrPut(i) { mutableListOf() } += num

    return layers.values.toList()
}

fun findMatrix(nums: IntArray) = buildList<MutableList<Int>> {
    val f = IntArray(nums.size + 1)
    nums.forEach { v ->
        if (f[v] == size) add(mutableListOf())
        get(f[v]++) += v
    }
}

fun findMatrix(nums: IntArray): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    val counters = IntArray(201)

    var i = nums.size - 1
    while (i >= 0) {
        val index = nums[i]
        val row = counters[index]++
        when {
            row < result.size -> result[row].add(index)
            else -> {
                val newList = ArrayList<Int>()
                newList.add(index)
                result.add(newList)
            }
        }
        i--
    }

    return result
}

// Complexity: * Time O(N) and Space O(N) where N is the size of nums;
fun findMatrix(nums: IntArray): List<List<Int>> {
    val numsCount = nums.asIterable().groupingBy { it }.eachCount()
    val minRows = numsCount.values.max()
    val result = List(minRows) { mutableListOf<Int>() }
    for ((num, count) in numsCount) {
        for (index in 0..<count) {
            result[index].add(num)
        }
    }
    return result
}

fun findMatrix(nums: IntArray): List<List<Int>> =
    nums.fold(Pair(mutableListOf<MutableList<Int>>(), IntArray(nums.size + 10))) { r, n ->
        if (r.first.size <= r.second[n]) r.first.add(mutableListOf())
        r.also{ r.first[r.second[n]++].add(n) }
    }.first

fun findMatrix(nums: IntArray): List<List<Int>> {
    val counts = IntArray(nums.size+1)
    val lists = mutableListOf<MutableList<Int>>()

    nums.forEach {
        if (lists.size <= counts[it])
            lists.add(mutableListOf<Int>())

        lists[counts[it]++].add(it)
    }

    return lists
}