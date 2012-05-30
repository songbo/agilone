package test;
/**
 * 
 * a simple FactDb implementation
 *
 */

public class FactDb implements FactDbApi {

	private realFactdbrecord head;
	private realFactdbrecord end;
	private realFactdbrecord pointer;
	public FactDb() 
	{
		head=new realFactdbrecord(-1,"","");
		end=new realFactdbrecord(-1,"","");
		head=end;
		pointer=head;
	}
	/**
	* insert a <key, value> to Factdb
	*/
	public void insert(int entityid, String fieldname, String fieldvalue)
	{
		if(head==end)
		{
			realFactdbrecord temp =new realFactdbrecord(entityid,fieldname,fieldvalue);
			head= temp;
			head.next=end;
			end.previous=head;
		}
		else {
			realFactdbrecord temp =new realFactdbrecord(entityid,fieldname,fieldvalue);
			end.previous.next=temp;
			temp.previous=end.previous;
			temp.next=end;
			end.previous=temp;
		}
	}
	/**
	* update a <key, value> to Factdb
	*/
	public void update(int entityid, String fieldname, String fieldvalue)
	{
		insert(entityid, fieldname, fieldvalue);
	}
	@Override
	public FactDbRecord next() {
		FactDbRecord result =pointer;
		pointer=pointer.next;
		return result;
	}

	@Override
	public FactDbRecord previous() {
		pointer=pointer.previous;
		return pointer;
	}

	@Override
	public void gotoAfterLatest() {
		pointer=end;

	}

	@Override
	public void gotoBeforeEarliest() {
		pointer=head;

	}

}
