#include <iostream>
#include <bits/stdc++.h>
using namespace std;

void binaryWatch(int &n)
{
    for (int hour = 0; hour < 12; hour++)
    {
        for (int min = 0; min < 60; min++)
        {
            bitset<8> h(hour);
            bitset<8> m(min);

            int setHourBit = h.count();
            int setMinBit = m.count();

            if (setHourBit + setMinBit == n)
            {
                char buffer[100];
                sprintf(buffer, "%d:%.02d", hour, min);
                cout << buffer << " ";
            }
        }
    }
}

int main(void)
{
    int n = 1;
    binaryWatch(n); //  0:01 0:02 0:04 0:08 0:16 0:32 1:00 2:00 4:00 8:00
}
