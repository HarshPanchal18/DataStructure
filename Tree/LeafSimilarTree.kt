/*
* Leaf-Similar Trees

Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
Two binary trees are considered leaf-similar if their leaf value sequence is the same.
Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

Example 1:
Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true

Example 2:
Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false

Constraints:
The number of nodes in each tree will be in the range [1, 200].
Both of the given trees will have values in the range [0, 200].
*/

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    private val list1 = ArrayList<Int>()
    private val list2 = ArrayList<Int>()

    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        findLeaves(root1,list1)
        findLeaves(root2,list2)
        return list1.equals(list2)
    }

    fun findLeaves(treeNode: TreeNode?,list:ArrayList<Int>) {
        if(treeNode == null)
            return
        treeNode?.let{ findLeaves(it.left,list) }
        if(treeNode!!.left == null && treeNode!!.right == null)
            list.add(treeNode.`val`)
        treeNode?.let{ findLeaves(it.right,list) }
    }

}