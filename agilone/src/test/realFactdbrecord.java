package test;
/**
 * 
 * a simple record implementation
 *
 */

public class realFactdbrecord implements FactDbRecord {

	private long timestamp;
	private int entityID;
	private String fieldName;
	private String fieldValue;
	public realFactdbrecord next;
	public realFactdbrecord previous;
	public realFactdbrecord(int entityid, String fieldname, String fieldvalue)
	{
		this.entityID=entityid;
		this.fieldName=fieldname;
		this.fieldValue=fieldvalue;
		this.timestamp=System.nanoTime();
	}
	@Override
	public long getMillis() {
		return this.timestamp;
	}

	@Override
	public long getEntityId() {
		return this.entityID;
	}

	@Override
	public String fieldName() {
		return this.fieldName;
	}

	@Override
	public String fieldValue() {
		return this.fieldValue;
	}

	

}
