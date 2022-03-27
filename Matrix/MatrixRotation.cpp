#include <iostream>

using namespace std;

void helpTranspose(int **matrix, int N)
{
    for (int i = 0; i < N; i++)
        for (int j = i + 1; j < N; j++)
            if (i != j)
                swap(matrix[i][j], matrix[j][i]);
}

void helperReverse(int *row, int N)
{
    for (int i = 0; i < N / 2; i++)
        swap(row[i], row[N - i - 1]);
}

void rotate1(int **matrix, int N)
{
    helpTranspose(matrix, N);
    for (int i = 0; i < N; i++)
        helperReverse(matrix[i], N);
}

void rotate2(int **matrix, int N)
{
    for (int i = 0; i < N / 2; i++)
    {
        for (int j = i; j < N - i - 1; j++)
        {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][N - i - 1];
            matrix[j][N - i - 1] = matrix[N - i - 1][N - j - 1];
            matrix[N - i - 1][N - j - 1] = matrix[N - j - 1][i];
            matrix[N - j - 1][i] = temp;
        }
    }
}

void printMatrix(int **matrix, int N)
{
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
            cout << matrix[i][j] << " ";
        cout << endl;
    }
}

int main(void)
{
    int n;
    cout << "Enter N for N * N matrix: ";
    cin >> n;

    int **matrix = new int *[n];
    for (int i = 0; i < n; i++)
        matrix[i] = new int[n];

    cout << "\nEnter Matrix data:\n";

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> matrix[i][j];

    // printMatrix(matrix, n);

    cout << "\nRotated Matrix by 90 (clockwise):\n";
    rotate1(matrix, n);
    printMatrix(matrix, n);

    cout << "\nRotated Matrix by 90 (anti-clockwise):\n";
    rotate2(matrix, n);
    printMatrix(matrix, n);
}
