package edu.gwu.akshay.sb;

import edu.gwu.akshay.sb.backgroungjobs.WebpageDownloaderTask;
import edu.gwu.akshay.sb.entities.Bookmark;
import edu.gwu.akshay.sb.entities.User;
import edu.gwu.akshay.sb.managers.BookmarkManager;
import edu.gwu.akshay.sb.managers.UserManager;

import java.util.List;

public class Launch {
	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);

		}
	}

	private static void loadData() {

		System.out.println("1. Loading data...");
		DataStore.loadData();
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();

		//System.out.println("Printing data...");
		//printUserData();
		//printBookmarkData();

	}

	private static void printBookmarkData() {
		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
	}

	private static void start() {
		//System.out.println("2. Bookmarking...");
		for(User user: users){
			View.browse(user, bookmarks);
		}
	}

	public static void main(String[] args) {
		loadData();
		start();

		// Background Jobs
		runDownloaderJob();
	}
		private static void runDownloaderJob(){
			WebpageDownloaderTask task = new WebpageDownloaderTask(true);
			(new Thread(task)).start();
		}

}
