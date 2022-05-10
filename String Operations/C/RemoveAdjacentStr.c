#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to remove adjacent duplicates characters from a string
void removeDuplicates(char s[])
{
    int n = strlen(s);
    char prev = '\0';
    int k = 0;

    // loop through the string
    for (int i = 0; i < n; i++)
    {
        // if the current char is different from the previous char
        if (prev != s[i])
            // set distinct chars at index `k` and increment it
            s[k++] = s[i];

        // update previous char to current char for the next iteration of the loop
        prev = s[i];
    }

    // null terminate the resultant string
    s[k] = '\0';
}

char *rem(char *s, int n)
{
    int i;
    int k = 0; // to store index of result

    // start from second index
    for (i = 1; i < n; i++)
    {

        // if the adjacent characters are different then add to output string
        if (s[i - 1] == s[i])
            s[k++] = s[i - 1];

        else
            while (s[i - 1] == s[i]) // keep skipping characters while they are same
                i++;
    }

    // Add last character and terminate string
    s[k++] = s[i - 1];
    s[k] = '\0';

    if (k != n)
        rem(s, k); // Recur for string if there were some removed character

    else
        return s; // if all characters are unique
}

int main(void)
{
    char st[] = "AAABBCDDD";
    char str[20];

    printf("%s => ", st);
    removeDuplicates(st);
    printf("%s\n\n", st);

    gets(str);
    printf("%s => ", str);
    removeDuplicates(str);
    printf("%s", str);

    printf("\n%s", rem(str, strlen(str)));
}

/*
AAABBCDDD => ABCD

cheeeesseeee
cheeeesseeee => chese
*/
