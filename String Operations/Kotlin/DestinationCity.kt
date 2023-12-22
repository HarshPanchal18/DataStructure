/*
* Destination City
You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi.
Return the destination city, that is, the city without any path outgoing to another city.
It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.

Example 1:
Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
Output: "Sao Paulo"
Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".

Example 2:
Input: paths = [["B","C"],["D","B"],["C","A"]]
Output: "A"
Explanation: All possible trips are:
"D" -> "B" -> "C" -> "A".
"B" -> "C" -> "A".
"C" -> "A".
"A".
Clearly the destination city is "A".

Example 3:
Input: paths = [["A","Z"]]
Output: "Z"

Constraints:
* 1 <= paths.length <= 100
* paths[i].length == 2
* 1 <= cityAi.length, cityBi.length <= 10
* cityAi != cityBi
* All strings consist of lowercase and uppercase English letters and the space character.
*/

fun main() {
    val path = listOf(
        listOf("London", "New York"),
        listOf("New York", "Lima"),
        listOf("Lima", "Sao Paulo")
    )

    print(destCity(path))
}

fun destCity(path: List<List<String>>): String {
    val start = path.map { it[0] }.toSet()
    //println(start)

    val end = path.map { it[1] }.toSet()
    //println(end)

    //println(end-start) // Sao Paulo
    //println(start-end) // London

    return (end - start).single()
}

fun destCity(paths: List<List<String>>): String {
    var endpoints = hashMapOf<String, String>()

    paths.stream().forEach { endpoints[it[0]] = it[1] }
    paths.stream().forEach {
        if (endpoints.containsKey(it[1])) {
            endpoints[it[1]] += it[0];
        } else {
            endpoints[it[1]] = ""
        }
    }

    var result: String = "";
    endpoints.entries.stream().forEach {
        if (it.value.isEmpty()) {
            result = it.key;
        }
    }

    return result;
}

fun destCity(paths: List<List<String>>): String = (paths.map { it[1] } - paths.map { it[0] }).first()

fun destCity(paths: List<List<String>>): String {
    val startCities = mutableSetOf<String>()
    val destinations = mutableSetOf<String>()
    paths.forEach { path ->
        startCities.add(path[0])
        destinations.add(path[1])
    }
    return (destinations - startCities).first()
}

fun destCity(paths: List<List<String>>): String {
    val set = paths.map { it[0] }.toSet()
    return paths.first { !set.contains(it[1]) }[1]
}

fun destCity(paths: List<List<String>>): String {
    val canVisit = hashSetOf<String>()
    for (p in paths)
        canVisit += p[0]

    for (path in paths) {
        if (!canVisit.contains(path[1]))
            return path[1]
    }

    throw Exception("No City")
}

fun destCity(paths: List<List<String>>): String {
    var destination = ""
    val entryList = mutableSetOf<String>()
    val exitList = mutableListOf<String>()

    // Iterate through paths and populate entryList and exitList
    paths.forEach{
        val entry = it.get(0)
        val exit = it.get(1)
        entryList.add(entry)
        exitList.add(exit)
    }

    // Iterate through exitList to find the destination city
    exitList.forEach{
        if(!entryList.contains(it)){
            destination = it
            return@forEach
        }
    }
    return destination
}

fun destCity(paths: List<List<String>>): String = HashMap<String, String>().run {
    for ((a, b) in paths) put(a, b)
    var b = iterator().next().value

    while (true)
        b = this[b] ?: break
    b
}