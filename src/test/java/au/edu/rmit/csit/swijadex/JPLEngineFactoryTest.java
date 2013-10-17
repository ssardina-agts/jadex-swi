package au.edu.rmit.csit.swijadex;

import static org.junit.Assert.*;

import org.junit.Test;

public class JPLEngineFactoryTest {

	@Test
	public void differentEnginesShouldHaveDifferentModules() {
		PrologEngineFactory factory = new JPLEngineFactory();
		PrologEngine a = factory.buildPrologEngine();
		PrologEngine b = factory.buildPrologEngine();
		assertNotSame(a.getModule(), b.getModule());
	}
	
}
