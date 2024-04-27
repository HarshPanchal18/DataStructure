/*
* Sum of Distances in Tree - https://leetcode.com/problems/sum-of-distances-in-tree/
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.

Example 1:
Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.

Example 2:
Input: n = 1, edges = []
Output: [0]

Example 3:
Input: n = 2, edges = [[1,0]]
Output: [1,1]

Constraints:
1 <= n <= 3 * 104
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
The given input represents a valid tree.
*/

fun main() {
	val result = sumOfDistancesInTree(
		6,
		arrayOf(
			intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(2, 3), intArrayOf(2, 4), intArrayOf(2, 5)
		)
	)

	result.forEach { print("$it ") }
}

class Solution {
	var graph = hashMapOf<Int, ArrayList<Int>>()
	var count = intArrayOf()
	var result = intArrayOf()

	fun dfs(node: Int, parent: Int) {
		for (child in graph[node] ?: arrayListOf()) {
			if (child != parent) {
				dfs(child, node)
				// The number of nodes in the subtree rooted at node
				count[node] += count[child]
				// The sum of distances of all nodes in the subtree rooted at node.
				result[node] += result[child] + count[child]
			}
		}
	}

	fun dfs2(node: Int, parent: Int) {
		for (child in graph[node] ?: arrayListOf()) {
			if (child != parent) {
				// Adjust the result values to represent the distances from each node to all other nodes. Update its res value using the parent's res value.
				// Subtract the count of nodes in the subtree rooted at the child (count[child]) and add the count of nodes not in the subtree (count.size - count[child]).
				result[child] = result[node] - count[child] + (count.size - count[child])
				dfs2(child, node)
			}
		}
	}

	fun sumOfDistancesInTree(n: Int, edges: Array<IntArray>): IntArray {
		graph = hashMapOf()
		count = IntArray(n) { 1 }
		result = IntArray(n)

		for (i in 0..n) {
			graph[i] = arrayListOf()
		}

		for (edge in edges) {
			val u = edge[0]
			val v = edge[1]

			graph[u]?.add(v)
			graph[v]?.add(u)
		}

		// Performing DFS traversal starting from root
		dfs(0, -1)
		dfs2(0, -1)

		return result
	}
}


fun sumOfDistancesInTree(n: Int, edges: Array<IntArray>): IntArray {
	val graph = Array(n) { mutableListOf<Int>() }
	for ((a, b) in edges) {
		graph[a] += b; graph[b] += a
	}

	val res = IntArray(n)
	fun dfs(curr: Int, from: Int, path: Int): Int = (1 + graph[curr]
		.sumOf {
			if (it != from)
				dfs(it, curr, path + 1)
			else 0
		})
		.also { res[0] += path; if (curr > 0) res[curr] = n - 2 * it }

	fun dfs2(curr: Int, from: Int) {
		if (curr > 0) res[curr] += res[from]
		for (e in graph[curr])
			if (e != from)
				dfs2(e, curr)
	}

	dfs(0, 0, 0)
	dfs2(0, 0)
	return res
}

class Solution {
	/* Complexity:
	 * Time O(n) and Space O(n);
	 */
	fun sumOfDistancesInTree(n: Int, edges: Array<IntArray>): IntArray {
		val root = 0
		val adjacencyList = adjacencyList(edges)
		val subTreeSizes = subTreeSizes(root, n, adjacencyList)
		val result = IntArray(n)
		result[root] = findRootSumOfDistances(root, 0, root, adjacencyList)

		for (childNode in adjacencyList[root] ?: emptySet()) {
			findOtherNodesSumOfDistances(childNode, root, n, adjacencyList, subTreeSizes, result)
		}
		return result
	}

	private fun adjacencyList(edges: Array<IntArray>): Map<Int, Set<Int>> {
		val result = mutableMapOf<Int, MutableSet<Int>>()
		for ((u, v) in edges) {
			result.computeIfAbsent(u) { mutableSetOf() }.add(v)
			result.computeIfAbsent(v) { mutableSetOf() }.add(u)
		}
		return result
	}

	private fun subTreeSizes(root: Int, totalNodes: Int, adjacencyList: Map<Int, Set<Int>>): IntArray {
		val result = IntArray(totalNodes)
		findSubTreeSizes(root, root, adjacencyList) { node, size ->
			result[node] = size
		}
		return result
	}

	private fun findSubTreeSizes(
		root: Int,
		parentNode: Int,
		adjacencyList: Map<Int, Set<Int>>,
		onEachNode: (node: Int, subTreeSize: Int) -> Unit,
	): Int {
		var result = 1
		for (childNode in adjacencyList[root] ?: emptySet()) {
			if (childNode == parentNode)
				continue

			result += findSubTreeSizes(childNode, root, adjacencyList, onEachNode)
		}

		onEachNode(root, result)
		return result
	}

	private fun findRootSumOfDistances(root: Int, depth: Int, parentNode: Int, adjacencyList: Map<Int, Set<Int>>): Int {
		var result = depth
		for (childNode in adjacencyList[root] ?: emptySet()) {
			if (childNode == parentNode)
				continue
			result += findRootSumOfDistances(childNode, depth + 1, root, adjacencyList)
		}
		return result
	}

	private fun findOtherNodesSumOfDistances(
		node: Int, parentNode: Int, totalNode: Int,
		adjacencyList: Map<Int, Set<Int>>,
		subTreeSizes: IntArray, memoization: IntArray,
	) {
		memoization[node] = (memoization[parentNode] + (totalNode - subTreeSizes[node]) - subTreeSizes[node])

		for (childNode in adjacencyList[node] ?: emptySet()) {
			if (childNode == parentNode)
				continue

			findOtherNodesSumOfDistances(
				childNode,
				node,
				totalNode, adjacencyList, subTreeSizes,
				memoization
			)
		}
	}
}

class Solution {
	fun sumOfDistancesInTree(n: Int, edges: Array<IntArray>): IntArray {
		if (n == 1)
			return intArrayOf(0)

		val nodeMapArray = Array<HashMap<Int, Pair<Int, Int>?>>(n) { hashMapOf() }

		edges.forEach { edge ->
			nodeMapArray[edge.first()][edge.last()] = null
			nodeMapArray[edge.last()][edge.first()] = null
		}

		val sumOfDistances = IntArray(n)

		nodeMapArray.forEachIndexed { index, _ ->
			sumOfDistances[index] = recursive(index, nodeMapArray, BooleanArray(n)).let { it.second - it.first }
		}

		return sumOfDistances
	}

	private fun recursive(
		index: Int,
		nodeMapArray: Array<HashMap<Int, Pair<Int, Int>?>>,
		visited: BooleanArray
	): Pair<Int, Int> {
		if (nodeMapArray[index].size == 1 && visited[nodeMapArray[index].keys.first()])
			return Pair(1, 1)

		visited[index] = true

		var numberOfNodes = 0
		var sumOfNodes = 0

		nodeMapArray[index].let { nodeMap ->
			nodeMap.keys.forEach { key ->
				if (visited[key])
					return@forEach

				if (nodeMap[key] != null) {
					numberOfNodes += nodeMap[key]!!.first
					sumOfNodes += nodeMap[key]!!.second
					return@forEach
				}

				recursive(key, nodeMapArray, visited).let {
					numberOfNodes += it.first
					sumOfNodes += it.second
					nodeMap[key] = it
				}
			}
		}

		numberOfNodes++

		return Pair(numberOfNodes, sumOfNodes + numberOfNodes)
	}
}