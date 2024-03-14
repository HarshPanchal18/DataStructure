/*
* Find the Pivot Integer

Given a positive integer n, find the pivot integer x such that:
The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most one pivot index for the given input.

Example 1:
Input: n = 8
Output: 6
Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.

Example 2:
Input: n = 1
Output: 1
Explanation: 1 is the pivot integer since: 1 = 1.

Example 3:
Input: n = 4
Output: -1
Explanation: It can be proved that no such integer exist.

Constraints:
1 <= n <= 1000
*/

class Solution {
    fun pivotInteger(n: Int): Int {

        //val collection = (1..n).toList()
        val collection = List(n) { it + 1 }

        collection.forEach { num ->
            if (
                (collection.first()..num).sum()
                ==
                (num..collection.last()).sum()
            ) {
                return num
            }
        }
        return -1
    }
}

class Solution {
    fun pivotInteger(n: Int): Int {

        for(i in 1..n)
            if(sum(i) == (sum(n) - sum(i - 1)))
                return i

        return -1
    }

    fun sum(n: Int): Int {
        if (n == 0)
            return 0

        return n + sum(n - 1)
    }
}

class Solution {
    fun pivotInteger(n: Int): Int {
        var left = 0
        var right = (1..n).sum()
        for (i in 1..n) {
            left += i
            right -= i - 1
            if (left == right)
                return i
            else if (left > right)
                break
        }
        return -1
    }
}

class Solution {
    fun pivotInteger(n: Int): Int {
        var result : Int = 0
        for (i in 1..n) {
            if (sum(i, n)) {
                result = i
                break
            }
            else result = -1
        }
        return result
    }

    fun sum(x : Int, endPoint : Int) : Boolean {
        var sumLeft = 0
        var sumRight = 0

        for (j in 1..x)
            sumLeft += j
        for (j in endPoint downTo x)
            sumRight += j

        if (sumLeft == sumRight)
            return true
        else
            return false
    }
}

class Solution {
    fun pivotInteger(n: Int): Int {
        /*
            myObservation
            1. Find the sum of given elements of array as it is 1..n so sum of n natural formula to be used to find sum.
            2. Sqaure of Pivot number should be equal to sum of given array for pivot to be present.
            3. If step 2 is found, then return pivot value else
            4. -1
         */
            // step 1
            var sum = n * (n + 1) / 2

            //step 2
            var pivot = Math.sqrt(sum.toDouble()).toInt()

            //step 3
            return if (pivot * pivot == sum)
                    pivot
                else //step 4
                    -1
    }
}

class Solution {
    fun pivotInteger(n: Int): Int {
        var left = 0
        var right = (1..n).sum()

        for (i in 1..n) {
            left += i
            right -= i - 1
            if (left == right) return i
        }

        return -1
    }
}

class Solution {
    fun pivotInteger(n: Int): Int {
        for (i in 1..n) {
            var left = i
            var right = i
            var leftSum = 0
            var rightSum = 0

            while (left >= 1)
                leftSum += left--

            while (right <= n)
                rightSum += right++

            if (leftSum == rightSum)
                return i

        }

        return -1
    }
}

class Solution {
    fun pivotInteger(n: Int): Int {
        var sum = IntArray(n)
        if (n == 1)
            return 1

        sum[0] = 1

        for (i in 1..n - 1)
            sum[i] = i + 1 + sum[i - 1]

        for (i in n - 2 downTo 1)
            if (sum[i] == sum[n - 1] - sum[i - 1])
                return i + 1

        return -1
    }
}