/*
* Design HashMap
Design a HashMap without using any built-in hash table libraries.
Implement the MyHashMap class:
MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap.
If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.

Example 1:
Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]

Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
*/

fun main(args: Array<String>) {

}

class MyHashMap() {
    val data = mutableMapOf<Int,Int>()

    fun put(key: Int, value: Int) {
        data[key]=value
    }

    fun get(key: Int): Int {
        return data[key] ?: -1
    }

    fun remove(key: Int) {
        data.remove(key)
    }

}

class MyHashMap() {
    private val capacity = 1_399
    private val arr = Array(capacity) { Node() } // dummy node

    fun put(key: Int, value: Int) = arr[key % capacity].put(key, value)
    fun get(key: Int): Int = arr[key % capacity].get(key)
    fun remove(key: Int) = arr[key % capacity].remove(key)

    private class Node(val key: Int = -1, var `val`: Int = 0, var next: Node? = null) {

        fun put(key: Int, `val`: Int) {
            var p: Node? = this
            while (p?.next != null && p.next!!.key != key) {
                p = p?.next
            }
            p?.next = Node(key, `val`, p?.next?.next)
        }

        fun get(key: Int): Int {
            var p: Node? = this
            while(p != null) {
                if ((p?.key ?: -1) == key) return p?.`val` ?: -1
                p = p?.next
            }
            return -1
        }

        fun remove(key: Int) {
            var p: Node? = this
            while(p?.next != null && p?.next?.key != key) {
                p = p?.next
            }

            p?.let { it.next = it.next?.next }
        }
    }
}

class MyHashMap() {
    var table = Array<MutableList<Pair<Int, Int>>>(16) { mutableListOf() }
    var count = 0

    fun bucket(key: Int) = table[key % table.size]

    fun rehash() = with(table.flatMap { it }) {
      table = Array(table.size * 2) { mutableListOf() }
      for ((key, value) in this) bucket(key) += key to value
    }

    fun put(key: Int, value: Int) = with(bucket(key)) {
      if (removeAll { it.first == key }) count++
      this += key to value
      if (count > table.size) rehash()
    }

    fun get(key: Int) = bucket(key)
      .firstOrNull { it.first == key }?.second ?: -1

    fun remove(key: Int) {
      if (bucket(key).removeAll { it.first == key }) count--
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * var obj = MyHashMap()
 * obj.put(key,value)
 * var param_2 = obj.get(key)
 * obj.remove(key)
 */