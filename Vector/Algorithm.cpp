#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(void)
{
    vector<int> v;

    v.push_back(1);
    v.push_back(3);
    v.push_back(6);
    v.push_back(7);

    cout << binary_search(v.begin(), v.end(), 3); // 0,1
}
