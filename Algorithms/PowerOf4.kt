/*
* Power of Four
Given an integer n, return true if it is a power of four. Otherwise, return false.
An integer n is a power of four, if there exists an integer x such that n == 4^x.

Example 1:
Input: n = 16
Output: true

Example 2:
Input: n = 5
Output: false

Example 3:
Input: n = 1
Output: true
*/

fun main(args: Array<String>) {
    print(square(16))
}

fun square(n: Int) : Boolean {
    if (n == 1) return true
    if (n <= 0) return false

    var _n = n
    while(_n > 1) {
        if(_n%4 != 0)
            return false
        _n /= 4
    }
    return true
}

fun isPowerOfFour(n: Int): Boolean {
    return n > 0  // verify if the number is negative
    && n and (n - 1) == 0     // ensure that there is only a single bit present
    && n and 0x55555555 == n  // verify the fourth power of the check bit
}


fun isPowerOfFour(n: Int): Boolean {
    return n > 0
    && (n and (n - 1)) == 0
    && n.countTrailingZeroBits() % 2 == 0
}

fun isPowerOfFour(n: Int): Boolean {
    return n.countOneBits() == 1 && n.countTrailingZeroBits() % 2 == 0
}