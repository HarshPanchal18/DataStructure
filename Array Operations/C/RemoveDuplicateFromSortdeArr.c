#include <stdio.h>
#include <stdlib.h>

static int removeDup(int *nums, int numSize)
{
    if (numSize < 1)
        return numSize;

    int count = 1;
    for (int i = 1; i < numSize; i++)
        if (nums[i - 1] != nums[i])
            nums[count++] = nums[i];

    return count;
}

int main(int argc, char **argv)
{
    int size = argc - 1;
    int *nums = malloc(size * sizeof(int));
    for (int i = 0; i < argc - 1; i++)
        nums[i] = atoi(argv[i + 1]);

    int count = removeDup(nums, size);
    for (int i = 0; i < count; i++)
        printf("%d ", nums[i]);

    printf("\n");
    return 0;
}
