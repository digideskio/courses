#Midterm Study Guide

##LOGICAL IMPLICATION
- **Valid argument**—an argument whose premises logically imply the conclusion
- **Tautological implication**—a sentential expression A tautologically implies a sentential expression B iff there is no situation in which A is true but B is false (i.e., iff, in a truth table that has columns for both expressions, there is no row in which the value of A is T and the value of B is F)
  - A sentence A tautologically implies a sentence B iff A and B can be written as sentential expressions for which the implication holds
- A contradiction implies anything
- A tautology is implied by anything
- A ≡ B iff A ⊨ B and B ⊨ A
- A ⊨ B iff A ≡ A ∧ B
- A ⊨ B iff “A → B” is a tautology

##PROPERTIES OF ⊨
- Reflexivity
  - A  ⊨ A
- Transitivity
  - If A  ⊨ B and B  ⊨ C, then A  ⊨ C
- Substitution
  - If A  ⊨ B and A ≡ A’, then A’ ⊨ B
  - If A  ⊨ B and B ≡ B’, then A ⊨ B'
- A list of sentential expressions tautologically implies a sentential expression B iff, in a truth table that contains columns for all of the expressions, there is no row in which all of the A’s is T while the value of B is F
  - A list of sentences tautologically implies a sentence B iff all of the sentences can be written as sentential expressions for which the implication holds
  - Notation: A<sub>1</sub>, …, A<sub>n</sub> ⊨ B
  - Corollary: A<sub>1</sub>, …, A<sub>n</sub> ⊨ B iff A<sub>1</sub> ∧ … ∧ A<sub>n</sub> ⊨ B
  - If n = 0, we write ⊨ B
  - ⊨ B iff B is a tautology

##TOP-DOWN DERIVATIONS
- Any given implication claim should be reduced to either:
  - Γ, C ⊨ C (premises include conclusion)
  - Γ, ¬A, A ⊨ C (presises are inconsistent)

##GENERAL IMPLICATION LAWS
- **Conditional Premise (→, ⊨)**: Γ, A → B ⊨ C iff Γ,¬A ⊨ C and Γ, B ⊨ C
- **Conditional Conclusion Law (⊨, →)**: Γ ⊨ A → B iff Γ, A ⊨ B
  - “A → B” is a tautology iff A  ⊨ B
  - In other words, ⊨ A → B iff A  ⊨ B
- **Monotonicity**: If Γ ⊨ C, and Δ includes all elements of Γ, then Δ ⊨ C
  - Holds true even if Δ includes formulas that contradict some elements of Γ or if Δ includes the negation of C
- **Consequence Addition**: If Γ ⊨ A, then Γ, A ⊨ C iff Γ ⊨ C
  - Example: Since P, Q ⊨ P ∧ Q, we have P, Q, P ∧ Q ⊨ C iff P, Q ⊨ C
- **Disjoining**: Γ, A, A → B ⊨ C iff Γ, A, B ⊨ C
- **Conjunction Premise (∧, ⊨)**: Γ, A ∧ B ⊨ C iff Γ, A, B ⊨ C
  - The order does not matter
    - Γ, A ∧ B ⊨ C iff Γ, A, B ⊨ C iff Γ, B, A ⊨ C
- **Conjunction Conclusion Law (⊨, ∧)**: Γ ⊨ A ∧ B iff Γ ⊨ A and Γ ⊨ B
  - Proofs using conjunction conclusion can be displayed as branches, as they split at the conjunction
- **Disjunction Premise (∨, ⊨)**: Γ, A ∨ B ⊨ C iff Γ, A ⊨ C and Γ, B ⊨ C
- **Disjunction Conclusion Law (⊨, ∨)**: Γ ⊨ A ∨ B iff Γ, ¬A ⊨ B
  - Essentially the same as the Conditional Conclusion Law, since A ∨ B ≡ ¬A → B
- **Biconditional Premise (↔, ⊨)**: Γ, A ↔ B ⊨ C iff Γ, A, B ⊨ C and Γ, ¬A, ¬B ⊨ C
- **Biconditional Conclusion Law (⊨, ↔)**: Γ ⊨ A ↔ B iff Γ, A ⊨ B and Γ, B ⊨ A

