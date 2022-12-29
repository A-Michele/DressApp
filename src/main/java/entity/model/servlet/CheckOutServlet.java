package entity.model.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.CartDao;
import entity.model.*;


@WebServlet("/check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result;
		try (PrintWriter out=response.getWriter()){
			//autenticazione dell'utente
			User auth=(User) request.getSession().getAttribute("auth");
			CartDao cdao=new CartDao(DbCon.getConnection());
			//controllo autenticazione
			if(auth!=null) {
				result=cdao.checkOut(auth.getId());
				//System.out.println(result);
				if(result){
					response.sendRedirect("payment.jsp");
				}else {
					out.print("Checkout error...");
				}
			}else {
				if(auth==null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
