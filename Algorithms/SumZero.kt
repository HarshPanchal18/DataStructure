// Sum of two elements of sorted array is ZERO with 2 pointer technique - O(n)
fun main(args: Array<String>) {

    val array = intArrayOf(-5, -4, -1, 0, 4, 5, 7, 9)
    var left = 0
    var right = array.size-1

    while(left < right) {
        val sum = array[left] + array[right]
        if(sum == 0) {
            print("${array[left]}, ${array[right]}")
            break
        } else if(sum < 0) {
            left++
        } else {
            right--
        }
    }
}
