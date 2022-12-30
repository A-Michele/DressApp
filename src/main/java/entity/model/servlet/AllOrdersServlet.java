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
import entity.model.Cappello;
import entity.model.User;

/**
 * Servlet implementation class AllOrdersServlet
 */
@WebServlet("/AllOrdersServlet")
public class AllOrdersServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		boolean result;
		try (PrintWriter out=response.getWriter()){
			User auth=(User) request .getSession().getAttribute("auth");
			CappelloDao pd = new CappelloDao(DbCon.getConnection());
			List<Cappello> products = pd.getAllProducts();
			request.setAttribute("products",products);
			RequestDispatcher dispatcher  = request.getRequestDispatcher("show-products");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	
	
	
}
