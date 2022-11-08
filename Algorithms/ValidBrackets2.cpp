#include <iostream>
#include <stack>
#include <map>
using namespace std;

/*
* Valid Parentheses

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false
*/

class Solution
{
public:
    bool isValid(string s)
    {
        stack<char> st;
        for (int i = 0; i < s.length(); i++)
        {
            if (st.empty())
                st.push(s[i]);
            else if (s[i] == ')')
            {
                if (st.top() != '(')
                    return false;
                else
                    st.pop();
            }
            else if (s[i] == '}')
            {
                if (st.top() != '{')
                    return false;
                else
                    st.pop();
            }
            else if (s[i] == ']')
            {
                if (st.top() != '[')
                    return false;
                else
                    st.pop();
            }
            else
                st.push(s[i]);
        }
        if (st.empty())
            return true;
        return false;
    }
};

class Solution
{
public:
    bool isValid(string s)
    {
        stack<char> st;  // for keep tracking the order of the brackets..
        for (auto i : s) // iterate over each and every elements
        {
            if (i == '(' or i == '{' or i == '[')
                st.push(i); // if current element of the string will be opening bracket then we will just simply push it into the stack
            else            // if control comes to else part, it means that current element is a closing bracket,
                            // so check two conditions  current element matches with top of the stack and the stack must not be empty...
            {
                if (st.empty() or
                    (st.top() == '(' and i != ')') or
                    (st.top() == '{' and i != '}') or
                    (st.top() == '[' and i != ']'))
                    return false;
                st.pop(); // if control reaches to that line, it means we have got the right pair of brackets, so just pop it.
            }
        }
        return st.empty(); // at last, it may possible that we left something into the stack unpair so return checking stack is empty or not..
    }
};

class Solution
{
public:
    bool isValid(string s)
    {
        string st;
        map<char, char> mp;
        mp['('] = ')';
        mp['{'] = '}';
        mp['['] = ']';
        st.push_back(s[0]);

        for (int i = 1; i < s.size(); i++)
        {
            if (mp[st.back()] == s[i])
                st.pop_back();
            else
                st.push_back(s[i]);
        }
        return st.size() ? 0 : 1;
    }
};
