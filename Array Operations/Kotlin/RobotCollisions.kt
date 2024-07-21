import java.util.Stack

/*
* Robot Collisions
There are n 1-indexed robots, each having a position on a line, health, and movement direction.
You are given 0-indexed integer arrays positions, healths, and a string directions (directions[i] is either 'L' for left or 'R' for right).
All integers in positions are unique.
All robots start moving on the line simultaneously at the same speed in their given directions.
If two robots ever share the same position while moving, they will collide.
If two robots collide, the robot with lower health is removed from the line, and the health of the other robot decreases by one.
The surviving robot continues in the same direction it was going.
If both robots have the same health, they are both removed from the line.
Your task is to determine the health of the robots that survive the collisions, in the same order that the robots were given, i.e. final heath of robot 1 (if survived), final health of robot 2 (if survived), and so on.
If there are no survivors, return an empty array.
Return an array containing the health of the remaining robots (in the order they were given in the input), after no further collisions can occur.
Note: The positions may be unsorted.

Example 1:
Input: positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = "RRRRR"
Output: [2,17,9,15,10]
Explanation: No collision occurs in this example, since all robots are moving in the same direction. So, the health of the robots in order from the first robot is returned, [2, 17, 9, 15, 10].

Example 2:
Input: positions = [3,5,2,6], healths = [10,10,15,12], directions = "RLRL"
Output: [14]
Explanation: There are 2 collisions in this example. Firstly, robot 1 and robot 2 will collide, and since both have the same health, they will be removed from the line. Next, robot 3 and robot 4 will collide and since robot 4's health is smaller, it gets removed, and robot 3's health becomes 15 - 1 = 14. Only robot 3 remains, so we return [14].

Example 3:
Input: positions = [1,2,5,6], healths = [10,10,11,11], directions = "RLRL"
Output: []
Explanation: Robot 1 and robot 2 will collide and since both have the same health, they are both removed. Robot 3 and 4 will collide and since both have the same health, they are both removed. So, we return an empty array, [].

Constraints:
1 <= positions.length == healths.length == directions.length == n <= 105
1 <= positions[i], healths[i] <= 109
directions[i] == 'L' or directions[i] == 'R'
All values in positions are distinct
*/

fun main() {
}

data class Robot(var position: Int, var health: Int, var direction: Char, val index: Int)

fun survivedRobotsHealths(positions: IntArray, healths: IntArray, directions: String): List<Int> {
	val robots = positions
		.withIndex()
		.map { (index, position) ->
			Robot(position, healths[index], directions[index], index)
		}.toMutableList()

	robots.sortBy { it.position }

	val rightRobots = mutableListOf<Robot>()

	for (robot in robots) {
		if (robot.direction == 'R') {
			rightRobots.add(robot)
		} else { // Left robot
			while (rightRobots.isNotEmpty() && robot.health > 0) {
				val rightMostRobot = rightRobots.removeLast()
				if (rightMostRobot.health > robot.health) {
					rightMostRobot.health -= 1
					robot.health = 0
					rightRobots.add(rightMostRobot)
				} else if (rightMostRobot.health < robot.health) {
					robot.health -= 1
					rightMostRobot.health = 0
				} else { // If both robots have equal health, both their health values become 0.
					robot.health = 0
					rightMostRobot.health = 0
				}
			}
		}
	}

	robots.sortBy { it.index }
	return robots.filter { it.health > 0 }.map { it.health } // return survived robots
}

class Solution {
	fun survivedRobotsHealths(positions: IntArray, healths: IntArray, directions: String): List<Int> {

		val robots = mutableListOf<Robot>()

		for (i in healths.indices)
			robots.add(Robot(i, positions[i], healths[i], directions[i]))

		robots.sortBy { it.pos }
		val stack = Stack<Robot>()

		for (cur in robots) {
			if (cur.dir == 'R') {
				stack.add(cur)
			} else {
				while (stack.isNotEmpty() && stack.peek().dir == 'R') {
					val prev = stack.pop()
					if (prev.health > cur.health) {
						prev.health -= 1
						cur.health = 0
						stack.add(prev)
						break
					} else if (prev.health < cur.health) {
						cur.health -= 1
					} else {
						cur.health = 0
						break
					}
				}

				if (cur.health > 0)
					stack.add(cur)
			}
		}

		val survivors = stack.sortedBy { it.index }

		return survivors.map { it.health }
	}

	data class Robot(val index: Int, val pos: Int, var health: Int, val dir: Char)
}

