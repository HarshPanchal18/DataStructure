/*
* Number of Students Unable to Eat Lunch
The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.
The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:
If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the i-th sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the j-th student in the initial queue (j = 0 is the front of the queue).
Return the number of students that are unable to eat.

Example 1:
Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
Output: 0
Explanation:
- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
Hence all students are able to eat.

Example 2:
Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
Output: 3

Constraints:
1 <= students.length, sandwiches.length <= 100
students.length == sandwiches.length
sandwiches[i] is 0 or 1.
students[i] is 0 or 1.
*/

fun main() {
    print(countStudents(intArrayOf(1,1,0,0),intArrayOf(0,1,0,1)))
}

fun countStudents(students: IntArray, sandwiches: IntArray): Int {
	val students: MutableList<Int> = students.toMutableList()
	val sandwiches: MutableList<Int> = sandwiches.toMutableList()

	while (students.isNotEmpty() && students.contains(sandwiches.first())) {
		if (students.first() == sandwiches.first()) {
			students.removeAt(0)
			sandwiches.removeAt(0)
		} else {
			students.add(students.first())
			students.removeAt(0)
		}
	}

	return students.size
}


fun countStudents(students: IntArray, sandwiches: IntArray): Int {
	val count = IntArray(2)

	for (s in students)
		count[s]++

	for ((index, sandwich) in sandwiches.withIndex())
		if (--count[sandwich] < 0)
			return students.size - index

	return 0
}

class Solution {
	fun countStudents(students: IntArray, sandwiches: IntArray): Int {
		var students1 = 0
		var students0 = 0

		for (st in students) {
			if (st == 1) students1++
			else students0++
		}

		for (sandwich in sandwiches) {
			if (sandwich == 1) students1-- else students0--
			if (students1 < 0) return students0
			if (students0 < 0) return students1
		}

		return 0
	}
}

class Solution {
	fun countStudents(students: IntArray, sandwiches: IntArray): Int {
		val studentQueue = ArrayDeque(students.toList())
		val sandwichQueue = ArrayDeque(sandwiches.toList())

		var numStudentsPassed = 0
		while (studentQueue.isNotEmpty() && numStudentsPassed != studentQueue.size) {
			when (studentQueue.first()) {
				0 -> {
					when (sandwichQueue.first()) {
						0 -> {
							studentQueue.removeFirst()
							sandwichQueue.removeFirst()
							numStudentsPassed = 0
						}

						1 -> {
							studentQueue.add(studentQueue.removeFirst())
							numStudentsPassed++
						}
					}
				}

				1 -> {
					when (sandwichQueue.first()) {
						0 -> {
							studentQueue.add(studentQueue.removeFirst())
							numStudentsPassed++
						}

						1 -> {
							studentQueue.removeFirst()
							sandwichQueue.removeFirst()
							numStudentsPassed = 0
						}
					}
				}
			}
		}

		return studentQueue.size
	}
}

class Solution {
	fun countStudents(students: IntArray, sandwiches: IntArray): Int {

		val stuQ = ArrayDeque<Int>()
		for (s in students)
			stuQ.add(s)

		val sanQ = ArrayDeque<Int>()
		for (s in sandwiches)
			sanQ.add(s)

		var notServed = 0
		while (notServed < sanQ.size) {
			if (stuQ.first() != sanQ.first()) {
				stuQ.add(stuQ.removeFirst()) // Appending at the last
				notServed++
			} else { // else serve the sandwich to a student
				stuQ.removeFirst()
				sanQ.removeFirst()
				notServed = 0
			}
		}

		return stuQ.size
	}
}

class Solution {
	fun countStudents(students: IntArray, sandwiches: IntArray): Int {
		var deficit = 0
		for (i in 0..<students.size) {
			if (students[i] == 0)
				deficit++
			if (sandwiches[i] == 0)
				deficit--
		}
		var sandwich = if (deficit < 0) 0 else 1
		deficit = Math.abs(deficit)
		var index = sandwiches.size
		while (deficit > 0) {
			index--
			if (sandwich == sandwiches[index])
				deficit--
		}
		return sandwiches.size - index
	}
}

class Solution {
	fun countStudents(students: IntArray, sandwiches: IntArray): Int {
		//[1,1,1,], sandwiches = [0,1,1]
		val map = mutableMapOf<Int, Int>()
		students.forEach { map[it] = map.getOrDefault(it, 0) + 1 }

		for (n in sandwiches) {
			if (!map.contains(n))
				return map[1 - n]!!

			map[n] = map.getOrDefault(n, 0) - 1
			if (map[n] == 0)
				map.remove(n)
		}
		return 0
	}
}

class Solution {
	fun countStudents(students: IntArray, sandwiches: IntArray): Int {
		var num1 = 0
		var num0 = 0

		students.forEach {
			if (it == 0) num0 += 1
			else num1 += 1
		}

		for (i in sandwiches.indices) {
			if (sandwiches[i] == 1) {
				if (num1 > 0) num1 -= 1
				else return sandwiches.size - i
			} else {
				if (num0 > 0) num0 -= 1
				else return sandwiches.size - i
			}
		}

		return 0
	}
}

class Solution {
	fun countStudents(students: IntArray, sandwiches: IntArray): Int {
		val studentDeque = ArrayDeque<Int>()
		val sandwichesDeque = ArrayDeque<Int>()

		for (s in students)
			studentDeque.addLast(s)

		for (s in sandwiches)
			sandwichesDeque.addLast(s)

		while (sandwichesDeque.isNotEmpty() && studentDeque.contains(sandwichesDeque.first())) {
			val currentStudent = studentDeque.removeFirst()
			if (sandwichesDeque.first() == currentStudent) sandwichesDeque.removeFirstOrNull()
			else studentDeque.addLast(currentStudent)
		}

		return studentDeque.size
	}
}

class Solution {
	fun countStudents(students: IntArray, sandwiches: IntArray): Int {
		val studentsQueue = ArrayDeque<Int>()
		val sandwichesQueue = ArrayDeque<Int>()
		students.forEach { studentsQueue.addLast(it) }
		sandwiches.forEach { sandwichesQueue.addLast(it) }

		var notEaten = 0
		while (notEaten < sandwichesQueue.size) {
			if (studentsQueue.first() != sandwichesQueue.first()) {
				studentsQueue.addLast(studentsQueue.removeFirst())
				notEaten++
			} else {
				studentsQueue.removeFirst()
				sandwichesQueue.removeFirst()
				notEaten = 0
			}
		}
		return sandwichesQueue.size
	}
}

class Solution {
	fun countStudents(students: IntArray, sandwiches: IntArray): Int {
		val studentQueue = ArrayDeque(students.toCollection(ArrayList()))
		val sandwichesStack = ArrayDeque(sandwiches.toCollection(ArrayList()))
		var counter = 0

		while (studentQueue.isNotEmpty()) {
			val firstStudent = studentQueue.first()
			val topSandwich = sandwichesStack.first()

			if (firstStudent == topSandwich) {
				studentQueue.removeFirst()
				sandwichesStack.removeFirst()
				counter = 0
			} else {
				studentQueue.removeFirst()
				studentQueue.addLast(firstStudent)
				counter++
			}

			if (counter > studentQueue.count())
				return studentQueue.count()

		}
		return 0
	}
}