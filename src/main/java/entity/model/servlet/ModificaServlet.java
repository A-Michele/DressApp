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
import entity.model.Cappello;

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
		CappelloDao cDao=null;
		Cappello c=null;
		int id=0;
		try {
			cDao = new CappelloDao(DbCon.getConnection());
			id=Integer.parseInt(request.getParameter("p_id"));
			c=cDao.retriveProductById(id);
			System.out.println(c);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String nome=request.getParameter("nomeP");
		String desc=request.getParameter("descP");
		float costo=0;
		if(!(request.getParameter("prezzo").equals(""))) {
			System.out.println(costo);
			costo=Float.parseFloat(request.getParameter("prezzo"));
		}
		String categoria=request.getParameter("cat");
		String foto=request.getParameter("foto");
		int dispo=0;
		if(!(request.getParameter("disp").equals(""))) dispo=Integer.parseInt(request.getParameter("disp"));
		int change=0;
		if(!(nome.equals(""))){
			c.setNome(nome);
			change=1;
		}
		if(!(desc.equals(""))) {
			c.setDescrizione(desc);
			change=1;
		}
		if(costo>0) {
			c.setPrezzo(costo);
			change=1;
		}
		if(!(categoria.equals(""))) {
			c.setCategoria(categoria);
			change=1;
		}
		if(!(foto.equals(""))) {
			c.setFoto(foto);
		}
		if(dispo>0) {
			if(change==0) {
				cDao.updateDisp(id,dispo);
				response.sendRedirect("ProdottiAdmin.jsp");
			}
			c.setDisp(dispo);
		}
		cDao.insertProduct(c.getNome(),c.getDescrizione(),c.getPrezzo(),c.getCategoria(),c.getFoto(),c.getDisp());
		cDao.updateModificato(id);
		response.sendRedirect("ProdottiAdmin.jsp");
		
	}

}
