/*
 ============================================================================
 Name        : string.c
 Author      :
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int length(char str[20])
{
    int i = 0;
    while (str[i] != '\0')
        i++;
    return i;
}

void copy(char str[20])
{
    int k;
    char tar[20];
    printf("\nEnter the string to be copied: ");
    gets(tar);

    k = length(tar);
    for (int i = 0; i < k; i++)
        str[i] = tar[i];
    printf("%s", str);
}

void compare(char str[20])
{
    int l = length(str);
    int i = 0;
    char tar[20];

    printf("Enter the string to be compared: ");
    scanf("%s", &tar);

    int k = length(tar);
    if (l == k)
    {
        for (i = 0; i < l; i++)
            if (str[i] != tar[i])
            {
                printf("\nTwo strings are not equal!!\n");
                return;
            }

        if (i == l)
            printf("\nTwo strings are equal\n");
    }
}

void reverse(char str[20])
{
    int l = length(str);
    int k = l;
    char tar[20];

    for (int i = 0; i < l; i++)
        tar[k - i - 1] = str[i];

    tar[l] = '\0';
    printf("\n\n%s", tar);
}

void palindrome(char str[20])
{
    int l = length(str);
    int k = l, i;
    char tar[20];

    for (i = 0; i < l; i++)
        tar[k - i - 1] = str[i];

    tar[l] = '\0';

    for (i = 0; i < l; i++)
        if (str[i] != tar[i])
            break;

    if (i == l)
        printf("\'%s\' is Palindrome\n", str);
    else
        printf("\'%s\' is not Palindrome\n", str);
}

void substring(char str[20])
{
    int l = length(str);
    int i, j;
    char sub[20];

    printf("Enter the Substring: ");
    gets(sub);

    int k = length(sub);

    for (i = 0; i < l; i++)
        if (sub[0] == str[i])
            break;

    for (j = 0; j < k; j++)
        if (sub[j] != str[j + i])
            break;

    if (j == k)
        printf("\'%s\' is substring. \n", str);
    else
        printf("\'%s\' not is substring. \n", str);
}

int main()
{
    char str[20];
    int f = 1, t;
    printf("Enter the String :");
    gets(str);

    while (1)
    {
        printf("\n\n 1.String Length \n 2.Palindrome \n 3.String Comparison \n 4.Copy \n 5.Reverse \n 6.Substring \n 7.Exit");
        printf("\n Enter the choice: ");
        scanf("%d", &t);
        switch (t)
        {
        case 1:
            printf("\nString Length is :");
            int k = length(str);
            printf("%d\n", k);
            break;

        case 2:
            palindrome(str);
            break;

        case 3:
            compare(str);
            break;

        case 4:
            copy(str);
            break;

        case 5:
            reverse(str);
            break;

        case 6:
            substring(str);
            break;

        case 7:
            exit(0);
            f = 0;
            break;
        }
    }

    return 0;
}
