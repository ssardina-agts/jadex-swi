% This is a copy of dictionary.pl with a module name hard-coded into every rule and fact.
%
% There seems to be some issue with asserting facts into a module so that rules in that module will pick up the facts.
% e.g. adding new words (assertz(en_es_wordpair(cat, gato))) works, but the rules using that (e.g. en_es_symmetrically/2)
% won't work without these hard-coded modules listed here, but that is merely a hack.
% TODO: figure out how to fix modules properly. Possibly by passing them around in the rules?
%
% a simple English German dictionary

:- dynamic jpl_engine_1:en_de_wordpair/2.
:- dynamic jpl_engine_1:de_en_wordpair/2.
:- dynamic jpl_engine_1:en_es_wordpair/2.
:- dynamic jpl_engine_1:es_en_wordpair/2.
:- dynamic jpl_engine_1:de_es_wordpair/2.
:- dynamic jpl_engine_1:es_de_wordpair/2.

% sugar to combine the different helper predicates
jpl_engine_1:en_de(EN, DE) :- jpl_engine_1:en_de_symmetrically(EN, DE) ; jpl_engine_1:en_de_transitively(EN, DE).
jpl_engine_1:de_en(DE, EN) :- jpl_engine_1:de_en_symmetrically(DE, EN) ; jpl_engine_1:de_en_transitively(DE, EN).
jpl_engine_1:en_es(EN, ES) :- jpl_engine_1:en_es_symmetrically(EN, ES) ; jpl_engine_1:en_es_transitively(EN, ES).
jpl_engine_1:es_en(ES, EN) :- jpl_engine_1:es_en_symmetrically(ES, EN) ; jpl_engine_1:es_en_transitively(ES, EN).
jpl_engine_1:de_es(DE, ES) :- jpl_engine_1:de_es_symmetrically(DE, ES) ; jpl_engine_1:de_es_transitively(DE, ES).
jpl_engine_1:es_de(ES, DE) :- jpl_engine_1:es_de_symmetrically(ES, DE) ; jpl_engine_1:es_de_transitively(ES, DE).

% words translate bi-directionally (symmetric)
jpl_engine_1:en_de_symmetrically(EN, DE) :- jpl_engine_1:de_en_wordpair(DE, EN) ; jpl_engine_1:en_de_wordpair(EN, DE).
jpl_engine_1:de_en_symmetrically(DE, EN) :- jpl_engine_1:de_en_wordpair(DE, EN) ; jpl_engine_1:en_de_wordpair(EN, DE).
jpl_engine_1:en_es_symmetrically(EN, ES) :- jpl_engine_1:es_en_wordpair(ES, EN) ; jpl_engine_1:en_es_wordpair(EN, ES).
jpl_engine_1:es_en_symmetrically(ES, EN) :- jpl_engine_1:es_en_wordpair(ES, EN) ; jpl_engine_1:en_es_wordpair(EN, ES).
jpl_engine_1:es_de_symmetrically(ES, DE) :- jpl_engine_1:de_es_wordpair(DE, ES) ; jpl_engine_1:es_de_wordpair(ES, DE).
jpl_engine_1:de_es_symmetrically(DE, ES) :- jpl_engine_1:de_es_wordpair(DE, ES) ; jpl_engine_1:es_de_wordpair(ES, DE).

% words can be translated via an intermediate language (transitive)
jpl_engine_1:de_en_transitively(DE, EN) :- jpl_engine_1:de_es_symmetrically(DE, ES), jpl_engine_1:es_en_symmetrically(ES, EN).
jpl_engine_1:en_de_transitively(EN, DE) :- jpl_engine_1:de_en_transitively(DE, EN).
jpl_engine_1:de_es_transitively(DE, ES) :- jpl_engine_1:de_en_symmetrically(DE, EN), jpl_engine_1:en_es_symmetrically(EN, ES).
jpl_engine_1:es_de_transitively(ES, DE) :- jpl_engine_1:de_es_transitively(DE, ES).
jpl_engine_1:en_es_transitively(EN, ES) :- jpl_engine_1:en_de_symmetrically(EN, DE), jpl_engine_1:de_es_symmetrically(DE, ES).
jpl_engine_1:es_en_transitively(ES, EN) :- jpl_engine_1:en_es_transitively(EN, ES).

% the actual word pairs
jpl_engine_1:en_de_wordpair('milk', 'Milch').
jpl_engine_1:en_de_wordpair('dog', 'Hund').
jpl_engine_1:en_de_wordpair('cat', 'Katze').
jpl_engine_1:en_de_wordpair('cow', 'Kuh').
jpl_engine_1:en_de_wordpair('pineapple', 'Ananas').

jpl_engine_1:de_en_wordpair('Apfel', 'apple').

jpl_engine_1:en_es_wordpair('dog', 'perro').
%jpl_engine_1:en_es_wordpair('cat', 'gato').
jpl_engine_1:en_es_wordpair('catty', 'gatoy').

jpl_engine_1:es_en_wordpair('vaca', 'cow').

jpl_engine_1:de_es_wordpair('Apfel', 'manzana').

jpl_engine_1:es_de_wordpair('leche', 'Milch').
