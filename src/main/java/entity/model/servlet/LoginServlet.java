package entity.model.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.CappelloDao;
import entity.dao.UserDao;
import entity.model.Cappello;
import entity.model.User;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			String email=request.getParameter("email");	//i nomi che passiamo alla get parameter sono quelli contenuti nel tag name all'interno del file jsp
			String password=request.getParameter("password");
			System.out.println(email+" "+password);
			
			try {
				UserDao udao=new UserDao(DbCon.getConnection());
				
				User user=udao.userLogin(email, password);
				String Textmsg=null;
				if(user==null) {
					getServletContext().setAttribute("errorLogin",  "errorLogin");
					out.print("Nulla");
				}else {
					//System.out.println("is admin:"+user.getIsAdmin());
					if(user.getIsAdmin()==1) {
						Textmsg+="LoginOk";
						request.getSession().setAttribute("auth", user);
						request.setAttribute("LoginOk",Textmsg);
						out.print("LoginOk");
						
					}else {		
						Textmsg+="LoginOk";
						request.getSession().setAttribute("auth", user);
						request.setAttribute("LoginOk",Textmsg);
						out.print("LoginOk");
						
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
