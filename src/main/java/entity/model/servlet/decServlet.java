package entity.model.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.CappelloDao;
import entity.dao.DettaglioOrdineDAO;
import entity.model.Cappello;
import entity.model.DettaglioOrdine;
import entity.model.User;

@WebServlet("/dec")
public class decServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("txt/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			int p_id= Integer.parseInt(request.getParameter("p_id"));
			User auth=(User) request.getSession().getAttribute("auth");
			int o_id=(int) request.getSession().getAttribute("o_id");
			ArrayList<DettaglioOrdine> list=(ArrayList<DettaglioOrdine>) request.getSession().getAttribute("listDettaglio");
				DettaglioOrdineDAO dtDao=new DettaglioOrdineDAO(DbCon.getConnection());
				if((dtDao.getQuantita(p_id, o_id)==1)) {
					response.sendRedirect("removeCart?p_id="+p_id);
				}else {
				dtDao.updateQuantita((dtDao.getQuantita(p_id, o_id))-1,p_id,o_id);
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
