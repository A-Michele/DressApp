package entity.model.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.CappelloDao;
import entity.dao.DettaglioOrdineDAO;
import entity.dao.OrderDao;
import entity.model.Cappello;
import entity.model.DettaglioOrdine;
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
			int o_id = Integer.parseInt(request.getParameter("o_id"));
			OrderDao oDao = new OrderDao(DbCon.getConnection());
			DettaglioOrdineDAO dDao =new DettaglioOrdineDAO(DbCon.getConnection());
			List<DettaglioOrdine> list=dDao.searchDettaglioOrdineByOrdineId(o_id);
			CappelloDao cDao=new CappelloDao(DbCon.getConnection());
			for(DettaglioOrdine o :list) {
				int p_id=o.getCappello();
				int quantita=o.getQuantita();
				Cappello c=cDao.retriveProductById(p_id);
				cDao.riduciDisponibilita(p_id,c.getDisp()-quantita);
			}
			
			boolean x = oDao.changeState(o_id);
			if(x==true) {
				response.sendRedirect("index.jsp");
		}
			else System.out.println("Errore");
		
		}
		catch (ClassNotFoundException | SQLException e) {
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
