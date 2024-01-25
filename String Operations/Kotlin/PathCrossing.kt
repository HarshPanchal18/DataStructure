/*
* Path Crossing
Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively.
You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.

Example 1:
Input: path = "NES"
Output: false
Explanation: Notice that the path doesn't cross any point more than once.

Example 2:
Input: path = "NESWW"
Output: true
Explanation: Notice that the path visits the origin twice.

Constraints:
1 <= path.length <= 104
path[i] is either 'N', 'S', 'E', or 'W'.

*/

fun isPathCrossing(path:String): Boolean {
    var x = 0
    var y = 0
    var visited = mutableSetOf<String>()
    visited.add("0,0")
    for (c in path) {
        when (c) {
            'N' -> y++
            'S' -> y--
            'E' -> x++
            'W' -> x--
        }
        if (visited.contains("$x,$y")) {
            return true
        } else {
            visited.add("$x,$y")
        }
    }
    return false
}

data class Pair(val x: Int, val y: Int)
fun isPathCrossing(path: String): Boolean {
    var x = 0
    var y = 0
    val set = HashSet<Pair>()
    set.add(Pair(x, y))

    for (ch in path) {
        when (ch) {
            'N' -> y++
            'S' -> y--
            'W' -> x--
            'E' -> x++
        }

        val pair = Pair(x, y)
        if (pair in set) {
            return true
        }
        set.add(pair)
    }

    return false
}

fun isPathCrossing(path: String): Boolean {
    val seen = mutableSetOf<Pair<Int, Int>>()

    return path.scan(0 to 0) { (x, y), char ->
        Pair(
            x +
                if (char == 'E') 1
                else if (char == 'W') -1
                else 0,
            y +
                if (char == 'N') 1
                else if (char == 'S') -1
                else 0
        )
    }.any { !seen.add(it) }
}

fun isPathCrossing(path: String): Boolean {
    var curr = 0 to 0
    var set = HashSet<String>();

    set.add("0,0")
    for (i in path) {
        if (i == 'N') curr = curr.first to curr.second + 1
        if (i == 'E') curr = curr.first + 1 to curr.second
        if (i == 'S') curr = curr.first to curr.second - 1
        if (i == 'W') curr = curr.first - 1 to curr.second

        if (!set.add("${curr.first},${curr.second}"))
            return true
    }
    return false
}

data class Point(val x : Int,val y : Int)
fun move(currPoint : Point, direction : Char, movePoint : (Int, Int, Point) -> Point = {dx , dy , p -> Point(p.x+dx, p.y+dy)} ) =
    when(direction) {
        'N' -> movePoint(-1,0,currPoint)
        'S' -> movePoint(1,0,currPoint)
        'E' -> movePoint(0,1,currPoint)
        'W' -> movePoint(0,-1,currPoint)
        else -> Point(Int.MAX_VALUE,Int.MAX_VALUE)
    }

fun isPathCrossing(path: String): Boolean {
    val visited = mutableSetOf(Point(0,0))
    var curr = Point(0,0)
    path.forEach {
        curr = move(curr,it)
        if(visited.contains(curr))
            return@isPathCrossing true
        visited.add(curr)
    }

    return false
}