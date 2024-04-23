import java.util.*

/*
* Minimum Height Trees - https://leetcode.com/problems/minimum-height-trees/
Topics - Depth-First Search | Breadth-First | Search Graph | Topological Sort

A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
Return a list of all MHTs' root labels. You can return the answer in any order.
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

Constraints:
1 <= n <= 2 * 104
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.
*/

fun main() {
	val result = findMinHeightTrees(
		6,
		arrayOf(
			intArrayOf(3, 0), intArrayOf(3, 1), intArrayOf(3, 2), intArrayOf(3, 4), intArrayOf(5, 4)
		)
	)
}

fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
	if (n == 1)
		return listOf(0)

	val adjecencies = HashMap<Int, HashSet<Int>>().apply {
		for ((u, v) in edges) { // Create with all edges.
			this[u] = getOrDefault(u, HashSet<Int>()).apply { add(v) }
			this[v] = getOrDefault(v, HashSet<Int>()).apply { add(u) }
		}
	}

	val queue = LinkedList<Int>().apply { // Add all leaves with having degree = 1.
		for ((k, v) in adjecencies) {
			if (v.size == 1)
				this.addFirst(k)
		}
	}

	var leftNodes = n
	while (leftNodes > 2) {
		leftNodes -= queue.size

		repeat(queue.size) {
			// Cut down the leaf.
			val node = queue.removeLast()

			adjecencies[node]?.forEach {
				adjecencies[it]?.remove(node)

				// Add the leafs neighbour to the queue if the neighbour degree is 1.
				if (adjecencies[it]?.size == 1)
					queue.addFirst(it)
			}
		}
	}
	return queue
}

class Solution {
	fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int?> {
		if (n == 1) return listOf(0)

		val ind = IntArray(n)
		val map: MutableMap<Int, MutableList<Int>> = HashMap()
		for (edge in edges) {
			ind[edge[0]]++
			ind[edge[1]]++
			map.putIfAbsent(edge[0], ArrayList<Int>())
			map[edge[0]]!!.add(edge[1])
			map.putIfAbsent(edge[1], ArrayList<Int>())
			map[edge[1]]!!.add(edge[0])
		}

		val queue: Queue<Int> = LinkedList()
		for (i in ind.indices) {
			if (ind[i] == 1)
				queue.add(i)
		}

		var processed = 0
		while (n - processed > 2) {
			val size = queue.size
			processed += size
			for (i in 0 until size) {
				val poll = queue.poll()
				for (adj in map[poll]!!) {
					if (--ind[adj] == 1) {
						queue.add(adj)
					}
				}
			}
		}

		val list: MutableList<Int> = ArrayList()
		list.addAll(queue)
		return list
	}
}

class Solution {

	fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
		if (n == 1) return arrayListOf(0)
		if (n == 2) return arrayListOf(0, 1)

		val adjMap = HashMap<Int, ArrayList<Int>>()

		// 1.  create the adjacency list
		for (edge in edges) {       // [[1,0],[1,2],[1,3]]
			if (adjMap.containsKey(edge[0]))
				adjMap[edge[0]]?.add((edge[1]))
			else
				adjMap[edge[0]] = arrayListOf(edge[1])
			if (adjMap.containsKey(edge[1]))
				adjMap[edge[1]]?.add(edge[0])
			else
				adjMap[edge[1]] = arrayListOf(edge[0])
		}

		// 2.  In regular BFS, we add the root to the queue.
		// In bottom-up BFS, we add the leaflets.
		// Leaflets are end nodes with only one edge.
		val queue = mutableListOf<Int>()   // Add the leaflets to the queue
		for (i in 0 until n) {
			val list = adjMap[i]
			if (list != null)
				if (list.size == 1) {
					queue.add(i)
				}
		}

		// 3.  With each depth, we remove all the leaflet nodes and their edges.  We also remove the leaflet from its adjacent neighbors. If those neighbors become leaflet nodes as a result, we add it to the queue for next level removal.
		//  We repeat until there is only 1 or 2 nodes left, which means we have reached the root.
		while (adjMap.size > 2) {
			var qSize = queue.size
			while (qSize-- > 0) {
				val i = queue.removeAt(0)
				val ni = adjMap[i]?.get(0)
				adjMap[ni]?.remove(i)
				if (adjMap[ni]?.size == 1)
					queue.add(ni!!)
				adjMap.remove(i)
			}
		}

		return adjMap.keys.toList()
	}
}

class Solution {
	fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
		if (n == 1)
			return listOf(0)

		val degree = IntArray(n)
		val adjacencyList: MutableMap<Int, MutableList<Int>> = HashMap()
		for (edge in edges) {
			degree[edge[0]]++
			degree[edge[1]]++
			adjacencyList.computeIfAbsent(edge[0]) { x: Int? -> ArrayList() }.add(edge[1])
			adjacencyList.computeIfAbsent(edge[1]) { x: Int? -> ArrayList() }.add(edge[0])
		}

