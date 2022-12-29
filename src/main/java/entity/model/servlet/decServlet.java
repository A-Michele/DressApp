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
import entity.dao.CartDao;
import entity.model.User;

@WebServlet("/dec")
public class decServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("txt/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			int p_id= Integer.parseInt(request.getParameter("p_id"));
			User auth=(User) request.getSession().getAttribute("auth");
			if(p_id>0) {
				CartDao cDao=new CartDao(DbCon.getConnection());
				cDao.dec(p_id,auth.getId());
				response.sendRedirect("cart.jsp");
			}else {
				response.sendRedirect("cart.jsp");
			}
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
