package entity.model.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");	//i nomi che passiamo alla get parameter sono quelli contenuti nel tag name all'interno del file jsp
		String password=request.getParameter("password");
		
		try(PrintWriter out=response.getWriter()) {
			UserDao udao=new UserDao(DbCon.getConnection());
			boolean x=udao.retrivebyMail(email);
			if(x==true) {
				out.print("Error");
			}
			else {
				User us=new User();
				us=udao.userRec(nome,cognome,email, password,0,0);
				out.print("OK");
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
