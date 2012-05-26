package test;
/**
* A record in a fact database representing the the value of only one field and
* the time that it was set.
*/
public interface FactDbRecord {
	/**
	* The time this field was set to this value, which is what the database
	* uses to order the records into earlier and later.
	*
	* @return time in milliseconds since the start of the epoch. This can never
	* be null.
	*/
	long getMillis();
	/**
	* The id of the entity that this field name and value belongs to.
	*
	* @return a number uniquely identifying the entity. This can never be null.
	*/
	long getEntityId();
	/**
	* The name of the field which values is recorded.
	*
	* @return a string identifying the field. This can never be null.
	*/
	String fieldName();
	/**
	* The recorded value of the field.
	*
	* @return the field value at the time. This can never be null.
	*/
	String fieldValue();
}
