/*
* Word Search - https://leetcode.com/problems/word-search/
Given an m x n grid of characters board and a string word, return true if word exists in the grid.
The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false

Constraints:
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15

board and word consists of only lowercase and uppercase English letters.

Follow up: Could you use search pruning to make your solution faster with a larger board?
*/

fun main() {
	print(
		exist(
			board = arrayOf(
				charArrayOf('A', 'B', 'C', 'E'),
				charArrayOf('S', 'F', 'C', 'S'),
				charArrayOf('A', 'D', 'E', 'E')
			),
			word = "SEE"
		)
	)
}

fun solve(board: Array<CharArray>, r: Int, c: Int, word: String, count: Int): Boolean {
	if (word.length == count) return true

	if (r >= board.size || r < 0 || c < 0 || c >= board.first().size || board[r][c] != word[count])
		return false

	board[r][c] = '@'
	val status = solve(board, r - 1, c, word, count + 1)
			|| solve(board, r + 1, c, word, count + 1)
			|| solve(board, r, c - 1, word, count + 1)
			|| solve(board, r, c + 1, word, count + 1)

	board[r][c] = word[count]
	return status
}

fun exist(board: Array<CharArray>, word: String): Boolean {
	for (i in board.indices) {
		for (j in board.first().indices)
			if (solve(board, i, j, word, 0))
				return true
	}
	return false
}

class Solution {
	fun exist(board: Array<CharArray>, word: String): Boolean {
		val char = word[0]
		for (i in 0..board.size - 1) {
			for (j in 0..board[0].size - 1) {
				if (board[i][j] == char) {
					board[i][j] = '0'
					val result = helper(board, word, 1, i, j)
					if (result == true) return true
					board[i][j] = char
				}
			}
		}
		return false
	}

	private fun helper(
		board: Array<CharArray>,
		word: String,
		index: Int,
		currentRow: Int,
		currentColumn: Int
	): Boolean {
		if (index == word.length) return true

		// var result = false
		if (currentRow != 0 && board[currentRow - 1][currentColumn] == word[index]) {
			val char = board[currentRow - 1][currentColumn]
			board[currentRow - 1][currentColumn] = '0'
			val result = helper(board, word, index + 1, currentRow - 1, currentColumn)
			if (result == true) return true
			board[currentRow - 1][currentColumn] = char
		}

		if (currentRow != board.size - 1 && board[currentRow + 1][currentColumn] == word[index]) {
			val char = board[currentRow + 1][currentColumn]
			board[currentRow + 1][currentColumn] = '0'
			val result = helper(board, word, index + 1, currentRow + 1, currentColumn)
			if (result == true) return true
			board[currentRow + 1][currentColumn] = char
		}

		if (currentColumn != 0 && board[currentRow][currentColumn - 1] == word[index]) {
			val char = board[currentRow][currentColumn - 1]
			board[currentRow][currentColumn - 1] = '0'
			val result = helper(board, word, index + 1, currentRow, currentColumn - 1)
			if (result == true) return true
			board[currentRow][currentColumn - 1] = char
		}

		if (currentColumn != board[0].size - 1 && board[currentRow][currentColumn + 1] == word[index]) {
			val char = board[currentRow][currentColumn + 1]
			board[currentRow][currentColumn + 1] = '0'
			val result = helper(board, word, index + 1, currentRow, currentColumn + 1)
			if (result == true) return true
			board[currentRow][currentColumn + 1] = char
		}

		return false

	}
}

class Solution {
	fun exist(board: Array<CharArray>, word: String): Boolean {
		fun search(r: Int, c: Int, from: Int): Boolean {
			return if (from == word.length) true
			else if (r < 0 || r == board.size || c < 0 || c == board[r].size || word[from] != board[r][c]) false
			else {
				val cc = board[r][c]
				board[r][c] = '-'
				val result = search(r - 1, c, from + 1)
						|| search(r, c + 1, from + 1)
						|| search(r + 1, c, from + 1)
						|| search(r, c - 1, from + 1)

				board[r][c] = cc
				result
			}
		}

		for (row in board.indices) {
			for (col in board[row].indices) {
				if (search(row, col, 0))
					return true
			}
		}

		return false
	}
}

