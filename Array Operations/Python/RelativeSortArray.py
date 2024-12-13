# https://leetcode.com/problems/relative-sort-array/

from typing import List


class Solution:
    def __init__(self):
        pass

    def relativeSort(self, a: List[int], b: List[int]) -> List[int]:
        nums = {}
        len2 = len(b)

        if len2 == 0:
            return a

        for i in range(len2):
            nums[b[i]] = i

        a = sorted(a, key=lambda x: nums.get(x, len2+x))

        return a


a1 = [5, 9, 71, 25, 6, 49]
b1 = [25, 6]

p1 = Solution()

c1 = p1.relativeSort(a1, b1)
print(c1)

#[25, 6, 5, 9, 49, 71]
