# @param A : tuple of integers
# @return an integer
def majorityElement(A):
    counter = 0
    element = 0

    for num in A:
        if counter == 0:
            element = num
            count = 1
        elif element == num:
            count = count + 1
        else:
            count = count - 1

    return element

print(majorityElement([3,2,3]))