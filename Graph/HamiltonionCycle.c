/*
A Hamiltonian cycle also called a Hamiltonian circuit, is a graph cycle (i.e., closed-loop) through a graph that visits each node exactly once.
Starting from vertex Vi, visit all vertices only once and return to the original vertex.
Write a program that prints the return path.
If the path does not exist, print "impossible".
*/

#include <stdio.h>

int w[6][6] = {
    0, 1, 0, 1, 1, 0,
    1, 0, 1, 1, 0, 0,
    0, 1, 0, 0, 0, 1,
    1, 1, 0, 0, 0, 0,
    1, 0, 0, 0, 0, 1,
    0, 0, 1, 0, 1, 0
    // adjacency matrix to store the graph
};

int path[6]; // path of visited node
int flag = 0;

int promising(int i)
{

    if (i == 5 && !w[path[i] - 1][path[0] - 1])
        return 0;

    else if (i > 0 && !w[path[i - 1] - 1][path[i] - 1])
        return 0;

    else if (i > 0)
        for (int j = 0; j < i; j++)
            if (path[i] == path[j])
                return 0;

    return 1;
}

void hamiltonian(int i)
{
    if (promising(i))
        if (i == 5) // Print when all vertices have been visited
        {
            for (int j = 0; j < 6; j++)
                printf("V%d->", path[j]);
            printf("V%d\n", path[0]);
            flag = 1;
        }
        else
            for (int j = 2; j < 6; j++) // j=1 is the starting vertex, so exclude
            {
                path[i + 1] = j;
                hamiltonian(i + 1);
            }
}

int main(void)
{
    path[0] = 1;
    hamiltonian(0);

    if (flag != 1)
        printf("Impossible");
}
