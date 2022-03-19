#include <iostream>

using namespace std;

void nulifyRow(int **matrix, int N, int row)
{
    for (int i = 0; i < N; i++)
        matrix[row][i] = 0;
}

void nulifyCol(int **matrix, int M, int col)
{
    for (int j = 0; j < M; j++)
        matrix[j][col] = 0;
}

void nulifyMatrix(int **matrix, int M, int N)
{
    bool firstrow = false;
    bool firstcol = false;

    // First Row
    for (int i = 0; i < N; i++)
        if (matrix[0][i] == 0)
        {
            firstrow = true;
            break;
        }

    // First Column
    for (int i = 0; i < M; i++)
        if (matrix[i][0] == 0)
        {
            firstcol = true;
            break;
        }

    // Rest of the matrix
    for (int i = 1; i < M; i++)
        for (int j = 1; j < N; j++)
            if (matrix[i][j] == 0)
            {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }

    // now we know which row and column to be nulify using information stored in previous step.
    // row first

    for (int i = 1; i < M; i++)
        if (matrix[i][0] == 0)
            nulifyRow(matrix, N, i);

    // Columns now
    for (int j = 1; j < N; j++)
        if (matrix[0][j] == 0)
            nulifyCol(matrix, M, j);

    // First Row
    if (firstrow)
        nulifyRow(matrix, N, 0);

    if (firstcol)
        nulifyCol(matrix, M, 0);
}

void printMatrix(int **matrix, int M, int N)
{
    for (int i = 0; i < M; i++)
    {
        for (int j = 0; j < N; j++)
            cout << matrix[i][j] << " ";
        cout << endl;
    }
    cout << endl;
}

int main(void)
{
    int M, N;

    cout << "Enter Row: ";
    cin >> M;

    cout << "Enter Column: ";
    cin >> N;

    int **matrix = new int *[M];

    for (int i = 0; i < M; i++)
        matrix[i] = new int[N];

    cout << "\nEnter Matrix data:\n";
    for (int i = 0; i < M; i++)
        for (int j = 0; j < N; j++)
            cin >> matrix[i][j];

    cout << "\nGiven Matrix:\n";
    printMatrix(matrix, M, N);

    nulifyMatrix(matrix, M, N);

    cout << "\nResultant Matrix:\n";
    printMatrix(matrix, M, N);
}

//If an element in MxN is 0, the entire row and column containing it are 0.
/*
Enter Row: 3
Enter Column: 3

Enter Matrix data:
1 5 9
5 6 0
7 1 4

Given Matrix:
1 5 9
5 6 0
7 1 4

Resultant Matrix:
1 5 0
0 0 0
7 1 0


Enter Row: 3
Enter Column: 3

Enter Matrix data:
5 9 7
9 2 6
1 0 0

Given Matrix:
5 9 7
9 2 6
1 0 0

Resultant Matrix:
5 0 0
9 0 0
0 0 0
*/
