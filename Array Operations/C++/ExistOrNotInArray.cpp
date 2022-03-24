/*
Given an array arr of n length.
You will be given Q queries for the array.
Each query contains a number N.
You have to determine whether a number exist in the array or not

For each Query print “Yes” if the number is present and “No” if it’s not.

Enter the number of elements: 5
4 7 1 2 3

Enter number of queries for check: 3
5
No
7
Yes
2
Yes

Enter the number of elements: 5
7 4 1 5 6

Enter number of queries for check: 3
5 7 2
Yes
Yes
No

*/

#include <iostream>
#include <map>

using namespace std;

int main(void)
{
    int n;
    cout << "\nEnter the number of elements: ";
    cin >> n;

    map<int, bool> pr;

    for (int i = 0; i < n; i++)
    {
        int pair;
        cin >> pair;
        pr[pair] = true;
    }

    int qry;
    cout << "\nEnter number of queries for check: ";
    cin >> qry;

    while (qry--)
    {
        int a;
        cin >> a;

        if (pr.count(a))
            cout << "Yes" << endl;
        else
            cout << "No" << endl;
    }
}
