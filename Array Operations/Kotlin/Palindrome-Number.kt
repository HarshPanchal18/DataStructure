fun main(args:Array<String>) {
    print(isPalindrome(1221))
}

fun isPalindrome(x: Int): Boolean {
    // method 0
    return x.toString() == x.toString().reversed()

    // method 1
    val str = x.toString()
    for(i in 0 until str.length/2)
        if(str[i]!=str[str.length-i-1]) return false

    return true
}

fun isPalindrome(x: Int): Boolean {
    when {
        x < 0 -> return false
        x < 10 -> return true
    }
    val digitList = breakNumberIntoDigitsList(x)
    return isDigitListPalindrome(digitList)
}

private fun breakNumberIntoDigitsList(num: Int): List<Int> {
    val digitList = mutableListOf<Int>()
    var remaining = num

    while (remaining > 0) {
        val lastDigit = remaining % 10
        remaining /= 10
        digitList.add(lastDigit)
    }
    return digitList
}

private fun isDigitListPalindrome(digitList: List<Int>): Boolean {
    var start = 0
    var end = digitList.lastIndex

    while (start < end) {
        val startDigit = digitList[start]
        val endDigit = digitList[end]

        if (startDigit == endDigit) {
            start++
            end--
        } else {
            return false
        }
    }
    return true
}