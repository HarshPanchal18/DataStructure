// https://www.spoj.com/problems/BUSYMAN/

/*
 * You are actually very busy man.
 * You have a big schedule of activities.
 * Your aim is to do as much as activities as possible.
 */

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

bool compare(pair<int, int> p1, pair<int, int> p2)
{
    return p1.second < p2.second;
}

int main()
{
    int n, s, e;
    vector<pair<int, int>> v;

    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> s >> e;
        v.push_back(make_pair(s, e));
    }

    // activity selection algorithm
    // sort
    sort(v.begin(), v.end(), compare);

    // start picking activities
    int res = 1;
    int fin = v[0].second;

    // iterate over remaining activites
    for (int i = 1; i < n; i++)
    {
        if (v[i].first >= fin)
        {
            fin = v[i].second;
            res++;
        }
    }
    cout << res << "\n";
    v.clear();
}

/*
4
1 7
5 8
7 8
1 8

2
*/
