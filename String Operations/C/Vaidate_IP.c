#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#define DELIMITER "."

int ValidDigit(char *ip)
{
    while (*ip)
    {
        if (*ip >= '0' && *ip <= '9')
            ++ip;
        else
            return 0;
    }
    return 1;
}

int ValidIP(char *ip)
{
    int no, dots = 0;
    char *ptr;

    if (ip == NULL)
        return 0;

    ptr = strtok(ip, DELIMITER);

    if (ptr == NULL)
        return 0;

    while (ptr)
    {
        if (!ValidDigit(ptr))
            return 0;

        no = atoi(ptr);

        if (no >= 0 && no <= 255)
        {
            ptr = strtok(NULL, DELIMITER);
            if (ptr != NULL)
                ++dots;
        }
        else
            return 0;
    }

    if (dots != 3)
        return 0;

    return 1;
}

int main(void)
{
    char ip1[] = "1.231.3.9";
    char ip2[] = "1.46.39.84";

    assert(ValidIP(ip1));
    assert(ValidIP(ip2));
}
