def decimals(n):
    """
    Problem 1a: Calculates decimal digits from fraction using long division,
    returning each digit through a generator.
    """
    digit, remainder = divmod(10, n)
    yield(digit)
    while remainder:
        digit, remainder = divmod(remainder * 10, n)
        yield(digit)

def genlimit(g, limit):
    """
    Problem 1b: Generates, at most, a given number of digits from a generator.
    """
    for i, digit in enumerate(g):
        yield(digit)
        if i == limit: break

def decimals2(n):
    """
    Problem 2: Generates decimal expansion, with a list of the repeating
    sequence of digits for any repeating decimals.
    """
    digit, remainder = divmod(1, n)
    decimal = []
    remainders = {}
    i = 0
    while remainder:
        if remainder in remainders:
            yield(decimal[remainders[remainder]:])
            break
        remainders[remainder] = i
        digit, remainder = divmod(remainder * 10, n)
        decimal.append(digit)
        yield(digit)
        i += 1

def select(l, selectors):
    """
    Problem 3a: Returns a new list that consists of the elements of l that have
    a true value in the corresponding element in selectors.
    """
    elements = []
    for el, selector in zip(l, selectors):
        if selector:
            elements.append(el)
    return elements

def nbitIntToDigits(i, n):
    """
    Problem 3b: Returns a list of the digits in a binary representation of i,
    with padding for zeroes on the left to reach a length of n.
    """
    binary = bin(i)[2:]
    padding = [0] * (n - len(binary))
    return padding + list(map(int, binary))

def powerSet(l):
    """
    Problem 3c: Returns all possible subsets of l.
    """
    powerset = [[]]
    for el in l:
        powerset.extend([set + [el] for set in powerset])
    return powerset

def oil(path):
    """
    Problem 4: Summarizes a document of government data on oil consumption.
    """
    with open(path, 'r') as f:
        for _ in range(7):
            next(f)
        for _ in range(7, 301, 14):
            year_one, year_two = next(f).split()
            summary = next(f).split()
            quantity_one = int(summary[1].replace(',', ''))
            quantity_two = int(summary[8].replace(',', ''))
            prices_one, prices_two = [], []
            for _ in range(12):
                line = next(f).split()
                prices_one.append(float(line[5]))
                prices_two.append(float(line[12]))
            (yield("%s: quan: total=%d prices: max = %f min=%f avg = %f" %
                (year_one, quantity_one, max(prices_one), min(prices_one),
                sum(prices_one) / len(prices_one))))
            (yield("%s: quan: total=%d prices: max = %f min=%f avg = %f" %
                (year_two, quantity_two, max(prices_two), min(prices_two),
                sum(prices_two) / len(prices_two))))

def countBases(dna):
    """
    Problem 5a: Returns the number of each base in a DNA strand.
    """
    return [dna.count('A'), dna.count('C'), dna.count('G'), dna.count('T')]

def reverseComplement(dna):
    """
    Problem 5b: Swaps A and T and C and G, then returns the DNA string in
    reverse order.
    """
    reversed_dna = ""
    for base in dna:
        if base == 'A': reversed_dna += 'T'
        elif base == 'T': reversed_dna += 'A'
        elif base == 'C': reversed_dna += 'G'
        elif base == 'G': reversed_dna += 'C'
    return reversed_dna[::-1]