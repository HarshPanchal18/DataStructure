import java.util.*

/*
* Open the Lock - https://leetcode.com/problems/open-the-lock/ (Array - Hash Table - String - Breadth-First Search)
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
The lock initially starts at '0000', a string representing the state of the 4 wheels.
You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

Example 1:
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

Example 2:
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".

Example 3:
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation: We cannot reach the target without getting stuck.

Constraints:
1 <= deadends.length <= 500
deadends[i].length == 4
target.length == 4
target will not be in the list deadends.
target and deadends[i] consist of digits only.
*/

fun main() {
	println(openLock(arrayOf("0201","0101","0102","1212","2002"),"0202"))
}

// Representing Lock Code
data class LockCode(val first: Int, val second: Int, val third: Int, val fourth: Int) {
	//override fun toString() = buildString { append(first, second, third, fourth) }
	override fun toString() = "$first$second$third$fourth"

	fun generateNextLevel(): List<LockCode> {
		return listOf(
			copy(first = first.next()),
			copy(second = second.next()),
			copy(third = third.next()),
			copy(fourth = fourth.next()),
			copy(first = first.prev()),
			copy(second = second.prev()),
			copy(third = third.prev()),
			copy(fourth = fourth.prev())
		)
	}
}

fun Int.next(): Int = if (this == 9) 0 else (this + 1)
fun Int.prev(): Int = if (this == 0) 9 else (this - 1)

fun openLock(deadends: Array<String>, target: String): Int {
	var count = 0
	val notValid = deadends.toSet()

	val queue: Queue<LockCode> = LinkedList()
	queue.add(LockCode(0, 0, 0, 0))

	val used = mutableSetOf<LockCode>()

	while (queue.isNotEmpty()) {
		repeat(queue.size) {
			val newCode = queue.poll() // Retrieves and removes the head of the queue
			if (newCode.toString() == target) // Target is found
				return count

			if (newCode.toString() !in notValid) {
				val next = newCode.generateNextLevel()

				next.forEach { code ->
					if (code.toString() == target)
						return count + 1

					if (code.toString() !in notValid && used.add(code))
						queue.add(code)
				}
			}
		}
		count++
	}

	return -1
}

// To generate single possible lock changes
/*private fun LockCode.generateNextLevel(): List<LockCode> =
	listOf(
		copy(first = first.next()),
		copy(second = second.next()),
		copy(third = third.next()),
		copy(forth = forth.next()),
		copy(first = first.prev()),
		copy(second = second.prev()),
		copy(third = third.prev()),
		copy(forth = forth.prev())
	)*/

class Solution {
	fun openLock(deadends: Array<String?>, target: String): Int {
		val deadendSet: Set<String> = HashSet(mutableListOf(*deadends))
		if (deadendSet.contains("0000"))
			return -1

		val queue: Queue<Pair<String, Int>> = LinkedList()
		queue.offer(Pair("0000", 0))

		val visited: MutableSet<String> = HashSet()
		visited.add("0000")

		while (queue.isNotEmpty()) {
			val current = queue.poll()
			val currentCombination: String = current.first
			val moves: Int = current.second

			if (currentCombination == target)
				return moves

			for (i in 0..3) {
				for (delta in intArrayOf(-1, 1)) {
					val newDigit = (currentCombination[i].code - '0'.code + delta + 10) % 10
					val newCombination =
						currentCombination.substring(0, i) + newDigit + currentCombination.substring(i + 1)

					if (visited.notContains(newCombination) && deadendSet.notContains(newCombination)) {
						visited.add(newCombination)
						queue.offer(Pair(newCombination, moves + 1))
					}
				}
			}
		}

		return -1 // Target is not reachable
	}

	fun <E> Collection<E>.notContains(element: E): Boolean {
		return this.contains(element).not()
	}

}

