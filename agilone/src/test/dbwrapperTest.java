package test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class dbwrapperTest {

	@Before
	public void setUp() throws Exception {
		/** connect to database, initialize the dbwrapper **/
	}

	@After
	public void tearDown() throws Exception {
		/** disconnect to database **/
	}

	@Test
	public void testGetelements() {
		/**
		* Given some entity_id in 3 case:
		* 	1. the same entity_id before and after changing
		* 	2. the entity_id not include in database
		* 	3. a lot of ordinary entity_id to retrieve fields and test if the cache works well
		*
		*/
	}

}
