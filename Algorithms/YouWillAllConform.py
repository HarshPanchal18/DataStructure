caps = ['F', 'F', 'B', 'B', 'B', 'F', 'B', 'B', 'B', 'F', 'F', 'B', 'B']
caps2 = ['F', 'F', 'B', 'B', 'B', 'F', 'B', 'B', 'B', 'F', 'F', 'F', 'F']


def pleaseConform(caps):
    start = 0
    forward = 0
    backward = 0
    intervals = []

    caps = caps+['END']

    # determine intervals where caps are on in the same direction
    for i in range(1, len(caps)):
        if caps[start] != caps[i]:
            intervals.append((start, i-1, caps[start]))

            if caps[start] == 'F':
                forward += 1
            else:
                backward += 1
            start = i

    # for forwards
    #intervals.append((start, len(caps) - 1, caps[start]))
    # if caps[start] == 'F':
    #    forward += 1
    # else:
    #    backward += 1

    if forward < backward:
        flip = 'F'
    else:
        flip = 'B'

    for t in intervals:
        if t[2] == flip:
            print('People in positions ',
                  t[0], ' through ', t[1], ' flip your cap')


def pleaseConformOnePass(caps):
    caps = caps+[caps[0]]

    for i in range(1, len(caps)):
        if caps[i] != caps[i-1]:
            if caps[i] != caps[0]:
                print('People in positions ', i, end='')
            else:
                print(' through ', i-1, ' flip your caps!')


pleaseConform(caps)
print()
pleaseConformOnePass(caps)
print()
pleaseConform(caps2)
print()
pleaseConformOnePass(caps2)
