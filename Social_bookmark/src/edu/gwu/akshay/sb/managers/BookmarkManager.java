package edu.gwu.akshay.sb.managers;

import edu.gwu.akshay.sb.dao.BookmarkDao;
import edu.gwu.akshay.sb.entities.Book;
import edu.gwu.akshay.sb.entities.Bookmark;
import edu.gwu.akshay.sb.entities.Movie;
import edu.gwu.akshay.sb.entities.User;
import edu.gwu.akshay.sb.entities.UserBookmark;
import edu.gwu.akshay.sb.entities.WebLink;

public class BookmarkManager {

	private BookmarkManager() {
	}

	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao =new BookmarkDao();

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, String genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setId(id);
		movie.setImdbRating(imdbRating);
		movie.setReleaseYear(releaseYear);
		movie.setProfileUrl(profileUrl);
		movie.setTitle(title);

		return movie;

	}

	public WebLink createWebLink(long id, String title, String url, String host) {
		WebLink weblink = new WebLink();
		weblink.setHost(host);
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrl(url);

		return weblink;

	}

	public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors, String genre,
			 double amazonRating) {
		Book book = new Book();
		book.setAmazonRating(amazonRating);
		book.setTitle(title);
		book.setAuthors(authors);
		book.setId(id);
		book.setPublisher(publisher);
		book.setGenre(genre);
		book.setPublicationYear(publicationYear);
		return book;
	}

	public Bookmark[][] getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
	dao.saveUserBookmark(userBookmark);
	} 
}
