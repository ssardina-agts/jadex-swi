% handle a java Person object

:- dynamic id/1.

name_of(Id, Name) :- jpl_call(@(Id), 'getName', [], Name).
age_of(Id, Age) :- jpl_call(@(Id), 'getAge', [], Age).
gender_of(Id, Gender) :- jpl_call(@(Id), 'getGender', [], Gender).
spouse_of(Id, Spouse) :- jpl_call(@(Id), 'getSpouse', [], Spouse).

person(Id, Name, Age, Gender, Spouse) :-
	id(Id),
	name_of(Id, Name),
	age_of(Id, Age),
	gender_of(Id, Gender),
	spouse_of(Id, Spouse).



% one possible way to handle event notification  
% myassert(A) :- jpl_call(EventListener, 'execute', ['predicate/arity'], @(null)), assert(A).
