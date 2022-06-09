#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n;
    scanf("%d", &n);

    int s[n];
    for (int a = 0; a < n; a++)
        scanf("%d", s + a);

    // Selection sort
    for (int i = 0; i < n; i++)
        swap(s[i], *min_element(s + i, s + n));

    for (int a = 0; a < n; a++)
        printf("%d%c", s[a], " \n"[a == n - 1]);
}

/*
5
8 1 9 4 6
1 4 6 8 9
*/
