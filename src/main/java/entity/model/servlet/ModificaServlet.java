package entity.model.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.connection.DbCon;
import entity.dao.CappelloDao;

/**
 * Servlet implementation class ModificaServlet
 */
@WebServlet("/edit-product")
public class ModificaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("p_id"));
		String nome=request.getParameter("nomeP");
		String desc=request.getParameter("descP");
		double costo=Double.parseDouble(request.getParameter("costo"));
		String categoria=request.getParameter("cat");
		String foto=request.getParameter("foto");
		int dispo=Integer.parseInt(request.getParameter("disp"));
		try {
			CappelloDao pdao=new CappelloDao(DbCon.getConnection());
			boolean b=pdao.updateProdotto(id,nome,desc,costo,categoria,foto,dispo);
			if(b=true) {
				response.sendRedirect("ProdottiAdmin.jsp");
			}
			else {
				System.out.println("Errore update");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
