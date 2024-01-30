class MyQueue
{
public:
    MyQueue() {}
    stack<int> inStk;
    stack<int> outStk;

    void push(int x)
    {
        inStk.push(x);
    }

    int pop()
    {
        peek();
        int val = outStk.top();
        outStk.pop();
        return val;
    }

    int peek()
    {
        if (outStk.empty())
            while (!inStk.empty())
            {
                outStk.push(inStk.top());
                inStk.pop();
            }
        return outStk.top();
    }

    bool empty()
    {
        return inStk.empty() && outStk.empty();
    }
};

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue* obj = new MyQueue();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->peek();
 * bool param_4 = obj->empty();
 */