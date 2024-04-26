import java.util.Arrays;

/*
* Max Min
Asked In: Google

Given an array A of size N. You need to find the sum of Maximum and Minimum element in the given array.
NOTE: You should make minimum number of comparisons.

Problem Constraints
1 <= N <= 105
-10^9 <= A[i] <= 10^9

Input Format
First and only argument is an integer array A of size N.

Output Format
Return an integer denoting the sum Maximum and Minimum element in the given array.

Example Input
Input 1:
A = [-2, 1, -4, 5, 3]
Input 2:
A = [1, 3, 4, 1]

Example Output
Output 1:
1
Output 2:
5

Example Explanation
Explanation 1:
Maximum Element is 5 and Minimum element is -4. (5 + (-4)) = 1.

Explanation 2:
Maximum Element is 4 and Minimum element is 1. (4 + 1) = 5.
*/

public class MaxMin {
    public static int solve(int[] A) {
        Arrays.sort(A);
        return A[A.length] + A[0];
    }

    public static void main(String[] args) {
        System.out.println(solve(new int[] { 1, 2, 3, 4 }));
    }
}
