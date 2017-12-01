package ie.wit.wwe;

import java.io.Serializable;
import java.util.Comparator;

public class RecycleID implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;

	public RecycleID(int id)
	{
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return ""+id;
	}
	
	public static Comparator<RecycleID> sortID = new Comparator<RecycleID>() {
		public int compare(RecycleID first, RecycleID second) {
			return Integer.compare(first.getId(), second.getId());
		}
	};

	
	
	
	
	
	
	
}