class Solution {
	fun survivedRobotsHealths(positions: IntArray, healths: IntArray, directions: String): List<Int> {
		val robots = sortRobots(positions, healths, directions)
		linkLeftRobots(robots)
		val pq = linkRightRobots(robots)

		if (pq.isEmpty())
			return healths.toList()

		while (pq.isNotEmpty()) {
			val pair = pq.poll()

			// skipping irrelevant pairs
			if (pair.left.health == 0 || pair.right.health == 0)
				continue

			val diff = pair.right.health - pair.left.health
			when {
				diff == 0 -> {
					val leftPrev = pair.left.remove()
					val rightPrev = pair.right.remove()
					if (leftPrev != null && rightPrev != null) {
						pq.offer(CollisionPair(rightPrev, leftPrev))
					}
				}

				diff > 0 -> {
					pair.right.health--
					pair.left.remove()?.let { leftPrev ->
						pq.offer(CollisionPair(pair.right, leftPrev))
					}
				}

				else -> {
					pair.left.health--
					pair.right.remove()?.let { rightPrev ->
						pq.offer(CollisionPair(rightPrev, pair.left))
					}
				}
			}
		}

		return robots
			.filter { it.health > 0 }
			.sortedBy { it.inputIndex }
			.map { it.health }
	}

	private fun sortRobots(positions: IntArray, healths: IntArray, directions: String): Array<Robot> {
		val robots = Array(positions.size) {
			val direction = if (directions[it] == 'L') LEFT else RIGHT
			Robot(it, positions[it], direction, healths[it])
		}

		return robots.sorted().toTypedArray()
	}

	private fun linkLeftRobots(robots: Array<Robot>) {
		var lastLeft: Robot? = null
		for (i in robots.size - 1 downTo 0) {
			val r = robots[i]
			if (r.direction == LEFT) {
				lastLeft?.link(r)
				lastLeft = r
			}
		}
	}

	private fun linkRightRobots(robots: Array<Robot>): PriorityQueue<CollisionPair> {
		val pq = PriorityQueue<CollisionPair>()
		var lastRight: Robot? = null
		var pqLastRight: Robot? = null

		for (r in robots) {
			if (r.direction == RIGHT) {
				lastRight?.link(r)
				lastRight = r
				pqLastRight = r
			} else {
				if (pqLastRight != null) {
					pq.offer(CollisionPair(pqLastRight, r))
					pqLastRight = null
				}
			}
		}

		return pq
	}

	class CollisionPair(val right: Robot, val left: Robot) : Comparable<CollisionPair> {
		private val distance = left.position - right.position
		override fun compareTo(other: CollisionPair): Int = distance - other.distance
	}

	class Robot(val inputIndex: Int, val position: Int, val direction: Int, var health: Int) : Comparable<Robot> {

		var prev: Robot? = null
		var next: Robot? = null

		override fun compareTo(other: Robot): Int = position - other.position

		fun link(next: Robot) {
			this.next = next
			next.prev = this
		}

		fun remove(): Robot? {
			health = 0
			prev?.next = next
			next?.prev = prev
			return prev
		}

		override fun toString(): String {
			return when (direction) {
				LEFT -> "<- [$position]"
				else -> "[$position] ->"
			}

		}
	}

	companion object {
		const val LEFT = 0
		const val RIGHT = 1
	}
}

fun survivedRobotsHealths(positions: IntArray, healths: IntArray, directions: String): List<Int> {
	return with(Stack<Int>()) {
		val indices = positions.indices.sortedBy { positions[it] }

		for (i in indices) {
			if (directions[i] > 'L') {
				push(i)
			} else {
				while (size > 0 && directions[peek()] > 'L') {
					if (healths[peek()] == healths[i]) {
						pop(); healths[i] = 0; break
					} else if (healths[peek()] < healths[i]) {
						pop(); healths[i]--
					} else {
						healths[peek()]--; healths[i] = 0; break
					}
				}

				if (healths[i] > 0)
					push(i)
			}
		}

		sorted().map { healths[it] }
	}
}

fun survivedRobotsHealths(p: IntArray, h: IntArray, d: String): List<Int> {
	return with(Stack<Int>()) {
		for (i in h.indices.sortedBy { p[it] }) {
			if (d[i] > 'L') {
				push(i)
			} else {
				while (size > 0 && h[i] > 0) if (h[peek()] < h[i]) {
					h[pop()] = 0; h[i]--
				} else {
					if (h[peek()] > h[i]) h[peek()]-- else h[pop()] = 0; h[i] = 0
				}
			}
		}
		h.filter { it > 0 }
	}
}