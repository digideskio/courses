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
  - Notation: A1, …, An ⊨ B
  - Corollary: A1, …, An ⊨ B iff A1 ∧ … ∧ An ⊨ B
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
- C**onjunction Premise (∧, ⊨)**: Γ, A ∧ B ⊨ C iff Γ, A, B ⊨ C
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