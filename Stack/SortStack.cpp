#include <bits/stdc++.h>

using namespace std;

void insert(stack<int> &s, int temp)
{
    if (s.size() == 0 || s.top() <= temp)
    {
        s.push(temp);
        return;
    }

    int val = s.top();
    s.pop();

    insert(s, temp);
    s.push(val);
}

void stackSort(stack<int> &s)
{
    if (s.size() == 1)
        return;

    int temp = s.top();
    s.pop();
    stackSort(s);
    insert(s, temp);
}

int main(void)
{
    int n;
    cin >> n;

    stack<int> s;

    for (int i = 0; i < n; i++)
    {
        int x;
        cin >> x;
        s.push(x);
    }

    stackSort(s); // sort in reverse as stack's top is the biggest

    while (!s.empty())
    {
        cout << s.top() << " ";
        s.pop();
    }
}
/*
5
8 7 1 5 9
9 8 7 5 1
*/
