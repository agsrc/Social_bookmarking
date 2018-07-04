package edu.gwu.akshay.sb;

import edu.askhay.sb.partner.Shareable;
import edu.gwu.akshay.sb.constants.KidFriendlyStatus;
import edu.gwu.akshay.sb.constants.UserType;
import edu.gwu.akshay.sb.controllers.BookmarkController;
import edu.gwu.akshay.sb.entities.Bookmark;
import edu.gwu.akshay.sb.entities.User;

public class View {
	public static void browse(User user, Bookmark[][] bookmarks) {
		System.out.println("\n" + user.getEmail() + "is browsing items...");
		int bookmarkCount = 0;

		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {// book marking!
				if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						bookmarkCount++;

						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						System.out.println("new item bookmarked --" + bookmark);
					}

				}

				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					// mark as kid-friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						String kidFriendlyStatus = getKidFriendlyStatusDecision();

						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
							bookmark.setKidFriendlyStatus(kidFriendlyStatus);
							System.out.println("Kid-friendly status --[" + kidFriendlyStatus + "] --  " + bookmark);
						}
					}
					// Sharing!!
					if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
							&& bookmark instanceof Shareable) {
						boolean isShared=getShareDecision();
						if(isShared) {
							BookmarkController.getInstance().share(user,bookmark);
						}

					}
				}
			}
		}
	}
//TODO: Below methods simulate user i/p. After IO chapter--input console will be used.
	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true : false;

		
	}

	/*
	 * for (int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) { // e.g without
	 * being an authenticated user we make // limitation.
	 * 
	 * int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT); //
	 * will be implemented later for // actual picking int bookmarkOffset = (int)
	 * (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE); Bookmark bookmark =
	 * bookmarks[typeOffset][bookmarkOffset];
	 * 
	 * BookmarkController.getInstance().saveUserBookmark(user, bookmark);
	 * System.out.println(bookmark); } }
	 */
	private static String getKidFriendlyStatusDecision() {
		return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED
				: ((Math.random() >= 0.4 && Math.random() < 0.8) ? KidFriendlyStatus.REJECTED
						: KidFriendlyStatus.UNKNOWN); // nested ternary

	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;

	}
}

/*
 * public static void bookmark(User user, Bookmark[][] bookmarks) {
 * System.out.println("\n" + user.getEmail() + "is bookmarking"); for (int i =
 * 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) { int typeOffset = (int)
 * (Math.random() * DataStore.BOOKMARK_TYPES_COUNT); int bookmarkOffset = (int)
 * (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE); Bookmark bookmark =
 * bookmarks[typeOffset][bookmarkOffset];
 * 
 * BookmarkController.getInstance().saveUserBookmark(user,bookmark);
 * System.out.println(bookmark); } } }
 */
