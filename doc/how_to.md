Getting started with using a prolog-backed belief in your Jadex agent
=====================================================================

create a file named `a_priori_knowledge.pl` with the content:

    % a basic prolog file that will be used as the agent's starting knowledge

    is_yellow(banana).
    is_yellow(smiley).
    is_yellow(submarine).


inside your Agent Definition File (ADF):

    <imports>
        <import>au.edu.rmit.csit.swijadex.PrologBridge</import>
    </imports>

    <beliefs>
        <belief name="prolog" class="PrologBridge">
            <fact>new PrologBridge().havingConsulted("a_priori_knowledge.pl")</fact>
        </belief>
    </beliefs>


You want to check a single ground fact (i.e. without any variables in the query)
--------------------------------------------------------------------------------

inside a plan body:

    boolean isBananaYellow = getBeliefbase().prolog.query("is_yellow(banana)").hasSolution();

    if (isBananaYellow) {
        // it's provably yellow
    }
    else {
        // it's not provably yellow
    }


You want to lookup a single answer
----------------------------------

inside a plan body:

    Hashtable somethingYellow = getBeliefbase().prolog.query("is_yellow(X)").oneSolution(); // {"X", "banana"} or null

    if (somethingYellow != null) {
        Atom yellowThing = somethingYellow.get("X"); // "banana" as Atom
    }
    else {
        // there's nothing yellow
    }


You want to iterate through the answers
---------------------------------------

inside a plan body:

    Query isYellow = getBeliefbase().prolog.query("is_yellow(X)");

    while (isYellow.hasMoreSolutions()) {
        Hashtable yellowThing = isYellow.nextSolution(); // {"X", "banana"} then {"X", "smiley"} then {"X", "submarine"} on subsequent iterations
        // do something with yellowThing.get("X").name()
    }


You want a fixed number of answers
----------------------------------

inside a plan body:

    Hashtable[] topThreeYellowThings = getBeliefbase().prolog.query("is_yellow(X)").nSolutions(3); // for the first 3 answers


You want to look up all answers
--------------------------------

inside a plan body:

    Hashtable[] everythingYellow = getBeliefbase().prolog.query("is_yellow(X)").allSolutions(); // [{"X", "banana"}, {"X", "smiley"}, {"X", "submarine"}] or []


You want to convert an Atom to a String
---------------------------------------

inside a plan body:

    String name = yellowThing.name(); // "London" as String
    String string = yellowThing.toString(); // "'London'" as String escaped for use as atom in Prolog
