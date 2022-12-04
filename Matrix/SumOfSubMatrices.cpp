#include <iostream>
#include <vector>

using namespace std;

int sumAllMatrices(vector<vector<int>> grid)
{
    int n = grid.size();
    int m = grid[0].size();
    int sum = 0;

    vector<vector<int>> prefix(n, vector<int>(m));

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (j == 0)
                prefix[i][j] = grid[i][j];
            else
                prefix[i][j] = prefix[i][j - 1] + grid[i][j];

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (i != 0)
                prefix[i][j] += prefix[i - 1][j];

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            for (int tl = i; tl < n; tl++)
                for (int br = j; br < m; br++)
                {
                    sum += (prefix[tl][br]);

                    if (i > 0 && j > 0)
                        sum = sum - (prefix[tl][j - 1] + prefix[i - 1][br]) + prefix[i - 1][j - 1];
                    else if (i > 0)
                        sum = sum - prefix[i - 1][br];
                    else
                        sum = sum - prefix[tl][j - 1];
                }

    return sum;
}

int main(void)
{
    int m, n;
    cout << "Enter the no. of rows and columns : ";
    cin >> m >> n;

    vector<vector<int>> grid(m, vector<int>(n));

    cout << "Enter the elements of the matrix : " << endl;
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            cin >> grid[i][j];

    int sum = sumAllMatrices(grid);

    cout << "Sum of all submatrices: " << sum;
}
