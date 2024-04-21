import java.util.*

/*
* Find if Path Exists in Graph
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
You want to determine if there is a valid path that exists from vertex source to vertex destination.
Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

Example 1:
Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2

Example 2:
Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.

Constraints:
1 <= n <= 2 * 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= source, destination <= n - 1
There are no duplicate edges.
There are no self edges.
*/

fun main() {
	print(
		validPath(
			n = 3,
			edges = arrayOf(
				intArrayOf(0, 1),
				intArrayOf(1, 2),
				intArrayOf(2, 0),
			),
			source = 0,
			destination = 2
		)
	)
}

// DFS
fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
	val graph: MutableMap<Int, MutableList<Int>> = mutableMapOf()

	for (edge in edges) {
		val u = edge.first()
		val v = edge[1]

		// If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless null.
		graph.computeIfAbsent(u) { ArrayList() }.add(v)
		graph.computeIfAbsent(v) { ArrayList() }.add(u)
	}

	val visited: HashSet<Int> = hashSetOf()
	return dfs(source, destination, graph, visited)
}

fun dfs(node: Int, destination: Int, graph: Map<Int, List<Int>>, visited: MutableSet<Int>): Boolean {
	if (node == destination) {
		return true
	}

	visited.add(node) // Keeping track of visited node to avoid revisiting and unnecessary loop iterations

	for (neighbour in graph[node]!!) {
		if (!visited.contains(neighbour)) {
			if (dfs(neighbour, destination, graph, visited))
				return true
		}
	}

	return false
}

// BFS
class Solution {
	fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
		val graph: MutableMap<Int, MutableList<Int>> = HashMap()
		for (edge in edges) {
			val u = edge[0]
			val v = edge[1]
			graph.computeIfAbsent(u) { k: Int? -> ArrayList() }.add(v)
			graph.computeIfAbsent(v) { k: Int? -> ArrayList() }.add(u)
		}

		val queue: Queue<Int> = LinkedList()
		val visited: MutableSet<Int> = HashSet()

		queue.offer(source)
		visited.add(source)

		while (!queue.isEmpty()) {
			val node = queue.poll()
			if (node == destination)
				return true

			val neighbors: List<Int> = graph.getOrDefault(node, ArrayList())
			for (neighbor in neighbors) {
				if (!visited.contains(neighbor)) {
					visited.add(neighbor)
					queue.offer(neighbor)
				}
			}
		}

		return false
	}
}

// Union Find
class Solution {
	private lateinit var parent: IntArray
	private lateinit var rank: IntArray

	fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
		parent = IntArray(n)
		rank = IntArray(n)


		// Initialize parent pointers and ranks
		for (i in 0 until n) {
			parent[i] = i
			rank[i] = 1
		}

		// Union-find operations based on given edges
		for (edge in edges)
			union(edge[0], edge[1])

		// Check if source and destination belong to the same set
		return find(source) == find(destination)
	}

	private fun find(x: Int): Int {
		if (parent[x] != x)
			parent[x] = find(parent[x]) // Path compression
		return parent[x]
	}

	private fun union(x: Int, y: Int) {
		val rootX = find(x)
		val rootY = find(y)

		if (rootX != rootY) {
			if (rank[rootX] > rank[rootY]) {
				parent[rootY] = rootX
			} else if (rank[rootX] < rank[rootY]) {
				parent[rootX] = rootY
			} else {
				parent[rootY] = rootX
				rank[rootX]++
			}
		}
	}
}

class Solution {
	fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
		val graph: MutableMap<Int, MutableList<Int>> = HashMap()
		for (edge in edges) {
			val u = edge[0]
			val v = edge[1]
			graph.computeIfAbsent(
				u
			) { k: Int? -> ArrayList() }.add(v)
			graph.computeIfAbsent(
				v
			) { k: Int? -> ArrayList() }.add(u)
		}

		val distances = IntArray(n)
		Arrays.fill(distances, Int.MAX_VALUE)
		distances[source] = 0

		val priorityQueue = PriorityQueue { a: IntArray, b: IntArray -> a[0] - b[0] }
		priorityQueue.offer(intArrayOf(0, source))

		while (priorityQueue.isNotEmpty()) {
			val current = priorityQueue.poll()
			val currentDistance = current[0]
			val currentNode = current[1]

			if (currentNode == destination)
				return true

			if (currentDistance > distances[currentNode])
				continue

			for (neighbor in graph.getOrDefault(currentNode, ArrayList<Int>())) {
				val distance = currentDistance + 1 // Assuming unweighted graph
				if (distance < distances[neighbor]) {
					distances[neighbor] = distance
					priorityQueue.offer(intArrayOf(distance, neighbor))
				}
			}
		}

		return false
	}
}

