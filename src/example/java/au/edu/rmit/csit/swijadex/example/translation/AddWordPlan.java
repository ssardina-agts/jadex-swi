package au.edu.rmit.csit.swijadex.example.translation;

import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import jadex.bridge.fipa.SFipa;
import java.util.StringTokenizer;

import au.edu.rmit.csit.swijadex.PrologEngine;

/**
 * An initial english german translation plan can translate english words to
 * german.
 */
@SuppressWarnings("serial")
public class AddWordPlan extends Plan {

	public AddWordPlan() {
		getLogger().info("I have created " + this);
	}

	public void body() {

		String message = (String) ((IMessageEvent) getReason()).getParameter(SFipa.CONTENT).getValue();
		PrologEngine dictionary = (PrologEngine) getBeliefbase().getBelief("dictionary").getFact();
		
		StringTokenizer tokenizer = new StringTokenizer(message, " ");
		
		if (tokenizer.countTokens() == 5) {
			tokenizer.nextToken();
			String sourceLanguage = tokenizer.nextToken();
			String targetLanguage = tokenizer.nextToken();
			String sourceWord = tokenizer.nextToken();
			String targetWord = tokenizer.nextToken();
			
			String query = String.format("%s_%s('%s', '%s')", sourceLanguage, targetLanguage, sourceWord, targetWord);
			
			if (dictionary.hasSolution(query)) {
				getLogger().info("Wordpair already known - ignoring");
			}
			else {
				String fact = String.format("%s_%s_wordpair('%s', '%s')", sourceLanguage, targetLanguage, sourceWord, targetWord);
				getLogger().info("fact = <" + fact + ">");
				dictionary.assertz(fact);
//				dictionary.hasSolution("leash(-all), trace");
				getLogger().info(String.format("Added wordpair %s (%s) = %s (%s)", sourceWord, sourceLanguage, targetWord, targetLanguage));
//				dictionary.dumpListing();
				getLogger().info("query: " + query);
				// TODO: this currently does not work, since there is a problem with modules.
				// it seems that the rules defined in dictionary.pl do not see the added _wordpair
				// SEE ALSO: dictionary_with_hardcoded_modules.pl
				if (dictionary.hasSolution(query)) {
					getLogger().info("Wordpair added successfully");
				}
				else {
					getLogger().info("Wordpair was not added for some reason (likely broken implementation/usage of modules)");
				}
			}
		}
		else {
			getLogger().warning("Sorry, format not correct.");
		}		
	}
}
