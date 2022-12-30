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
import entity.dao.UserDao;
import entity.model.User;

@WebServlet("/insert-product")
public class InsertProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome=request.getParameter("nomeP");
        String descrizione=request.getParameter("descP");
        double costo=Double.parseDouble(request.getParameter("costo"));    //i nomi che passiamo alla get parameter sono quelli contenuti nel tag name all'interno del file jsp
        String categoria=request.getParameter("cat");
        String foto=request.getParameter("nomeP");
        try {
            CappelloDao pdao=new CappelloDao(DbCon.getConnection());
            pdao.insertProduct(nome,descrizione,costo, categoria,foto);
            response.sendRedirect("show-products");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}