# Complete the solve function below.
def solve(s):
    word = s.split(' ')
    for i in range(len(word)):
        word[i] = word[i].capitalize()
    return " ".join(word)

if __name__ == '__main__':
    s = input()
    print(solve(s))