class Solution {
	fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
		if (source == destination) return true
		val unionFind = UnionFind(n)
		edges.forEach {
			unionFind.union(it[0], it[1])
		}
		return unionFind.find(source) == unionFind.find(destination)
	}

	inner class UnionFind(n: Int) {
		private val root = IntArray(n) { i -> i }

		fun union(x: Int, y: Int) {
			root[find(x)] = find(y)
		}

		fun find(x: Int): Int {
			if (root[x] != x)
				root[x] = find(root[x])
			return root[x]
		}
	}
}

class Solution {
	fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
		if (source == destination)
			return true

		if (edges.isEmpty())
			return false

		val adjacencies: MutableMap<Int, MutableList<Int>> = mutableMapOf()
		for (edge in edges) {
			val (u, v) = edge
			adjacencies.getOrPut(u) { mutableListOf() }.add(v)
			adjacencies.getOrPut(v) { mutableListOf() }.add(u)
		}

		val visited: MutableSet<Int> = mutableSetOf()

		val queue: Queue<Int> = LinkedList()
		queue.add(source)

		while (queue.peek() != null) {
			val parent = queue.poll()
			if (parent == destination)
				return true

			if (adjacencies[parent] != null)
				for (child in adjacencies[parent]!!)
					if (child !in visited) {
						visited.add(child)
						queue.offer(child)
					}
		}

		return false
	}
}

class Solution {
	var graph: HashMap<Int, ArrayList<Int>> = HashMap()
	var seen = mutableSetOf<Int>()
	var check = 0
	var result = false
	fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
		if (edges.isEmpty())
			return true

		check = destination
		for (edge in edges) {
			edge.toString()
			if (!graph.containsKey(edge[0]))
				graph[edge[0]] = arrayListOf()

			if (!graph.containsKey(edge[1]))
				graph[edge[1]] = arrayListOf()

			graph[edge[0]]?.add(edge[1])
			graph[edge[1]]?.add(edge[0])
		}

		dfs(source)
		return result
	}

	fun dfs(node: Int) {
		for (neighbor in graph[node]!!) {
			if (neighbor == check) {
				result = true
				break
			}

			if (!seen.contains(neighbor)) {
				seen.add(neighbor)
				dfs(neighbor)
			}
		}
	}
}

class Solution {
	var graph: HashMap<Int, ArrayList<Int>> = HashMap()
	var seen = mutableSetOf<Int>()
	var check = 0
	var result = false

	fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
		if (edges.isEmpty())
			return true

		check = destination
		for (edge in edges) {
			edge.toString()

			if (!graph.containsKey(edge[0]))
				graph[edge[0]] = arrayListOf()

			if (!graph.containsKey(edge[1]))
				graph[edge[1]] = arrayListOf()

			graph[edge[0]]?.add(edge[1])
			graph[edge[1]]?.add(edge[0])
		}

		if (!graph.containsKey(source))
			return false
		if (!graph.containsKey(destination))
			return false

		dfs(source)
		return result
	}

	fun dfs(node: Int) {
		for (neighbor in graph[node]!!) {
			if (neighbor == check) {
				result = true
				break
			}

			if (!seen.contains(neighbor)) {
				seen.add(neighbor)
				dfs(neighbor)
			}
		}
	}
}

import java.util.ArrayDeque
class Solution {

	//Using BFS
	fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
		val graph= Array(n) { mutableSetOf<Int>() }
		for ((src, dest) in edges) {
			graph[src].add(dest)
			graph[dest].add(src)
		}

		val queue = ArrayDeque<Int>()
		val visited = BooleanArray(n)
		queue.offer(source)
		visited[source] = true

		while (queue.isNotEmpty()) {
			val front = queue.poll()
			if (front == destination) return true
			graph[front].forEach {
				if (!visited[it]) {
					visited[it] = true
					queue.offer(it)
				}
			}
		}
		return false
	}
}

// import java.util.*
// class Solution {

//     //Using DFS
//     fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
//         val graph = Array(n) { mutableSetOf<Int>() }
//         edges.forEach {
//             graph[it.first()].add(it.last())
//             graph[it.last()].add(it.first())
//         }

//         val stack = Stack<Int>()
//         val visited = BooleanArray(n)

//         stack.push(source)
//         while (stack.isNotEmpty()) {
//             val front = stack.pop()
//             visited[front] = true

//             if (front == destination) return true
//             graph[front].forEach {
//                 if (!visited[it]) stack.push(it)
//             }
//         }
//         return false
//     }
// }

//====================TLE==============
//size: 20000
//src: 0, dest: 19999
//=====================================

// import java.util.ArrayDeque
// class Solution {

//     //Using BFS
//     fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
//         val graph= Array(n) { mutableSetOf<Int>() }
//         for ((src, dest) in edges) {
//             graph[src].add(dest)
//             graph[dest].add(src)
//         }

//         val queue = ArrayDeque<Int>()
//         val visited = BooleanArray(n)
//         queue.offer(source)

//         while (queue.isNotEmpty()) {
//             val front = queue.poll()
//             visited[front] = true
//             if (front == destination) return true
//             graph[front].forEach {
//                 if (!visited[it]) queue.offer(it)
//             }
//         }
//         return false
//     }
// }