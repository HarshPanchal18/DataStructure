import java.util.Stack

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

fun main(args:Array<String>) {
    println(isValid("(]"))
    println(isValid0("()[]"))
}

fun isValid(s: String): Boolean {
    val bracketStack = Stack<Char>()
    for (i in s) {
        if (i == '(' || i == '[' || i == '{')
            bracketStack.push(i)
        else {
            if (bracketStack.empty()
                || bracketStack.peek() == '(' && i != ')'
                || bracketStack.peek() == '[' && i != ']'
                || bracketStack.peek() == '{' && i != '}'
            )
                return false
            bracketStack.pop()
        }
    }
    return bracketStack.empty()
}

fun isValid0(s:String): Boolean {
    var str = String()
    val bracketMap = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}'
    )
    str += s[0]
    for( i in 1 until s.length) {
        if(bracketMap[str.last()] == s[i]) {
            str.dropLast(1)
        } else {
            str+=s[i]
        }
    }

    return str.isEmpty()
}
