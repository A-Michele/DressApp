package entity.model.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.*;
import entity.model.Product;

@WebServlet("/search-bar")
public class SearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out=response.getWriter()){
			String s=request.getParameter("search");	
			try {
				ProductDao pdao=new ProductDao(DbCon.getConnection());
				ArrayList<Product> products=pdao.searchItems(s);
	
				request.setAttribute("search-product",products);
				RequestDispatcher dispatcher  = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
								
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
