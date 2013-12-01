package au.edu.rmit.csit.swijadex;

import java.util.Hashtable;

import jpl.Atom;
import jpl.Query;
import jpl.Term;

public interface PrologEngine {

	/*
	 * this might be useful for debugging if you want to interact with prolog
	 * while an agent is running, but without going through the agent
	 */
	public String getModule();
	
	/*
	 * load a prolog file
	 * filename is relative to project directory ? // TODO
	 */
	public void consult(String filename);
	
	/*
	 * create a query that can be executed later
	 */
	public Query query(String query);
	
	/*
	 * use this to interpolate terms into a query
	 * the query should have as many question marks ("?") as there are parameters after the query parameter
	 * e.g.: query("father(?, ?)", "joseph", "jesus");
	 * this is useful for user input, avoiding injection attacks
	 */
	public Query query(String query, Term... params);
	
	/*
	 * add a fact before the other facts
	 */
	public void asserta(String fact);

	/*
	 * add a fact after the other facts
	 */
	public void assertz(String fact);

	/*
	 * remove a fact
	 */
	public void retract(String fact);

	/*
	 * remove all facts matching the parameter
	 */
	public void retractall(String fact);
	
	/*
	 * does the same as consult(String filename), but returns the engine
	 */
	public PrologEngine havingConsulted(String filename);
	
	/*
	 * execute query and retrieve first solution
	 * 
	 * Only use this when you only want one solution. If you want more, use getAllSolutions or nSolutions
	 */
	public Hashtable<String, Term> getSolution(String query);

	/*
	 * use this to interpolate terms into a query
	 * the query should have as many question marks ("?") as there are parameters after the query parameter
	 * e.g.: getSolution("father(?, ?)", "joseph", "jesus");
	 * this is useful for user input, avoiding injection attacks
	 */
	public Hashtable<String, Term> getSolution(String query, Term... params);
	
	/*
	 * use this if you only care about whether the query has a provable solution, but not what that solution actually is
	 */
	public boolean hasSolution(String query);
	
	/*
	 * use this to interpolate terms into a query
	 * the query should have as many question marks ("?") as there are parameters after the query parameter
	 * e.g.: hasSolution("father(?, ?)", "joseph", "jesus");
	 * this is useful for user input, avoiding injection attacks
	 */
	public boolean hasSolution(String query, Term... params);
	
	/*
	 * use this to iterate through all solutions to a query
	 */
	public Hashtable<String, Atom>[] getAllSolutions(String query);

	/*
	 * use this to interpolate terms into a query
	 * the query should have as many question marks ("?") as there are parameters after the query parameter
	 * e.g.: getAllSolutions("is_coloured(X, ?)", "yellow");
	 * this is useful for user input, avoiding injection attacks
	 */
	public Hashtable<String, Atom>[] getAllSolutions(String query, Term... params);

//  TODO : generalise to use Strings rather than Terms once we use multiple backends, since Terms are JPL specific
//	public Hashtable<String, Atom>[] getAllSolutions(String query, String... params);

	/*
	 * this might be convenient for debugging
	 */
	public void dumpListing();
	
	/*
	 * turn a java-side object into an ID that can be used in prolog to identify that object
	 */
	public Atom atomise(Object object);
	
	/*
	 * turn a prolog-side ID into the java object it refers to
	 */
	public Object objectify(Atom key);

}
