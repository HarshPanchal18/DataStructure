#include <iostream>
using namespace std;
int main(void)
{
    int Son = 95, Mom = 54;
    if (Son >= Mom)
    {
        try
        {
            throw(float) 99.7;
        }
        catch (float x)
        {
            cerr << "Error " << x << '\n';
        }
    }
}
