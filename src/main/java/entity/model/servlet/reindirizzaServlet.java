package entity.model.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.ProductDao;
import entity.model.Product;

@WebServlet("/reindirizzaServlet")
public class reindirizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p_id=request.getParameter("p_id");
		int x= Integer.parseInt(p_id);
		System.out.println("id"+x);
		
		try {
			ProductDao pdao=new ProductDao(DbCon.getConnection());
			Product p=pdao.retriveProductById(x);
			if(p!=null) {
				request.setAttribute("item",p);
				RequestDispatcher dispatcher  = request.getRequestDispatcher("modificaProdotto.jsp");
				dispatcher.forward(request, response);
			}
			else {
				System.out.println("Product null");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("inserisciProdotto.jsp");
	}
	
	
}
