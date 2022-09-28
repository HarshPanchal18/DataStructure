#include <iostream>
#include <vector>

using namespace std;

/*
* Push Dominoes
* https://leetcode.com/problems/push-dominoes/
There are n dominoes in a line, and we place each domino vertically upright.
In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left.
Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides,
it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends
no additional force to a falling or already fallen domino.

You are given a string dominoes representing the initial state where:

dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.


Example 1:
Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.

Example 2:
Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
*/

string pushDominoes(string dominoes)
{

    int n = dominoes.size();
    int i = 0, j = n - 1;
    int count = 0, previous = 0;
    // string str="";

    vector<int> left(n, 0);
    // store the time duration when left effect will occur to that dominoe box

    vector<int> right(n, 0); // same for right

    while (i < n)
    {
        if (dominoes[previous] == 'R' && dominoes[i] == '.')
            count++;
        else
        {
            previous = i;
            count = 0;
        }
        right[i++] = count;
    }

    count = 0;
    previous = j;
    while (j >= 0)
    {
        if (dominoes[previous] == 'L' && dominoes[j] == '.')
            count++;
        else
        {
            previous = j;
            count = 0;
        }
        left[j--] = count;
    }

    for (int i = 0; i < n; i++)
    {
        if (left[i] < right[i] && dominoes[i] == '.' && left[i] != 0)
            dominoes[i] = 'L';

        if (left[i] > right[i] && dominoes[i] == '.' && right[i] == 0)
            dominoes[i] = 'L';

        if (left[i] > right[i] && dominoes[i] == '.' && right[i] != 0)
            dominoes[i] = 'R';

        if (left[i] < right[i] && dominoes[i] == '.' && left[i] == 0)
            dominoes[i] = 'R';

        if (left[i] == right[i] && dominoes[i] == '.')
            dominoes[i] = '.';
    }
    return dominoes;
}

int main(void)
{
    string domin;
    cout << "Enter Domino Pattern: ";
    cin >> domin;

    cout << pushDominoes(domin);
    return 0;
}
