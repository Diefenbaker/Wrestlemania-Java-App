package ie.wit.wwe;

/* This is our custom Exception class, and is used in our smackdown() method,
 * it is thrown if the user selects the same Wrestler twice.
 */

@SuppressWarnings("serial")
public class CustomEx extends Exception {

	public CustomEx(){
		
		super("You've selected the same Wrestler twice, you must choose two unique wrestlers!");
	}
	
	
	public CustomEx(String message)
	{
		super(message);
	}
	
	
}
