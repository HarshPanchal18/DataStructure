#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// * Simulate file organization techniques.

struct
{
    char dname[10], fname[10][10];
    int fileCount;
} dir[10];

void main()
{
    int dirCount = 0, choice;
    int i;
    char d[10], f[10];

    while (1)
    {
        printf("\n1. Create Directory\n2. Create File\n3. Delete File");
        printf("\n4. Search File\n5. Display\n6. Exit\nEnter your choice: ");
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            printf("\nEnter Directory Name: ");
            scanf("%s", dir[dirCount].dname);

            dir[dirCount].fileCount = 0;
            dirCount++;
            break;

        case 2:
            printf("\nEnter Directory Name: ");
            scanf("%s", d);

            for (i = 0; i < dirCount; i++)
                if (strcmp(d, dir[i].dname) == 0)
                {
                    printf("\nEnter File Name: ");
                    scanf("%s", dir[i].fname[dir[i].fileCount]);

                    dir[i].fileCount++;
                    break;
                }

            if (i == dirCount)
                printf("\nDirectory %s not found", d);
            break;

        case 3:
            printf("\nEnter name of the Directoy: ");
            scanf("%s", d);

            for (i = 0; i < dirCount; i++)
            {
                if (strcmp(d, dir[i].dname) == 0)
                {
                    printf("\nEnter File Name: ");
                    scanf("%s", f);

                    for (int k = 0; k < dir[i].fileCount; k++)
                        if (strcmp(f, dir[i].fname[k]) == 0)
                        {
                            printf("File %s Deleted", f);
                            dir[i].fileCount--;
                            strcpy(dir[i].fname[k], dir[i].fname[dir[i].fileCount]);
                            goto jmp;
                        }
                    printf("File %s not found", f);
                    goto jmp;
                }
            }
            printf("Directory %s not found", d);
        jmp:
            break;

        case 4:
            printf("\nEnter name of the Directoy: ");
            scanf("%s", d);

            for (i = 0; i < dirCount; i++)
            {
                if (strcmp(d, dir[i].dname) == 0)
                {
                    printf("\nEnter File Name: ");
                    scanf("%s", f);

                    for (int k = 0; k < dir[i].fileCount; k++)
                        if (strcmp(f, dir[i].fname[k]) == 0)
                        {
                            printf("File %s is found ", f);
                            goto jmp1;
                        }

                    printf("File %s not found", f);
                    goto jmp1;
                }
            }
            printf("Directory %s not found", d);
        jmp1:
            break;

        case 5:
            if (dirCount == 0)
                printf("\nNo Directories");

            else
            {
                printf("\nDirectories\tFiles\n");
                for (int i = 0; i < dirCount; i++)
                {
                    printf("\n%s\t\t", dir[i].dname);

                    for (int k = 0; k < dir[i].fileCount; k++)
                        printf("%s\n\t\t", dir[i].fname[k]);
                }
            }
            break;
        default:
            exit(0);
        }
    }
}
