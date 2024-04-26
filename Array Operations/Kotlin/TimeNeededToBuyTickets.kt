import java.util.LinkedList
import kotlin.math.min

/*
* Time Needed to Buy Tickets
There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.
You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].
Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.
Return the time taken for the person at position k (0-indexed) to finish buying tickets.

Example 1:
Input: tickets = [2,3,2], k = 2
Output: 6
Explanation:
- In the first pass, everyone in the line buys a ticket and the line becomes [1, 2, 1].
- In the second pass, everyone in the line buys a ticket and the line becomes [0, 1, 0].
The person at position 2 has successfully bought 2 tickets and it took 3 + 3 = 6 seconds.

Example 2:
Input: tickets = [5,1,1,1], k = 0
Output: 8
Explanation:
- In the first pass, everyone in the line buys a ticket and the line becomes [4, 0, 0, 0].
- In the next 4 passes, only the person in position 0 is buying tickets.
The person at position 0 has successfully bought 5 tickets and it took 4 + 1 + 1 + 1 + 1 = 8 seconds.

Constraints:
n == tickets.length
1 <= n <= 100
1 <= tickets[i] <= 100
0 <= k < n
*/

fun main() {
	print(timeRequiredToBuy(intArrayOf(2, 3, 2), 2))
}

fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
	var timeTaken = 0
	val ticketToBuy = tickets[k]

	for (i in tickets.indices) {
		println(ticketToBuy)
		if (i <= k)
			timeTaken += min(ticketToBuy, tickets[i])
		else
			timeTaken += min(ticketToBuy - 1, tickets[i])
	}

	return timeTaken
}

class Solution {
	fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
		var secondsPassed = 0
		var currentBuying = 0

		while (tickets[k] > 0) {
			if (tickets[currentBuying] > 0) {
				tickets[currentBuying] -= 1
				secondsPassed += 1
			}
			currentBuying = (currentBuying + 1) % tickets.size
		}

		return secondsPassed
	}
}

class Solution {
	fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
		var result = 0
		val person = tickets[k]

		for (i in 0..<person) {
			for (j in tickets.indices) {
				if (i == person - 1 && j > k)
					break
				if (tickets[j] > 0) {
					tickets[j]--
					result++
				}
			}
		}

		return result
	}
}

class Solution {
	fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
		var currentIndex = 0
		var time = 0

		while (tickets[k] > 0) {
			if (tickets[currentIndex] > 0)
				time++

			tickets[currentIndex]--

			if (currentIndex == tickets.size - 1) {
				currentIndex = 0
			} else {
				currentIndex++
			}
		}
		return time
	}
}

class Solution {
	fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
		var time = tickets[k]

		for (i in k - 1 downTo 0) {
			time += min(tickets[k], tickets[i])
		}

		for (i in k + 1..tickets.lastIndex) {
			time += min(tickets[k] - 1, tickets[i])
		}

		return time
	}
}

class Solution {
	fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
		var step = 0
		var pos = 0
		while (tickets[k] > 0) {
			while (tickets[pos] == 0) pos = (pos + 1) % tickets.size
			tickets[pos]--
			pos = (pos + 1) % tickets.size
			step++
		}
		return step
	}
}

class Solution {
	fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
		var res = 0

		for (i in tickets.indices) {
			res += minOf(tickets[k] - if (i > k) 1 else 0, tickets[i])
		}

		return res
	}
}

class Solution {
	fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
		val queue = LinkedList<Pair<Int, Int>>()

		tickets.forEachIndexed { index, ticketsNum ->
			queue.addLast(Pair(index, ticketsNum))
		}

		var timeCounter = 0

		while (queue.isNotEmpty()) {
			val next = queue.removeFirst()
			timeCounter++

			val nextTicketsNum = next.second - 1
			if (nextTicketsNum == 0) {
				if (next.first == k)
					return timeCounter
			} else {
				queue.addLast(Pair(next.first, nextTicketsNum))
			}
		}

		return timeCounter
	}
}