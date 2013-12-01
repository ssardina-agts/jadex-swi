package au.edu.rmit.csit.swijadex;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

import jpl.Atom;
import jpl.Query;
import jpl.Term;

public class JPLEngine implements PrologEngine {

	private final String module;
    static AtomicInteger nextId = new AtomicInteger();
	private HashMap<String, Object> objects;
	
	public JPLEngine(String module) {
		this.module = module;
		this.objects = new HashMap<String, Object>();
	}
	
	public String getModule() {
		return this.module;
	}
	
	public void consult(String filename) {
		boolean consulted = this.query("consult('" + filename + "')").hasSolution();
		
		if (!consulted) {
			throw new RuntimeException("Couldn't consult " + filename);
		}
	}
	
	public Query query(String query) {
		System.out.println("Creating query " + this.module + ":" + query);
		return new Query(this.module + ":" + query);
	}
	
	public Query query(String query, Term... params) {
		System.out.println("Creating query " + this.module + ":" + query + ", " + params);
		return new Query(this.module + ":" + query, params);
	}
		
	public void asserta(String fact) {
		this.query("asserta(" + this.module + ":" + fact + ")").hasSolution();
	}

	public void assertz(String fact) {
		this.query("assertz(" + this.module + ":" + fact + ")").hasSolution();
	}

	public void retract(String fact) {
		this.query("retract(" + this.module + ":" + fact + ")").hasSolution();
	}

	public void retractall(String fact) {
		this.query("retractall(" + this.module + ":" + fact + ")").hasSolution();
	}

	public JPLEngine havingConsulted(String filename) {
		this.consult(filename);
		return this;
	}
	
	public Hashtable<String, Term> getSolution(String query) {
		System.out.println("Called getSolution(" + query + ")");
		@SuppressWarnings("unchecked")
		Hashtable<String, Term> solution = this.query(query).oneSolution();
		System.out.println("Returning solution: " + solution);
		return solution;
	}
	
	public Hashtable<String, Term> getSolution(String query, Term... params) {
		System.out.println("Called getSolution(" + query + "), " + params);
		@SuppressWarnings("unchecked")
		Hashtable<String, Term> solution = this.query(query, params).oneSolution();
		System.out.println("Returning solution: " + solution);
		return solution;
	}
	
	public boolean hasSolution(String query) {
		return this.query(query).hasSolution();
	}
	
	public boolean hasSolution(String query, Term... params) {
		return this.query(query, params).hasSolution();
	}
	
	public Hashtable<String, Atom>[] getAllSolutions(String query) {
		@SuppressWarnings("unchecked")
		Hashtable<String, Atom>[] solutions = this.query(query).allSolutions();
		return solutions;
	}
	
	public Hashtable<String, Atom>[] getAllSolutions(String query, Term... params) {
		@SuppressWarnings("unchecked")
		Hashtable<String, Atom>[] solutions = this.query(query, params).allSolutions();
		return solutions;
	}
	
	public void dumpListing() {
		this.query("listing").hasSolution();
	}
	
	public Atom atomise(Object object) {
		Atom key = new Atom( jpl.fli.Prolog.object_to_tag(object) );
		this.objects.put(key.name(), object);
		return key;
	}
	
	public Object objectify(Atom key) {
		if (key.isJNull()) {
			return null;
		}
		Object object = this.objects.get(key.name());
		
		// just use: tag_to_object?
		// do we need to hang onto the java side objects to prevent them
		// from being GC'd with dangling references on the prolog side? 
		//Object object = jpl.fli.Prolog.tag_to_object(key.name());
		
		return object;
	}
	
}
