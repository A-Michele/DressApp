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
import entity.dao.DettaglioOrdineDAO;
import entity.dao.CappelloDao;
import entity.model.User;


@WebServlet("/removeCart")
public class removeFromCart extends HttpServlet {


	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("txt/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			int p_id= Integer.parseInt(request.getParameter("p_id"));
			int o_id=(int) request.getSession().getAttribute("o_id");
			DettaglioOrdineDAO dtDao=new DettaglioOrdineDAO(DbCon.getConnection());
			dtDao.removeById(p_id, o_id);
			response.sendRedirect("cart.jsp");
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
