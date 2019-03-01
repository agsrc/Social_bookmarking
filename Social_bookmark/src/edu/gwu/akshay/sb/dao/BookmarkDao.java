package edu.gwu.akshay.sb.dao;

import edu.gwu.akshay.sb.DataStore;
import edu.gwu.akshay.sb.entities.*;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDao { // this methods would be internally invoked through manager when controller
							// invokes manager.
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		//DataStore.add(userBookmark);

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCIAL_BOOKMARKING?useSSL=false", "root", "password");
			 Statement stmt = conn.createStatement();){
				if(userBookmark.getBookmark() instanceof Book){
					saveUserBook(userBookmark,stmt);

				}else if(userBookmark.getBookmark() instanceof Movie){
					saveUserMovie(userBookmark,stmt);

				}else {
					saveUserWebLink(userBookmark,stmt);

				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void saveUserWebLink(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query="insert into User_WebLink(user_id, weblink_id) values( " + userBookmark.getUser().getId()+ ","+userBookmark.getBookmark().getId()+")";
		stmt.executeUpdate(query);
	}

	private void saveUserMovie(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query="insert into User_Movie(user_id, movie_id) values( " + userBookmark.getUser().getId()+ ","+userBookmark.getBookmark().getId()+")";
		stmt.executeUpdate(query);
	}

	private void saveUserBook(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query="insert into User_Book(user_id, book_id) values( " + userBookmark.getUser().getId()+ ","+userBookmark.getBookmark().getId()+")";
		stmt.executeUpdate(query);
	}

	// in real application simple SQL and hibernate queries would be used

	public List<WebLink> getAllWebLinks(){
		List<WebLink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
		List<Bookmark> allWebLinks =bookmarks.get(0);

		for(Bookmark bookmark:allWebLinks){
			result.add((WebLink) bookmark);
		}
		return result;
	}

	public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus){

		List<WebLink> result = new ArrayList<>();

		List<WebLink> allWebLinks =getAllWebLinks();

		for (WebLink webLink: allWebLinks){
			if(webLink.getDownloadStatus().equals(downloadStatus)){
				result.add(webLink);
			}
		}
		return  result;
	}
}
