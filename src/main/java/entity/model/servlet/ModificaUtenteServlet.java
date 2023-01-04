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


@WebServlet("/ModificaUtenteServlet")
public class ModificaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ModificaUtenteServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("auth_id"));
		String nuovaEmail=request.getParameter("email");
		String nuovaPassword=request.getParameter("password");
		/*
		 * Bisogna applicare i controlli sul formato e sulla lunghezza
		 */
		
		//-------CONTROLLO EMAIL----------
		if(nuovaEmail.length()>0) {
			try {
				UserDao user= new UserDao(DbCon.getConnection());
				boolean b= user.doUpdateById(id, "email", nuovaEmail);
				if(b==true) {
					response.sendRedirect("modificheUtente.jsp");
				}
				else {
					System.out.println("Errore update Email");
				}
			}
			catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		//-----------CONTROLLO PASSWORD--------
		
		if(nuovaPassword.length()>0) {
			try {
				UserDao user= new UserDao(DbCon.getConnection());
				boolean b= user.doUpdateById(id, "password", nuovaPassword);
				if(b==true) {
					response.sendRedirect("modificheUtente.jsp");
				}
				else {
					System.out.println("Errore update password");
				}
			}
			catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
