package edu.gwu.akshay.sb.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.gwu.akshay.sb.constants.BookGenre;
import edu.gwu.akshay.sb.managers.BookmarkManager;

class BookTest {

	@Test
	void testIsKidFriendlyEligible() {
		Book book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "	Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		boolean isKidFriendlyEligible=book.isKidFriendlyEligible();
		assertFalse(isKidFriendlyEligible, "if the genre is philosophy isKidFriendlyEligible should return false");
		
		 book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "	Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);
		 isKidFriendlyEligible=book.isKidFriendlyEligible();
		assertFalse(isKidFriendlyEligible, "if the genre is SELF_HELP isKidFriendlyEligible should return false");
	}

}
