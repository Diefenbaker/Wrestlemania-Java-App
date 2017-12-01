package ie.wit.wwe;

/*The only purpose of this class is to enable the user to reset
 * the program to factory settings. It is home to a constructor, 
 * a getter and setter method. The constructor has only one 
 * variable, and in the program there will only ever be one 
 * instance of this object in use. Once the program is run for the 
 * first time the object is created after creating default wrestlers,
 * each time the program starts it will check if there is an object of 
 * this type or not; if there is the program will not add the wrestlers again.
 */


import java.io.Serializable;

@SuppressWarnings("serial")
public class FreshStart implements Serializable {
	
String inUse;

public FreshStart(String inUse){
	this.inUse = inUse;
}

public String getHasBeenReset() {
	return inUse;
}






}
