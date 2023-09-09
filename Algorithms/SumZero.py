# Sum of two elements of sorted array is ZERO with 2 pointer technique - O(n)
arr = [-5, -4, -1, 0, 4, 5, 7, 9]

left = 0
right = len(arr) - 1

while left < right:
    sum = arr[left] + arr[right]
    if sum == 0:
        print("left:", arr[left])
        print("right:", arr[right])
        break
    elif sum < 0:
        left += 1
    else:
        right -= 1