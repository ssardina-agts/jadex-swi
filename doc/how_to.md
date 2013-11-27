Getting started with using a prolog-backed belief in your Jadex agent
=====================================================================

create a file named `a_priori_knowledge.pl` with the content:

    % a basic prolog file that will be used as the agent's starting knowledge

    is_yellow(banana).
    is_yellow(smiley).
    is_yellow(submarine).


inside your Agent Definition File (ADF):

    <imports>
        <import>au.edu.rmit.csit.swijadex.PrologEngine</import>
        <import>au.edu.rmit.csit.swijadex.JPLEngineFactory</import>
    </imports>

    <beliefs>
        <belief name="knowledge_base" class="PrologEngine">
            <fact>new JPLEngineFactory().buildPrologEngine().havingConsulted("a_priori_knowledge.pl")</fact>
        </belief>
    </beliefs>


You want to access your prolog knowledge base in an agent's plan body
---------------------------------------------------------------------

    PrologEngine kb = getBeliefbase().knowledge_base; // use the belief name defined in the ADF


You want to check a single ground fact (i.e. without any variables in the query)
--------------------------------------------------------------------------------

inside a plan body:

    boolean isBananaYellow = kb.hasSolution("is_yellow(banana)"); // you can pass in any valid prolog source code

    if (isBananaYellow) {
        // it's provably yellow
    }
    else {
        // it's not provably yellow
    }


You want to lookup a single answer
----------------------------------

inside a plan body:

    Hashtable somethingYellow = kb.getSolution("is_yellow(Something)"); // {"Something", "banana"} or null

    if (somethingYellow != null) {
        Atom yellowThing = somethingYellow.get("Something"); // "banana" as Atom
    }
    else {
        // there's nothing yellow
    }


You want to iterate through the answers
---------------------------------------

inside a plan body:

    Query isYellow = kb.query("is_yellow(X)");

    while (isYellow.hasMoreSolutions()) {
        Hashtable yellowThing = isYellow.nextSolution(); // {"X", "banana"} then {"X", "smiley"} then {"X", "submarine"} on subsequent iterations
        // do something with yellowThing.get("X").name()
        isYellow.close(); // this is done automatically when the query is exhausted, but if you exit the iteration prematurely, close it manually!
    }


You want a fixed number of answers
----------------------------------

inside a plan body:

    Hashtable[] topThreeYellowThings = kb.query("is_yellow(X)").nSolutions(3); // for the first 3 answers


You want to look up all answers
--------------------------------

inside a plan body:

    Hashtable[] everythingYellow = kb.getAllSolutions("is_yellow(X)"); // [{"X", "banana"}, {"X", "smiley"}, {"X", "submarine"}] or []


You want to convert an Atom to a String
---------------------------------------

inside a plan body:

    String name = yellowThing.name(); // "London" as String
    String string = yellowThing.toString(); // "'London'" as String escaped for use as atom in Prolog


You want to add a fact
--------------------------------------

inside a plan body:

    kb.asserta("is_yellow(cheese)"); // add it before other facts
    kb.assertz("is_yellow(sun)"); // add it after other facts


You want to remove a fact
--------------------------------------

inside a plan body:

    kb.retract("is_yellow(blue_cheese)");


You want to modify a fact
--------------------------------------

to do this, simply remove the old fact and add the new fact; so inside a plan body:

    kb.retract("is_yellow(blue_cheese)");
    kb.asserta("is_blue(blue_cheese)");


You want to be able to reason over an entire java object in prolog
------------------------------------------------------------------

this example will assume a plain old java object Person with the accessors name, age, gender

first, you have to teach prolog what a person is, by consulting a file with the following content:

    :- dynamic id/1.

    name_of(Id, Name) :- jpl_call(@(Id), 'getName', [], Name).
    age_of(Id, Age) :- jpl_call(@(Id), 'getAge', [], Age).
    gender_of(Id, Gender) :- jpl_call(@(Id), 'getGender', [], Gender).

    person(Id, Name, Age, Gender) :-
        id(Id),
        name_of(Id, Name),
        age_of(Id, Age),
        gender_of(Id, Gender).

this basically creates a rule allowing prolog to use the java accessors to access the actual object on the java side.

we'll use Peter for this example:

    Person peter = new Person("Peter", 23, "male"); // you have some object in java...

now, you can add the object to the knowledge base:

    Atom prologPeter = kb.deflateObject(peter); // turn it into a prolog atom
    kb.asserta("id('?')", prologPeter); // add it to the knowledge base

and now you can:

    Hashtable<String, Term> javaPeter = kb.getSolution("person(Id, 'Peter', Age, Gender)"); // get the first person named Peter
    Hashtable<String, Term> tweens = kb.getAllSolutions("person(Id, Name, 23, Gender)"); // get all persons aged 23

and finally, with the Id, you can get back to the original object:

    Person PeterInJavaAgain = (Person) kb.inflateAtom((Atom) javaPeter.get("Id"));


You want to do something that SWI-Jadex doesn't support
-------------------------------------------------------

you can bypass SWI-Jadex and access prolog using JPL. You'll need to manually specify the module in your queries.

to obtain the module each knowledge base resides in, use:

    kb.getModule();

see also the JPL documentation.


