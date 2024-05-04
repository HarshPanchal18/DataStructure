import java.util.*

/*
* Boats to Save People
You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
Return the minimum number of boats to carry every given person.

Example 1:
Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)

Example 2:
Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)

Example 3:
Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)

Constraints:
1 <= people.length <= 5 * 104
1 <= people[i] <= limit <= 3 * 104
*/

fun main() {
	println(numRescueBoat(intArrayOf(3, 2, 2, 1), 3))
}

fun numRescueBoat(people: IntArray, limit: Int): Int {
	people.sort()
	var boats = 0
	var i = 0
	var j = people.lastIndex

	while (i <= j) {
		if (people[i] + people[j] <= limit)
			i++
		j--
		boats++
	}

	return boats
}


fun numRescueBoats(people: IntArray, limit: Int): Int {
	people.sortDescending()

	var j = people.lastIndex

	for ((i, p) in people.withIndex())
		if (i > j) return i
		else if (p + people[j] <= limit) j--

	return people.size
}

class Solution {
	fun numRescueBoats(people: IntArray, limit: Int): Int {
		people.sort()
		var antokir = 0
		var l = 0
		var r: Int = people.size - 1

		while (l <= r) {
			if (people[r] + people[l] <= limit) {
				l++
				r--
				antokir++
			} else if (people[r] <= limit) {
				r--
				antokir++
			}
		}
		return antokir
	}
}

class Solution {
	fun numRescueBoats(people: IntArray, limit: Int): Int {
		people.sortDescending()

		var left = 0
		var right = people.size - 1
		var count = 0
		val rest = LinkedList<Int>()

		while (left <= right) {
			rest.offer(limit - people[left++])
			count++

			while (right >= left && rest.isNotEmpty() && people[right] > rest.peek())
				rest.poll()

			if (rest.isNotEmpty()) {
				rest.poll()
				right--
			}

		}

		return count
	}
}

class Solution {
	fun numRescueBoats(people: IntArray, limit: Int): Int {
		people.sort()
		var boatCnt = 0
		var peopleLeft = people.size
		var l = 0
		var r = people.lastIndex

		while (peopleLeft > 0) {
			if (l != r && people[l] + people[r] <= limit) {
				++l
				--r
				peopleLeft -= 2
			} else {
				--r
				peopleLeft -= 1
			}
			++boatCnt
		}

		return boatCnt
	}
}

class Solution {
	fun numRescueBoats(people: IntArray, limit: Int): Int {
		people.sort()
		var boatCount = 0
		var left = 0
		var right = people.size - 1

		while(right >= left) {
			if (right == left) {
				boatCount += 1
				right -= 1
				left += 1
			} else {
				val weightR = people[right]
				val weightL = people[left]
				val total = weightR +  weightL

				if (total <= limit) {
					boatCount += 1
					right -= 1
					left += 1
				} else {
					boatCount += 1
					right -= 1
				}
			}
		}

		return boatCount
	}
}