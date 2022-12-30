package entity.model.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.CartDao;
import entity.dao.CappelloDao;
import entity.model.*;

/**
 * Servlet implementation class SmistaCartDaoServlet
 */
@WebServlet("/smista-cart")
public class SmistaCartDaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SmistaCartDaoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		User auth=(User) request.getSession().getAttribute("auth");
		CappelloDao pDao;
		try {
			if (auth != null) {
				request.setAttribute("auth", auth);
			}else{
				auth=new User();
				auth.setIsGuest(1);
			}
			pDao = new CappelloDao(DbCon.getConnection());
			CartDao cDao=new CartDao(DbCon.getConnection());
			ArrayList<Cart> cart_list = cDao.retrivePerUser(auth.getId());
			ArrayList<Cart> carrello=new ArrayList<Cart>();
			for(Cart c:cart_list){
				Cart c1=pDao.completeCart(c);
				carrello.add(c1);
			}
			double total=0;
			//total+= pDao.getTotal(carrello);
			request.setAttribute("total", total);
			request.setAttribute("carrello", carrello);
			RequestDispatcher dispatcher  = request.getRequestDispatcher("cart.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
