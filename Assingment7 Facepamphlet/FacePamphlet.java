/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		//initializing graphics area
    	canvas = new FacePamphletCanvas();
    	add(canvas);
    	
		addInteractors();
		addActionListeners();
    }
    
  
    private void addInteractors() {
    	
		//Northern TextFields and buttons
    	add(new JLabel ("Name "), NORTH);
    	nameField = new JTextField (TEXT_FIELD_SIZE);
    	
    	JButton add = new JButton ("Add");
    	JButton delete = new JButton ("Delete");
    	JButton lookup = new JButton ("Lookup");
    	
    	add(nameField, NORTH);
    	add(add, NORTH);
    	add(delete, NORTH);
    	add(lookup, NORTH);
    	
    	//Western TextFields and Buttons
    	status = new JTextField (TEXT_FIELD_SIZE);
    	status.addActionListener(this);
    	status.setActionCommand("Status");
    
    	picture = new JTextField (TEXT_FIELD_SIZE);
    	picture.addActionListener(this);
    	picture.setActionCommand("Picture");
    	
    	friend= new JTextField (TEXT_FIELD_SIZE);
    	friend.addActionListener(this);
    	friend.setActionCommand("Friend");
    	
    	JButton statusBut = new JButton ("Status");
    	JButton pictureBut = new JButton ("Picture");
    	JButton friendBut = new JButton ("Friend");
    	
    	add(status, WEST);
    	add(statusBut, WEST);
    	add(new JLabel (EMPTY_LABEL_TEXT), WEST);
    	add(picture, WEST);
    	add(pictureBut, WEST);
    	add(new JLabel (EMPTY_LABEL_TEXT), WEST);
    	add(friend, WEST);
    	add(friendBut, WEST);
    	
	}


	/**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		String inputName = nameField.getText();
		
		switch(cmd) {
		case "Add":
			addProfile(inputName);
			break;
			
		case "Delete":
			deleteProfile(inputName);
			break;
			 
		case "Lookup":
			lookUp(inputName);
			break;
			
		case "Status":
			updateStatus();
			break;
			
		case "Picture":
			updatePicture();
			break;
			
		case "Friend":
			addFriend();
			break;
		}
		
	}
    
    /**
     * Pre-Condition: a non-Empty friend textField
     * Post-Conditions: currentProfile will have friend textField as a new friend and 
     * friend textField with have currentProfile as a new friend 
     * if the database contains the friend textField value and they are not friends before this call.
     * If currentProfile is null or they are friends before, an error message will be printed.
     */
    private void addFriend() {
    	if (!friend.getText().equals("")) {
			if (currentProfile != null) {
				if (!database.containsProfile(friend.getText())) {
					canvas.showMessage(friend.getText() + " profile does not exists");
				} else if (isfriendsBefore()) {
					canvas.showMessage(friend.getText() + " is already a friend");
				} else {
					makeTheTwoFriends();
					canvas.displayProfile(currentProfile);
					canvas.showMessage(friend.getText() + " added as friend");
				}
		
			}
			else {
				canvas.showMessage("Select a profile to add friends");
			}
		}
	}

    /**
     * 
     */
	private void makeTheTwoFriends() {
		currentProfile.addFriend(friend.getText());
		FacePamphletProfile friendProfile = database.getProfile(friend.getText());
		friendProfile.addFriend(currentProfile.getName());
	}

	/**
	 * 
	 * @return
	 */
	private boolean isfriendsBefore() {
		Iterator<String> friendNames = currentProfile.getFriends();
		while (friendNames.hasNext()) {
			String name = friendNames.next();
			if (name.equals(friend.getText())) {
				canvas.showMessage(name + " is already a friend of " + currentProfile.getName());
				return true;
			}
		}
		return false;
	}


	/**
     * Pre-Condition: a non-Empty picture textField
     * post-Conditions: The currentProfile picture will be updated to 
     * reflect the file that picture textField opens. If opening picture with said 
     * string value failed, an error will be thrown.
     * If currentProfile is null, an error message will be printed.
     */
    private void updatePicture() {
    	if (!picture.getText().equals("")) {
			if ( currentProfile != null ) {
				//Tries to open the given image fileName from user, throws an error if unable to open file.
				GImage image = null;
				try {
					image = new GImage(picture.getText());
				} catch (ErrorException ex) {
					throw ex;
				}
				
				//Sets the currentProfile picture to the opened image and informs the user accordingly
				currentProfile.setImage(image);
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Picture updated");
			} else {
				canvas.showMessage("Please select a profile to change picture");
			}
			
		}
	}


	/**
     * Pre-Condition: a non-Empty Status textField
     * post-Conditions: The currentProfile Status will be updated to reflect the Status textField.
     * If currentProfile is null, an error message will be printed.
     */
    private void updateStatus() {
    	if (!status.getText().equals("")) {
			if (currentProfile != null) {
				currentProfile.setStatus(status.getText());
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Status updated to " + status.getText());
			} else {
				canvas.showMessage("Please select a profile to change status");
			}
		}
	}


	/**
     * Pre-Conditions: A non--empty string
     * Post-Conditions: Profile of non-empty String will be looked up in the database 
     * and printed if the value is found.
     * Else, a message informing about the non-existant entry will be printed.
     * The currentProfile will also be set to the looked up entry or null respectively
     * @param inputName provides the String which will be used to check the database for previous records of it.
     */
    private void lookUp(String inputName) {
    	if (! inputName.equals("")) {
			if (database.containsProfile(inputName)) {
				currentProfile = database.getProfile(inputName);
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Displaying " + inputName);
			} else {
				currentProfile = null;
				canvas.removeAll();
				canvas.showMessage("A profile with the name " + inputName + " does not exist.");
			}
		}
	}


	/**
     * Pre-Conditions: A non--empty string
     * Post-Conditions: Profile will be deleted from the Database for the non-empty String 
     * if the value is found in the database. Else, a message informing about the non-existant entry will be printed.
     * The currentProfile will also be set to null
     * @param inputName provides the String which will be used to check the database for previous records of it.
     */
    private void deleteProfile(String inputName) {
    	if (! inputName.equals("")) {
			//removes the inputName from the database if it existed
    		if (database.containsProfile(inputName)) {
				database.deleteProfile(inputName);
				canvas.removeAll();
				canvas.showMessage("Profile of " + inputName + " deleted.");
			} 
    		//If the entry with the inputName as value cannot be found, a statement is printed to inform as such
    		else {
				canvas.showMessage("A profile with the name " + inputName + " does not exist.");
			}
			currentProfile = null;
		}
	}


	/**
     * Pre-Conditions: A non--empty string
     * Post-Conditions: new profile will be created and stored in the Database for the non-empty String 
     * if the value is not found in the database. Else, the pre-existing profile will be printed.
     * Current profile will be set to the entry which has just been added.
     * @param inputName provides the String which will be used to check the database for previous records of it.
     */
    private void addProfile(String inputName) {
    	if (! inputName.equals("")) {
			///Creates a new profile and adds it to the database if the inputName is not found in the database
    		if (database.containsProfile(inputName)) {
    			lookUp(inputName);
    			canvas.showMessage("Profile with name " + inputName + " already exist.");
    			return;
    		}
			profile = new FacePamphletProfile (inputName);
			database.addProfile(profile);
			currentProfile = database.getProfile(inputName);
    		canvas.displayProfile(currentProfile);
			canvas.showMessage("New profile created.");
			
    	}
		
	}

	/*Instance Variables */
    private JTextField nameField;
    private JTextField status; 
    private JTextField picture;
    private JTextField friend;
    private FacePamphletDatabase database = new FacePamphletDatabase();
    private FacePamphletProfile profile;
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas canvas;
}
