fun main(args:Array<String>) {
    val array = intArrayOf(2,1,2,3,5)
    val res = twoSum(array,4)
    res.forEach { print(it) }
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    for(i in 0..nums.size-1) {
        for(j in i+1..nums.size-1) {
            if(nums[i]+nums[j]==target) return intArrayOf(i,j)
        }
    }
    return intArrayOf()
}