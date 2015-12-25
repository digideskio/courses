p = [0.2, 0.2, 0.2, 0.2, 0.2]
world = ['green', 'red', 'red', 'green', 'green']
measurements = ['red', 'red']
motions = [1, 1]
pHit = 0.6
pMiss = 0.2
pExact = 0.8
pOvershoot = 0.1
pUndershoot = 0.1

def sense(p, Z):
    q = []
    for i in range(len(p)):
        q.append(p[i] * pHit) if world[i] == Z else q.append(p[i] * pMiss)
    s = sum(q)
    for i in range(len(q)):
        q[i] /= s
    return q

def move(p, U):
    q = []
    for i in range(len(p)):
        s = pExact * p[(i - U) % len(p)]
        s = s + pOvershoot * p[(i - U - 1) % len(p)]
        s = s + pUndershoot * p[(i - U + 1) % len(p)]
        q.append(s)
    return q

for measure, motion in zip(measurements, motions):
    p = sense(p, measure)
    p = move(p, motion)

print p