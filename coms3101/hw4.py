from itertools import combinations, permutations

def los(string):
    """
    Problem 1: Returns the longest ordered substring in which the characters
    are strictly increasing.
    """
    count, max_count, start, end = 1, 1, 0, 0
    for i in range(len(string) - 1):
        if string[i] < string[i + 1]:
            count += 1
            if count > max_count:
                max_count = count
                start, end = i + 2 - count, i + 2
        else:
            count = 1
    if max_count == 1:
        return string[0]
    return string[start:end]

"""
Problem 2: Converts Celsius to Fahrenheit and vice versa. The Constraint class
takes variable names, coefficients, and a total for an equation, and it returns
the values for each variable once all but one of them are set.
"""

def c2f(temp):
    return temp * (9.0 / 5.0) + 32

def f2c(temp):
    return (temp - 32) * (5.0 / 9.0)

class Constraint:

    def __init__(self, names, coefficients, total):
        self.names = names.split()
        self.values = [None] * len(self.names)
        self.coefficients = coefficients
        self.total = total

    def findx(self):
        var_total = float(self.total)
        for i in range(len(self.values)):
            if self.values[i]:
                var_total -= self.coefficients[i] * self.values[i]
        return var_total / self.coefficients[self.values.index(None)]

    def setvar(self, var, value):
        if isinstance(var, int):
            ind = var
        elif isinstance(var, str):
            ind = self.names.index(var)
        self.values[ind] = float(value)
        if self.values.count(None) == 1:
            self.values[self.values.index(None)] = self.findx()
            print(self.values)
            self.values = [None] * len(self.names)

def mindot(v1, v2):
    """
    Problem 3: Calculates the minimum dot product for two vectors of equal
    length.
    """
    mindot = sum([a * b for a, b in zip(v1, v2)])
    for perm in permutations(v1):
        dot = sum([a * b for a, b in zip(perm, v2)])
        if dot < mindot:
            mindot = dot
    return mindot

def pickitems(prices, cash):
    """
    Problem 4: Returns a list of prices from a list of items that will spend
    an exact amount of cash.
    """
    valid_prices = [p for p in prices if p <= cash]
    for i in range(1, len(valid_prices) + 1):
        for c in combinations(valid_prices, i):
            if sum(c) == cash:
                return c

"""
Problem 5: The secure class creates a decorator that ensures that a username
and password are entered whenever a function is called underneath it.
"""

class secure:

    def __init__(self, f):
        self.f = f

    def __call__(self, user, pw, *pos, **kw):
        if not user in up:
            raise Exception('User %s not registered' % user)
        if pw != up[user]:
            raise Exception('Bad password')
        return self.f(*pos, **kw)