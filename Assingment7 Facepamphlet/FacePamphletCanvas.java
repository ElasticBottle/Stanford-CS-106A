/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		message.setLabel(msg);
		message.setFont(MESSAGE_FONT);
		double x = (getWidth() - message.getWidth()) / 2;
		add(message, x, getHeight() - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		addNameTag(profile.getName());
		addProfileImage(profile.getImage());
		addStatusTag(profile.getName(), profile.getStatus());
		addFriendsList(profile.getFriends());
	}
	
	/**
	 * 
	 * @param name
	 */
	private void addNameTag(String name) {
		GLabel nameLabel = new GLabel (name);
		nameLabel.setFont(PROFILE_NAME_FONT);
		imageMargin = TOP_MARGIN + nameLabel.getAscent();
		add(nameLabel, LEFT_MARGIN, imageMargin);
	}

	/**
	 * 
	 * @param image
	 */
	private void addProfileImage(GImage image) {
		if (image == null) {
			GRect imageSpace = new GRect (IMAGE_WIDTH, IMAGE_HEIGHT);
			GLabel noImageNotice = new GLabel (PROFILE_IMAGE_TEXT);
			noImageNotice.setFont(PROFILE_IMAGE_FONT);
			
			add(imageSpace, LEFT_MARGIN, imageMargin + IMAGE_MARGIN);
			add(noImageNotice, 
					LEFT_MARGIN + (IMAGE_WIDTH - noImageNotice.getWidth()) / 2, 
					imageMargin + IMAGE_MARGIN + (IMAGE_HEIGHT + noImageNotice.getAscent()) / 2);
		} else {
			image.scale(IMAGE_WIDTH / image.getWidth(), IMAGE_HEIGHT / image.getHeight());
			add(image, LEFT_MARGIN, imageMargin + IMAGE_MARGIN);
		}
	}


	private void addStatusTag(String name, String status) {
		GLabel statusLabel;
		if (status.equals("")) {
			statusLabel = new GLabel ("No current Status");
		} else {
			statusLabel = new GLabel (name + " is " + status);
		}
		statusLabel.setFont(PROFILE_STATUS_FONT);
		add(statusLabel, 
				LEFT_MARGIN, 
				imageMargin + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + statusLabel.getAscent());
	}


	private void addFriendsList(Iterator<String> friendList) {
		GLabel friendLabel = new GLabel ("Friends:");
		friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		double y = imageMargin + IMAGE_MARGIN;
		add(friendLabel, getWidth() / 2, y);

		while (friendList.hasNext()) {
			String name = friendList.next();
			GLabel friend = new GLabel (name);
			friend.setFont(PROFILE_FRIEND_FONT);
			add(friend, getWidth() / 2, y + friend.getHeight() + FRIEND_MARGIN);
		}
	}

	/* Instance Variables */
	double imageMargin;
	GLabel message = new GLabel ("");
}
