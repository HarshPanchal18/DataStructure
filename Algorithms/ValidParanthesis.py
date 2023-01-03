def validPara(expr):
    openBrace = ["{", "(", "["]
    closeBrace = ["}", ")", "]"]
    stack = []

    for i in expr:
        if i in openBrace:
            stack.append(i)
        elif i in closeBrace:
            p = closeBrace.index(i)

            if len(stack) > 0 and openBrace[p] == stack[len(stack)-1]:
                stack.pop()
            else:
                return False

    if len(stack) == 0:
        return True
    else:
        return False


s = input("Enter the expression: ")

if(validPara(s)):
    print("Balanced")
else:
    print("Unbalanced")

'''
Enter the expression: a+b(c*d)
Balanced

Enter the expression: a+b)*c+d(
Unbalanced
'''
