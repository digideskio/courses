import pickle
import re
import string
from collections import defaultdict
from urllib import request

def goodperm(cipher):
    """
    Problem 1a: Returns whether a cipher produces a valid permutation as True
    or False.
    """
    alphabet = string.ascii_lowercase
    c = cipher
    encoded_alphabet = c.encode(alphabet)
    if len(encoded_alphabet) != 26 or len(set(encoded_alphabet)) != 26:
        return False
    return True

def saveKey(path, key):
    """
    Problem 1b: Saves valid keys using pickling and raises an exception for
    invalid keys.
    """
    if goodperm(key):
        with open(path, 'wb') as f:
            pickle.dump(key, f)
        return None
    raise Exception('Key does not generate valid permutations.')

"""
Problem 2a: Encodes and decodes strings using a path to the pickled key.
"""

class swapaz:

    def encode(self, s):
        s = s.replace('a', 'A').replace('z', 'Z').replace('Z','a').replace('A', 'z')
        return(s)

    def decode(self, s):
        return(self.encode(s))

def encode(path, string):
    with open(path, 'rb') as f:
        key = pickle.load(f)
        return key.encode(string)

def decode(path, string):
    with open(path, 'rb') as f:
        key = pickle.load(f)
        return key.decode(string)

class encrot:

    """
    Problem 2b: Takes an integer and makes a permutation by cyclically rotating
    the character set by that number.
    """

    def __init__(self, n):
        self.n = n

    def encode(self, string):
        return string[-self.n:] + string[:-self.n]

def actors(url):
    """
    Problem 3: Analyzes Hamlet from an HTML file, returning a list of the line
    count, number of speeches, and number of speeches per character.
    """
    line_count = 0
    speech_count = 0
    speeches = defaultdict(int)
    with request.urlopen(url) as ef:
        for bin in ef:
            line_count += 1
            s = bin.decode('utf-8')
            if re.search('speech[0-9]+', s):
                name = BeautifulSoup(bin).find('b')
                if name:
                    speech_count += 1
                    speeches[name.text] += 1
    return [line_count, speech_count, speeches]

class Interval:

    """
    Problem 4: Implements a class 'Interval' that does interval arithmetic,
    allowing addition and multiplication of intervals.
    """

    def __init__(self, imin, imax):
        self.imin = imin
        self.imax = imax

    def __str__(self):
        return "Interval<%d, %d>" % (self.imin, self.imax)

    def __repr__(self):
        return self.__str__()

    def __add__(self, v):
        if isinstance(v, int): v = Interval(v, v)
        return Interval(self.imin + v.imin, self.imax + v.imax)

    def __mul__(self, v):
        if isinstance(v, int): v = Interval(v, v)
        return Interval(self.imin * v.imax, self.imax * v.imax)

"""
Problem 5: Converts polylists to polydicts and vice versa.
""" 

class polylist: 

    def __init__(self, coe):
        self.coe = coe

    def termString(self, c , e):
        cs = str(c)
        if c > 0:
            cs = '+ ' + cs
        if (e == 0):
            return(cs)
        if (e == 1):
            return('%s*X' % cs)    
        return('%s*X**%d' % (cs,e))
        
    def __str__(self):
        terms = [self.termString(c,e) 
            for e,c in enumerate(self.coe) 
            if c != 0]
        terms.reverse()
        s = (' '.join(terms))
        return(s)
        
    def __repr__(self):
        return(self.__str__())

    def __len__(self):
        return(len(self.coe) - self.coe.count(0))

    def __add__(self, p2):
        p1len = len(self.coe)
        p2len = len(p2.coe)
        pad = p2len - p1len
        c1 = self.coe
        c2 = p2.coe
        if pad < 0:
            c1, c2 = c2, c1
            pad = -pad
        c1 = c1[:]
        c1.extend([0]*pad)
        return(poly([t1+t2 for t1,t2 in zip(c1,c2)]))
    
    __hash__ = None    
    
    def evaluate(self, n):
        sum = 0
        for e,c in enumerate(self.coe):
            sum += c*n**e
        return(sum)
  
    def __mul__(self, p2):
        sums = []
        for e1,c1 in enumerate(self.coe):
            prod = [c1 * c2 for c2 in p2.coe]
            for rpt in range(e1):
                prod.insert(0, 0)
            sums.append(poly(prod))
        return(functools.reduce(poly.__add__, sums))

    def topolydict(self):
        d = {}
        for i, c in enumerate(self.coe):
            d[i] = c
        return polydict(d)

class polydict:

    def __init__(self, d={}):
        self.sparse = d.copy()

    def printTerm(self, c ,e):
        cs = str(c)
        if c > 0:
            cs = '+ ' + cs
        if (e == 0):
            return(cs)
        if (e == 1):
            return('%s*X' % cs)    
        return('%s*X**%d' % (cs,e))   
        
    def __str__(self):
        if len(self.sparse) == 0:
            return('0')
        terms = [self.printTerm(self.sparse[e],e) 
                for e in sorted(self.sparse.keys(), 
                                reverse=True) 
                    if self.sparse[e] != 0]
        s = ' '.join(terms)
        if '+ ' == s[0:2]:
            s = s[2:]
        return (s)
    
    def __repr__(self):
        return(self.__str__())

    __hash__ = None  
    
    def __len__(self):
        return(len(self.sparse))

    def __bool__(self):
        return(False if len(self.sparse)==0 else True)
        
    def __iter__(self):
        return( (i for i in self.sparse.items() ))

    def __eq__(self, other):
        return(self.sparse == other.sparse)
        
    def __ne__(self, other):
        return(self.sparse != other.sparse)
        
    def __lt__(self, other):
        return(self.evaluate(1) < other.evaluate(1))
        
    def __le__(self, other):
        return(self.evaluate(1) <= other.evaluate(1))
        
    def __contains__(self, e):
        return(e in self.sparse)

    def evaluate(self, n):
        sum = 0
        for e in self.sparse.keys():
            sum += self.sparse[e]*n**e
        return(sum)
            
    def __add__(self, p2):
        n = self.sparse.copy()
        for k,v in p2.sparse.items():
            if None == n.get(k):
                n[k] = v
            else:
                n[k] += v
        return(polydict(n))
        
    def __getitem__(self, index):
        keys = sorted(self.sparse.keys(), reverse=True)
        if isinstance(index, int):
            inds = [index]
        if isinstance(index, slice):
            inds = range(*index.indices(len(keys)))
        d = {}
        for i in inds:
            e = keys[i]
            d[e] = self.sparse[e]
        return(polydict(d))
        
    def __rmul__(self, p2):
        if isinstance(p2, int):
            nd = {}
        for e,c in self.sparse.items():
            nd[e] = c * p2 
        return(polydict(nd))
        
    def differentiate(self):
        d = {}
        for e,c in self.sparse.items():
            if e != 0:
                d[e-1] = c * e
        return(polydict(d))
    
    def integrate(self):
        d = {}
        for e,c in self.sparse.items():
            d[e+1] = c /(e+1.)
        return(polydict(d))

    def topolylist(self):
        l = []
        for k, v in sorted(self.sparse.items()):
            if k < 0:
                raise ValueError("Negative exponent: %d" % (k))
            l.append(v)
        return polylist(l)