class Solution {
	fun exist(board: Array<CharArray>, word: String): Boolean {
		for (row in board.indices) {
			for (col in board[0].indices) {
				if (board[row][col] == word[0] && dfs(board, row, col, 0, word)) {
					return true
				}
			}
		}
		return false //word not found in the grid
	}

	private fun dfs(
		board: Array<CharArray>,
		row: Int,
		col: Int,
		count: Int,
		word: String,
	): Boolean {
		if (count == word.length)
			return true

		if (
			row !in board.indices ||
			col !in board[0].indices ||
			board[row][col] != word[count]
		)
			return false

		val temp = board[row][col]
		board[row][col] = ' '

		val found = dfs(board, row + 1, col, count + 1, word)
				|| dfs(board, row - 1, col, count + 1, word)
				|| dfs(board, row, col + 1, count + 1, word)
				|| dfs(board, row, col - 1, count + 1, word)

		board[row][col] = temp

		return found
	}
}

class Solution {
	fun exist(board: Array<CharArray>, word: String): Boolean {
		// val dirs = intArrayOf(1, 0, -1, 0, 1)
		val rowSize = board.size
		val colSize = board[0].size

		fun dfs(x: Int, y: Int, wordIndex: Int): Boolean {
			if (wordIndex == word.length)
				return true

			val isOutOfBounds = x < 0 || x >= rowSize || y < 0 || y >= colSize
			if (isOutOfBounds)
				return false

			val hasVisited = board[x][y] == '#'
			if (hasVisited)
				return false

			if (board[x][y] != word[wordIndex])
				return false

			val visitedValue = board[x][y]
			board[x][y] = '#'
			// for(i in 0..3) {
			//     val nextX = x + dirs[i]
			//     val nextY = y + dirs[i+1]
			//     if(dfs(nextX, nextY, wordIndex + 1)) {
			//         return true
			//     }
			// }
			if (dfs(x + 1, y, wordIndex + 1)) return true
			if (dfs(x - 1, y, wordIndex + 1)) return true
			if (dfs(x, y - 1, wordIndex + 1)) return true
			if (dfs(x, y + 1, wordIndex + 1)) return true
			board[x][y] = visitedValue

			return false
		}

		for (i in 0..<rowSize) {
			for (j in 0..<colSize) {
				if (dfs(i, j, 0)) {
					return true
				}
			}
		}
		return false
	}
}

class Solution {
	fun exist(board: Array<CharArray>, word: String): Boolean {
		for (i in board.indices) {
			for (j in board[0].indices) {
				if (board[i][j] == word[0]) {
					if (exist(board, word, 0, i, j)) {
						return true
					}
				}
			}
		}
		return false
	}

	val dircs = listOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

	fun exist(board: Array<CharArray>, word: String, index: Int, i: Int, j: Int): Boolean {
		if (board[i][j] == word[index]) {
			board[i][j] = board[i][j] + 100

			if (index == word.length - 1)
				return true

			for (dirc in dircs) {
				val newX = i + dirc.first
				val newY = j + dirc.second
				if (newX >= 0 && newX < board.size && newY >= 0 && newY < board[0].size) {
					if (exist(board, word, index + 1, newX, newY))
						return true
				}
			}

			board[i][j] = board[i][j] - 100

		}
		return false
	}
}

class Solution {
	fun exist(board: Array<CharArray>, word: String): Boolean {
		val usage = List(board.size) { MutableList(board[0].size) { false } }
		for (i in board.indices) {
			for (j in 0..<board[0].size) {
				if (board[i][j] != word[0]) continue
				usage[i][j] = true
				if (findWord(usage, board, StringBuilder(board[i][j].toString()), word, i, j)) return true
				usage[i][j] = false
			}
		}
		return false
	}

