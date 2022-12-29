package entity.model.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.connection.DbCon;
import entity.dao.*;
import entity.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		boolean result;
		try (PrintWriter out=response.getWriter()){
			User auth=(User) request .getSession().getAttribute("auth");
			//Guest guest=(Guest) request .getSession().getAttribute("guest");
			int p_id= Integer.parseInt(request.getParameter("p_id"));
			if(auth!=null && auth.getIsGuest()==0) {
				System.out.println(auth);
				CartDao cDao=new CartDao(DbCon.getConnection());
				result=cDao.AddToCart(auth.getId(), p_id);
				if(result) {
					response.sendRedirect("show-products");
				}else {
					out.print("Error in adding..,");
				}
			}
			else {
				response.sendRedirect("login.jsp");
			}
			/*
			else {
				if(guest!=null) {
					CartDao cDao=new CartDao(DbCon.getConnection());
					result=cDao.AddToCartAsGuest(guest.getId(), p_id);
					if(result) {
						response.sendRedirect("index.jsp");
					}else {
						out.print("Error in adding as guest..,");
					}
				}else {
					//System.out.println(guest);
					//System.out.println(auth);
					response.sendRedirect("login.jsp");
				}
			}
			*/
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*int p_id=Integer.parseInt(request.getParameter("p_id"));
		
		if(request.getParameter("u_id")==null) {
			if(request.getParameter("cookie_id")==null) {
				GuestDao gdao;
				try {
					gdao = new GuestDao(DbCon.getConnection());
					Cookie c=new Cookie("guest_id",gdao.getNewId()+"");
					response.addCookie(c);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				response.sendRedirect("index.jsp");
			}			
		}
		else {
			int u_id=Integer.parseInt(request.getParameter("u_id"));
			try(PrintWriter out=response.getWriter()){
				CartDao cdao=new CartDao(DbCon.getConnection());
				cdao.AddToCart(u_id, p_id);
				response.sendRedirect("index.jsp");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}

}
