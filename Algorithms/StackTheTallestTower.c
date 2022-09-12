/*
Build the tallest tower.
I want to build a tower using cuboid bricks with a square base.
The tower is built by stacking bricks one by one from the bottom up.
Implement a method to build the tallest tower while satisfying the following conditions.

1) Bricks cannot be rotated. That is, the side cannot be used as the base.
2) There are no star stones with the same base area, and no bricks with the same weight.
3) The height of the bricks may be the same.
4) When building a tower, a brick with a wide base cannot be placed on top of a brick with a narrow base.
5) Heavy bricks cannot be placed on top of lighter weight bricks.

The first line of input gives the number of bricks to be entered.
The maximum number of bricks given as input is 100.
From the second row, the area, height, and weight of the bottom of the brick,
which are information about one brick in each row, are given in turn as positive integers.
The first line of output prints the number of bricks used. From the second line onwards,
from the top of the tower to the bottom of the bricks are printed sequentially.
*/

#include <stdio.h>

int number[5] = {1, 2, 3, 4, 5};  // brick number
int width[5] = {25, 4, 9, 16, 1}; // area
int height[5] = {3, 4, 2, 2, 5};
int weight[5] = {4, 6, 3, 5, 2};
int block[5];   // Brick number from ith to the bottom
int highest[5]; // highest height at i

void sort() // bubble Sort
{
    int temp = 0, flag = 1;

    for (int i = 4; i > 0 && flag == 1; i--)
    {
        flag = 0;
        for (int j = 1; j <= i; j++)
        {
            if (width[j - 1] < width[j])
            {
                temp = number[j];
                number[j] = number[j - 1];
                number[j - 1] = temp;

                temp = width[j];
                width[j] = width[j - 1];
                width[j - 1] = temp;

                temp = height[j];
                height[j] = height[j - 1];
                height[j - 1] = temp;

                temp = weight[j];
                weight[j] = weight[j - 1];
                weight[j - 1] = temp;

                flag = 1;
            }
        }
    }
}

void dynamic()
{
    int i = 0, j = 0;
    for (i = 0; i < 5; i++)
    {
        highest[i] = height[i];
        block[i] = -1; // nothing below
    }
    for (i = 1; i < 5; i++)
    {
        for (j = 0; j < i - 1; j++)
        {
            if (weight[i] < weight[j])
            { // It must be heavy so it can be placed under it.
                if (highest[i] < highest[j] + height[i])
                {
                    highest[i] = highest[j] + height[i];
                    block[i] = j;
                }
            }
        }
    }
}

void printRes()
{
    int max = 0;
    int num, path[5];

    for (int i = 0; i < 5; i++) // find the highest height
        if (max < highest[i])
        {
            max = highest[i];
            num = i;
        }

    int count = 0;
    path[count++] = number[num];

    while (block[num] != -1)
    {
        path[count] = number[block[num]];
        count++;
        num = block[num];
    }

    path[count] = -1;

    printf("\nNumbers of brick staked: %d", count);
    for (int i = 0; path[i] != -1; i++)
        printf("\nOrder of brick staked %d : %d", i, path[i]);
}

int main(void)
{
    sort();
    dynamic();
    printRes();
}
