package entity.model.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.model.Ordine;

/**
 * Servlet implementation class selectPayment
 */
@WebServlet("/selectPayment")
public class selectPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Ordine ordine = request.getAttribute("ordine");
			OrderDao oDao = newOrderDao(Dbcon.getConnection());
			boolean x = oDao.changeState(ordine.getId());
			if(x==true) {
				window.alert("Ordine effettuato");
				response.sendRedirect("index.jsp");
		}
			else out.print("Errore");
		
		}catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
