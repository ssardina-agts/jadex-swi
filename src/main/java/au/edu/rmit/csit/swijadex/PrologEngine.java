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
	
	public Query query(String query);
	
	public Query query(String query, Term... params);
	
	public void asserta(String fact);

	public void assertz(String fact);

	public void retract(String fact);

	public void retractall(String fact);
	
	/*
	 * does the same as consult(String filename), but returns the engine
	 */
	public PrologEngine havingConsulted(String filename);
	
	public Hashtable<String, Term> getSolution(String query);

	public Hashtable<String, Term> getSolution(String query, Term... params);
	
//	public Hashtable<String, Term> getSolution(String query, String... params);
	
	public boolean hasSolution(String query);
	
	public boolean hasSolution(String query, Term... params);
	
//	public boolean hasSolution(String query, String... params);
	
	public Hashtable<String, Atom>[] getAllSolutions(String query);
	
	public Hashtable<String, Atom>[] getAllSolutions(String query, Term... params);

//	public Hashtable<String, Atom>[] getAllSolutions(String query, String... params);

	public void dumpListing();
	
	public Atom deflateObject(Object object);
	
	public Object inflateAtom(Atom key);

}
