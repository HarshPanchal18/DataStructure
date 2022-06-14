#include <stdio.h>
#include <string.h>

char *compress(char myStr[])
{
    char *s, *in;
    for (s = myStr, in = myStr; *s; s++)
    {
        int count = 1;
        in[0] = s[0];
        in++;
        while (s[0] == s[1])
        {
            count++;
            s++;
        }
        if (count > 1)
        {
            // method1
            //  int len = sprintf(in, "%d", count);
            //  in += len;

            // method2
            in[0] = '0' + count;
            in++;
        }
    }

    in[0] = 0;
    return myStr;
}

char *compress2(char myStr[])
{
    char *s = myStr;
    char *r, *p;
    int count, i;

    while (*s)
    {
        /*initially only 1 character of a kind is present*/
        count = 1;

        /*we check whether current character matches the next one*/
        while (*s && *s == *(s + 1))
        {
            /*if yes,then increase the count due to the match
            and increment the string pointer to next */
            count++;
            s++;
        }

        if (count > 1) /*if more than one character of a kind is present*/
        {
            /*assign the value of count to second occurence of a particular character*/
            *(s - count + 2) = count + '0';

            /*delete all other occurences except the first one and second one using array shift*/
            for (i = 0; i < count - 2; i++)
            {
                p = s + 1;
                r = s;

                while (*r)
                    *r++ = *p++;

                s--;
            }
        }
        s++;
    }

    return myStr;
}

int main(void)
{
    char str[] = "aaaaaabbcccccdd";
    printf("Compressed: %s\n", compress(str));
    printf("Compressed: %s\n", compress2(str));
}

/*
Compressed: a6b2c5d2
Compressed: a6b2c5d2
*/