	private fun findWord(
		used: List<MutableList<Boolean>>, board: Array<CharArray>, currentWord: StringBuilder, word: String,
		x: Int, y: Int
	): Boolean {

		if (currentWord.length > word.length) return false
		if (currentWord.toString() == word) return true

		//println("currentWord: $currentWord")

		if (canMove(used, x, y)) {
			for (i in 0..3) {
				val currentX = x + xMove[i]
				val currentY = y + yMove[i]
				if (currentX < 0 || currentX >= used.size) continue
				if (currentY < 0 || currentY >= used[0].size) continue
				if (used[currentX][currentY] == false) {
					used[currentX][currentY] = true
					currentWord.append(board[currentX][currentY])
					if (findWord(used, board, currentWord, word, currentX, currentY)) {
						return true
					} else {
						used[currentX][currentY] = false
						currentWord.deleteCharAt(currentWord.length - 1)
					}
				}
			}
			return false
		} else {
			return false
		}
	}

	private val xMove = listOf(1, 0, -1, 0)
	private val yMove = listOf(0, 1, 0, -1)

	private fun canMove(used: List<MutableList<Boolean>>, x: Int, y: Int): Boolean {
		if (x > 0 && used[x - 1][y] == false) return true
		if (y > 0 && used[x][y - 1] == false) return true
		if (x < used.size - 1 && used[x + 1][y] == false) return true
		if (y < used[0].size - 1 && used[x][y + 1] == false) return true
		return false
	}
}

class Solution {
	fun exist(board: Array<CharArray>, word: String): Boolean {
		val m: Int = board.size
		val n: Int = board[0].size

		// Compare character counts of word to the character counts of board - if a character appears fewer times
		// in board than in word, word cannot exist in board.
		val wordCharCounts = countFrequencies(word)
		val boardCharCounts = countFrequencies(board.map({ row -> row.joinToString("") }).joinToString(""))
		for (entry in wordCharCounts.iterator()) {
			val count: Int? = boardCharCounts.get(entry.key)
			if (count == null || count < entry.value) {
				return false
			}
		}

		// Identify all candidate start positions in board to begin searching for word (i.e. positions in
		// board which are equal to the first character of word).
		val startPositions: MutableList<Pair<Int, Int>> = mutableListOf()
		for (row in 0..<m) {
			for (col in 0..<n) {
				if (board[row][col] == word[0]) {
					startPositions.add(Pair(row, col))
				}
			}
		}

		// From each candidate start position, perform DFS to find word
		var foundWord: Boolean = false
		for (candidate in startPositions) {
			if (dfs(board, word, candidate, emptySet())) {
				foundWord = true
				break
			}
		}

		return foundWord
	}

	// Helper function which returns a Map representing the counts of each character in the given string
	fun countFrequencies(str: String): Map<Char, Int> {
		val characterCounts: MutableMap<Char, Int> = mutableMapOf()
		for (ch in str) {
			val count: Int? = characterCounts[ch]
			characterCounts[ch] = if (count != null) count + 1 else 1
		}

		return characterCounts
	}

	// Function which performs DFS to search for word in board. Returns true if word exists and false otherwise.
	fun dfs(board: Array<CharArray>, word: String, start: Pair<Int, Int>, visited: Set<Pair<Int, Int>>): Boolean {
		val m: Int = board.size
		val n: Int = board[0].size

		// Returns list of cells which you can move to from the current position
		fun getPossibleMoves(position: Pair<Int, Int>): List<Pair<Int, Int>> {
			val x: Int = position.first
			val y: Int = position.second

			val moves: MutableList<Pair<Int, Int>> = mutableListOf()

			if (x - 1 >= 0) moves.add(Pair(x - 1, y))
			if (x + 1 < m) moves.add(Pair(x + 1, y))
			if (y + 1 < n) moves.add(Pair(x, y + 1))
			if (y - 1 >= 0) moves.add(Pair(x, y - 1))

			return moves
		}

		val row: Int = start.first
		val col: Int = start.second

		if (board[row][col] == word[0] && word.length == 1) return true

		if (board[row][col] != word[0]) return false

		// Perform DFS from each cell adjacent to start to find the remainder of word
		val possibleMoves: List<Pair<Int, Int>> = getPossibleMoves(start)
		for (move in possibleMoves) {
			if (!visited.contains(move)) {
				if (dfs(board, word.drop(1), move, visited.union(setOf(start))))
					return true
			}
		}

		return false
	}
}