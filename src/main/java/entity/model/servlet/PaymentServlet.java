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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			String proprietario=request.getParameter("proprietario");//i nomi che passiamo alla get parameter sono quelli contenuti nel tag name all'interno del file jsp
			String num=request.getParameter("num");
			int cvv=Integer.parseInt(request.getParameter("cvv"));
			String scadenza=request.getParameter("scadenza");
			User auth=(User) request.getSession().getAttribute("auth");
			try {
				CardDAO pdao=new CardDAO(DbCon.getConnection());
				boolean exist = pdao.exist(proprietario,scadenza,num,cvv,auth.getId());
				boolean result = pdao.insertCard(proprietario,num,scadenza,cvv,auth.getId());
				if(result && !(exist)) {
					out.print("Registrato");
				}else if(exist){
					out.print("Error");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
