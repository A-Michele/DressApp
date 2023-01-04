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


@WebServlet("/CancellaProfiloServlet")
public class CancellaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CancellaProfiloServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("auth_id"));
		
		try {
			UserDao user= new UserDao(DbCon.getConnection());
			boolean b= user.doDeleteById(id);
			if(b==true) {
				response.sendRedirect("modificheUtente.jsp");
			}
			else {
				System.out.println("Errore Cancellazione profilo");
			}
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
