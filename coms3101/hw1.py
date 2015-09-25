from itertools import groupby

def dp(v1, v2):
    """
    Problem 1a: Computes dot products for two vectors, ignoring extra elements
    on the right of the longer one.
    """
    length = min(len(v1), len(v2))
    return sum(a * b for a, b in zip(v1[:length], v2[:length]))

def shortlong(v1, v2):
    """
    Problem 1b: Takes two vectors and returns the short vector, the short
    vector length, the long vector, and the long vector length in a list.
    """
    if len(v1) > len(v2):
        return [v2, len(v2), v1, len(v1)]
    else:
        return [v1, len(v1), v2, len(v2)]

def odp(v1, v2, offset):
    """
    Problem 1c: Computes dot products for two vectors with a flexible offset
    that can move the short vector to the right.
    """
    short, s_length, long, l_length = shortlong(v1, v2)
    return sum(a * b for a, b in zip(short, long[offset:offset + s_length]))

def pdp(v1, v2, pad):
    """
    Problem 1d: Computes dot products for two vectors with a pad value that
    pads the short vector on the right.
    """
    short, s_length, long, l_length = shortlong(v1, v2)
    padded_short = short + [pad] * (l_length - s_length)
    return sum(a * b for a, b in zip(padded_short, long))

def rle(numbers):
    """
    Problem 2: Converts a list into a list of values and number of times each
    value is present in the list.
    """
    return [[n, sum(1 for _ in run)] for n, run in groupby(numbers)]

def partition(l, n, overlap=0):
    """
    Problem 3: Divides a list into segments, with a defined segment length and
    an option to set a length of overlap between adjacent segments.
    """
    return [l[i - n:i] for i in range(n, len(l) + 1, n - overlap)]

def expandlazy(value):
    """
    Problem 4a: Expands lazy values (range, zip, enumerate) to lists when
    given, while non-lazy values are returned unchanged.
    """
    return list(value) if type(value) in [enumerate, range, zip] else value

def expandlazylist(l):
    """
    Problem 4b: Expands lazy elements (range, zip, enumerate) in a list.
    """
    return [expandlazy(el) for el in l]

def flatten(l):
    """
    Problem 5: Flattens a nested list into a linear one.
    """
    if isinstance(l, list):
        return [n for el in l for n in flatten(el)]
    return [l]
