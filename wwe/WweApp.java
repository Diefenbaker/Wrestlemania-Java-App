package ie.wit.wwe;

/*The class containing the main menu of the program,
 * user input calls methods from the WrestlerManager class
 * if on-screen instruction is properly performed using a 
 * switch statement. At the beginning of the program the files
 * are read and data loaded in, when user exits the program 
 * data is output to appropriate files.
 */


public class WweApp
{
 
	
   public static void main( String args[] )
   {
	   int option;

	   WrestlerManager manager = new WrestlerManager();
	   manager.readFromFile();
	   do {
	   option = manager.mainMenu();
	   
	   	switch(option)
	   		{
	   	case 1  : manager.addWrestler();
	   			  break;
	   	case 2  : manager.listWrestlers();
	   			  break;
	   	case 3  : manager.updateWrestler();
	   		      break;		
	   	case 4 :  manager.deleteWrestler();
	   			  break;
	   	case 5 :  manager.smackdown();
			      break;
	   	case 6 :  manager.appAdmin(); 
	   	default : break;
	   		}
	   } while(option != 7);
	   manager.saveToFile();
   } 			
}


