#Final Study Guide

##MODELS
- The interpretation of a first-order language is given as a model
  - A model is a structure of form (U, π, δ) in which:
    - U is a non-empty set that represents the model’s universe or domain
    - π is a function that correlates with every predicate, P, of the language a relation, π(P), over U of the same parity as P; also known as the interpretation of P
    - δ is a function that correlates with every individual constant, c, of the language a member, δ(c), of U; also known as the denotation of c or the interpretation of c
  - Now that we have wffs with free variables, it makes no sense to ask whether they are true or false in a model—only to ask whether they are true or false in a model relative to an assignment of values to their free variables

##SEMANTICS OF FIRST-ORDER LOGIC
- Atomic
  - val<sup>M</sup>αP(t<sub>1</sub>, …, t<sub>n</sub>)[g] = T if (t<sub>1</sub><sup>M</sup>, …, t<sub>n</sub><sup>M</sup>) ∈ P<sup>M</sup> and F otherwise, where t<sub>1</sub><sup>M</sup> is δ(t<sub>i</sub>) if t<sub>i</sub> is a constant and g(t<sub>i</sub>) if t<sub>i</sub> is a variable
- Sentential
  - val<sup>M</sup>¬α[g] = T if val<sup>M</sup>α[g] = F; F if otherwise
  - val<sup>M</sup>α∧β[g] = T if val<sup>M</sup>α[g] = T and val<sup>M</sup>β[g] = T; F if otherwise
- Quantifiers
  - val<sup>M</sup>∀xα[g] = T if val<sup>M</sup>α[gx<sub>u</sub>] = T for every u ∈ U; F if otherwise
  - val<sup>M</sup>∃xα[g] = T if val<sup>M</sup>α[gx<sub>u</sub>] = T for some u ∈ U; F if otherwise

##TOP-DOWN DERIVATIONS FOR FOL
- (∀, ⊨): Γ, ∀xα ⊨⊥iff Γ, ∀xα, S<sup>x</sup><sub>c</sub> α ⊨ ⊥
- (∃, ⊨): Γ, ∃xα ⊨⊥iff Γ, S<sup>x</sup><sub>c</sub> α ⊨ ⊥
- Subst.: Γ ⊨⊥iff S<sup>x</sup><sub>c</sub> α ⊨ ⊥