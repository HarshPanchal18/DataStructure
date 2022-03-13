#include <bits/stdc++.h>
//#include <unordered_map>
//#include <unordered_set>

using namespace std;

/*
N = 4, M = 5 matrix[][] =
           {
               {1 0 0 1 0},
               {0 0 1 0 1},  (1,2)--(1,4)
               {0 0 0 1 0},    |      |
               {1 0 1 0 1}}  (3,2)--(3,4)

       Output : Yes */

class Solution
{
public:
    bool ValidCorner(const vector<vector<int>> &matrix)
    {
        int row = matrix.size();
        int col = matrix[0].size();

        for (int i = 0; i < row; i++)
        {
            for (int j = i + 1; j < row; j++)
            {
                int count = 0;
                for (int k = 0; k < col; k++)
                {
                    if (matrix[i][j] == 1 && matrix[j][k] == 1)
                        count++;

                    if (count == 2)
                        return true;
                }
            }
        }
        return false;
    }
};

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int row, col;
        cin >> row >> col;

        vector<vector<int>> matrix(row);

        for (int i = 0; i < row; i++)
        {
            matrix[i].assign(col, 0);
            for (int j = 0; j < col; j++)
            {
                cin >> matrix[i][j];
            }
        }
        Solution ob;
        if (ob.ValidCorner(matrix))
            cout << "Yes\n";
        else
            cout << "No\n";
    }

    return 0;
}
