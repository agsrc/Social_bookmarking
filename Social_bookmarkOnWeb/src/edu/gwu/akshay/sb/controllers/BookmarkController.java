package edu.gwu.akshay.sb.controllers;

import edu.gwu.akshay.sb.constants.KidFriendlyStatus;
import edu.gwu.akshay.sb.entities.Book;
import edu.gwu.akshay.sb.entities.Bookmark;
import edu.gwu.akshay.sb.entities.User;
import edu.gwu.akshay.sb.managers.BookmarkManager;
import edu.gwu.akshay.sb.managers.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = {"/bookmark", "/bookmark/save","/bookmark/mybooks"})
public class BookmarkController extends HttpServlet {

	public BookmarkController(){

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =null;
		System.out.println("Srvlet Path:" +request.getServletPath());

		if (request.getSession().getAttribute("userId")!=null){
			long userId= (Long) request.getSession().getAttribute("userId");

			if(request.getServletPath().contains("save")){
				//save
				dispatcher=request.getRequestDispatcher("/mybooks.jsp");

				String bid =request.getParameter("bid");
				User user = UserManager.getInstance().getUser(userId);
				Bookmark bookmark = BookmarkManager.getInstance().getBook(Long.parseLong(bid));
				BookmarkManager.getInstance().saveUserBookmark(user,bookmark);
				Collection <Bookmark>list =BookmarkManager.getInstance().getBooks(true,userId);
				request.setAttribute("books",list);

			} else if(request.getServletPath().contains("mybooks")) {

				dispatcher=request.getRequestDispatcher("/mybooks.jsp");
				Collection <Bookmark>list =BookmarkManager.getInstance().getBooks(true,5);
				request.setAttribute("books",list);
			}else{
				dispatcher=request.getRequestDispatcher("/browse.jsp");

				Collection <Bookmark>list =BookmarkManager.getInstance().getBooks(false,userId);
				request.setAttribute("books",list);
			}
		}else {
			dispatcher=request.getRequestDispatcher("/browse.jsp");

		}

		 dispatcher.forward(request,response);
	}
	/*private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user,bookmark);
		
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
	BookmarkManager.getInstance().setKidFriendly(user, kidFriendlyStatus, bookmark);
		
	}

	public void share(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().share(user,bookmark);
		
	}
}
