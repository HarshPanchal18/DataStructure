#include <iostream>
#include <conio.h>

using namespace std;

class A
{
public:
    A()
    {
        cout << "A" << endl;
    }
};

class B : public A
{
public:
    B()
    {
        //::A();
        cout << "B" << endl;
    }
};

class Rectangle
{
    int length;
    int width;

public:
    Rectangle() {}
    Rectangle(int l, int w)
    {
        length = l;
        width = w;
    }

    Rectangle(Rectangle &rect)
    {
        length = rect.length;
        width = rect.width;
    }
};

int main()
{
    B b;

    Rectangle r1(20, 60);
    cout << r1.length << " " << r2.width << endl;

    Rectangle r2(r1);
}