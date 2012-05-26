package test;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
/**
* implement getelements(int entity_id, Vector<String> fields) to retrieve required fields base on 
* entity_id. 
*/
public class dbwrapper {
	
	private FactDbApi db;
	/** the latest access time **/
	private long latestMills;
	/** the LRU cache **/
	LRUrecordhashmap<Integer,HashMap<String,String>> resultmap;
	/** maxCapacity is the size of LRU cache **/
	public dbwrapper(int maxCapacity) 
	{
		/** initialize the FactDbApi **/
		/** initialize the LRU cache **/
		resultmap = new LRUrecordhashmap<Integer, HashMap<String,String>>(maxCapacity);
	}
	/**
	* The method return the required field in a vector
	* keep a LRU cache, if fail to find result in cache , retrieve from database.
	* take the timestamp into account
	* @return the required field in a vector. This can be null if entity_id not exists
	* 
	*/
	public Vector<String> getelements(int entity_id, Vector<String> fields)
	{
		Vector<String> result =new Vector<String>();
		FactDbRecord record;
		String fieldname;
		if(resultmap.containsKey(entity_id))
		{
			HashMap<String,String> map = resultmap.get(entity_id);
			db.gotoAfterLatest();
			while((record=db.previous()) != null)
			{
				if(record.getMillis()<=this.latestMills)
					break;
				resultmap.get(record.getEntityId()).put(record.fieldName(), record.fieldValue());
			}			
			Iterator<String> iterator= fields.iterator();
			while(iterator.hasNext())
			{
				fieldname =iterator.next();
				/**check the required field exists or not **/
				if(!map.containsKey(fieldname))
				{
					System.out.println("the field "+fieldname+" not exists");
					return null;
				}
				result.add(map.get(fieldname));
			}
		}
		else {
			HashMap<String, String> resultrecord =new HashMap<String, String>();
			db.gotoAfterLatest();
			
			while((record=db.previous()) != null)
			{
				if(record.getEntityId()==entity_id&&!resultrecord.containsKey((record.fieldName())))
				{
					resultrecord.put(record.fieldName(), record.fieldValue());
				}
			}
			db.gotoAfterLatest();
			latestMills=db.previous().getMillis();
			Iterator<String> iterator= fields.iterator();
			while(iterator.hasNext())
			{
				fieldname =iterator.next();
				/**check the required field exists or not **/
				if(!resultrecord.containsKey(fieldname))
				{
					System.out.println("the field "+fieldname+" not exists");
					return null;
				}
				result.add(resultrecord.get(iterator.next()));
			}
			resultmap.put(entity_id, resultrecord);
		}
		return result;
	}
	

}
