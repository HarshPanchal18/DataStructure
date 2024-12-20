import java.util.*

/*
* Maximum Number of Operations to Move Ones to the End

You are given a binary strings.
You can perform the following operation on the string any number of times:
Choose any index i from the string where i + 1 < s.length such that s[i] == '1' and s[i + 1] == '0'.
Move the character s[i] to the right until it reaches the end of the string or another '1'. For example, for s = "010010", if we choose i = 1, the resulting string will be s = "000110".
Return the maximum number of operations that you can perform.

Example 1:
Input: s = "1001101"
Output: 4
Explanation:
We can perform the following operations:
Choose index i = 0. The resulting string is s = "0011101".
Choose index i = 4. The resulting string is s = "0011011".
Choose index i = 3. The resulting string is s = "0010111".
Choose index i = 2. The resulting string is s = "0001111".

Example 2:
Input: s = "00111"
Output: 0

Constraints:
1 <= s.length <= 105
s[i] is either '0' or '1'.
*/

fun main() {
    println(maxOperations("0010111"))
}

fun maxOperations(s: String): Int {
    var cnt = 0
    var res = 0
    for (i in s.indices) {
        if (s[i] == '1') ++cnt
        else if (i > 0 && s[i - 1] == '1') res += cnt
    }
    return res
}

class Solution {
    fun maxOperations(binaryString: String): Int {
        val binaryArray = binaryString.toCharArray()
        var countOnes = binaryArray[0].code - '0'.code
        var maxOperations = 0

        for (i in 1 until binaryArray.size) {
            countOnes += binaryArray[i].code - '0'.code
            maxOperations += if (binaryArray[i - 1] > binaryArray[i]) countOnes else 0
        }
        return maxOperations
    }
}

class Solution {
    fun maxOperations(s: String): Int {

        val st = Stack<Int>()
        var ans = 0
        val n = s.length

        for (i in s.indices) {
            if (s[i] == '1' && st.isEmpty()) {
                st.push(i)
            } else if (s[i] == '1') {
                while (st.peek() != i - 1) {
                    st.pop()
                    st.push(i - 1)
                    ans += st.size
                }
                st.push(i)
            }
        }

        if (st.isEmpty())
            return 0

        if (n - 1 != st.peek())
            ans += st.size

        return ans
    }
}