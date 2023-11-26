/*
* Staircase detail
This is a staircase of size n = 4:
   #
  ##
 ###
####

Its base and height are both equal to n.
It is drawn using # symbols and spaces.
The last line is not preceded by any spaces.

Write a program that prints a staircase of size .
Function Description
Complete the staircase function in the editor below.
staircase has the following parameter(s):
int n: an integer
Print
Print a staircase as described above.

Input Format
A single integer, , denoting the size of the staircase.

Constraints
0 < n <= 100

Output Format
Print a staircase of size  using # symbols and spaces.

Note: The last line must have  spaces in it.

Sample Input
6

Sample Output
Explain
     #
    ##
   ###
  ####
 #####
######

Explanation
The staircase is right-aligned, composed of # symbols and spaces, and has a height and width of .

 * Complete the 'staircase' function below.
 *
 * The function accepts INTEGER n as parameter.
 */

fun staircase(n: Int): Unit {
    // Write your code here
    for (i in 1..n) {
        for (j in n.downTo(i + 1))
            print(" ")
        for (j in 1..i)
            print("#")
        println()
    }

}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    staircase(n)
}
