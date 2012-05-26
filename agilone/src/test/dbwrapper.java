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
	public dbwrapper(int maxCapacity) 
	{
		db=new testFactDbApi();
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
				result.add(map.get(iterator.next()));
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
				result.add(resultrecord.get(iterator.next()));
			}
			resultmap.put(entity_id, resultrecord);
		}
		return result;
	}
	

}
