package test;


import java.util.LinkedHashMap;
/**
* implement LRU (Least Recently Used) cache to keep latest used record in memory 
*/
public class LRUrecordhashmap<K, V> extends LinkedHashMap<K, V>{
	
	/** size of record kept in cache **/
	private final int maxCapacity; 
	/** default load factor of the LRU cache **/
	private static final float DEFAULT_LOAD_FACTOR = 0.75f; 
	public LRUrecordhashmap(int maxCapacity)  
    {  
        super(maxCapacity, DEFAULT_LOAD_FACTOR, true);  
        this.maxCapacity = maxCapacity;  
    } 
	 @Override  
	 protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest)  
	 {  
	    return size() > maxCapacity;  
	 }  
}
