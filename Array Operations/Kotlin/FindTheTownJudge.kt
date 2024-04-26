/*
* Find the Town Judge

In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:
* The town judge trusts nobody.
* Everybody (except for the town judge) trusts the town judge.
* There is exactly one person that satisfies properties 1 and 2.
* You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi. If a trust relationship does not exist in trust array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

Example 1:
Input: n = 2, trust = [[1,2]]
Output: 2

Example 2:
Input: n = 3, trust = [[1,3],[2,3]]
Output: 3

Example 3:
Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1

Constraints:
1 <= n <= 1000
0 <= trust.length <= 104
trust[i].length == 2
All the pairs of trust are unique.
ai != bi
1 <= ai, bi <= n
*/

fun main() {
    print(findJudge(2,arrayOf(intArrayOf(1,2))))
}

fun findJudge(n: Int, trust: Array<IntArray>): Int {
    val trustMap = mutableMapOf<Int,Int>()
    trust.forEach {
        trustMap[it[0]] = trustMap.getOrDefault(it[0], 0) + 1
        trustMap[it[1]] = trustMap.getOrDefault(it[1], 0) - 1
    }
    return trustMap.filter { it.value == n - 1 }.keys.first()
}

fun findJudge(n: Int, trust: Array<IntArray>): Int {
    val w = IntArray(n) { 0 }
    trust.forEach { (p1, p2) ->
        w[p1-1] -= 1
        w[p2-1] += 1
    }
    w.forEachIndexed { i, v ->
        if (v == n-1) return i+1
    }
    return -1
}

fun findJudge(n: Int, trust: Array<IntArray>): Int {
    if (n == 1) return 1

    val peopleWhoTrust = BitSet(n + 1)
    val trustRating = IntArray(n + 1) { 1 }
    for (i in trust.indices) {
        val (a, b) = trust[i]
        peopleWhoTrust.set(a)
        trustRating[b]++
    }

    for (candidate in 1..n) {
        if (peopleWhoTrust.get(candidate)) continue
        if (trustRating[candidate] == n) return candidate
    }

    return -1
}

class Solution {
    fun findJudge(n: Int, trust: Array<IntArray>): Int {
        val citizens = Array(n + 1) { Citizen(it) }
        var candidate = citizens[1]
        for (t in trust) {
            citizens[t[0]].markAsVoter()
            candidate = citizens[t[1]].addVote(candidate)
        }
        return when {
            candidate.votes + 1 == n -> candidate.number
            else -> -1
        }
    }

    class Citizen(val number: Int) {
        var votes = 0
        fun addVote(candidate: Citizen): Citizen {
            if (votes != -1) votes++
            return when {
                votes > candidate.votes -> this
                else -> candidate
            }
        }
        fun markAsVoter() {
            votes = -1
        }
    }
}

class Solution {
    fun findJudge(n: Int, trust: Array<IntArray>): Int {
        val indegree = IntArray(n+1)
        val outdegree = IntArray(n+1)

        for(edge in trust) {
            indegree[edge[1]]++
            outdegree[edge[0]]++
        }

        for(person in 1..n)
            if(indegree[person] == n-1 && outdegree[person] == 0) return person

        return -1
    }
}

class Solution {
    fun findJudge(n: Int, trust: Array<IntArray>): Int {
        val trustFreq = IntArray(n + 1) { 0 }

        for ((from, to) in trust) {
            trustFreq[from]--
            trustFreq[to]++
        }

        for (i in 1 .. n)
            if (trustFreq[i] == n-1)
                return i

        return -1
    }
}