class Solution {
	fun openLock(deadends: Array<String>, target: String): Int {
		val pow10 = intArrayOf(1, 10, 100, 1000)
		val visit =
			IntArray(10000) // 0: not visited, 1: visited through forward direction, -1: visited through backward direction, 2: deadends
		for (dead in deadends) {
			visit[dead.toInt()] = 2
		}
		val src = 0
		val dest = target.toInt()
		var steps = 0
		var dir = 1
		if (visit[src] == 2 || visit[dest] == 2) return -1
		if (src == dest) return 0
		var forward: Queue<Int> = LinkedList()
		var backward: Queue<Int> = LinkedList()
		forward.add(src)
		visit[src] = 1
		backward.add(dest)
		visit[dest] = -1

		while (forward.isNotEmpty() && backward.isNotEmpty()) {
			if (forward.size > backward.size) {
				val tmp = forward
				forward = backward
				backward = tmp
				dir = -dir
			}

			steps++

			var size = forward.size
			while (size-- > 0) {
				val cur = forward.poll()

				for (p in pow10) {
					val d = (cur / p) % 10
					var i = -1

					while (i <= 1) {
						var z = d + i
						z = if (z == -1) 9 else (if (z == 10) 0 else z)
						val next = cur + (z - d) * p
						if (visit[next] == -dir)
							return steps

						if (visit[next] == 0) {
							forward.add(next)
							visit[next] = dir
						}
						i += 2
					}
				}
			}
		}
		return -1
	}
}

class Solution {
	fun openLock(deadends: Array<String>, target: String): Int {
		val deadSet = deadends.toHashSet()
		val visited = HashSet<String>()
		val queue = LinkedList<String>()
		queue.offer("0000")
		visited.add("0000")
		var steps = 0

		while (queue.isNotEmpty()) {
			val size = queue.size
			repeat(size) {
				val current = queue.poll()
				if (current == target) return steps
				if (!deadSet.contains(current)) {
					// The code inside this block will execute only if current is not in deadSet
					for (i in 0 until 4) {
						for (d in -1..1 step 2) {
							val next = current.substring(
								0,
								i
							) + ((current[i] - '0' + d + 10) % 10).toString() + current.substring(i + 1)
							if (!visited.contains(next)) {
								visited.add(next)
								queue.offer(next)
							}
						}
					}
				}
			}
			steps++
		}
		return -1
	}
}

class Solution {

	fun openLock(deadends: Array<String>, target: String): Int {
		val visited = HashSet<String>()

		val initState = "0000"

		deadends.forEach { deadend ->
			if (target == deadend || initState == deadend)
				return -1

			visited.add(deadend)
		}

		val queue: Queue<String> = LinkedList()
		queue.add(initState)
		visited.add(initState)

		var count = 0

		while (queue.isNotEmpty()) {
			val queueSize = queue.size

			repeat(queueSize) {
				val lock = queue.poll()

				if (lock == target)
					return count

				addTurnedWheelsToQueue(queue, visited, lock, true)
				addTurnedWheelsToQueue(queue, visited, lock, false)
			}

			count++
		}

		return -1
	}

	private fun addTurnedWheelsToQueue(
		queue: Queue<String>,
		visited: HashSet<String>,
		lock: String,
		isTurnLeft: Boolean
	) {
		repeat(4) { rockNumber ->
			val turnedWheel = turnWheel(lock, rockNumber, isTurnLeft)
			if (visited.add(turnedWheel))
				queue.add(turnedWheel)
		}
	}

	private fun turnWheel(target: String, rockNumber: Int, isTurnLeft: Boolean): String {
		val currentWheel = StringBuilder(target)
		val targetNumber = currentWheel[rockNumber]

		if (isTurnLeft) currentWheel[rockNumber] = if (targetNumber == '9') '0' else targetNumber + 1
		else currentWheel[rockNumber] = if (targetNumber == '0') '9' else targetNumber - 1


		return currentWheel.toString()
	}
}

class Solution {
	private val nextCombinationMap = mapOf(
		'0' to setOf('1', '9'),
		'1' to setOf('0', '2'),
		'2' to setOf('1', '3'),
		'3' to setOf('2', '4'),
		'4' to setOf('3', '5'),
		'5' to setOf('4', '6'),
		'6' to setOf('5', '7'),
		'7' to setOf('6', '8'),
		'8' to setOf('7', '9'),
		'9' to setOf('8', '0'),
	)

