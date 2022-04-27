#include <stdio.h>

int painters(int *a, int maxtime, int len, int num)
{
    int time = 0, count = 1;
    for (int i = 0; i < len; i++)
    {
        time += a[i];
        if (time > maxtime)
        {
            time = a[i];
            count++;

            if (count > num)
                return 0;
        }
    }
    return 1;
}

int getMax(int arr[], int len)
{
    int max = arr[0];

    for (int i = 1; i < len; i++)
        if (max < arr[i])
            max = arr[i];

    return max;
}

int main(void)
{
    int time, len, num, sum = 0;

    printf("\nEnter the length of the array, number of painters and time: ");
    scanf("%d%d%d", &len, &num, &time);

    int arr[len];

    printf("\nEnter the array elements:\n");
    for (int i = 0; i < len; i++)
    {
        scanf("%d", &arr[i]);
        sum += arr[i];
    }

    // finding the max
    int start = getMax(arr, len);
    int end = sum;
    int ans;

    while (start <= end)
    {
        int mid = (start + end) / 2;
        if (painters(arr, mid, len, num))
        {
            ans = mid;
            end = mid - 1;
        }
        else
            start = mid + 1;
    }
    ans *= time;
    printf("\nTime taken is: %d", ans);
}
/*
Enter the length of the array, number of painters and time: 5 9 2

Enter the array elements:
7 4 2 6 9

Time taken is: 18
*/
