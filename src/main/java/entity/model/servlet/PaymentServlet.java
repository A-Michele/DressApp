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
import entity.dao.*;
import entity.model.*;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		boolean result;
		try(PrintWriter out=response.getWriter()){
			String proprietario=request.getParameter("payment-proprietario");	//i nomi che passiamo alla get parameter sono quelli contenuti nel tag name all'interno del file jsp
			String num=request.getParameter("payment-num");
			int cvi=Integer.parseInt(request.getParameter("payment-cvi"));
			String scadenza=request.getParameter("payment-scadenza");
			User auth=(User) request .getSession().getAttribute("auth");
			try {
				PaymentDao pdao=new PaymentDao(DbCon.getConnection());
				result=pdao.insertPayment(auth.getId(),proprietario,scadenza,num,cvi);
				if(result) {
					response.sendRedirect("show-products");
				}else {
					out.print("Payment error...");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
