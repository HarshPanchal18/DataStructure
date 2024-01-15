/*
We define super digit of an integer x using the following rules:
Given an integer, we need to find the super digit of the integer.
If  has only  digit, then its super digit is x.
Otherwise, the super digit of x is equal to the super digit of the sum of the digits of x.

For example, the super digit of 9875 will be calculated as:
	super_digit(9875)   9+8+7+5 = 29
	super_digit(29) 	2 + 9 = 11
	super_digit(11)		1 + 1 = 2
	super_digit(2)		= 2

Example
n = 9875
k = 4

The number p is created by concatenating the string k  times so the initial p=9875987598759875.
    superDigit(p) = superDigit(9875987598759875)
                  9+8+7+5+9+8+7+5+9+8+7+5+9+8+7+5 = 116
    superDigit(p) = superDigit(116)
                  1+1+6 = 8
    superDigit(p) = superDigit(8)

All of the digits of p sum to 116. The digits of 116 sum to 8. 8 is only one digit, so it is the super digit.

Function Description
Complete the function superDigit in the editor below. It must return the calculated super digit as an integer.
superDigit has the following parameter(s):
string n: a string representation of an integer
int k: the times to concatenate n to make p

Returns
int: the super digit of n repeated k times

Input Format
The first line contains two space separated integers, n and k.

Constraints
1<=n<10^100000
1<=k<=10^5
*/

fun main() {
    println(superDigit("123123", 2))
}

/*fun superDigit(n: String, k: Int): Int {
    val digits: Long = n.repeat(k).toLong()
    var result = sumOfDigits(digits)

    while (result > 9)
        result = sumOfDigits(result.toLong())

    return result
}

fun sumOfDigits(n: Long): Int {
    var _n = n
    var sum = 0
    while (_n != 0.toLong()) {
        sum += _n.mod(10)
        _n /= 10
    }
    return sum
}*/

/*fun superDigit(n: String, k: Int): Int {
    var sum: Long = 0
    for (element in n) {
        sum += (element.code - '0'.code).toLong()
    }
    sum *= k
    return sumOfDigit(sum).toInt()
}*/

fun sumOfDigit(sum: Long): Long {
    var sum = sum
    if (sum <= 9) {
        return sum
    }
    var newSum: Long = 0
    while (sum > 0) {
        newSum += (sum % 10)
        sum /= 10
    }
    return sumOfDigit(newSum)
}

fun superDigit(n: String, k: Int): Int {
    if (n.length == 1) {
        return n.toInt()
    } else {
        var total: Long = 0
        for (element in n) {
            total += Character.getNumericValue(element)
        }
        total *= k
        return superDigit(total.toString(), 1)
    }
}