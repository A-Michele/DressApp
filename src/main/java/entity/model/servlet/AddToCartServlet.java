package entity.model.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
		
		OrderDao oDao=null;
		Ordine ordine= null;
		DettaglioOrdine dettaglio = null;
		DettaglioOrdineDAO dDao= null;
		
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out=response.getWriter()){
			oDao=new OrderDao(DbCon.getConnection());
			dDao=new DettaglioOrdineDAO(DbCon.getConnection());
			User auth=(User) request .getSession().getAttribute("auth");
			if(auth.getIsGuest()==1) {
				response.sendRedirect("login.jsp");
			}
			int p_id= Integer.parseInt(request.getParameter("p_id"));
			ordine= oDao.doRetriveByIdBuy(auth.getId());
			if(ordine==null) {
				ordine=new Ordine(0,new Date(System.currentTimeMillis()),auth.getId(),false);
				oDao.doSave(ordine.getDate(),ordine.getUser(), ordine.getIsBuy());
				dettaglio= new DettaglioOrdine(p_id,1,ordine.getId());
				dDao.insertDettaglioOrdine(dettaglio.getCappello(), 1, dettaglio.getOrdine());
			}
			else {
				ArrayList<DettaglioOrdine> lista=dDao.searchDettaglioOrdineByOrdineId(ordine.getId());
				System.out.println(lista);
				for( int i=0; i<lista.size();i++) {
					DettaglioOrdine x = lista.get(i);
					if(x.getCappello()==p_id) {
						dDao.updateQuantita(x.getQuantita()+1,p_id,ordine.getId());
						response.sendRedirect("index.jsp");
						return;
					}
				}
				dettaglio= new DettaglioOrdine(p_id,1,ordine.getId());
				dDao.insertDettaglioOrdine(dettaglio.getCappello(), dettaglio.getQuantita(), dettaglio.getOrdine());
				
			}
			response.sendRedirect("index.jsp");
			
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
