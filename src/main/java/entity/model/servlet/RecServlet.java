package entity.model.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.UserDao;
import entity.model.User;


@WebServlet("/rec-servlet")
public class RecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("registrazione.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("rec-name");
		String cognome=request.getParameter("rec-surname");
		String email=request.getParameter("login-email");	//i nomi che passiamo alla get parameter sono quelli contenuti nel tag name all'interno del file jsp
		String password=request.getParameter("login-password");
		
		try {
			UserDao udao=new UserDao(DbCon.getConnection());
			User us=new User();
			us=udao.userRec(nome,cognome,email, password,false);
			if(us==null) {
				response.sendRedirect("registrazione.jsp");
			}
			else {
				response.sendRedirect("login.jsp");
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
