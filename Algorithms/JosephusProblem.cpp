#include <bits/stdc++.h>
using namespace std;

/*

Flavius Josephus
The problem is named after Flavius Josephus, a Jewish historian living in the 1st century.
According to Josephus' account of the siege of Yodfat, he and his 40 soldiers were trapped in a cave by Roman soldiers.
They chose suicide over capture, and settled on a serial method of committing suicide by drawing lots

https://en.wikipedia.org/wiki/Josephus_problem

There are N people standing in a circle waiting to be executed.
The counting out begins at some point in the circle and proceeds around the circle in a fixed direction.
In each step, a certain number of people are skipped and the next person is executed.

The Josephus Problem is a theoretical problem in computer science and mathematics, where people standing in a circle get executed one-by-one until one person remains.
A person at a specified position starts counting, and this counting proceeds in one direction, until it reaches a pre-fixed number, after which the person standing next in the circle gets executed.
The counting restarts from the next person, and this process continues until all but one person remains.
The problem is usually stated in terms of the number of persons standing in the circle (n), and a number k, which denotes that the kth person will be executed on counting.
*/
int getPosition(vector<int> &v, int n, int k, int in)
{
    if (v.size() == 1)
        return v[0];

    in = (in + k - 1) % n;

    for (int i = in; i < v.size() - 1; i++)
        v[i] = v[i + 1];

    v.pop_back();

    return getPosition(v, n - 1, k, in);
}

int getPos(int n, int k)
{
    if (n == 1)
        return 1;

    int y = getPos(n - 1, k);
    return (y + k - 1) % (n) + 1;
}

int JP2(int n, int k)
{
    int pos = 1;
    for (int i = 2; i <= n; i++)
        pos = (pos + k - 1) % i + 1;
    return pos;
}

void Josh(vector<int> person, int k, int index)
{
    if (person.size() == 1) // Base case , when only one person is left
    {
        cout << person[0] << endl;
        return;
    }

    index = ((index + k) % person.size()); // find the index of first person which will die

    person.erase(person.begin() + index); // remove the first person which is going to be killed

    Josh(person, k, index); // recursive call for n-1 persons
}

/*
int main()
{
    int n = 14; // specific n and k values for original
                // josephus problem
    int k = 2;
    k--;           // (k-1)th person will be killed
    int index = 0; // The index where the person which will die

    vector<int> person;
    // fill the person vector
    for (int i = 1; i <= n; i++)
        person.push_back(i);

    Josh(person, k, index);
}
*/

int main(void)
{
    int n, k;
    cin >> n >> k;

    vector<int> a(n);

    for (int i = 0; i < n; i++)
        a[i] = i + 1;

    cout << getPosition(a, n, k, 0);
    cout << endl;
    cout << getPos(n, k);
}
