package test;


import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class dbwrapperTest {



	@Test
	public void testGetelements() {
		/**
		* Given some entity_id in 3 case:
		* 	1. the same entity_id before and after changing
		* 	2. the entity_id not include in database
		* 	3. a lot of ordinary entity_id to retrieve fields and test if the cache works well
		*
		*/
		FactDb db = new FactDb();
		/** set maxCapacity of LRU cache =3**/
		dbwrapper wrapper= new dbwrapper(db, 3);
		
		/**test getelements after insertion**/ 
		db.insert(1, "name", "songbo");
		db.insert(1, "major", "database");
		Vector<String> fields=new Vector<String>();
		fields.add("name");
		fields.add("major");
		Vector<String> result =wrapper.getelements(1, fields);
		assertEquals(result.get(0),"songbo");
		assertEquals(result.get(1),"database");
		db.insert(2, "name", "peter");
		db.insert(2, "major", "hadoop");
		result =wrapper.getelements(2, fields);
		assertEquals(result.get(0),"peter");
		assertEquals(result.get(1),"hadoop");
		
		/**test getelements after update**/
		db.update(1, "major", "finance");
		fields.clear();
		fields.add("major");
		result =wrapper.getelements(1, fields);
		System.out.print(result.get(0));
		assertEquals(result.get(0),"finance");
		
		db.insert(3, "name", "toni");
		db.insert(3, "major", "java");
		fields.clear();
		fields.add("name");
		fields.add("major");
		result =wrapper.getelements(2, fields);
		assertEquals(result.get(0),"peter");
		assertEquals(result.get(1),"hadoop");
		
		/**test getelements after LRU cache exceeds**/
		db.insert(4, "name", "tim");
		db.insert(4, "major", "cpp");
		result =wrapper.getelements(4, fields);
		assertEquals(result.get(0),"tim");
		assertEquals(result.get(1),"cpp");
	}

}
