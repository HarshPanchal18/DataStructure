/*
* Defuse the Bomb

You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.
To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.
* If k > 0, replace the ith number with the sum of the next k numbers.
* If k < 0, replace the ith number with the sum of the previous k numbers.
* If k == 0, replace the ith number with 0.

As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].
Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!

Example 1:
Input: code = [5,7,1,4], k = 3
Output: [12,10,16,13]
Explanation: Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.

Example 2:
Input: code = [1,2,3,4], k = 0
Output: [0,0,0,0]
Explanation: When k is zero, the numbers are replaced by 0.

Example 3:
Input: code = [2,4,9,3], k = -2
Output: [12,5,6,13]
Explanation: The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. If k is negative, the sum is of the previous numbers.

Constraints:
n == code.length
1 <= n <= 100
1 <= code[i] <= 100
-(n - 1) <= k <= n - 1
*/

fun main() {
    val result = decrypt(intArrayOf(5, 7, 1, 4), 3)
    result.forEach { println(it) }
}

fun decrypt(code: IntArray, k: Int): IntArray {
    val n = code.size
    val result = IntArray(n)
    if (k == 0) return code

    var start = if (k > 0) 1 else n + k
    var end = if (k > 0) k else n - 1

    val doubleArray = code + code

    var sum = (start..end).sumOf { doubleArray[it] }
    for (i in code.indices) {
        result[i] = sum

        sum -= doubleArray[start++]
        sum += doubleArray[++end]
    }

    return result
}

class Solution {
    fun decrypt(code: IntArray, k: Int): IntArray {
        val n = code.size
        val res = IntArray(n)

        if (k == 0) return res

        for (i in code.indices) {
            var sum = 0

            if (k > 0) {
                for (j in 1..k)
                    sum += code[(i + j) % n]
            } else {
                for (j in 1..-k)
                    sum += code[(i - j + n) % n]
            }

            res[i] = sum
        }

        return res
    }
}

class Solution {
    fun decrypt(code: IntArray, k: Int): IntArray {
        val n = code.size
        val arr = IntArray(n)
        if (k == 0) return arr

        val pre = IntArray(2 * n)
        var sum = 0
        for (i in 1..2 * n - 1) {
            sum += code[i % n]
            pre[i] = sum
        }

        for (i in 0..n - 1) {
            if (k >= 0) arr[i] = pre[i + k] - pre[i]
            else arr[i] = pre[n + i - 1] - pre[n + i + k - 1]
        }

        return arr
    }
}

class Solution {
    fun decrypt(code: IntArray, k: Int): IntArray {
        val n = code.size
        val arr = IntArray(n)
        if (k == 0) return arr

        var (start, end, sum) = listOf(1, k, 0)
        if (k < 0) {
            start = n + k
            end = n - 1
        }

        for (i in start..end) sum += code[i]

        for (i in 0..n - 1) {
            arr[i] = sum
            sum -= code[start++ % n]
            sum += code[++end % n]
        }

        return arr
    }
}

class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the size of code.
     */
    fun decrypt(code: IntArray, k: Int): IntArray {
        if (k == 0) {
            return IntArray(code.size)
        }
        val n = code.size
        val result = IntArray(n)
        var (start, end) = if (k < 0) k to 0 else 1 to k + 1
        result[0] = (start..<end).sumOf { code[(it + n) % n] }

        start = if (start < 0) start + n else start
        for (i in 1..<n) {
            result[i] = result[i - 1] + code[end % n] - code[start % n]
            start += 1
            end += 1
        }
        return result
    }
}