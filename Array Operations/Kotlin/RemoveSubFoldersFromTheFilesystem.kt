import java.util.*

/*
* Remove Sub-Folders from the Filesystem

Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".
The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.

Example 1:
Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.

Example 2:
Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".

Example 3:
Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]

Constraints:
1 <= folder.length <= 4 * 104
2 <= folder[i].length <= 100
folder[i] contains only lowercase letters and '/'.
folder[i] always starts with the character '/'.
Each folder name is unique.
*/

fun main() {
    println(removeSubfolders(arrayOf("/a", "/a/b", "/c/d", "/c/d/e", "/c/f")))
}

fun removeSubfolders(folder: Array<String>): List<String> {
    return buildList {
        folder.sort()
        for (f in folder)
            if (size < 1 || !f.startsWith(last() + "/"))
                add(f)
    }
}

class Solution {
    data class TrieNode(val children: MutableMap<String, TrieNode> = mutableMapOf()) {
        var isFolder = false
    }

    fun removeSubfolders(folders: Array<String>): List<String> {
        val result = mutableListOf<String>()
        val root = buildTrie(folders)

        for (folder in folders) {
            var ptr = root
            var parentExists = false

            for (segment in folder.split("/")) {
                if (ptr.isFolder) {
                    parentExists = true
                    break
                }
                ptr = ptr.children[segment]!!
            }
            if (!parentExists) {
                result.add(folder)
            }
        }
        return result
    }

    fun buildTrie(folders: Array<String>): TrieNode {
        val root = TrieNode()

        for (folder in folders) {
            var ptr = root
            var parentExists = false

            for (segment in folder.split("/")) {
                if (ptr.isFolder) {
                    // There is already another folder added that this is a subfolder of No reason to finish adding
                    parentExists = true
                    break
                }
                ptr.children.getOrPut(segment) { TrieNode() }
                ptr = ptr.children[segment]!!
            }
            if (!parentExists) ptr.isFolder = true
        }

        return root
    }
}

class Solution {
    fun removeSubfolders(folder: Array<String>): List<String> {
        fun String.hashes(): LongArray {
            val path = this.split('/')
            val result = LongArray(path.size)

            result[0] = path.first().hashCode().toLong()

            for (i in 1..path.lastIndex) {
                result[i] = (result[i - 1] * BASE % MOD + path[i].hashCode()) % MOD
            }

            return result
        }

        val allHashes = folder.map { it.hashes() }
        val roots = allHashes.map { it.last() }.toSet()
        val ans = mutableListOf<String>()

        for ((i, hashes) in allHashes.withIndex()) {
            var isSubFolder = false

            for ((j, hash) in hashes.withIndex()) {
                if (j == hashes.lastIndex) break

                if (hash in roots) {
                    isSubFolder = true
                    break
                }
            }

            if (!isSubFolder) {
                ans += folder[i]
            }
        }

        return ans
    }

    companion object {
        const val BASE = 283
        const val MOD = 1_000_000_000_000_000 - 11
    }
}

class Solution {
    fun removeSubfolders(folder: Array<String>): List<String> {
        val trie = Trie()
        return folder.toList().sortedBy { it.length }.filter {
            !trie.isSubFolder(it.split("/"))
        }
    }
}

class Trie {
    private var head: Node? = Node()

    fun isSubFolder(list: List<String>, i: Int = 0, node: Node? = head): Boolean {
        var i = i

        if (i == list.size) {
            node!!.isEnd = true
            return false
        }

        if (list[i] == "") i++
        if (node!!.isEnd) return true

        if (node.nodes[list[i]] == null) node.nodes[list[i]] = Node()

        return isSubFolder(list, i + 1, node.nodes[list[i]])
    }
}

class Node {
    var nodes = mutableMapOf<String, Node?>()
    var isEnd: Boolean = false
}

class Solution {
    class Node(val dirs: HashMap<String, Node> = HashMap(), var isIncluded: Boolean = false)

    private val root = Node()

    fun removeSubfolders(folder: Array<String>): List<String> {
        for (s in folder) add(s)
        return getAllIncluded()
    }

    private fun add(path: String) {
        val dirList = path.split('/').filter { it.isNotBlank() }

        var temp = root
        for (dir in dirList) {
            if (!temp.dirs.contains(dir)) temp.dirs[dir] = Node()
            temp = temp.dirs[dir]!!
        }

        temp.isIncluded = true
    }

    private fun getAllIncluded(): List<String> {
        val result = ArrayList<String>()

        val q: Queue<Pair<Node, String>> = LinkedList()
        q.add(Pair(root, ""))

        while (q.isNotEmpty()) {
            val (u, path) = q.remove()
            if (u.isIncluded) {
                result.add(path)
                continue
            }

            for (dir in u.dirs)
                q.add(Pair(dir.value, "$path/${dir.key}"))

        }

        return result
    }
}