##NEGATION
- Via specific laws
  - Γ, ¬¬A ⊨ C iff Γ, A ⊨ C (¬¬, ⊨)
  - Γ ⊨ ¬¬C iff Γ ⊨ C (⊨, ¬¬)
  - Γ, ¬(A ∧ B) ⊨ C iff Γ, ¬A ⊨ C and Γ, ¬B ⊨ C (¬∧, ⊨)
  - Γ ⊨ ¬(A ∧ B) iff Γ, A ⊨ ¬B (⊨, ¬∧)
  - Γ, ¬(A ∨ B) ⊨ C iff Γ, ¬A, ¬B ⊨ C (¬∨, ⊨)
  - Γ ⊨ ¬(A ∨ B) iff Γ ⊨ ¬A and Γ ⊨ ¬B (⊨, ¬∨)
- Via substitution of equivalents
  - Drop ¬¬
  - Transform ¬(A * B) by pushing in ¬:
    - ¬(A ∨ B) ≡ ¬A ∧ ¬B
    - ¬(A ∧ B) ≡ ¬A ∨ ¬B
    - ¬(A → B) ≡ A ∧ ¬B
    - ¬(A ↔ B) ≡ (A ∧ ¬B) ∨ (¬A ∧ B)

##THE FOOLPROOF METHOD
- The foolproof method is an effective method for dealing with any implication claim:
  - If the implication holds, the method produces a top-down proof
  - If the implication does not hold, the method produces a counterexample
- It relies on the following three properties of ⊨:
  - **Counterexample Equivalence Property**—a truth-value assignment is a counterexample to the initial goal iff it is a counterexample to some goal(s) occurring down in the top-down derivation
  - **Elementary Reduction Property**—every implication claim can be reduced to a finite number of elementary implication claims (= no binary connectives)
  - **Triviality Property**—an elementary implication is valid iff it is trivially valid; if it is not valid, one can get a counterexample through the assignment:
    - Premises: T to variables; F to negated variables
    - Conclusion: F if a variable; T if a negated variable

##PROOF BY CONTRADICTION
- Γ ⊨ A iff Γ, ¬A is inconsistent
- Δ is inconsistent iff Δ ⊨ ⊥(where ⊥ is an arbitrarily fixed contradiction)
- **Contradictory Conclusion Law**: Γ ⊨ A iff Γ, ¬A ⊨ ⊥
  - The general law allows us to dispense with all other conclusion laws, and the following special instances will do:
    - Γ, A ∧ B ⊨ ⊥ iff Γ, A, B ⊨ ⊥
    - Γ, A ∨ B ⊨ ⊥ iff Γ, A ⊨ ⊥ and Γ, B ⊨ ⊥
    - Γ, A → B ⊨ ⊥ iff Γ, ¬A ⊨ ⊥ and Γ, B ⊨ ⊥
    - Γ, A ↔ B ⊨ ⊥ iff Γ, A, B ⊨ ⊥ and Γ, ¬A, ¬B ⊨ ⊥
  - We also only need one trivial implication law:
    - **Inconsistent Premises**: Γ, A, ¬A ⊨ ⊥

