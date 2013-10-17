package au.edu.rmit.csit.swijadex;

import java.util.concurrent.atomic.AtomicInteger;

public class JPLEngineFactory implements PrologEngineFactory {

    static AtomicInteger nextId = new AtomicInteger();
	
	public PrologEngine buildPrologEngine() {
        String module = "jpl_engine_" + nextId.incrementAndGet();
        return new JPLEngine(module);
	}
	
}
