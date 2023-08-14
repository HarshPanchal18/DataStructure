/*
* Coin Change II
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the number of combinations that make up that amount.
If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10]
Output: 1
*/

fun main(args:Array<String>) {
    val arr = intArrayOf(1,2,5)
    val combinations = change(5,arr)
    print("$combinations")
}

fun change(amount:Int, coins:IntArray): Int {
    val dp = IntArray(amount + 1) // store the result of adding one to each coin in the coin list.
    dp[0]=1
    for(coin in coins)
        for(i in coin..amount)
            dp[i]+=dp[i-coin] // adds one to every coin except the first

    return dp[amount]
}