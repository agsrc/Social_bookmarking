package edu.gwu.akshay.sb.managers;

import edu.gwu.akshay.sb.constants.BookGenre;
import edu.gwu.akshay.sb.constants.KidFriendlyStatus;
import edu.gwu.akshay.sb.constants.MovieGenre;
import edu.gwu.akshay.sb.dao.BookmarkDao;
import edu.gwu.akshay.sb.entities.Book;
import edu.gwu.akshay.sb.entities.Bookmark;
import edu.gwu.akshay.sb.entities.Movie;
import edu.gwu.akshay.sb.entities.User;
import edu.gwu.akshay.sb.entities.UserBookmark;
import edu.gwu.akshay.sb.entities.WebLink;
import edu.gwu.akshay.sb.util.HttpConnect;
import edu.gwu.akshay.sb.util.IOUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public class BookmarkManager {

	private BookmarkManager() {
	}

	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
							 String[] directors, MovieGenre genre, double imdbRating) {
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

	public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors, BookGenre genre,
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

	public List<List<Bookmark>> getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		dao.saveUserBookmark(userBookmark);
		if (bookmark instanceof WebLink) {
			try {
				String url = ((WebLink)bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(((WebLink)bookmark).getUrl());
					if (webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		dao.saveUserBookmark(userBookmark);
	}


	public void setKidFriendly(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {

		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		// bookkeeping
		System.out.println("Kid-friendly status --" + "[" + kidFriendlyStatus + "] -- MarkedBy : " + user.getEmail()
				+ "," + bookmark);

	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);

		System.out.println("Data to be shared: ");
		if (bookmark instanceof Book) {
			System.out.println(((Book) bookmark).getItemData());
	}
		else if (bookmark instanceof WebLink) {
			System.out.println(((WebLink)bookmark).getItemData());
		}
	}
}
