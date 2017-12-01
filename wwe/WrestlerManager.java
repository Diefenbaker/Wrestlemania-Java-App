package ie.wit.wwe;

import ie.wit.wweAbstract.Wrestler;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class WrestlerManager {

	/*Below are all of the custom icons & images being called in the app,
	 *coded in a way that will allow the jar file to have a valid path
	 *to the resource folder images are located in.
	 */
	
	private ImageIcon myIcon = new ImageIcon(getClass().getResource(
			"/wrestlemania30.jpg"));
	private static Icon anIcon = new ImageIcon();
	private ImageIcon myIcon2 = new ImageIcon(getClass().getResource(
			"/Superstars.jpg"));
	private ImageIcon logo = new ImageIcon(getClass().getResource("/logo.jpg"));
	private ImageIcon myIcon3 = new ImageIcon(getClass().getResource(
			"/Divas.jpg"));
	private ImageIcon error = new ImageIcon(getClass()
			.getResource("/error.png"));
	private ImageIcon selectType = new ImageIcon(getClass().getResource(
			"/selectType.jpg"));

	private ImageIcon aBanner = new ImageIcon(getClass().getResource(
			"/banner.jpg"));
	private ImageIcon divaBanner = new ImageIcon(getClass().getResource(
			"/divaBanner.jpg"));
	private ImageIcon superBanner = new ImageIcon(getClass().getResource(
			"/superBanner.png"));
	private ImageIcon belt = new ImageIcon(getClass().getResource("/belt.jpg"));
	private ImageIcon caution = new ImageIcon(getClass().getResource(
			"/caution.jpg"));

	
	
	
	ArrayList<Superstar> superstarList = new ArrayList<Superstar>(); // Creating an ArrayList in which
																	 // to store our Superstars

	ArrayList<Diva> divaList = new ArrayList<Diva>(); // Creating an Arraylist for our Diva's

	ArrayList<RecycleID> recIdList = new ArrayList<RecycleID>(); //Creating a list in which we will store
																 //ID's of deleted Wrestlers

	ArrayList<FreshStart> freshStart = new ArrayList<FreshStart>(); //An ArrayList that will be used to enable
																	//a program reset.

	private int currentNum = 0; // Using an int to keep track of and assign New ID's to members,
								// this number is set by totaling the size of
								// all 3 lists in the readFromFile() method

	
	/*
	 * These two Strings had at one point been working, until the smackdown()
	 * method was created (FIGHT!!) but we had to revert to the default toString
	 * method as this one stopped displaying results.
	 * 
	 * String modifiedSuperString = superstarList.toString().replace("[",
	 * "").replace("]", "").trim();
	 * 
	 * String modifiedDivaString = divaList.toString() .replace("[",
	 * "").replace("]", "").trim();
	 */

	
	
	@SuppressWarnings("unchecked")
	public void readFromFile() { // Creating new, or reading in data from
								 // existing, files

		UIManager.put("OptionPane.background",
				new ColorUIResource(0,0,0));
		UIManager.put("Panel.background", new ColorUIResource(0,0,0));  //Customising the GUI look
		UIManager.put("OptionPane.messageForeground", Color.white);

		try {

			File divaIn, superIn, recIdIn, freshIn;
			FileInputStream divaInpStr, superInpStr, recInpStr, freshInpStr;	//Preparing to read in data 
			ObjectInputStream divaOutStr, superOutStr, recOutStr, freshOutStr;	

			divaIn = new File("diva.data");
			superIn = new File("superstar.data");	//Creating the files if not already existing
			recIdIn = new File("recycleID.data");
			freshIn = new File("factory.data");

			divaInpStr = new FileInputStream(divaIn);
			superInpStr = new FileInputStream(superIn);	//Passing the files over to FileInputStreams
			recInpStr = new FileInputStream(recIdIn);
			freshInpStr = new FileInputStream(freshIn);

			divaOutStr = new ObjectInputStream(divaInpStr);
			superOutStr = new ObjectInputStream(superInpStr); //Passing the FileInputStream to the ObjectInputStream
			recOutStr = new ObjectInputStream(recInpStr);
			freshOutStr = new ObjectInputStream(freshInpStr);

			divaList = (ArrayList<Diva>) divaOutStr.readObject(); //Reading objects from the Diva file
			for (Diva temp : divaList) { // Filling the Diva ArrayList with Diva's saved to file
				temp.toString();
				currentNum++;
			}
			divaOutStr.close();

			superstarList = (ArrayList<Superstar>) superOutStr.readObject();
			for (Superstar temp : superstarList) { // Filling the Superstar ArrayList with objects on
				// file
				temp.toString();
				currentNum++;
			}
			superOutStr.close();

			recIdList = (ArrayList<RecycleID>) recOutStr.readObject();
			for (RecycleID temp : recIdList) { // Populating the RecycleID ArrayList with deleted ID's
				// saved to file
				temp.toString();
				currentNum++;
			}
			recOutStr.close();

			freshStart = (ArrayList<FreshStart>) freshOutStr.readObject(); //If this file contains an object it means
			for (FreshStart temp : freshStart) {						   //the program has been in use before.
				temp.toString();
			}
			freshOutStr.close();

			JOptionPane.showMessageDialog(null,
					"Wrestler details have been loaded from file.",
					"Ooooh yeah!", JOptionPane.INFORMATION_MESSAGE, logo);

		} catch (Exception ioe) {
			JOptionPane
			.showMessageDialog(
					null,
					"No files have been found, don't worry, I'll create them now!",
					"Creating New Files",
					JOptionPane.INFORMATION_MESSAGE, logo);
		}

		/*This next code checks to see if the program is being run for the first time
		 * or if the Reset function has been called. If the program has been in use
		 * the freshStart ArrayList will contain data, however if it has not been in use
		 * or has been reset the list will have been emptied, in both of these cases
		 * the following default wrestlers are added.
		 */

		if (freshStart.isEmpty()) {
			
				Superstar default1 = new Superstar(1, "Finlay", 1, 28, 10, 14,
						7, "Male");
				Superstar default2 = new Superstar(2, "Roman Reigns", 0, 24,
						17, 8, 13, "Male");
				Superstar default3 = new Superstar(3, "Shawn Michaels", 3, 31,
						15, 14, 9, "Male");
				Superstar default4 = new Superstar(4, "Bad News Barrett", 2,
						29, 17, 6, 11, "Male");

				superstarList.add(default1);
				currentNum++;
				superstarList.add(default2);
				currentNum++;
				superstarList.add(default3);
				currentNum++;
				superstarList.add(default4);
				currentNum++;
			

				Diva default5 = new Diva(5, "AJ Lee", 0, 22, 9, 17, 11,
						"Female");
				Diva default6 = new Diva(6, "Cameron", 1, 21, 12, 14, 15,
						"Female");
				Diva default7 = new Diva(7, "Alicia Fox", 2, 25, 15, 9, 17,
						"Female");
				Diva default8 = new Diva(8, "Eden", 0, 20, 7, 19, 11, "Female");

				divaList.add(default5);
				currentNum++;
				divaList.add(default6);
				currentNum++;
				divaList.add(default7);
				currentNum++;
				divaList.add(default8);
				currentNum++;
			}
			FreshStart noLongerNew = new FreshStart("In Use"); 	//Adding data to the freshStart list to
			freshStart.add(noLongerNew);						//indicate that the program has been in use.
		
	}

	public int mainMenu() { // The main menu of the program

		int option = 0;

		String opt1 = new String("1. Add a Wrestler");
		String opt2 = new String("2. List Wrestlers");
		String opt3 = new String("3. Update a Wrestler (by ID)"); // The options on display in the main menu
		String opt4 = new String("4. Delete a Wrestler (by ID)");
		String opt5 = new String("5. FIGHT!!");
		String opt6 = new String("6. App Admin (Careful now!)");
		String opt7 = new String("7. Exit Program");
		String msg = new String("Enter Option:");
		JTextField opt = new JTextField(""); // An input area for the users choice
		String info = new String("There are currently "
				+ (superstarList.size() + divaList.size())
				+ " Wrestlers stored in the App.");

		Object message[] = new Object[11]; // Creating an object array for the menu

		message[0] = myIcon;
		message[1] = opt1;
		message[2] = opt2;
		message[3] = opt3;
		message[4] = opt4;
		message[5] = opt5; // populating the menu array
		message[6] = opt6;
		message[7] = opt7;
		message[8] = msg;
		message[9] = opt;
		message[10] = info;

		int response = JOptionPane.showConfirmDialog(null,
				message, 									//displaying the menu and allowing the user to choose using a number 
				"Main Menu", JOptionPane.OK_CANCEL_OPTION,  //in the Textfield using a number in the Textfield
				JOptionPane.QUESTION_MESSAGE, anIcon);
		
		

		if (response == JOptionPane.CANCEL_OPTION) {//If the user decides to cancel at this point 
				saveToFile();						//the program is shut down
				System.exit(0);					    
				}								  
		else {
			try {
				option = Integer.parseInt(opt.getText()); //grabbing the users number contained in the String, 
			} catch (Exception e) {						  //changing it to an int to be used for method selection	
				JOptionPane.showMessageDialog(null,
						"Data Input Error Please Try Again",
						"You gotta give me something....",
						JOptionPane.INFORMATION_MESSAGE, error);
			}
		}
		return option;

	}

	// Because of the way we're using the JComboBox
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addWrestler() {

		Collections.sort(recIdList, RecycleID.sortID); // If there are ID's contained in the RecID list, sorting them in ascending 
													   // order as opposed to the order in which they were added.
		String[] list = { "Superstar", "Diva" }; // Creating and populating an array to display in a dropdown menu.
		JComboBox type = new JComboBox(list); // Creating the dropdown menu and calling the array in.

		JOptionPane.showMessageDialog(null, type, "Select Wrestler Type", // Displaying the dropdown box to the user.
				JOptionPane.QUESTION_MESSAGE, selectType);

		Object choice = type.getSelectedItem(); // Taking in the users choice

		if (choice == "Superstar") {

			if (!recIdList.isEmpty()) {
				String[] idList = { "Recycle ID", "Create new ID" }; //Checking if there are previously used ID's available,
				JComboBox chooseID = new JComboBox(idList); // creating option of using one of these or creating a new ID.

				JOptionPane
				.showMessageDialog(null, chooseID, "Whatcha gonna do?",
						JOptionPane.QUESTION_MESSAGE, logo); // Displaying the choice to the user

				Object response = chooseID.getSelectedItem(); // Getting the users choice

				if (response == "Recycle ID") {

					String msgId = new String("Wrestler ID: ");
					String msgName = new String("Wrestler Name: ");
					String msgTitles = new String("Titles Held: ");
					String msgAge = new String("Wrestler Age: ");
					String msgStrength = new String("Wrestler Strength: ");
					String msgSpeed = new String("Wrestler Speed: ");
					String msgLuck = new String("Wrestler Luck: ");
					String msgGender = new String("Wrestler Gender: ");

					JComboBox id = new JComboBox(recIdList.toArray()); // Creating and populating a dropdown box with
																	   // previously deleted ID's
					JTextField name = new JTextField("");
					JTextField titles = new JTextField("");
					JTextField age = new JTextField("");
					String[] strValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16", // Setting the attribute values the user can assign to a Wrestler
							"17", "18", "19", "20" };
					JComboBox strength = new JComboBox(strValue); // Displaying the values in a dropdown box
					String[] spValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox speed = new JComboBox(spValue);
					String[] luckValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox luck = new JComboBox(luckValue);
					JTextField gender = new JTextField("Male"); // As this is a Superstar the gender should be Male, but we 
																// won't judge, the user can change to whatever.

					Object message[] = new Object[17];

					message[0] = myIcon2;
					message[1] = msgId;
					message[2] = id;
					message[3] = msgName;
					message[4] = name;
					message[5] = msgTitles;
					message[6] = titles;
					message[7] = msgAge;
					message[8] = age;
					message[9] = msgStrength;
					message[10] = strength;
					message[11] = msgSpeed;
					message[12] = speed;
					message[13] = msgLuck;
					message[14] = luck;
					message[15] = msgGender;
					message[16] = gender;

					int response2 = JOptionPane.showConfirmDialog(null,
							message, "Enter Superstar details",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, anIcon);

					if (response2 == JOptionPane.CANCEL_OPTION)
						;
					else {
						try {

							Object whatID = id.getSelectedItem(); // Taking in the users choice from the dropdown box
							int id1 = ((RecycleID) whatID).getId(); // Taking the ID value from the user choice,
																	// assigning it to the new Wrestler
							String name1 = name.getText(); // Taking in the users choice of name from the Textfield
							int title1 = Integer.parseInt(titles.getText()); // Taking in number of titles from
																			 // Textfield, changing it to an int.
							int age1 = Integer.parseInt(age.getText());
							int strength1 = Integer.parseInt((String) strength
									.getSelectedItem());
							int speed1 = Integer.parseInt((String) speed
									.getSelectedItem());
							int luck1 = Integer.parseInt((String) luck
									.getSelectedItem());
							String gender1 = gender.getText();

							Superstar superstar1 = new Superstar(id1, name1,
									title1, age1, strength1, speed1, luck1, // Creating a new Superstar by calling the 
									gender1);							    // constructer and passing in required variables.
							superstarList.add(superstar1); // Adding the new Superstar to the ArrayList

							recIdList.remove(whatID); // Removing the now used ID from the Recycle ID list

							JOptionPane.showMessageDialog(null,
									"Superstar has been added.", "Welcome "
											+ superstar1.getName()
											+ ", to the arena....",
											JOptionPane.INFORMATION_MESSAGE, logo);
						}

						catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error (" + e
									+ ")");
						}

					}
				} else {

					String msgId = new String("Wrestler ID: ");
					String msgName = new String("Wrestler Name: ");
					String msgTitles = new String("Titles Held: ");
					String msgAge = new String("Wrestler Age: ");
					String msgStrength = new String("Wrestler Strength: ");
					String msgSpeed = new String("Wrestler Speed: ");
					String msgLuck = new String("Wrestler Luck: ");
					String msgGender = new String("Wrestler Gender: ");

					int id = currentNum + 1; // Creating a new ID using the number of objects in all three lists and adding 1
					JTextField name = new JTextField("");
					JTextField titles = new JTextField("");
					JTextField age = new JTextField("");
					String[] strValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox strength = new JComboBox(strValue);
					String[] spValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox speed = new JComboBox(spValue);
					String[] luckValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox luck = new JComboBox(luckValue);
					JTextField gender = new JTextField("Male");

					Object message[] = new Object[17];

					message[0] = myIcon2;
					message[1] = msgId;
					message[2] = id;
					message[3] = msgName;
					message[4] = name;
					message[5] = msgTitles;
					message[6] = titles;
					message[7] = msgAge;
					message[8] = age;
					message[9] = msgStrength;
					message[10] = strength;
					message[11] = msgSpeed;
					message[12] = speed;
					message[13] = msgLuck;
					message[14] = luck;
					message[15] = msgGender;
					message[16] = gender;

					int response3 = JOptionPane.showConfirmDialog(null,
							message, "Enter Superstar details",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, anIcon);

					if (response3 == JOptionPane.CANCEL_OPTION)
						;
					else {
						try {

							int id1 = id;
							String name1 = name.getText();
							int title1 = Integer.parseInt(titles.getText());
							int age1 = Integer.parseInt(age.getText());
							int strength1 = Integer.parseInt((String) strength
									.getSelectedItem());
							int speed1 = Integer.parseInt((String) speed
									.getSelectedItem());
							int luck1 = Integer.parseInt((String) luck
									.getSelectedItem());
							String gender1 = gender.getText();

							Superstar superstar1 = new Superstar(id1, name1,
									title1, age1, strength1, speed1, luck1,
									gender1);
							superstarList.add(superstar1);
							currentNum++;

							JOptionPane.showMessageDialog(null,
									"Superstar has been added.", "Welcome "
											+ superstar1.getName()
											+ ", to the arena....",
											JOptionPane.INFORMATION_MESSAGE, logo);
						}

						catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error (" + e
									+ ")");
						}

					}
				}
			} else {
				String msgId = new String("Wrestler ID: ");
				String msgName = new String("Wrestler Name: ");
				String msgTitles = new String("Titles Held: ");
				String msgAge = new String("Wrestler Age: ");
				String msgStrength = new String("Wrestler Strength: ");
				String msgSpeed = new String("Wrestler Speed: ");
				String msgLuck = new String("Wrestler Luck: ");
				String msgGender = new String("Wrestler Gender: ");

				int id = currentNum + 1;
				JTextField name = new JTextField("");
				JTextField titles = new JTextField("");
				JTextField age = new JTextField("");
				String[] strValue = { "1", "2", "3", "4", "5", "6", "7", "8",
						"9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20" };
				JComboBox strength = new JComboBox(strValue);
				String[] spValue = { "1", "2", "3", "4", "5", "6", "7", "8",
						"9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20" };
				JComboBox speed = new JComboBox(spValue);
				String[] luckValue = { "1", "2", "3", "4", "5", "6", "7", "8",
						"9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20" };
				JComboBox luck = new JComboBox(luckValue);
				JTextField gender = new JTextField("Male");

				Object message[] = new Object[17];

				message[0] = myIcon2;
				message[1] = msgId;
				message[2] = id;
				message[3] = msgName;
				message[4] = name;
				message[5] = msgTitles;
				message[6] = titles;
				message[7] = msgAge;
				message[8] = age;
				message[9] = msgStrength;
				message[10] = strength;
				message[11] = msgSpeed;
				message[12] = speed;
				message[13] = msgLuck;
				message[14] = luck;
				message[15] = msgGender;
				message[16] = gender;

				int response3 = JOptionPane.showConfirmDialog(null, message,
						"Enter Superstar details",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, anIcon);

				if (response3 == JOptionPane.CANCEL_OPTION)
					;
				else {
					try {

						int id1 = id;
						String name1 = name.getText();
						int title1 = Integer.parseInt(titles.getText());
						int age1 = Integer.parseInt(age.getText());
						int strength1 = Integer.parseInt((String) strength
								.getSelectedItem());
						int speed1 = Integer.parseInt((String) speed
								.getSelectedItem());
						int luck1 = Integer.parseInt((String) luck
								.getSelectedItem());
						String gender1 = gender.getText();

						Superstar superstar1 = new Superstar(id1, name1,
								title1, age1, strength1, speed1, luck1, gender1);
						superstarList.add(superstar1);
						currentNum++;

						JOptionPane.showMessageDialog(null,
								"Superstar has been added.", "Welcome "
										+ superstar1.getName()
										+ ", to the arena....",
										JOptionPane.INFORMATION_MESSAGE, logo);
					}

					catch (Exception e) {
						JOptionPane
						.showMessageDialog(null, "Error (" + e + ")");
					}

				}
			}
		}

		else {

			if (!recIdList.isEmpty()) {
				String[] idList = { "Recycle ID", "Create new ID" };
				JComboBox chooseID = new JComboBox(idList);

				JOptionPane
				.showMessageDialog(null, chooseID, "Whatcha gonna do?",
						JOptionPane.QUESTION_MESSAGE, logo);

				Object response = chooseID.getSelectedItem();

				if (response == "Recycle ID") {

					String msgId = new String("Wrestler ID: ");
					String msgName = new String("Wrestler Name: ");
					String msgTitles = new String("Titles Held: ");
					String msgAge = new String("Wrestler Age: ");
					String msgStrength = new String("Wrestler Strength: ");
					String msgSpeed = new String("Wrestler Speed: ");
					String msgLuck = new String("Wrestler Luck: ");
					String msgGender = new String("Wrestler Gender: ");

					JComboBox id = new JComboBox(recIdList.toArray());
					JTextField name = new JTextField("");
					JTextField titles = new JTextField("");
					JTextField age = new JTextField("");
					String[] strValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox strength = new JComboBox(strValue);
					String[] spValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox speed = new JComboBox(spValue);
					String[] luckValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox luck = new JComboBox(luckValue);
					JTextField gender = new JTextField("Female");

					Object message[] = new Object[17];

					message[0] = myIcon3;
					message[1] = msgId;
					message[2] = id;
					message[3] = msgName;
					message[4] = name;
					message[5] = msgTitles;
					message[6] = titles;
					message[7] = msgAge;
					message[8] = age;
					message[9] = msgStrength;
					message[10] = strength;
					message[11] = msgSpeed;
					message[12] = speed;
					message[13] = msgLuck;
					message[14] = luck;
					message[15] = msgGender;
					message[16] = gender;

					int response2 = JOptionPane.showConfirmDialog(null,
							message, "Enter Diva details",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, anIcon);

					if (response2 == JOptionPane.CANCEL_OPTION)
						;
					else {
						try {

							Object whatID = id.getSelectedItem();
							int id1 = ((RecycleID) whatID).getId();
							String name1 = name.getText();
							int title1 = Integer.parseInt(titles.getText());
							int age1 = Integer.parseInt(age.getText());
							int strength1 = Integer.parseInt((String) strength
									.getSelectedItem());
							int speed1 = Integer.parseInt((String) speed
									.getSelectedItem());
							int luck1 = Integer.parseInt((String) luck
									.getSelectedItem());
							String gender1 = gender.getText();

							Diva diva1 = new Diva(id1, name1, title1, age1,
									strength1, speed1, luck1, gender1);
							divaList.add(diva1);

							recIdList.remove(whatID);

							JOptionPane.showMessageDialog(null,
									"Superstar has been added.", "Welcome "
											+ diva1.getName()
											+ ", to the arena....",
											JOptionPane.INFORMATION_MESSAGE, logo);
						}

						catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error (" + e
									+ ")");
						}

					}
				} else {

					String msgId = new String("Wrestler ID: ");
					String msgName = new String("Wrestler Name: ");
					String msgTitles = new String("Titles Held: ");
					String msgAge = new String("Wrestler Age: ");
					String msgStrength = new String("Wrestler Strength: ");
					String msgSpeed = new String("Wrestler Speed: ");
					String msgLuck = new String("Wrestler Luck: ");
					String msgGender = new String("Wrestler Gender: ");

					int id = currentNum + 1;
					JTextField name = new JTextField("");
					JTextField titles = new JTextField("");
					JTextField age = new JTextField("");
					String[] strValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox strength = new JComboBox(strValue);
					String[] spValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox speed = new JComboBox(spValue);
					String[] luckValue = { "1", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "11", "12", "13", "14", "15", "16",
							"17", "18", "19", "20" };
					JComboBox luck = new JComboBox(luckValue);
					JTextField gender = new JTextField("Female");

					Object message[] = new Object[17];

					message[0] = myIcon3;
					message[1] = msgId;
					message[2] = id;
					message[3] = msgName;
					message[4] = name;
					message[5] = msgTitles;
					message[6] = titles;
					message[7] = msgAge;
					message[8] = age;
					message[9] = msgStrength;
					message[10] = strength;
					message[11] = msgSpeed;
					message[12] = speed;
					message[13] = msgLuck;
					message[14] = luck;
					message[15] = msgGender;
					message[16] = gender;

					int response3 = JOptionPane.showConfirmDialog(null,
							message, "Enter Diva details",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, anIcon);

					if (response3 == JOptionPane.CANCEL_OPTION)
						;
					else {
						try {

							int id1 = id;
							String name1 = name.getText();
							int title1 = Integer.parseInt(titles.getText());
							int age1 = Integer.parseInt(age.getText());
							int strength1 = Integer.parseInt((String) strength
									.getSelectedItem());
							int speed1 = Integer.parseInt((String) speed
									.getSelectedItem());
							int luck1 = Integer.parseInt((String) luck
									.getSelectedItem());
							String gender1 = gender.getText();

							Diva diva1 = new Diva(id1, name1, title1, age1,
									strength1, speed1, luck1, gender1);
							divaList.add(diva1);
							currentNum++;

							JOptionPane.showMessageDialog(null,
									"Superstar has been added.", "Welcome "
											+ diva1.getName()
											+ ", to the arena....",
											JOptionPane.INFORMATION_MESSAGE, logo);
						}

						catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error (" + e
									+ ")");
						}

					}
				}
			} else {
				String msgId = new String("Wrestler ID: ");
				String msgName = new String("Wrestler Name: ");
				String msgTitles = new String("Titles Held: ");
				String msgAge = new String("Wrestler Age: ");
				String msgStrength = new String("Wrestler Strength: ");
				String msgSpeed = new String("Wrestler Speed: ");
				String msgLuck = new String("Wrestler Luck: ");
				String msgGender = new String("Wrestler Gender: ");

				int id = currentNum + 1;
				JTextField name = new JTextField("");
				JTextField titles = new JTextField("");
				JTextField age = new JTextField("");
				String[] strValue = { "1", "2", "3", "4", "5", "6", "7", "8",
						"9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20" };
				JComboBox strength = new JComboBox(strValue);
				String[] spValue = { "1", "2", "3", "4", "5", "6", "7", "8",
						"9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20" };
				JComboBox speed = new JComboBox(spValue);
				String[] luckValue = { "1", "2", "3", "4", "5", "6", "7", "8",
						"9", "10", "11", "12", "13", "14", "15", "16", "17",
						"18", "19", "20" };
				JComboBox luck = new JComboBox(luckValue);
				JTextField gender = new JTextField("Female");

				Object message[] = new Object[17];

				message[0] = myIcon3;
				message[1] = msgId;
				message[2] = id;
				message[3] = msgName;
				message[4] = name;
				message[5] = msgTitles;
				message[6] = titles;
				message[7] = msgAge;
				message[8] = age;
				message[9] = msgStrength;
				message[10] = strength;
				message[11] = msgSpeed;
				message[12] = speed;
				message[13] = msgLuck;
				message[14] = luck;
				message[15] = msgGender;
				message[16] = gender;

				int response3 = JOptionPane.showConfirmDialog(null, message,
						"Enter Diva details", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, anIcon);

				if (response3 == JOptionPane.CANCEL_OPTION)
					;
				else {
					try {

						int id1 = id;
						String name1 = name.getText();
						int title1 = Integer.parseInt(titles.getText());
						int age1 = Integer.parseInt(age.getText());
						int strength1 = Integer.parseInt((String) strength
								.getSelectedItem());
						int speed1 = Integer.parseInt((String) speed
								.getSelectedItem());
						int luck1 = Integer.parseInt((String) luck
								.getSelectedItem());
						String gender1 = gender.getText();

						Diva diva1 = new Diva(id1, name1, title1, age1,
								strength1, speed1, luck1, gender1);
						divaList.add(diva1);
						currentNum++;

						JOptionPane.showMessageDialog(null,
								"Superstar has been added.",
								"Welcome " + diva1.getName()
								+ ", to the arena....",
								JOptionPane.INFORMATION_MESSAGE, logo);
					}

					catch (Exception e) {
						JOptionPane
						.showMessageDialog(null, "Error (" + e + ")");
					}

				}
			}
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// Because of the way we're using the JComboBox
	public void smackdown() {

		if (superstarList.isEmpty() && divaList.isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"There are no Wrestlers in the program.",
					"A fight's not a fight without some fighters...",	// If returns as true no wrestlers are present
					JOptionPane.INFORMATION_MESSAGE, error);
		} 
		else {
			String[] list = { "Superstar", "Diva" };
			JComboBox type = new JComboBox(list);

			JOptionPane.showMessageDialog(null, type,
					"Select Wrestler type to matchup:",
					JOptionPane.QUESTION_MESSAGE, selectType);

			Object choice = type.getSelectedItem();

			if (choice == "Diva") {

				String msg1 = new String("Select your first fighter:");
				JComboBox option = new JComboBox(divaList.toArray()); // Creating a menu that will display two dropdown boxes, both filled
				String msg2 = new String("Select your second fighter:"); // with the same list, allowing user to choose two Divas to fight
				JComboBox option2 = new JComboBox(divaList.toArray());

				Object[] display = new Object[5];

				
				display[0] = divaBanner;
				display[1] = msg1;
				display[2] = option;
				display[3] = msg2;
				display[4] = option2;

				int response = JOptionPane.showConfirmDialog(null, display,
						"Lets get ready to ruuuuuuuumble!",
						JOptionPane.OK_CANCEL_OPTION);

				if (response == JOptionPane.CANCEL_OPTION)
					;

				else {

					Object rumbleChoice = option.getSelectedItem(); // getting the two Wrestlers the user has chosen
					Object rumbleChoice2 = option2.getSelectedItem();

					try {

						if (rumbleChoice == rumbleChoice2) { // If the user has selected the same Wrestler in
							throw new CustomEx();			 // both boxes a custom Exception is thrown
						}

						int stat1 = ((Diva) rumbleChoice).getTotalAttributes();
						int stat2 = ((Diva) rumbleChoice2).getTotalAttributes(); // Getting the totaled attributes of both Wrestlers to compare
																				 // below, determining a winner or else if attribs equal we say
																				 // too close to call.

						if (stat1 > stat2) {
							JOptionPane.showMessageDialog(
									null,
									"We predict that "
											+ ((Wrestler) rumbleChoice)
											.getName()
											+ " would win this match.",
											"We have a winner!",
											JOptionPane.INFORMATION_MESSAGE, belt);
						} else if (stat1 < stat2) {
							JOptionPane.showMessageDialog(
									null,
									"We predict that "
											+ ((Wrestler) rumbleChoice2)
											.getName()
											+ " would win this match.",
											"We have a winner!",
											JOptionPane.INFORMATION_MESSAGE, belt);

						} else {
							JOptionPane
							.showMessageDialog(
									null,
									"This one is"
											+ " too close to call, don't bet the house!",
											"Toss a coin instead....",
											JOptionPane.INFORMATION_MESSAGE,
											logo);

						}
					} catch (CustomEx e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}

			else {
				String msg1 = new String("Select your first fighter:");
				JComboBox option = new JComboBox(superstarList.toArray());
				String msg2 = new String("Select your second fighter:");
				JComboBox option2 = new JComboBox(superstarList.toArray());

				Object[] display = new Object[5];

				display[0] = superBanner;
				display[1] = msg1;
				display[2] = option;
				display[3] = msg2;
				display[4] = option2;

				int response = JOptionPane.showConfirmDialog(null, display,
						"Lets get ready to ruuuuuuuumble!",
						JOptionPane.OK_CANCEL_OPTION);

				if (response == JOptionPane.CANCEL_OPTION)
					;

				else {

					Object rumbleChoice = option.getSelectedItem();
					Object rumbleChoice2 = option2.getSelectedItem();

					try {

						if (rumbleChoice == rumbleChoice2) {
							throw new CustomEx();
						}

						int stat1 = ((Superstar) rumbleChoice)
								.getTotalAttributes();
						int stat2 = ((Superstar) rumbleChoice2)
								.getTotalAttributes();

						if (stat1 > stat2) {
							JOptionPane.showMessageDialog(
									null,
									"We predict that "
											+ ((Wrestler) rumbleChoice)
											.getName()
											+ " would win this match.",
											"We have a winner!",
											JOptionPane.INFORMATION_MESSAGE, belt);
						} else if (stat1 < stat2) {
							JOptionPane.showMessageDialog(
									null,
									"We predict that "
											+ ((Wrestler) rumbleChoice2)
											.getName()
											+ " would win this match.",
											"We have a winner!",
											JOptionPane.INFORMATION_MESSAGE, belt);

						} else {
							JOptionPane
							.showMessageDialog(
									null,
									"This one is"
											+ " too close to call, don't bet the house!",
											"Toss a coin instead....",
											JOptionPane.INFORMATION_MESSAGE,
											logo);

						}
					} catch (CustomEx e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		}
	}
	
	
	
	/*This next method has two separate but similar funtions - delete all objects from 
	 * Superstar, Diva and RecycleID lists, or alternatively Reset the program to factory 
	 * settings, ie delete objects from said three lists and also the FreshStart list. 
	 * Delete all uses the .clear method to remove all objects, Reset also uses clear but
	 * as the FreshStart list would be empty upon next startup the default Wrestlers would be 
	 * added again.
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void appAdmin() {

		String[] list = { "Delete All Wrestlers", "Reset to Factory Settings" };

		JComboBox select = new JComboBox(list);

		int userSelect = JOptionPane.showConfirmDialog(null, select,
				"Approach with caution....", JOptionPane.CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, caution);

		if (userSelect == JOptionPane.CANCEL_OPTION)
			;

		else {
			Object userChoice = select.getSelectedItem();

			if (userChoice == "Delete All Wrestlers") {
				if (!(superstarList.isEmpty() && divaList.isEmpty())) {

					int response = JOptionPane
							.showConfirmDialog(
									null,
									"Warning! Are you sure you want to delete ALL wrestlers?"
											+ "\n(This will clear all files, including recyclable ID's)",
											"Sad to see them go....",
											JOptionPane.OK_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE, caution);

					if (response == JOptionPane.CANCEL_OPTION)
						;

					else {
						superstarList.clear();
						divaList.clear();
						recIdList.clear();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"There are no Wrestlers on file.",
							"Add some wrestlers instead....",
							JOptionPane.INFORMATION_MESSAGE, error);
				}
			}

			else {
				int response = JOptionPane
						.showConfirmDialog(
								null,
								"Warning! Are you sure you want to reset the App?\n"
										+ "(This will reset the App to Factory Settings)",
										"Thinking of starting over?",
										JOptionPane.OK_CANCEL_OPTION,
										JOptionPane.QUESTION_MESSAGE, caution);

				if (response == JOptionPane.CANCEL_OPTION)
					;

				else {
					superstarList.clear();
					divaList.clear();
					recIdList.clear();
					freshStart.clear();
					saveToFile();
					JOptionPane
					.showMessageDialog(
							null,
							"The program will revert to Factory Settings when next started.",
							"Like a phoenix....",
							JOptionPane.INFORMATION_MESSAGE, logo);
					System.exit(0);
				}
			}
		}
	}
	
	
	
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// Because of the way we're using the JComboBox
	public void listWrestlers() {

		String[] list = { "Superstar", "Diva" };
		JComboBox type = new JComboBox(list);

		JOptionPane.showMessageDialog(null, type,
				"Select Wrestler type to display:",
				JOptionPane.QUESTION_MESSAGE, selectType);

		Object choice = type.getSelectedItem();

		if (choice == "Superstar") {

			if (superstarList.isEmpty() == true) {
				JOptionPane.showMessageDialog(null,
						"There are no Superstars on file.",
						"Can't show you nothing....",
						JOptionPane.INFORMATION_MESSAGE, error);
			} else {

				String[] list1 = { "ID (Asc)", "ID (Desc)", "Name (Asc)",
						"Name (Desc)", "Age (Asc)", "Age (Desc)",
						"Titles (Asc)", "Titles (Desc)", "Strength (Asc)",
						"Strength (Desc)", "Speed (Asc)", "Speed (Desc)",
						"Luck (Asc)", "Luck (Desc)" };
				JComboBox type1 = new JComboBox(list1);

				JOptionPane.showMessageDialog(null, type1,
						"Select attribute to sort by:",
						JOptionPane.QUESTION_MESSAGE, logo);

				Object choice1 = type1.getSelectedItem();

				if (choice1 == "Name (Asc)") {
					Collections.sort(superstarList, Superstar.sortName);
				}

				else if (choice1 == "Name (Desc)") {
					Collections.sort(superstarList,
							Collections.reverseOrder(Superstar.sortName));
				}

				else if (choice1 == "Age (Asc)") {
					Collections.sort(superstarList, Superstar.sortAge); // These functions allow the user to list all the
				}														// Wrestlers saved to file by first choosin type, then 
				else if (choice1 == "Age (Desc)") { 					// choosing one of a selection of different sorting methods 
																		// based on their attributes using Comparator methods 		
					Collections.sort(superstarList,						// contained in the Diva and Superstarclasses
							Collections.reverseOrder(Superstar.sortAge));
				}

				else if (choice1 == "Titles (Asc)") {
					Collections.sort(superstarList, Superstar.sortTitles);
				}

				else if (choice1 == "Titles (Desc)") {
					Collections.sort(superstarList,
							Collections.reverseOrder(Superstar.sortTitles));
				}

				else if (choice1 == "Strength (Asc)") {
					Collections.sort(superstarList, Superstar.sortStrength);
				}

				else if (choice1 == "Strength (Desc)") {
					Collections.sort(superstarList,
							Collections.reverseOrder(Superstar.sortStrength));
				}

				else if (choice1 == "Speed (Asc)") {
					Collections.sort(superstarList, Superstar.sortSpeed);
				}

				else if (choice1 == "Speed (Desc)") {
					Collections.sort(superstarList,
							Collections.reverseOrder(Superstar.sortSpeed));
				}

				else if (choice1 == "Luck (Asc)") {
					Collections.sort(superstarList, Superstar.sortLuck);
				}

				else if (choice1 == "Luck (Desc)") {
					Collections.sort(superstarList,
							Collections.reverseOrder(Superstar.sortLuck));
				}

				else if (choice1 == "ID (Asc)") {
					Collections.sort(superstarList, Superstar.sortID);
				}

				else {
					Collections.sort(superstarList,
							Collections.reverseOrder(Superstar.sortID));
				}
				{

					JOptionPane.showMessageDialog(null,
							superstarList.toString(), "Your Superstars...",
							JOptionPane.INFORMATION_MESSAGE, logo);

				}
			}
		}

		else if (choice == "Diva") {
			if (divaList.isEmpty() == true) {
				JOptionPane.showMessageDialog(null,
						"There are no Divas on file.",
						"Can't show you nothing....",
						JOptionPane.INFORMATION_MESSAGE, error);
			} else {

				String[] list1 = { "ID (Asc)", "ID (Desc)", "Name (Asc)",
						"Name (Desc)", "Age (Asc)", "Age (Desc)",
						"Titles (Asc)", "Titles (Desc)", "Strength (Asc)",
						"Strength (Desc)", "Speed (Asc)", "Speed (Desc)",
						"Luck (Asc)", "Luck (Desc)" };
				JComboBox type1 = new JComboBox(list1);

				JOptionPane.showMessageDialog(null, type1,
						"Select attribute to sort by:",
						JOptionPane.QUESTION_MESSAGE, logo);

				Object choice1 = type1.getSelectedItem();

				if (choice1 == "Name (Asc)") {
					Collections.sort(divaList, Diva.sortName);
				}

				else if (choice1 == "Name (Desc)") {
					Collections.sort(divaList,
							Collections.reverseOrder(Diva.sortName));
				}

				else if (choice1 == "Age (Asc)") {
					Collections.sort(divaList, Diva.sortAge);
				}

				else if (choice1 == "Age (Desc)") {
					Collections.sort(divaList,
							Collections.reverseOrder(Diva.sortAge));
				}

				else if (choice1 == "Titles (Asc)") {
					Collections.sort(divaList, Diva.sortTitles);
				}

				else if (choice1 == "Titles (Desc)") {
					Collections.sort(divaList,
							Collections.reverseOrder(Diva.sortTitles));
				}

				else if (choice1 == "Strength (Asc)") {
					Collections.sort(divaList, Diva.sortStrength);
				}

				else if (choice1 == "Strength (Desc)") {
					Collections.sort(divaList,
							Collections.reverseOrder(Diva.sortStrength));
				}

				else if (choice1 == "Speed (Asc)") {
					Collections.sort(divaList, Diva.sortSpeed);
				}

				else if (choice1 == "Speed (Desc)") {
					Collections.sort(divaList,
							Collections.reverseOrder(Diva.sortSpeed));
				}

				else if (choice1 == "Luck (Asc)") {
					Collections.sort(divaList, Diva.sortLuck);
				}

				else if (choice1 == "Luck (Desc)") {
					Collections.sort(divaList,
							Collections.reverseOrder(Diva.sortLuck));
				}

				else if (choice1 == "ID (Asc)") {
					Collections.sort(divaList, Diva.sortID);
				}

				else {
					Collections.sort(divaList,
							Collections.reverseOrder(Diva.sortID));
				}

				JOptionPane.showMessageDialog(null, divaList.toString(),
						"Your Diva's....", JOptionPane.INFORMATION_MESSAGE,
						logo);
			}
		}
	}

	
	
	Superstar superId(int id, ArrayList<Superstar> superstarList) { // Search functions used in the Update and Delete options
		for (Superstar temp : superstarList) {					    // to find and retrieve the users desired objects
			if (temp.getId() == id)
				return temp;

		}
		throw new IllegalStateException("ID: " + id
				+ " is not found in the list of Superstars.");
	}

	Diva divaId(int id, ArrayList<Diva> divaList) {
		for (Diva temp : divaList) {
			if (temp.getId() == id)
				return temp;

		}
		throw new IllegalStateException("ID: " + id
				+ " is not found in the list of Divas.");
	}

	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	// Because of the way we're using the JComboBox
	public void updateWrestler() {

		String[] list = { "Superstar", "Diva" };
		JComboBox type = new JComboBox(list);

		JOptionPane.showMessageDialog(null, type,
				"Select Wrestler type to update:",
				JOptionPane.QUESTION_MESSAGE, selectType);

		Object choice = type.getSelectedItem();

		if (choice == "Superstar") {

			if (superstarList.isEmpty() == true) {
				JOptionPane.showMessageDialog(null,
						"There are no Superstars to update.",
						"Nobody's home....", JOptionPane.INFORMATION_MESSAGE,
						logo);
			} else {
				try {
					String msgUpdate = new String(
							"Enter ID of Superstar you want to update:");
					JTextField choice2 = new JTextField("");

					Object message[] = new Object[3];

					message[0] = aBanner;
					message[1] = msgUpdate;
					message[2] = choice2;

					int response = JOptionPane.showConfirmDialog(null, message,
							"Update Superstar Details",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, anIcon);

					if (response == JOptionPane.CANCEL_OPTION)
						;

					else {
						try {

							int superID = Integer.parseInt(choice2.getText());// Getting the ID the user has input,
																			  // changing to int

							Superstar reply = superId(superID, superstarList);// Using said int to search the appropriate
																			  // list and returning editing menu if found
							String msgId = new String("Wrestler ID: ");
							String msgName = new String("Wrestler Name: ");
							String msgTitles = new String("Titles Held: ");
							String msgAge = new String("Wrestler Age: ");
							String msgStrength = new String(
									"Wrestler Strength: \n(Currently set to "
											+ reply.getStrength()
											+ ", select again to keep value)");
							String msgSpeed = new String(
									"Wrestler Speed: \n(Currently set to "
											+ reply.getSpeed()
											+ ", select again to keep value)");
							String msgLuck = new String(
									"Wrestler Luck: \n(Currently set to "
											+ reply.getLuck()
											+ ", select again to keep value)");
							String msgGender = new String("Wrestler Gender: ");

							int id = reply.getId();
							JTextField name = new JTextField(""
									+ reply.getName());
							JTextField titles = new JTextField(""
									+ reply.getTitles());
							JTextField age = new JTextField("" + reply.getAge());
							String[] strValue = { "1", "2", "3", "4", "5", "6",
									"7", "8", "9", "10", "11", "12", "13",
									"14", "15", "16", "17", "18", "19", "20" };
							JComboBox strength = new JComboBox(strValue);
							String[] spValue = { "1", "2", "3", "4", "5", "6",
									"7", "8", "9", "10", "11", "12", "13",
									"14", "15", "16", "17", "18", "19", "20" };
							JComboBox speed = new JComboBox(spValue);
							String[] luckValue = { "1", "2", "3", "4", "5",
									"6", "7", "8", "9", "10", "11", "12", "13",
									"14", "15", "16", "17", "18", "19", "20" };
							JComboBox luck = new JComboBox(luckValue);
							JTextField gender = new JTextField("Male");

							Object message2[] = new Object[17];

							message2[0] = myIcon2;
							message2[1] = msgId;
							message2[2] = id;
							message2[3] = msgName;
							message2[4] = name;
							message2[5] = msgTitles;
							message2[6] = titles;
							message2[7] = msgAge;
							message2[8] = age;
							message2[9] = msgStrength;
							message2[10] = strength;
							message2[11] = msgSpeed;
							message2[12] = speed;
							message2[13] = msgLuck;
							message2[14] = luck;
							message2[15] = msgGender;
							message2[16] = gender;

							int response2 = JOptionPane.showConfirmDialog(null,
									message2, "Edit Superstar details",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, anIcon);

							if (response2 == JOptionPane.CANCEL_OPTION)
								;
							else {

								try {

									int id1 = id;
									String name1 = name.getText();
									int titles1 = Integer.parseInt(titles
											.getText());
									int age1 = Integer.parseInt(age.getText());
									int strength1 = Integer
											.parseInt((String) strength
													.getSelectedItem());
									int speed1 = Integer
											.parseInt((String) speed
													.getSelectedItem());
									int luck1 = Integer.parseInt((String) luck
											.getSelectedItem());
									String gender1 = gender.getText();

									Superstar super1 = new Superstar(id1,
											name1, titles1, age1, strength1,
											speed1, luck1, gender1);
									superstarList.remove(reply); // removing the original object the user searched for.
									superstarList.add(super1);   // replacing it with new object based on user input.

									JOptionPane.showMessageDialog(null,
											"Superstar ID-" + super1.getId()
											+ " has been updated.",
											"Success!",
											JOptionPane.INFORMATION_MESSAGE,
											logo);

								} catch (Exception e) {
									JOptionPane.showMessageDialog(null,
											"Error (" + e + ")");
								}
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error (" + e
									+ ")");
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error (" + e + ")");
				}
			}
		}

		else {

			if (divaList.isEmpty() == true) {
				JOptionPane.showMessageDialog(null,
						"There are no Divas to update.", "Nobody's home....",
						JOptionPane.INFORMATION_MESSAGE, logo);
			} else {
				try {
					String msgUpdate = new String(
							"Enter ID of Diva you want to update:");
					JTextField choice2 = new JTextField("");

					Object message[] = new Object[3];

					message[0] = aBanner;
					message[1] = msgUpdate;
					message[2] = choice2;

					int response = JOptionPane.showConfirmDialog(null, message,
							"Update Diva Details",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, anIcon);

					if (response == JOptionPane.CANCEL_OPTION)
						;

					else {
						try {

							int divaID = Integer.parseInt(choice2.getText());

							Diva reply = divaId(divaID, divaList);

							String msgId = new String("Wrestler ID: ");
							String msgName = new String("Wrestler Name: ");
							String msgTitles = new String("Titles Held: ");
							String msgAge = new String("Wrestler Age: ");
							String msgStrength = new String(
									"Wrestler Strength: \n(Currently set to "
											+ reply.getStrength()
											+ ", select again to keep value)");
							String msgSpeed = new String(
									"Wrestler Speed: \n(Currently set to "
											+ reply.getSpeed()
											+ ", select again to keep value)");
							String msgLuck = new String(
									"Wrestler Luck: \n(Currently set to "
											+ reply.getLuck()
											+ ", select again to keep value)");
							String msgGender = new String("Wrestler Gender: ");
							int id = reply.getId();
							JTextField name = new JTextField(""
									+ reply.getName());
							JTextField titles = new JTextField(""
									+ reply.getTitles());
							JTextField age = new JTextField("" + reply.getAge());
							String[] strValue = { "1", "2", "3", "4", "5", "6",
									"7", "8", "9", "10", "11", "12", "13",
									"14", "15", "16", "17", "18", "19", "20" };
							JComboBox strength = new JComboBox(strValue);
							String[] spValue = { "1", "2", "3", "4", "5", "6",
									"7", "8", "9", "10", "11", "12", "13",
									"14", "15", "16", "17", "18", "19", "20" };
							JComboBox speed = new JComboBox(spValue);
							String[] luckValue = { "1", "2", "3", "4", "5",
									"6", "7", "8", "9", "10", "11", "12", "13",
									"14", "15", "16", "17", "18", "19", "20" };
							JComboBox luck = new JComboBox(luckValue);
							JTextField gender = new JTextField("Female");

							Object message2[] = new Object[17];

							message2[0] = myIcon3;
							message2[1] = msgId;
							message2[2] = id;
							message2[3] = msgName;
							message2[4] = name;
							message2[5] = msgTitles;
							message2[6] = titles;
							message2[7] = msgAge;
							message2[8] = age;
							message2[9] = msgStrength;
							message2[10] = strength;
							message2[11] = msgSpeed;
							message2[12] = speed;
							message2[13] = msgLuck;
							message2[14] = luck;
							message2[15] = msgGender;
							message2[16] = gender;

							int response2 = JOptionPane.showConfirmDialog(null,
									message2, "Edit Diva details",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, anIcon);

							if (response2 == JOptionPane.CANCEL_OPTION)
								;

							else {

								try {

									int id1 = id;
									String name1 = name.getText();
									int titles1 = Integer.parseInt(titles
											.getText());
									int age1 = Integer.parseInt(age.getText());
									int strength1 = Integer
											.parseInt((String) strength
													.getSelectedItem());
									int speed1 = Integer
											.parseInt((String) speed
													.getSelectedItem());
									int luck1 = Integer.parseInt((String) luck
											.getSelectedItem());
									String gender1 = gender.getText();

									Diva diva1 = new Diva(id1, name1, titles1,
											age1, strength1, speed1, luck1,
											gender1);
									divaList.remove(reply);
									divaList.add(diva1);

									JOptionPane.showMessageDialog(null,
											"Diva ID-" + diva1.getId()
											+ " has been updated.",
											"Success!",
											JOptionPane.INFORMATION_MESSAGE,
											logo);

								} catch (Exception e) {
									JOptionPane.showMessageDialog(null,
											"Error (" + e + ")");
								}
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error (" + e
									+ ")");
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error (" + e + ")");
				}
			}
		}
	}
	
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// Because of the way we're using the JComboBox
	public void deleteWrestler() {
		String[] list = { "Superstar", "Diva" };
		JComboBox type = new JComboBox(list);

		JOptionPane.showMessageDialog(null, type,
				"Select Wrestler type to delete:",
				JOptionPane.QUESTION_MESSAGE, selectType);

		Object choice = type.getSelectedItem();

		if (choice == "Superstar") {
			if (superstarList.isEmpty() == true) {
				JOptionPane.showMessageDialog(null,
						"There are no Superstars to delete.",
						"Nobody is here!", JOptionPane.INFORMATION_MESSAGE,
						error);
			} else {
				String msgRemove = new String(
						"Enter ID of Superstar you wish to remove: ");
				JTextField reply = new JTextField("");

				Object message[] = new Object[3];

				message[0] = aBanner;
				message[1] = msgRemove;
				message[2] = reply;

				int response = JOptionPane.showConfirmDialog(null, message,
						"Remove Superstar",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, anIcon);

				if (response == JOptionPane.CANCEL_OPTION)
					;

				else {
					try {

						int searchID = Integer.parseInt(reply.getText());

						Superstar temp = superId(searchID, superstarList);
						RecycleID move = new RecycleID(searchID); // Creating a new object of type RecID using the ID the user has input.
						recIdList.add(move); // Adding the object, effectively extracting the ID of the object that will be deleted and storing it for
											 // future use.
						superstarList.remove(temp); // Removing the object the user has selected.

						JOptionPane.showMessageDialog(null, "Superstar ID"
								+ temp.getId() + " (" + temp.getName()
								+ ") has been removed from the program.",
								"Success!", JOptionPane.INFORMATION_MESSAGE,
								logo);
					} catch (Exception e) {
						JOptionPane
						.showMessageDialog(null, "Error (" + e + ")");
					}
				}
			}
		} else {
			if (divaList.isEmpty() == true) {
				JOptionPane.showMessageDialog(null,
						"There are no Divas to delete.", "Nobody is here!",
						JOptionPane.INFORMATION_MESSAGE, error);
			}
			String msgRemove = new String(
					"Enter ID of Diva you wish to remove: ");
			JTextField reply = new JTextField("");

			Object message[] = new Object[3];

			message[0] = aBanner;
			message[1] = msgRemove;
			message[2] = reply;

			int response = JOptionPane.showConfirmDialog(null, message,
					"Remove Diva", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, anIcon);

			if (response == JOptionPane.CANCEL_OPTION)
				;

			else {
				try {

					int searchID = Integer.parseInt(reply.getText());

					Diva temp = divaId(searchID, divaList);
					RecycleID move = new RecycleID(searchID);
					recIdList.add(move);
					divaList.remove(temp);

					JOptionPane.showMessageDialog(null, "Diva " + temp.getId()
							+ " " + temp.getName()
							+ " has been removed from the program.",
							"Success!", JOptionPane.INFORMATION_MESSAGE, logo);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Error, cannot complete this action at this time!\n"
									+ e);
				}
			}
		}

	}

	public void saveToFile() { // Writing data to the appropriate files as necessary
		File superFile, divaFile, recIdFile, freshFile;
		FileOutputStream superFileOutStr, divaFileOutStr, recIdFileOutStr, freshFileOutStr;
		ObjectOutputStream superObjOutStr, divaObjOutStr, recIdObjOutStr, freshObjOutStr;

		try {

			superFile = new File("superstar.data");
			superFileOutStr = new FileOutputStream(superFile);
			superObjOutStr = new ObjectOutputStream(superFileOutStr);
			superObjOutStr.writeObject(superstarList);
			superObjOutStr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error with Superstar file: ("
					+ e + ")");
		}

		try {

			divaFile = new File("diva.data");
			divaFileOutStr = new FileOutputStream(divaFile);
			divaObjOutStr = new ObjectOutputStream(divaFileOutStr);
			divaObjOutStr.writeObject(divaList);
			divaObjOutStr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error with Diva file: (" + e
					+ ")");
		}

		try {
			recIdFile = new File("recycleID.data");
			recIdFileOutStr = new FileOutputStream(recIdFile);
			recIdObjOutStr = new ObjectOutputStream(recIdFileOutStr);
			recIdObjOutStr.writeObject(recIdList);
			recIdObjOutStr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error with RecycleID file: ("
					+ e + ")");
		}

		try {
			freshFile = new File("factory.data");
			freshFileOutStr = new FileOutputStream(freshFile);
			freshObjOutStr = new ObjectOutputStream(freshFileOutStr);
			freshObjOutStr.writeObject(freshStart);
			freshObjOutStr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error with Factory Reset file: (" + e + ")");
		}

	}
}
