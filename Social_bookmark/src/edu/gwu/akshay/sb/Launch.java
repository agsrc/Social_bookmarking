package edu.gwu.akshay.sb;

import edu.gwu.akshay.sb.entities.Bookmark;
import edu.gwu.akshay.sb.entities.User;
import edu.gwu.akshay.sb.managers.BookmarkManager;
import edu.gwu.akshay.sb.managers.UserManager;

public class Launch {
	private static User[] users;
	private static Bookmark[][] bookmarks;

	private static void loadData() {

		System.out.println("1. Loading data...");
		DataStore.loadData();
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();

		System.out.println("Printing data...");
		printUserData();
		printBookmarkData();

	}

	private static void printBookmarkData() {
		for (Bookmark[] list: bookmarks ) {
			for(Bookmark bookmark: list) {
				System.out.println(bookmark);
			}
		}
	}

	private static void printUserData() {
		for(User user:users) {
			System.out.println(user);
			
		}
	}

	public static void main(String[] args) {
		loadData();

	}

}
