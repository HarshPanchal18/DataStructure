#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[])
{
    char *s;
    for (int i = 1; i < argc; i++)
    {
        if ((s = strrchr(argv[i], '.')))
            *s = '\0';
        puts(argv[i]);
    }

    return 0;
}
/*
.\rawName 1.c 4r.py
1
4r
*/
