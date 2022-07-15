#include <iostream>
#include <queue>

using namespace std;
int main(void)
{
    // MaxHeap
    priority_queue<int> maxi;

    // MinHeap
    priority_queue<int, vector<int>, greater<int>> mini;

    maxi.push(6);
    maxi.push(8);
    maxi.push(1);
    maxi.push(2);

    cout << "Size of PrioQueue: " << maxi.size() << endl;

    int n = maxi.size(); // Store the size to N, bcoz size is changing on every iteration

    for (int i = 0; i < n; i++)
    {
        cout << maxi.top() << " ";
        maxi.pop();
    }
    cout << endl;

    mini.push(6);
    mini.push(8);
    mini.push(1);
    mini.push(2);

    cout << "Size of PrioQueue: " << mini.size() << endl;

    n = mini.size(); // Store the size to N, bcoz size is changing on every iteration

    for (int i = 0; i < n; i++)
    {
        cout << mini.top() << " ";
        mini.pop();
    }
}
