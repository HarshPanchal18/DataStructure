#include <iostream>
#include <algorithm>
#include <set>
using namespace std;

int main()
{
    int n;
    int B[] = {1, 2, 3, 4, 4, 5, 9, 1};
    n = 8; // Size of B
    set<int> A;
    for (int i = 0; i < n; i++)
        if (binary_search(A.begin(), A.end(), B[i]) == 0)
            A.insert(B[i]);
    set<int>::iterator it = A.begin();
    for (; it != A.end(); it++)
        cout << *it << " ";
    cout << endl;
}
