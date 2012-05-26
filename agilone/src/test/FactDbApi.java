package test;
/**
* Stateful API for reading from a fact oriented database, which is a sequence
* of fact records ordered from earliest to latest. The state is in the form of
* a cursor that is positioned either (a) before the earliest record, (b)
* between two records, (c) after the latest record.
*/
public interface FactDbApi {
	/**
	* Move the cursor over the next record and return that record.
	*
	* @return a record, or null if cursor is after the latest.
	*/
	FactDbRecord next();
	/**
	* Move the cursor over the previous record and return that record.
	*
	* @return a record, or null if cursor is before the earliest.
	*/
	FactDbRecord previous();
	/**
	* Position the cursor to after the latest record.
	*/
	void gotoAfterLatest();
	/**
	* Position the cursor to before the earliest record.
	*/
	void gotoBeforeEarliest();
}
