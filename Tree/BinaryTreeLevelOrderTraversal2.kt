import java.util.*

/*
* Binary Tree Level Order Traversal II

Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[15,7],[9,20],[3]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
*/

class TreeNode(val `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

// Calculate height of the tree
fun TreeNode?.treeHeight(): Int {
    if (this == null) return -1

    return maxOf(this.left.treeHeight(), this.right.treeHeight()) + 1
}

fun main() {
    val node3 = TreeNode(3)
    val node9 = TreeNode(9)
    val node20 = TreeNode(20)
    val node15 = TreeNode(15)
    val node7 = TreeNode(7)

    node3.left = node9
    node3.right = node20
    node20.left = node15
    node20.right = node7

    val result = levelOrderBottom(node3)
    println(result)
}

// List for holding current level's node value
var levelN = mutableListOf<Int>()

fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
    return buildList {
        val height = root.treeHeight()

        for (level in height downTo 0) { // bottom to top
            currentLevel(root, level) // Go recursive...
            add(levelN) // Add each level of node values to the list
            levelN = mutableListOf() // resetting the list
        }
    }
}

fun currentLevel(root: TreeNode?, level: Int) {
    if (root == null) return

    if (level == 0) {
        levelN.add(root.`val`) // add node values
    } else {
        currentLevel(root.left, level - 1)
        currentLevel(root.right, level - 1)
    }
}

class Solution {
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        val queue = LinkedList<TreeNode>()

        root?.let { queue.add(root) }

        val result = mutableListOf<List<Int>>()

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val levelList = mutableListOf<Int>()

            for (i in 0..<levelSize) {
                val item = queue.pop()

                item.left?.let { queue.add(it) }
                item.right?.let { queue.add(it) }

                levelList.add(item.`val`)
            }

            result.add(0, levelList)
        }

        return result
    }
}

class Solution {
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val queue = LinkedList<TreeNode?>()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val queueSize = queue.size
            val partialResult = mutableListOf<Int>()

            for (i in 0 until queueSize) {
                val current = queue.poll()

                if (current != null)
                    partialResult.add(current.`val`)

                current?.let {
                    queue.add(current.left)
                    queue.add(current.right)
                }
            }

            if (partialResult.isNotEmpty())
                result.add(partialResult)

        }

        return result.reversed()
    }
}

class Solution {
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        var level = 0
        val map = mutableMapOf<Int, List<TreeNode>>()
        val queue: Queue<TreeNode> = LinkedList()

        queue.add(root)

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val currentLevelNode = LinkedList<TreeNode>()

            for (i in 0 until levelSize) {
                val node = queue.poll()
                currentLevelNode.add(node)

                if (node.left != null) queue.add(node.left)

                if (node.right != null) queue.add(node.right)
            }

            map[level] = currentLevelNode
            level++
        }

//        println("MAP $map")
        val result = mutableListOf<List<Int>>()

        for (i in level downTo 0) {
            map[i]?.map { it.`val` }?.let {
                result.add(it)
            }
        }

//        println("RESULT $result")
        return result
    }
}

class Solution {
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        val result = LinkedList<MutableList<Int>>()
        if (root == null) return result
        val queue: Queue<TreeNode> = LinkedList()

        queue.add(root)

        while (queue.isNotEmpty()) {
            val levelItem = mutableListOf<Int>()
            val levelSize = queue.size

            for (i in 0 until levelSize) {
                val node = queue.remove()
                levelItem.add(node.`val`)

                node.left?.let { queue.add(node.left) }
                node.right?.let { queue.add(node.right) }
            }
            result.addFirst(levelItem)
        }
        return result
    }
}