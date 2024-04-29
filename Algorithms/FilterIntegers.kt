import org.junit.Test
import org.testng.AssertJUnit.assertEquals
import kotlin.random.Random

/*
* Description
In this kata you will create a function that takes a list of non-negative integers and strings and returns a new list with the strings filtered out.

* Example
filterList(ListOf(1, 2, "a", "b")) == [1, 2]
filterList(ListOf(1, "a", "b", 0, 15)) == [1, 0, 15]
filterList(ListOf(1, 2, "a", "b", "aasf", "1", "123", 123)) == [1, 2, 123]
*/

fun filterList(l: List<Any>): List<Int> {
	return l.filterIsInstance<Int>()
}

fun filterList(l: List<Any>): List<Int> {
	val res: MutableList<Int> = mutableListOf()

	for (m in l) {
		if (m !is Int)
			continue
		res.add(m)
	}

	return res.toList()
}

fun filterList(l: List<Any>): List<Int> {
	val ans = mutableListOf<Int>()
	for (i in l) {
		when (i) {
			is Int -> ans.add(i)
		}
	}
	return ans
}

fun filterList(l: List<Any>): List<Int> {
	val getInt = ArrayList<Int>()
	val getNumbers = l.filterIsInstance<Int>()
	getNumbers.forEach { getInt.add(it.toString().toInt()) }
	return getInt
}

class TestExample {
	@Test
	fun basicTests() {
		assertEquals(listOf(1, 2), filterList(listOf(1, 2, 'a', 'b')), "For input [1, 2, 'a', 'b']")
		assertEquals(listOf(1, 0, 15), filterList(listOf(1, 'a', 'b', 0, 15)), "For input [1, 'a', 'b', 0, 15]")
		assertEquals(
			listOf(1, 2, 123),
			filterList(listOf(1, 2, "aasf", '1', "123", 123)),
			"For input [1, 2, 'aasf', '1', '123', 123]"
		)
		assertEquals(listOf(), filterList(listOf('a', 'b', '1')), "For input ['a', 'b', '1']")
		assertEquals(listOf(1, 2), filterList(listOf(1, 2, 'a', 'b')), "For input [1, 2, 'a', 'b']")
	}

	@Test
	fun randomTest() {
		for (i in 1..20) {
			val input: MutableList<Any> = mutableListOf()
			val expected: MutableList<Int> = mutableListOf()
			for (j in 0 until Random.nextInt(20)) {
				if (Random.nextInt(2) == 0) {
					val n: Int = Random.nextInt(1000)
					input.add(n)
					expected.add(n)
				} else {
					if (Random.nextInt(2) == 0) {
						input.add(Random.nextInt(1000).toString())
					} else {
						for (k in 0 until Random.nextInt(6)) {
							input.add(Random.nextInt(48, 122).toChar())
						}
					}
				}
				assertEquals(expected, filterList(input), "For input $input")
			}
		}
	}
}