import java.util.LinkedList
import java.util.Arrays

/*
* Reveal Cards In Increasing Order
You are given an integer array deck. There is a deck of cards where every card has a unique integer. The integer on the ith card is deck[i].
You can order the deck in any order you want. Initially, all the cards start face down (unrevealed) in one deck.
You will do the following steps repeatedly until all cards are revealed:

1. Take the top card of the deck, reveal it, and take it out of the deck.
2. If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.
3. If there are still unrevealed cards, go back to step 1. Otherwise, stop.
Return an ordering of the deck that would reveal the cards in increasing order.

Note that the first entry in the answer is considered to be the top of the deck.

Example 1:
Input: deck = [17,13,11,2,3,5,7]
Output: [2,13,3,11,5,17,7]
Explanation:
We get the deck in the order [17,13,11,2,3,5,7] (this order does not matter), and reorder it.
After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
We reveal 13, and move 17 to the bottom.  The deck is now [17].
We reveal 17.
Since all the cards revealed are in increasing order, the answer is correct.

Example 2:
Input: deck = [1,1000]
Output: [1,1000]

Constraints:
1 <= deck.length <= 1000
1 <= deck[i] <= 106
All the values of deck are unique.
*/

fun main() {
	val result = deckRevealedIncreasing(intArrayOf(17, 13, 11, 2, 3, 5, 7))
	result.forEach { print("$it ") }
}

fun deckRevealedIncreasing(deck: IntArray): IntArray {
	deck.sortDescending() // 17, 13, 11, 7, 5, 3, 2
	val result = mutableListOf<Int>()

	for (card in deck) {
		// println(result)
		if (result.isNotEmpty())
			result.add(0, result.removeLast())
		result.add(0, card)
	}

	return result.toIntArray()
}

class Solution {
	fun deckRevealedIncreasing(deck: IntArray): IntArray {
		Arrays.sort(deck)
		val result = LinkedList<Int>()
		for (i in deck.indices.reversed()) {
			if (!result.isEmpty()) {
				result.addFirst(result.removeLast())
			}
			result.addFirst(deck[i])
		}

		return result.stream().mapToInt { obj -> obj.toInt() }.toArray()
	}
}

class Solution {
	fun deckRevealedIncreasing(deck: IntArray): IntArray {
		when (deck.size) {
			0, 1 -> return deck
			2 -> return deck.sorted().toIntArray()
		}

		val absolute = deck.sorted()
		val finalDeck = Array<Int?>(deck.size) { null }

		var i = 0
		fun incI() {
			i = (i + 1) % deck.size
		}

		fun findNext() {
			while (finalDeck[i] != null)
				incI()
		}

		for (j in 0..<deck.size - 2) {
			finalDeck[i] = absolute[j]
			// find next slot
			findNext()

			// skip it
			incI()
			findNext()
		}

		if (deck.size > 2) {
			finalDeck[i] = absolute[deck.size - 2]
			findNext()
		}
		finalDeck[i] = absolute[deck.size - 1]

		return finalDeck.filterNotNull().toIntArray()
	}
}