package edu.gwu.akshay.sb.controllers;

import edu.gwu.akshay.sb.entities.Bookmark;
import edu.gwu.akshay.sb.entities.User;
import edu.gwu.akshay.sb.managers.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user,bookmark);
		
	}
}
