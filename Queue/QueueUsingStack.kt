/*
* Implement Queue using Stacks
Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:
void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.

Notes:
You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

Example 1:
Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]
Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, peek, and empty.
All the calls to pop and peek are valid.
*/

class MyQueue() {

    val inStack = Stack<Int>()
    val outStack = Stack<Int>()

    fun push(x: Int) {
        inStack.push(x)
    }

    fun pop(): Int {
        peek()
        val `val` = outStack.top()
        outStack.pop()
        return val
    }

    fun peek(): Int {
        if(outStack.isNotEmpty())
    }

    fun empty(): Boolean {

    }

}

 //Your MyQueue object will be instantiated and called as such:
fun main(args: Array<String>) {
    val x = 20
    var obj = MyQueue()
    obj.push(x)

    var param_2 = obj.pop()
    var param_3 = obj.peek()
    var param_4 = obj.empty()
}

class MyQueue() {
    val pushStack = Stack<Int>()
    val popStack = Stack<Int>()

    fun push(x: Int) {
        pushStack.push(x)
    }

    fun pop(): Int {
        peek()
        return popStack.pop()
    }

    fun peek(): Int {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.add(pushStack.pop())
            }
        }
        return popStack.peek()
    }

    fun empty(): Boolean {
        return pushStack.isEmpty() && popStack.isEmpty()
    }
}

class MyQueue(): ArrayList<Int>() {

    val stack = ArrayList<Int>()

    fun push(x: Int) {
        stack.add(x)
    }

    fun pop(): Int {
        if (size == 0) {
            while (stack.size != 0) {
                add(stack.removeLast())
            }
        }
        return removeAt(size - 1)
    }

    fun peek(): Int {
        if (size == 0) {
            while (stack.size != 0) {
                add(stack.removeLast())
            }
        }
        return elementAt(size - 1)
    }

    fun empty(): Boolean {
        return stack.size == 0 && size == 0
    }
}


class MyQueue() {
    val a = Stack<Int>()
    val b = Stack<Int>()

    fun push(x: Int) = a.push(x)

    fun pop() = peek().also { b.pop() }

    fun peek(): Int {
        if (b.size < 1) while (a.size > 0) b += a.pop()
        return b.peek()
    }

    fun empty() = a.size + b.size == 0
}