##PREDICATE LOGIC
- Sentential logic is limited in its scope (i.e., representing quantifiers)
  - Quantifiers could be used as connectives (“or" for some, “and” for all), but these would be really long disjunctions/conjunctions
- The purpose of predicate logic is to go deeper into the analysis of declarative sentences in order to further appreciate their logical structure
  - John is happy; therefore, someone is happy
  - Atomic sentences have internal structure and are composed of more basic expressions (names and predicates)
  - There are other logical operators besides the connectives—namely, existential quantifiers (somebody, someone, something) and universal quantifiers (everyone, everybody, everything)

##NAMES AND PREDICATES
- Names (singular terms, individual constants) stand for objects
  - John, Barack Obama, the moon, 75
- Predicates (general terms) correspond to adjectives, verbs, common nouns, noun phrases, etc., and stand for properties or relations
  - …is happy/is a man/runs/is greater than/introduced… to ...

##PREDICATE LOGIC WITHOUT QUANTIFIERS
- The vocabulary of PC<sub>0</sub> consists of:
  - A set of individual constants a, b, c, …, that symbolize singular terms such as names (“John,” “Mary”) and definite descriptions (“the moon,” “my brother”)
  - A set of predicates P, Q, R, …, that symbolize qualifying phrases involving adjectives (“is happy,” “was taller than”), nouns (“is a philosopher,” “will be a famous magician”), and verbs (“is walking,” “loves”)
    - A predicate is said to be unary, binary, etc., if it needs to combine with one, two, etc., singular terms to yield a sentence
  - The five connectives (¬, ∧, ∨, →, ↔)
- The grammar of PC<sub>0</sub> defines which strings of symbols count as well-formed sentences:
  - Atomic sentences—if P is an n-place predicate constant and c<sub>1</sub>, …, c<sub>n</sub> are individual constants, then P(c<sub>1</sub>, …, c<sub>n</sub>) is an atomic sentence
  - Compound sentences—if A is a sentence, then so is ¬A; if A and B are sentences, then so are A ∧ B, A ∨ B, A → B, A ↔ B

##SEMANTICS OF PREDICATE LOGIC
- An interpretation (also called a model) is determined by:
  - A domain of discourse (i.e., the set of all people in the room)
  - An assignment of denotations to the individual constants: the denotation of constant c is the object c in the domain
    - Some objects may have no names, and two names may denote the same object
  - An assignment of extensions to the predicates
    - If P is a one-place predicate, then its extension is a subset P of the domain
    - If P is an n-place predicate for n > 1, then its extension is a n-ary relation P in the domain of discourse
    - Some sets and relations may not be the extension of any predicates, and two predicates may have overlapping or even identical extensions

##SETS
- **Set**—can be described by enumerating its members or by specifying a predicate/condition that identifies its members
- **Extensionality Axiom**: A set is completely determined by its members; thus, order and repetition do not matter
- **Sequence**—by contrast, order and repetitions do matter
- An n-ary relation can be identified with a set of ordered n-tuples (i.e., finite sequences of length n)

##PROPERTIES OF PC<sub>0</sub>
- **FACT 1**: Every interpretation determines a truth-value assignment:
  - Atomic sentences
    - P(c<sub>1</sub>, ..., c<sub>n</sub>) is T (in the interpretation) iff (δ(c<sub>1</sub>), ..., δ(c<sub>n</sub>)) ∈ π(P)
  - Compound sentences
    - ¬A is T iff A is F
    - A ∧ B is T iff A is T and B is T
  - In PC<sub>0</sub>, all tautologies are logically true
- **FACT 2**: Every truth-value assignment determines an interpretation
  - Interpret different names as different objects
  - Interpret each P so that (x<sub>1</sub>, …, x<sub>n</sub>) ∈ π(P) iff P(c<sub>1</sub>, …, c<sub>n</sub>) = T (on the given truth-value assignment) for some c<sub>1</sub>, …, c<sub>n</sub> such that δ(c<sub>i</sub>) = x<sub>i</sub> for each i = 1, …, n
  - In PC<sub>0</sub>, only tautologies are logically true
- A is logically true iff A is true on every interpretation
- A is logically false iff A is false on every interpretation
- A is logically implied iff there is no interpretation on which all members of Γ are T but A is F
- Thus, in PC<sub>0</sub>, a formula A is logically true iff A is a tautology
  - In PC<sub>0</sub>, a formula A is logically implied by a set of formulas Γ iff A is tautologically implied by Γ

##ADDING EQUALITY
- Equality can be represented as a distinguished binary predicate, ≈
  - a ≈ b, not ≈(a, b)
  - a ≈ b is T iff δ(a) = δ(b)
- Equality is always represented as identity; its interpretation is fixed
  - As a result, we have some logical truths that are not tautologies (and some logically valid arguments which are not tautologically valid):
    - a ≈ a
    - a ≈ b → b ≈ a
    - ≈ b ∧ b ≈ c → a ≈ c
    - ⊨ a ≈ a
    - a ≈ b ⊨ b ≈ a
    - c ≈ c' ⊨ A ↔ A’, where A’ is obtained from A by replacing one or more occurrences of c by c’ (Extensionality Principle)

##PROOF BY CONTRADICTION LAWS
- The top-down derivation rules must be extended to reflect ≈
  - EQ1, Trivial Validity: Γ, ¬(c ≈ c) ⊨ ⊥
  - ESL, Equality Subst.: Γ, c ≈ c' ⊨ ⊥ ⇔ Γ', c ≈ c' ⊨ ⊥
- Apply ESL first
- After ESL, c does not occur anywhere except c ≈ c'