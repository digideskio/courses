import os
import numpy as np
from collections import defaultdict

def loadFileProps(f):
    with open(f, 'r') as file:
        props = defaultdict(dict)
        for line in file:
            prop, val = line.strip().split('=')
            try:
                val = float(val)
            except ValueError:
                val = val[1:-1]
            props[prop] = val
    return props

class stars:

    def __init__(self):
        self.data = {}
        for root, dirs, files in os.walk('stars'):
            for d in dirs:
                self.data[d] = {}
            for f in files:
                path = os.path.join(root, f)
                self.data[path.split('/')[1]][f] = loadFileProps(path)

    def overlap(self):
        near, bright = set(self.data['near']), set(self.data['bright'])
        return near.intersection(bright)

    def starsWithPropertyValue(self, property, value):
        matches = {}
        for star_type, stars in self.data.items():
            for star, props in stars.items():
                if props[property] == value:
                    matches[star] = star_type
        return matches

    def starMeanStd(self, categoryName, property):
        values = []
        for star, props in self.data[categoryName].items():
            values.append(props[property])
        return [np.average(values), np.std(values)]

def sumTriangle(path):
    f = open(path, 'r')
    triangle = [[int(x) for x in line.split()] for line in f]
    for i in range(len(triangle) - 2, -1, -1):
        for j in range(i + 1):
            triangle[i][j] += max([triangle[i + 1][j], triangle[i + 1][j + 1]])
    return triangle[0][0]

def isPrime(n):
    if n < 2: return False
    elif n == 2: return True
    for div in range(2, int(n ** 0.5) + 1):
        if n % div == 0: return False
    return True

def genPrimes():
    n = 2
    while True:
        if isPrime(n): yield n
        n += 1

def differences(nums):
    diffs = []
    for i in range(len(nums) - 1):
        diffs.append(nums[i + 1] - nums[i])
    return diffs

def findTriples():
    g = genPrimes()
    prime, four_digit_primes = next(g), set()
    while len(str(prime)) < 5:
        prime = next(g)
        if len(str(prime)) == 4:
            four_digit_primes.add(prime)
    primes = {}
    while four_digit_primes:
        prime, perms = four_digit_primes.pop(), []
        for perm in permutations(str(prime)):
            perm = int(''.join(map(str, perm)))
            if perm in four_digit_primes:
                perms.append(perm)
                four_digit_primes.remove(perm)
        primes[prime] = perms
        if len(s) == 3:
            primes[s] = [differences(list(c)) for c in combinations(s, 3)]
    triples = []
    for key, val in primes.items():
        for diff in val:
            if len(set(val)) == 1:
                triples.append(key)
                break
    return triples