package entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.connection.DbCon;
import entity.model.Cart;
import entity.model.Product;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		this.con=con;
	}
	
	public ProductDao() {
	}


	public List<Product> getAllProducts(){
		List<Product> products=new ArrayList<Product>();
		try {
			query="SELECT * FROM prodotto";
			//Connection c = DbCon.getConnection();
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Product row=new Product();
				row.setId(rs.getInt("id"));
				row.setNome(rs.getString("nome"));
				row.setCategoria(rs.getString("categoria"));
				row.setPrezzo(rs.getDouble("costo"));
				row.setImg(rs.getString("foto"));
				row.setDescrizione(rs.getString("descrizione"));
				row.setDisp(rs.getInt("disponibilita"));
				products.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	public List<Cart> getCartProduct(ArrayList<Cart> cartList){
		List<Cart> products=new ArrayList<Cart>();
		try{
			if(cartList.size()>0){
				for(Cart item:cartList) {
					query="SELECT * FROM prodotto WHERE id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs=pst.executeQuery();
					while(rs.next()) {
						Cart row=new Cart();
						row.setId(rs.getInt("id"));
						row.setNome(rs.getString("nome"));
						row.setCategoria(rs.getString("categoria"));
						row.setPrezzo(rs.getDouble("costo")*item.getQuantita());
						row.setQuantita(item.getQuantita());
						
						products.add(row);
					}
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			//e.printStacktrace();
		}
		return products;
	}
	
	public double getTotal(ArrayList<Cart> cartList) {
		double sum=0;
		try {
			if(cartList.size()>0) {
				for(Cart item: cartList) {
					query="SELECT costo FROM prodotto WHERE id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1,item.getId_prodotto());
					rs=pst.executeQuery();
					while(rs.next()) {
						sum+=rs.getDouble("costo")*item.getQuantita();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	public Cart completeCart(Cart c) {
		query="SELECT nome,categoria,costo FROM prodotto WHERE id=?";
		try {
			pst=this.con.prepareStatement(query);
			pst.setInt(1,c.getId_prodotto());
			rs=pst.executeQuery();
			while(rs.next()) {
				c.setNome(rs.getString("nome"));
				c.setCategoria(rs.getString("categoria"));
				c.setPrezzo(rs.getDouble("costo"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public ArrayList<Product> searchItems(String s){
        ArrayList<Product> products=new ArrayList<Product>();
        try {
            query="SELECT * FROM prodotto WHERE nome LIKE '%"+s+"%'";
            pst=this.con.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next()) {
                Product p =new Product();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrezzo(rs.getDouble("costo"));
                p.setImg(rs.getString("foto"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setDisp(rs.getInt("disponibilita"));
                products.add(p);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return products;
    }
	
	public void removeProduct(int p_id) {
		query="DELETE FROM prodotto WHERE id=" + p_id;
			 try {
				pst=this.con.prepareStatement(query);
				pst.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
	}
	
	public void insertProduct(String nome,String descrizione,double costo,String categoria,String foto) throws SQLException {
        try {
            query="INSERT INTO prodotto(nome,descrizione,costo,categoria,foto) VALUES(?,?,?,?,?)";
            pst=this.con.prepareStatement(query);
            pst.setString(1, nome);
            pst.setString(2, descrizione);
            pst.setDouble(3, costo);
            pst.setString(4, categoria);
            pst.setString(5, foto);
            pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

	public Product retriveProductById(int p_id) {
		Product p=new Product();
		try {

            query="SELECT * from prodotto where id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, p_id);
            rs=pst.executeQuery();
            while(rs.next()) {
            	p.setId(rs.getInt("id"));
            	p.setNome(rs.getString("nome"));
            	p.setCategoria(rs.getString("categoria"));
            	p.setPrezzo(rs.getDouble("costo"));
            	p.setImg(rs.getString("foto"));
            	p.setDescrizione(rs.getString("descrizione"));
            	p.setDisp(rs.getInt("disponibilita"));
            }
            return p;
			
        }catch(Exception e){
            e.printStackTrace();
        }
		return null;
	}

	public boolean updateProdotto(int id, String nome, String desc, double costo, String categoria, String foto,
			int dispo) {
		try {
            query="UPDATE Prodotto SET nome=?, descrizione=?, costo=?, categoria=?,foto=?,disponibilita=? WHERE id=?";
            pst=this.con.prepareStatement(query);
            pst.setString(1, nome);
            pst.setString(2, desc);
            pst.setDouble(3, costo);
            pst.setString(4, categoria);
            pst.setString(5, foto);
            pst.setInt(6, dispo);
            pst.setInt(7, id);
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
		
		return false;
	}
}
