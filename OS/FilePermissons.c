#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <dir.h>
#include <dos.h>

int list_file()
{
    int l;
    char e[] = "*.*";

    printf("\t\tLIST FILE DETAIL\n\t--------------------------------------\n");

    printf("1.List All Files\n2.List of Extention Files\n3.List of Name Wise\n");
    scanf("%d", &l);

    switch (l)
    {
    case 1:
        printf("List of All(*.*) Files\n");
        subfun(e);
        break;

    case 2:
        printf("Enter the Extention:");
        scanf("%s", &e);
        subfun(e);
        break;

    case 3:
        printf("Enter the Name wise(eg:moha*.*):");
        scanf("%s", &e);
        subfun(e);
        break;
    }

    return 0;
}

int directory()
{
    struct ffblk ffblk;
    unsigned attrib;
    int d;
    char name[10], buffer[MAXPATH];
    printf("Enter the Directory name:");
    scanf("%s", &name);
    getcwd(buffer, MAXPATH);
    printf("Current directory:%s\n", buffer);
    if (_dos_getfileattr(name, &attrib) == 0)
    {
        printf("%s has already available", name);
        return 0;
    }
    else
    {
        mkdir(name);
        printf("%s Directory Successfully Created\n", name);
    }
    return 0;
}

int change_dir()
{
    char buffer[MAXPATH];
    int d, d1;
    printf("\nCurrent Directory:%s\n", getcwd(buffer, MAXPATH));
    printf("\t\tChange Directory\n\t\t-----------------\n");
    printf("\n1.Step by Step Backward\n2.Goto Root Directory\n3.Forward Directory \nEnter the number:");
    scanf("%d", &d);
    switch (d)
    {
    case 1:
        chdir("..");
        break;
    case 2:
        chdir("\\");
        break;
    case 3:
        printf("Please enter the Filename:");
        scanf("%s", buffer);
        chdir(buffer);
        break;
    }
    printf("\nCurrent Directory:%s", getcwd(buffer, MAXPATH));
    return 0;
}

char s[10];
int subfun(s)
{
    struct ffblk ffblk;
    int d, p = 0, i = 0;
    d = findfirst(s, &ffblk, 0);
    while (!d)
    {
        printf("%s\n", ffblk.ff_name);
        d = findnext(&ffblk);
        i++;
        p = p + 1;
        if (p >= 22)
        {
            printf("Press any key to continue:\n");
            getch();
            p = 0;
        }
    }
    printf("\nTotal File:%d", i);
    return 0;
}

int main()
{
    int ch;

    do
    {
        printf("\n\t\t\tMAIN MENU\n\t\t-------------------------\n");
        printf("1.To Display List of Files\n");
        printf("2.To Create New Directory\n");
        printf("3.To Change the Working Directory\n");
        printf("4.Exit\n");

        printf("Enter the Number:");
        scanf("%d", &ch);

        switch (ch)
        {
        case 1:
            list_file();
            break;

        case 2:
            directory();
            break;

        case 3:
            change_dir();
            break;

        case 4:
            exit(0);
        }

    } while (ch <= 4);
}
