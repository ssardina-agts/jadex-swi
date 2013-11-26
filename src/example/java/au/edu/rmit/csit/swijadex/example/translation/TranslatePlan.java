package au.edu.rmit.csit.swijadex.example.translation;

import java.util.StringTokenizer;

import au.edu.rmit.csit.swijadex.PrologEngine;

import jadex.bdi.runtime.IExpression;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import jadex.bridge.fipa.SFipa;

/**
 * An initial English German translation plan can translate English words to
 * German.
 */
@SuppressWarnings("serial")
public class TranslatePlan extends Plan {
	
	protected IExpression queryword;
	
	public TranslatePlan() {
		getLogger().info("I have created " + this);
	}

	public void body() {

		String message = (String) ((IMessageEvent) getReason()).getParameter(SFipa.CONTENT).getValue();
		PrologEngine dictionary = (PrologEngine) getBeliefbase().getBelief("dictionary").getFact();
		
		StringTokenizer tokenizer = new StringTokenizer(message, " ");
		
		if (tokenizer.countTokens() == 4) {
			tokenizer.nextToken();
			String sourceLanguage = tokenizer.nextToken();
			String targetLanguage = tokenizer.nextToken();
			String sourceWord = tokenizer.nextToken();
						
			String query = String.format("%s_%s('%s', TargetWord)", sourceLanguage, targetLanguage, sourceWord);
			
			if (dictionary.hasSolution(query)) {
				String targetWord = dictionary.getSolution(query).get("TargetWord").name();
				getLogger().info("I'm now translating from " + sourceLanguage + " to " + targetLanguage + ": " + sourceWord + " - " + targetWord);
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
