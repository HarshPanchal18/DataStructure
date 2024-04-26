#include <process.h>
#include <dos.h>
#include <stdio.h>
#include <conio.h>
#include <dir.h>
#include <io.h>

int main()
{
    char name[10], c, ch;
    unsigned attrib;
    int d, n, f, p;

    printf("\t\tMAIN MENU OF PERMISSION\n\t--------------------------------\n");
    printf("1.Only Read\n2.Only Write\n3.Exit\nEnter your choice:\n");
    scanf("%d", &n);

    switch (n)
    {
    case 1:
        printf("\nEnter the File Name:\n");
        scanf("%s", name);
        attrib |= _A_RDONLY;
        printf("%s file  read permission Accepted", name);
        break;

    case 2:
        printf("\nEnter the File Name:\n");
        scanf("%s", name);
        attrib = _A_ARCH;
        printf("%s excute permission accepted", name);
        break;

    case 3:
        exit(0);
    }

    if (_dos_setfileattr(name, attrib) != 0)
    {
        perror("\nUnable to set");
        getch();
        return 0;
    }

    getch();
    return 0;
}