	private fun getNextCombinationSlot(slot: Char): Set<Char> {
		return this.nextCombinationMap[slot]!!
	}

	private fun getNextCombinations(combination: String): Set<String> {
		val possible = mutableSetOf<String>()

		for (i in combination.indices) {
			val char = combination[i]
			val possibleForChar = this.getNextCombinationSlot(char)

			for (pChar in possibleForChar) {
				var newCombination = ""

				for (j in 0 until i)
					newCombination += combination[j]

				newCombination += pChar

				for (j in i + 1 until combination.length)
					newCombination += combination[j]

				possible.add(newCombination)
			}
		}

		return possible
	}

	fun openLock(deadends: Array<String>, target: String): Int {
		val nodes: MutableList<String> = LinkedList<String>()
		val visited: MutableSet<String> = mutableSetOf(*deadends)

		nodes.add("0000")

		var level = 0
		while (nodes.isNotEmpty()) {
			val nodesToAdd = mutableListOf<String>()

			for (node in nodes) {
				if (visited.contains(node))
					continue

				if (node == target)
					return level

				nodesToAdd.addAll(getNextCombinations(node))
				visited.add(node)
			}

			nodes.clear()
			nodes.addAll(nodesToAdd)

			level++
		}

		return -1
	}
}

class Solution {
	fun openLock(deadends: Array<String>, target: String): Int {
		val deads = deadends.toSet()
		val visited = mutableSetOf<String>()
		val queue: Queue<Pair<String, Int>> = LinkedList()
		queue.add(Pair("0000", 0))

		while (queue.isNotEmpty()) {
			val (state, turns) = queue.poll()

			if (state in deads || state in visited)
				continue
			if (state == target)
				return turns

			visited.add(state)

			for (i in 0..3) {
				val digit = state[i] - '0'

				for (dir in listOf(-1, 1)) {
					val newDigit = (digit + dir + 10) % 10
					val newState = state.substring(0, i) + "$newDigit" + state.substring(i + 1)

					if (newState !in visited && newState !in deads)
						queue.add(Pair(newState, turns + 1))
				}
			}
		}

		return -1

	}
}

import kotlin.math.min

class Solution {
	class Item(
		val parent: Item?,
		val len: Int,
		val lock: IntArray
	)

	fun openLock(deadends: Array<String>, target: String): Int {
		val targetInt = target.map { it.toString().toInt() }.toIntArray()
		val deadendsMap = IntArray(10000) { Int.MAX_VALUE }
		deadends.forEach { deadendsMap[it.toInt()] = -1 }

		fun IntArray.number(): Int {
			var number = 0
			for (v in this) {
				number *= 10
				number += v
			}
			return number
		}

		fun IntArray.moveForward(pos: Int): IntArray {
			val res = copyOf()
			res[pos] = (res[pos] + 1) % 10
			return res
		}

		fun IntArray.moveBackward(pos: Int): IntArray {
			val res = copyOf()
			res[pos] = if (res[pos] > 0) res[pos] - 1 else 9
			return res
		}

		val queue = ArrayDeque<Item>()
		queue.addFirst(Item(null, 0, intArrayOf(0, 0, 0, 0)))

		var result: Int? = null

		while (queue.isNotEmpty()) {
			val item = queue.removeLast()
			val lock = item.lock

			if (lock.contentEquals(targetInt)) {
				result = min(result ?: item.len, item.len)
				continue
			}

			val number = lock.number()
			if (deadendsMap[number] > item.len)
				deadendsMap[number] = item.len
			else
				continue

			fun add(lock: IntArray) {
				queue.addFirst(Item(item, item.len + 1, lock))
			}

			for (pos in 0 until 4) {
				add(lock.moveForward(pos))
				add(lock.moveBackward(pos))
			}
		}

		return result ?: -1
	}
}