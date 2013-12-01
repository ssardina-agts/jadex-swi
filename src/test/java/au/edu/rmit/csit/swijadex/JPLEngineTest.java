package au.edu.rmit.csit.swijadex;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Hashtable;
import jpl.Atom;
import jpl.Term;

import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

public class JPLEngineTest {

	private PrologEngine iut;
	private static JPLEngineFactory factory;

    @BeforeClass
    public static void startup() {
    	factory = new JPLEngineFactory();
    }
    
	@Before
	public void setup() {
        iut = factory.buildPrologEngine();
	}
	
	@After
	public void teardown() {
		iut = null;
	}
	
	@Test
	public void shouldRememberItsModule() {
		PrologEngine engine = new JPLEngine("test_module");
		assertEquals(engine.getModule(), "test_module");
	}

	@Test
	public void shouldBeAbleToConsultFile() {
		iut.consult("target/test-classes/test_kb.pl");
		assertTrue(iut.query("is_yellow(submarine)").hasSolution());
	}

	@Test
	public void shouldBeAbleToQuery() {
		assertTrue(iut.query("assertz(is_yellow(sun))").hasSolution());
	}

	@Test
	public void differentEnginesShouldNotClobberEachOther() {
		PrologEngine a = factory.buildPrologEngine();
		PrologEngine b = factory.buildPrologEngine();
				
		a.asserta("is_blue(sky)");
		b.asserta("is_blue(lagoon)");
				
		assertThat(a.hasSolution("is_blue(sky)"), is(true));
		assertThat(a.hasSolution("is_blue(lagoon)"), is(false));

		assertThat(b.hasSolution("is_blue(sky)"), is(false));
		assertThat(b.hasSolution("is_blue(lagoon)"), is(true));
				
	}

	
/*
 * this currently doesn't work, because compiling memfile inside
 *  json inside json_convert inside person.pl is broken:
 *  	/home/rene.fischer/opt/jdk1.7.0_21/bin/java: symbol
 *   	lookup error: /usr/lib/swi-prolog/lib/amd64/memfile.so: undefined
 *   	symbol: PL_query
 */
//	@Test
//	public void shouldBeAbleToHandleObjects() throws IOException {
//		Person john = new Person("John", 23);
//		Person jane = new Person("Jane", 22);
//		
//		john.setSpouse(jane);
//		jane.setSpouse(john);
//		
//		String johnAsJson = JsonWriter.objectToJson(john);
//		//System.out.println(johnAsJson);
//		
//		iut.consult("bin/person.pl");
//		
//		iut.asserta("person('" + john.getName() + "', " + john.getAge() + ")");
//		iut.dumpListing();
//		Hashtable<String, Atom> results = iut.getSolution("person(Name, Age)");
//		System.out.println(JsonWriter.objectToJson(results));
//
//		System.out.println( results.get("Name") + ", " + results.get("Age") );
//	}
	
	@Test
	public void shouldbeAbleToDeflateAndInflateObjects() {
		Person john = new Person("John", 23);
		
		Object reinflatedJohn = iut.objectify(iut.atomise(john));
		
		assertThat(john, is(equalTo(reinflatedJohn)));
	}
	
	@Test
	public void shouldBeAbleToTransferOpaqueObjectsRoundTrip() {
		Person original = new Person("Bob Smith", 35);
		
		iut.asserta("person(" + iut.atomise(original) + ")");
		
		Person reconstituted = (Person) iut.objectify((Atom) iut.getSolution("person(X)").get("X"));
		
		assertThat(original, is(equalTo(reconstituted)));
	}
	
	@Test
	public void shouldBeAbleToCallBackIntoJavaMethodsOnObjects() {
		
		Person jack = new Person("Jack", 23, "male");
		
		iut.consult("target/test-classes/person.pl");
		
		Atom prologJack = iut.atomise(jack);
		iut.hasSolution("asserta(id('?'))", prologJack);
				
		Hashtable<String, Term> javaJack = iut.getSolution("person(Id, 'Jack', Age, Gender, Spouse)");

		assertThat(jack.getGender(), is(equalTo(javaJack.get("Gender").name())));
		
	}
	
	@Test
	public void shouldKeepObjectsInSync() {
		
		Person john = new Person("John", 23, "male");

		iut.consult("target/test-classes/person.pl");
		
		Atom prologJohn = iut.atomise(john);
		
		iut.hasSolution("asserta(id('?'))", prologJohn);
					
		Hashtable<String, Term> solution;
		
		solution = iut.getSolution("person(?, Name, Age, _Gender, _Spouse)", prologJohn);
		assertThat(solution.get("Age").intValue(), is(equalTo(john.getAge())));
		
		john.setAge(24);

		solution = iut.getSolution("person(?, Name, Age, _Gender, _Spouse)", prologJohn);
		assertThat(solution.get("Age").intValue(), is(equalTo(john.getAge())));
		
	}
	
	@Test
	@Ignore
	public void playWithThis() {
		
		Person john = new Person("John", 23, "male");

		iut.consult("target/test-classes/person.pl");
		
		Atom prologJohn = iut.atomise(john);
		
//		iut.hasSolution("asserta(id('?'))", prologJohn);
					
		iut.dumpListing();
		john.dumpDetails();
		
		Hashtable<String, Term> solution;
		
		System.out.println("-------------------------------");
		solution = iut.getSolution("person(?, Name, Age, Gender, Spouse)", prologJohn);
		System.out.println(solution);
		System.out.println("-------------------------------");
		
		System.out.println("John turns 24...");
		john.setAge(24);

		System.out.println("-------------------------------");
		solution = iut.getSolution("person(?, Name, Age, Gender, Spouse)", prologJohn);
		System.out.println(solution);
		System.out.println("-------------------------------");
		
	}
	
	@Test
	public void shouldBeAbleToHandlePseudoJRefs() throws IOException {
		Person john = new Person("John", 23);
		
		String prologJohn = jpl.fli.Prolog.object_to_tag(john);
		
		iut.asserta("talking_to('" + prologJohn + "')");
		
		Person conversationPartner = (Person) jpl.fli.Prolog.tag_to_object(iut.getSolution("talking_to(Who)").get("Who").name());
		
		assertEquals(john, conversationPartner);
				
	}
	
	@Test
	public void shouldBeAbleToReasonWithDeflatedObject() {
		Person john = new Person("John", 23);
		
		iut.assertz("is_married(" + iut.atomise(john) + ")");
		Object somebodyMarried = iut.objectify((Atom) iut.getSolution("is_married(X)").get("X"));
		
		assertEquals(john, somebodyMarried);
	}
	
}