		val leaves: Queue<Int> = LinkedList()
		for (i in degree.indices) {
			if (degree[i] == 1)
				leaves.add(i)
		}

		var remainingNodes = n
		while (remainingNodes > 2) {
			val size = leaves.size
			remainingNodes -= size
			for (i in 0 until size) {
				val leaf = leaves.poll()
				for (neighbor in adjacencyList[leaf]!!) {
					if (--degree[neighbor] == 1) {
						leaves.add(neighbor)
					}
				}
			}
		}

		return ArrayList(leaves)
	}
}

class Solution {
	fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
		// Pathological case
		if (n == 1)
			return listOf(0)

		val neighbors = Array(n) { mutableSetOf<Int>() }
		var leaves = mutableListOf<Int>()
		var remaining = n

		for (e in edges) {
			val start = e[0]
			val end = e[1]
			neighbors[start].add(end)
			neighbors[end].add(start)
		}

		for (i in 0 until n) {
			if (neighbors[i].size == 1)
				leaves.add(i)
		}

		while (remaining > 2) {
			remaining -= leaves.size
			val newLeaves = mutableListOf<Int>()

			for (l in leaves) {
				val neighbor = neighbors[l].first()
				neighbors[neighbor].remove(l)
				if (neighbors[neighbor].size == 1)
					newLeaves.add(neighbor)
			}

			leaves = newLeaves
		}

		return leaves
	}
}

class Solution {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if (edges.isEmpty())
            return (0..<n).toList()

        //Track node degrees
        val vertexToDegree = IntArray(n)
        val vertexToNeighbors = createGraph(n, edges, vertexToDegree)

        //Filter initial leaves
        var leaves = vertexToDegree.withIndex()
            .filter { it.value == 1 }
            .map { it.index }
            .toMutableList()

        while (leaves.isNotEmpty()) {
            val newLeaves = mutableListOf<Int>()

            leaves.forEach { leaf ->
                val neighbors = vertexToNeighbors[leaf]
                for (neighbor in neighbors) {
                    if (vertexToDegree[neighbor] <= 1)
                        continue

                    vertexToDegree[neighbor]--
                    if (vertexToDegree[neighbor] == 1)
                        newLeaves.add(neighbor)

                }
            }

            if (newLeaves.isEmpty())
                return leaves

            leaves = newLeaves
        }

        return leaves
    }

    private fun createGraph(
        n: Int,
        edges: Array<IntArray>,
        vertexToDegree: IntArray
    ): Array<HashSet<Int>> {
        val vertexToNeighbors = Array(n) { HashSet<Int>() }

        edges.forEach { (start, end) ->
            //Start -> end edge
            vertexToNeighbors[start].add(end)
            vertexToDegree[start]++

            //End -> start edge
            vertexToNeighbors[end].add(start)
            vertexToDegree[end]++
        }

        return vertexToNeighbors
    }
}

class Solution {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        val graph = Array<MutableSet<Int>>(n) { mutableSetOf() }
        for ((fromNode, toNode) in edges) {
            graph[fromNode].add(toNode)
            graph[toNode].add(fromNode)
        }

        val queue = graph.withIndex().asSequence()
            .filter { it.value.size <= 1}
            .map { it.index }
            .toMutableList()

        while (queue.size > 1) {
            if (queue.size == 2) {
                val (firstNode, secondNode) = queue
                if (graph[firstNode].contains(secondNode))
                    break
            }
            repeat(queue.size) {
                val node = queue.removeFirst()
                for (nextNode in graph[node]) {
                    val nextNodeNodes = graph[nextNode]
                    if (nextNodeNodes.remove(node) && nextNodeNodes.size == 1)
                        queue += nextNode
                }
            }
        }
        return queue
    }
}

class Solution {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
            if (n == 1)
            return listOf(0) //<-- Edge case

        val mapping = mutableMapOf<Int, MutableSet<Int>>()
        edges.forEach { edge ->
            if (mapping.containsKey(edge[0]))
                mapping[edge[0]]!!.add(edge[1])
            else
                mapping[edge[0]] = mutableSetOf(edge[1])


            if (mapping.containsKey(edge[1]))
                mapping[edge[1]]!!.add(edge[0])
            else
                mapping[edge[1]] = mutableSetOf(edge[0])

        }

        while(mapping.size > 2) {
            val toBeRemoved = mapping.entries.filter { it.value.size == 1 }.map { it.key }
            toBeRemoved.forEach {
                mapping[mapping[it]!!.first()]?.remove(it)
                mapping.remove(it)
            }
        }

        return mapping.map { it.key }
    }

    private fun maxHeight(mapping: Map<Int, MutableList<Int>>, node: Int, visited: MutableSet<Int>): Int {
        if (visited.contains(node)) return 0

        var height = 1
        visited.add(node)

        var maxChildHeight = 0
        mapping[node]!!.forEach {
            maxChildHeight = max(maxChildHeight, maxHeight(mapping, it, visited))
        }

        visited.remove(node)
        return height + maxChildHeight
    }
}