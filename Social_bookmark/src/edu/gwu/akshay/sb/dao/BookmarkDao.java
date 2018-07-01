package edu.gwu.akshay.sb.dao;

import edu.gwu.akshay.sb.DataStore;
import edu.gwu.akshay.sb.entities.Bookmark;
import edu.gwu.akshay.sb.entities.UserBookmark;

public class BookmarkDao { // this methods would be internally invoked through manager when controller
							// invokes manager.
	public Bookmark[][] getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
		
	}
}
