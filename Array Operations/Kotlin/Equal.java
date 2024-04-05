import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * Equal - https://www.interviewbit.com/problems/equal/
Asked In: Facebook

Problem Description
Given an array A of N integers, find the index of values that satisfy P + Q = R + S, where P, Q, R & S are integers values in the array

Expected time complexity O(N2)

NOTE:
1) Return the indices A1 B1 C1 D1, so that
  A[A1] + A[B1] = A[C1] + A[D1]
  A1 < B1, C1 < D1
  A1 < C1, B1 != D1, B1 != C1
2) If there are more than one solutions,
   then return the tuple of values which are lexicographical smallest.

Assume we have two solutions
S1 : A1 B1 C1 D1 ( these are values of indices in the array )
S2 : A2 B2 C2 D2

S1 is lexicographically smaller than S2 if:
  A1 < A2 OR
  A1 = A2 AND B1 < B2 OR
  A1 = A2 AND B1 = B2 AND C1 < C2 OR
  A1 = A2 AND B1 = B2 AND C1 = C2 AND D1 < D2
If no solution is possible, return an empty list.


Problem Constraints
1 <= N <= 1000
0 <= A[i] <= 1000

Input Format
First and only argument is an integer array A of length N.

Output Format
Return an array of size four which contains indices of values P, Q, R, and S.

Example Input
Input 1:
A = [3, 4, 7, 1, 2, 9, 8]

Input 2:
A = [2, 5, 1, 6]

Example Output
Output 1:
[0, 2, 3, 5]

Output 2:
[0, 1, 2, 3]

Example Explanation
Explanation 1:
A[0] + A[2] = A[3] + A[5]
Note: indexes returned should be 0-based.

Explanation 2:
A[0] + A[1] = A[2] + A[3]

*/

public class Equal {

    // Given an array A of N integers, find the index of values that satisfy P + Q =
    // R + S, where P, Q, R & S are integers values in the array

    public static int[] equal(int[] A) {
        int n = A.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = 1; j < n - 2; j++) {
                for (int k = 2; k < n - 1; k++) {
                    for (int l = 3; l < n; l++) {
                        if (A[i] + A[j] == A[k] + A[l]) {
                            return new int[] { i, j, k, l };
                        }
                    }
                }
            }
        }

        return new int[] {};
    }

    public static void main(String[] args) {
        int[] result =
                // equal(new int[] { 3, 4, 7, 1, 2, 9, 8 });
                equal(new int[] { 1, 3, 3, 3, 3, 2, 2 });

        for (int i : result) {
            System.out.println(i);
        }
    }
}

// O(n^3)
class Solution0 {
    public ArrayList<Integer> equal(ArrayList<Integer> a) {
        int len = a.size();
        ArrayList<Integer> res = new ArrayList<>(4);
        HashSet<Integer> hs = new HashSet<>(a);

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = i + 1; k < len - 1; k++) {
                    if (j == k)
                        continue;

                    int x = a.get(i) + a.get(j) - a.get(k);
                    if (hs.contains(x)) {
                        for (int m = k + 1; m < len; m++)
                            if (a.get(m) == x && m != j) {
                                res.add(i);
                                res.add(j);
                                res.add(k);
                                res.add(m);
                                return res;
                            }
                    }
                }
            }
        }
        return res;
    }

}

// O(n^2)
class Solution1 {

    public ArrayList<Integer> equal(ArrayList<Integer> a) {
        int len = a.size();
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        ArrayList<Integer> al;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int sum = a.get(i) + a.get(j);

                if (hm.containsKey(sum)) {
                    al = hm.get(sum);
                    int a1 = al.get(0), b1 = al.get(1);

                    if (a1 < i && b1 != i && b1 != j) {
                        if ((res.size() == 0) ||
                                (a1 < res.get(0)) ||
                                (a1 == res.get(0) && b1 < res.get(1))// ||
                        /* No need to compare for c1 and d1 as they will surely be in order */
                        // (a1==res.get(0) && b1==res.get(1) && i<res.get(2)) ||
                        // (a1==res.get(0) && b1==res.get(1) && i==res.get(2) && j<res.get(3))
                        ) {
                            res.clear();
                            res.add(a1);
                            res.add(b1);
                            res.add(i);
                            res.add(j);
                        }
                    }
                } else {
                    al = new ArrayList<>();
                    al.add(i);
                    al.add(j);
                    hm.put(sum, al);
                }
            }
        }
        return res;
    }
}