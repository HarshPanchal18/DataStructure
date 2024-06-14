/*
* Wordle
Chef invented a modified wordle.
There is a hidden word S and a guess word T, both of length 5.
Chef defines a string M to determine the correctness of the guess word. For the ith index:
If the guess at the ith index is correct, the ith character of M is G.
If the guess at the ith index is wrong, the ith character of M is B.
Given the hidden word S and guess T, determine string M.

Input Format
First line will contain T, number of test cases. Then the test cases follow.
Each test case contains of two lines of input.
First line contains the string S - the hidden word.
Second line contains the string T - the guess word.

Output Format
For each test case, print the value of string M.

You may print each character of the string in uppercase or lowercase (for example, the strings BgBgB, BGBGB, bgbGB and bgbgb will all be treated as identical).

Constraints
1 ≤ T ≤ 1000
∣S∣ = ∣T∣ = 5
S,T contain uppercase english alphabets only.

Sample 1:
Input
3
ABCDE
EDCBA
ROUND
RINGS
START
STUNT

Output
BBGBB
GBBBB
GGBBG

*/

fun main() {
	val cases = readln().toInt()
	for (i in 1..cases) {
		val s = readln()
		val g = readln()

		val result = StringBuilder()
		for (index in s.indices) {
			if (s[index] == g[index])
				result.append('G')
			else
				result.append('B')
		}
		println(result)
	}
}