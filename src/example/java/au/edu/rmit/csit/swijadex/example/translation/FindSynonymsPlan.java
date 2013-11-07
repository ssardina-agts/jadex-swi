package au.edu.rmit.csit.swijadex.example.translation;

import jadex.bdi.runtime.IExpression;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import jadex.bridge.fipa.SFipa;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import jpl.Atom;

import au.edu.rmit.csit.swijadex.PrologEngine;

/**
 * An initial english german translation plan can translate english words to
 * german.
 */
@SuppressWarnings("serial")
public class FindSynonymsPlan extends Plan {

	protected IExpression querytranslate;
	protected IExpression queryfind;
	String reply;  // The message event type of the reply.
	String content; // The content of the reply message event.

	public FindSynonymsPlan() {
		getLogger().info("I have created " + this);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void body() {
		
		String message = (String) ((IMessageEvent) getReason()).getParameter(SFipa.CONTENT).getValue();
		PrologEngine dictionary = (PrologEngine) getBeliefbase().getBelief("dictionary").getFact();
		
		StringTokenizer stok = new StringTokenizer(	message, " ");
		
		if (stok.countTokens() == 3) {
			stok.nextToken();
			String sourceLanguage = stok.nextToken();
			String sourceWord = stok.nextToken();
			
			if (sourceWord != null) {
				//getLogger().info("I'm now finding synonyms for '" + sourceWord + "'");
				Hashtable[] solutions = dictionary.getAllSolutions(String.format("synonym('%s', '%s', Synonym)", sourceLanguage, sourceWord));
				List synonyms = new ArrayList();
				for (Hashtable solution : solutions) {
					String synonym = ((Atom) solution.get("Synonym")).name();
					//getLogger().info("found synonym: " + synonym);
					if (!synonym.equals(sourceWord)) {
						//getLogger().info("adding it to list of synonyms");
						synonyms.add(synonym);
					}
					else {
						//getLogger().info("but ignoring it, as it's the same word");
					}
				}
				if (synonyms.isEmpty()) {
					getLogger().info("Sorry, I don't know any synonyms for '" + sourceWord + "'");
				}
				else {
					getLogger().info("synonyms for " + sourceWord + ": " + synonyms.toString());
				}
			}
			else {
				getLogger().info("Sorry, I don't know the word '" + sourceWord + "'");
			}
		}
		else {
			getLogger().warning("Sorry, format not correct.");
		}		
	}
}
