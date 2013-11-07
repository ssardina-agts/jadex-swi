% a simple English German dictionary

:- dynamic en_de_wordpair/2.
:- dynamic de_en_wordpair/2.
:- dynamic en_es_wordpair/2.
:- dynamic es_en_wordpair/2.
:- dynamic de_es_wordpair/2.
:- dynamic es_de_wordpair/2.

% sugar to combine the different helper predicates
en_de(EN, DE) :- en_de_symmetrically(EN, DE) ; en_de_transitively(EN, DE).
de_en(DE, EN) :- de_en_symmetrically(DE, EN) ; de_en_transitively(DE, EN).
en_es(EN, ES) :- en_es_symmetrically(EN, ES) ; en_es_transitively(EN, ES).
es_en(ES, EN) :- es_en_symmetrically(ES, EN) ; es_en_transitively(ES, EN).
de_es(DE, ES) :- de_es_symmetrically(DE, ES) ; de_es_transitively(DE, ES).
es_de(ES, DE) :- es_de_symmetrically(ES, DE) ; es_de_transitively(ES, DE).

% words translate bi-directionally (symmetric)
en_de_symmetrically(EN, DE) :- de_en_wordpair(DE, EN) ; en_de_wordpair(EN, DE).
de_en_symmetrically(DE, EN) :- de_en_wordpair(DE, EN) ; en_de_wordpair(EN, DE).
en_es_symmetrically(EN, ES) :- es_en_wordpair(ES, EN) ; en_es_wordpair(EN, ES).
es_en_symmetrically(ES, EN) :- es_en_wordpair(ES, EN) ; en_es_wordpair(EN, ES).
es_de_symmetrically(ES, DE) :- de_es_wordpair(DE, ES) ; es_de_wordpair(ES, DE).
de_es_symmetrically(DE, ES) :- de_es_wordpair(DE, ES) ; es_de_wordpair(ES, DE).

% words can be translated via an intermediate language (transitive)
de_en_transitively(DE, EN) :- de_es_symmetrically(DE, ES), es_en_symmetrically(ES, EN).
en_de_transitively(EN, DE) :- de_en_transitively(DE, EN).
de_es_transitively(DE, ES) :- de_en_symmetrically(DE, EN), en_es_symmetrically(EN, ES).
es_de_transitively(ES, DE) :- de_es_transitively(DE, ES).
en_es_transitively(EN, ES) :- en_de_symmetrically(EN, DE), de_es_symmetrically(DE, ES).
es_en_transitively(ES, EN) :- en_es_transitively(EN, ES).

% synonyms are trivially found by finding other words that translate to the same foreign word
synonym(en, EN, Synonym) :- en_de(EN, DE) , en_de(Synonym, DE).
synonym(en, EN, Synonym) :- en_es(EN, ES) , en_es(Synonym, ES).
synonym(de, DE, Synonym) :- de_en(DE, EN) , de_en(Synonym, EN).
synonym(de, DE, Synonym) :- de_es(DE, ES) , en_es(Synonym, ES).
synonym(es, ES, Synonym) :- es_de(ES, DE) , es_de(Synonym, DE).
synonym(es, ES, Synonym) :- es_en(ES, EN) , es_en(Synonym, EN).


% the actual word pairs
en_de_wordpair('milk', 'Milch').
en_de_wordpair('dog', 'Hund').
en_de_wordpair('cat', 'Katze').
en_de_wordpair('tiger', 'Katze').
en_de_wordpair('cow', 'Kuh').
en_de_wordpair('pineapple', 'Ananas').

de_en_wordpair('Apfel', 'apple').

en_es_wordpair('dog', 'perro').
en_es_wordpair('cat', 'gato').
en_es_wordpair('lion', 'gato').

es_en_wordpair('vaca', 'cow').

de_es_wordpair('Apfel', 'manzana').

es_de_wordpair('leche', 'Milch').
