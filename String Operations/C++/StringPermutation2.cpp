#include <iostream>
using namespace std;

/*
* Permutation in String
* Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
* In other words, return true if one of s1's permutations is the substring of s2.

Example 1:
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input: s1 = "ab", s2 = "eidboaoo"
Output: false
*/

/*
* EXPLANATION:-
  In this problem, we need to find a substring in s2
  that is permuation of s1.

  Permutation means re-arranging the letters of s1.

  In other words, we can say that we need to
  find an anagram of s1 in s2.

  First of all, What is anagram?
       A string s is angram of p, if it satisfies the following conditions,
       1. s should contain all the characters in p.
       2. Frequency of each character should be same in two strings.

  Now, we need to find anagram of s1 in s2.

  How to find?.
  This can be done by finding all the substrings of length same as s1
  and check that substring is anagram or not.
  If it is anagram, then return true.
  otherwise check next substring.

  Let's see how to solve this problem using an example.

  Take,  s1 = ab,  s2 = eioubab
  find all substrings of length 2 in s2.

  substrings = [ ei , io, ou, ub, ba, ab ]
  from these substrings, find anagram

  1. ei -> it is not anagram of s1. Because e and i are not in s1.
  2. io -> it is not anagram of s1. Because e is not in s1.
  3. ou -> it is not anagram of s1. Because o is not in s1.
  4. ub -> it is not anagram of s1. Because u is not in s1.
  5. ba -> It is anagram of s1. So, return true.
      Correctness :- Is this correct? Is ba permutation of ab?
      -> Yes. if we re-arrange letters in ab. we will get ba.
          So, it is correct.

If you don't find any anagram, then return false.

Now, let's develop an algorithm to solve this problem.

1. Find frequency of each character in s1.
2. Now, we need to find all substrings of length s1 in s2.
    This process can be efficiently done by using sliding window technique.
    Sliding Window Technique:-
    s2 = dbcad, s1 = abc
    Take two pointers i and j.
    Intially i and j point to starting position of string s.
    s = d  b  c  a  d
        ^
       i, j
    ->  move j until j - i == len(p)
    s = d  b  c  a  d
        ^        ^
        i        j
    Now, the substring formed here is  dbc,
    it is not anagram so, move to next substring.
    s = d  b  c  a  d
           ^     ^
           i     j
   Now, j at 3rd index, i at 1st index.
   3 - 1 < 3
   so, move j until j - i == len(p)
   s = d  b  c  a  d
          ^        ^
          i        j
    Now, substring formed here is bca.
    It is anagram. So, return true.
    We keep moving i and j until j reaches end of s2.
    This is how we find substring using sliding window technique.
    and check whether it is anagram or not.
    if it is anagram, return true.
3. If you don't find anagram, then return false.

TIME:- O(N)
SPACE:- O(1)

*/

class Solution
{
public:
    bool checkInclusion(string s1, string s2)
    {
        int map[26] = {0};
        int n = s1.size();
        int i = 0, j = 0;

        for (char ch : s1)
            map[ch - 'a']++;

        while (j < s2.size())
        {
            if (map[s2.at(j++) - 'a']-- > 0)
                n--;

            if (n == 0)
                return true;

            if (j - i == s1.size() && map[s2.at(i++) - 'a']++ >= 0)
                n++;
        }
        return false;
    }
};
