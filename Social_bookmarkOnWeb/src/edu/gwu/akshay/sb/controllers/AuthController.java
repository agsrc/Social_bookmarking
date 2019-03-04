package edu.gwu.akshay.sb.controllers;

import edu.gwu.akshay.sb.entities.Bookmark;
import edu.gwu.akshay.sb.entities.User;
import edu.gwu.akshay.sb.managers.BookmarkManager;
import edu.gwu.akshay.sb.managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = {"/auth", "/auth/logout"})
public class AuthController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Servlet Path:" + request.getServletPath());

        if (!request.getServletPath().contains("logout")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            long userId = UserManager.getInstance().authenticate(email, password);
            if (userId != -1) {
                HttpSession session = request.getSession(); // session id -- as an analogy -the container is maintaining a hashmap where key is session id and value is session object
                session.setAttribute("userId", userId);  // once instantiated lopps on implicitly
            } else {
                request.getRequestDispatcher("/bookmark/mybooks.jsp");
            }}
        else{
                request.getSession().invalidate();
                request.getRequestDispatcher("/login.jsp");    // appending slash for jsp -- not required for servlets
            }


        }
}
