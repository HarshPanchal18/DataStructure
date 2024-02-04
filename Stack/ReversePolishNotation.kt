/*
* Evaluate Reverse Polish Notation
You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
Evaluate the expression. Return an integer that represents the value of the expression.

Note that:
The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.

Example 1:
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6

Example 3:
Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

Constraints:
1 <= tokens.length <= 104
tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
*/

fun evalRPN(tokens: Array<Int>): Int {

    val stack = ArrayDequeue<Int>()

    fun operation(op: (Int, Int) -> Int): Int {
        val a = stack.removeLast()
        val b = stack.removeLast()
        return op(a,b)
    }

    for(token in tokens) {
        stack += when (token) {
            "+" -> operation { a,b -> a+b}
            "-" -> operation { a,b -> a+b}
            "*" -> operation { a,b -> a+b}
            "/" -> operation { a,b -> a+b}
            else -> token.toInt()
        }
    }

    return stack.removeLast()
}

fun evalRPN(tokens:Array<Int>): Int {
    var stack = ArrayDeque<Int>()

    for(token in tokens) {
        when (token) {
            "+" -> {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack += b+a
            }
            "-" -> {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack += b-a
            }
            "*" -> {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack += b*a
            }
            "/" -> {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack += b/a
            }
            else -> stack += token.toInt()
        }
    }
    return stack.removeLast()
}

fun evalRPN(tokens: Array<String>): Int {
    val stack = LinkedList<String>()

    tokens.forEach {
        if (it.isOperator()) {
            val num1 = stack.pop()
            val num2 = stack.pop()

            when (it) {
                "+" -> stack.push((num1.toInt() + num2.toInt()).toString())
                "-" -> stack.push((num2.toInt() - num1.toInt()).toString())
                "/" -> stack.push((num2.toInt() / num1.toInt()).toString())
                "*" -> stack.push((num2.toInt() * num1.toInt()).toString())
            }

        } else
            stack.push(it)
    }
    return stack.pop().toInt()
}

fun String.isOperator(): Boolean {
    return this == "+" || this == "-" || this == "*" || this == "/"
}


fun resolves(a: Long, b: Long, Operator: Char): Long {
        return when (Operator) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            else -> a / b
        }
    }

fun evalRPN(tokens: Array<String>): Int {
    val stack = Stack<Long>()
    val n = tokens.size
    for (i in 0..<n) {
        if (tokens[i].length == 1 && tokens[i][0].code < 48) {
            val integer2 = stack.pop()
            val integer1 = stack.pop()
            val operator = tokens[i][0]
            val resolvedAns = resolves(integer1, integer2, operator)
            stack.push(resolvedAns)
        } else {
            stack.push(tokens[i].toLong())
        }
    }
    return stack.pop().toInt()
}


fun evalRPN(tokens: Array<String>): Int {
    val stack = IntArray(tokens.size)
    var top = 0
    for (s in tokens) {
        val c = s[0]
        if (c == '+') {
            val b = stack[--top]
            val a = stack[--top]
            stack[top++] = a + b
        } else if (c == '-' && s.length == 1) {
            val b = stack[--top]
            val a = stack[--top]
            stack[top++] = a - b
        } else if (c == '*') {
            val b = stack[--top]
            val a = stack[--top]
            stack[top++] = a * b
        } else if (c == '/') {
            val b = stack[--top]
            val a = stack[--top]
            stack[top++] = a / b
        } else stack[top++] = s.toInt()
    }
    return stack[0]
}


fun evalRPN(tokens: Array<String>): Int {
    val st = Stack<String>()

    for (str in tokens) {
        if (isOperator(str)) {
            var result = 0
            val ele2 = st.pop().toInt()
            val ele1 = st.pop().toInt()

            when (str) {
                "+" -> result = ele1 + ele2
                "-" -> result = ele1 - ele2
                "/" -> result = ele1 / ele2
                "*" -> result = ele1 * ele2
            }
            st.push(result.toString() + "")
        } else {
            st.push(str)
        }
    }
    return st.peek().toInt()
}

private fun isOperator(str: String): Boolean {
    return str == "+" || str == "-" || str == "*" || str == "/"
}