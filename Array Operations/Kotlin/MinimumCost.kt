/*
* Minimum Cost of Buying Candies With Discount
A shop is selling candies at a discount. For every two candies sold, the shop gives a third candy for free.
The customer can choose any candy to take away for free as long as the cost of the chosen candy is less than or equal to the minimum cost of the two candies bought.
For example, if there are 4 candies with costs 1, 2, 3, and 4, and the customer buys candies with costs 2 and 3, they can take the candy with cost 1 for free, but not the candy with cost 4.
Given a 0-indexed integer array cost, where cost[i] denotes the cost of the ith candy, return the minimum cost of buying all the candies.

Example 1:
Input: cost = [1,2,3]
Output: 5
Explanation: We buy the candies with costs 2 and 3, and take the candy with cost 1 for free.
The total cost of buying all candies is 2 + 3 = 5. This is the only way we can buy the candies.
Note that we cannot buy candies with costs 1 and 3, and then take the candy with cost 2 for free.
The cost of the free candy has to be less than or equal to the minimum cost of the purchased candies.

Example 2:
Input: cost = [6,5,7,9,2,2]
Output: 23
Explanation: The way in which we can get the minimum cost is described below:
- Buy candies with costs 9 and 7
- Take the candy with cost 6 for free
- We buy candies with costs 5 and 2
- Take the last remaining candy with cost 2 for free
Hence, the minimum cost to buy all candies is 9 + 7 + 5 + 2 = 23.

Example 3:
Input: cost = [5,5]
Output: 10
Explanation: Since there are only 2 candies, we buy both of them. There is not a third candy we can take for free.
Hence, the minimum cost to buy all candies is 5 + 5 = 10.

Constraints:
1 <= cost.length <= 100
1 <= cost[i] <= 100
*/

class Solution {
    fun minimumCost(cost: IntArray): Int {
        var result = 0

        cost.sort()
        for(i in 1..cost.size)
            result += if(i % 3 == 0) 0 else cost[cost.size - i]

        return result
    }
}

class Solution {
    fun minimumCost(cost: IntArray): Int {
        val maxHeap = PriorityQueue<Int> {a, b -> b - a}
        var res = 0

        for (c in cost)
            maxHeap.offer(c)

        while (maxHeap.isNotEmpty()) {
            if (maxHeap.size >= 3) {
                repeat(3) {
                    val c = maxHeap.poll()
                    if (it != 2)
                        res += c

                }
            } else {
                res += maxHeap.poll()
            }
        }

        return res
    }
}

class Solution {
    fun minimumCost(cost: IntArray): Int {
        var result = 0

        cost.sort()
        for (i in cost.size - 1 downTo 0 step 3)
            result += cost[i] + if (i > 0) cost[i - 1] else 0

        return result
    }
}

class Solution {
    fun minimumCost(cost: IntArray): Int {
        val sortDesc = cost.sorted()
            .reversed()

        var total = 0
        for (i in 1..sortDesc.size) {
            if (i % 3 != 0) {
                total += sortDesc[i - 1]
            }
        }

        return total
    }
}

/*

Sort

Starting backwards, take 2, skip 1, until less than 3 remain

[6, 5, 7, 9, 2, 2, 1]
[9, 7, 6, 5, 2, 2, 1]
Buy:  9 + 7 + 5 + 2 + 1
Free: 6 + 2

0 % 3 == 0
1 % 3 == 1
2 % 3 == 2
3 % 3 == 0

Runtime: nlogn > assuming sorted most likely uses some iteration of quick sort under the hood
Space:   n     > since we store a list equal to 'cost'
*/

class Solution {
    fun minimumCost(cost: IntArray): Int {
        return if (cost.size < 2) cost[0]
        else cost.sortedDescending().windowed(2, 3, true).sumOf { it.sum() }
    }
}

class Solution {
    fun minimumCost(c: IntArray): Int {
        if (c.size < 3)
            return c.sum()

        var cost = c.toMutableList()
        var minCost = 0
        var idx1 = 0

        cost.sortDescending()
        while (idx1 < cost.size-1) {
            minCost += cost[idx1]
            minCost += cost[idx1+1]
            // find nextValueToRemove index
            innerLoop@ for (idx2 in idx1+2 until cost.size) {
                if (cost[idx2] <= cost[idx1+1]) {
                    cost.removeAt(idx2)
                    break@innerLoop
                }
            }
            idx1+=2
        }

        while (idx1 < cost.size) {
            minCost += cost[idx1]
            idx1++
        }

        return minCost
    }
}

class Solution {
    fun minimumCost(cost: IntArray): Int {
        if(cost.size < 3)
            return cost.sum()

        cost.sortDescending()

        var sum = 0
        for(i in 0..cost.size -1 step 3) {
            sum += cost[i]
            if(i+1 < cost.size)
                sum += cost[i+1]
        }

        return sum
    }
}