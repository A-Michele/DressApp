package entity.model.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.CappelloDao;
import entity.dao.OrderDao;
import entity.model.Cappello;
import entity.model.Ordine;
import entity.model.User;

/**
 * Servlet implementation class AllOrdersServlet usata per la visualizzazione di tutti gli ordini da parte dell'admin
 */
@WebServlet("/AllOrdersServlet")
public class AllOrdersServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		boolean result;
		try (PrintWriter out=response.getWriter()){
			User auth=(User) request .getSession().getAttribute("auth");
			System.out.println("SERVLETTTT");
			String nome=request.getParameter("search");
			try {
				OrderDao od = new OrderDao(DbCon.getConnection());
				ArrayList<Ordine> ordini=od.searchOrdersFromNameProduct(nome);
				request.setAttribute("search-orders",ordini);
			
			RequestDispatcher dispatcher  = request.getRequestDispatcher("admin.jsp");
			dispatcher.forward(request, response);
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			} }catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	
}
