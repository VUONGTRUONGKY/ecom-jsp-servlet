package bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.QueryDb;
import model.Product;

public class Body {
	
	/*
	 * 
	 * */
	public ResultSet result(String sql) {
		
		QueryDb data = new QueryDb();
		ResultSet rs = null;
		rs = data.query(sql);
		
		return rs;
	}
	
	public List<Product> getProduct() {
		int i = 1;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<Product>();
		final String sql = "select * from products";
		Product e = new Product();
		rs = result(sql);
		
		try {
			while (rs.next()) {
				e = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("product_des"), rs.getFloat("product_price"), 
						rs.getString("product_img_source"), rs.getString("product_type"), rs.getString("product_brand"), i);
				productList.add(e);
				i++;
				
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		
		
		return productList;
		
	}
	

	/*
	 * 
	 * take out name of product from database
	 * 
	 */
	public List<String> getProduct_name() {
		
		ResultSet rs = null;
		List<String> product_name = new ArrayList<String>();
		final String sql = "select product_name from products";
		rs = result(sql);
		try {
			while (rs.next()) {
				product_name.add(rs.getString("product_name"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return product_name;

	}

	/*
	 * take out price of product form data base
	 */
	public List<Double> getProduct_price() {
		List<Double> productPrice = new ArrayList<Double>();
		ResultSet rs = null;
		final String sql = "select product_price from products";
		rs = result(sql);

		try {
			while (rs.next()) {
				productPrice.add(rs.getDouble("product_price"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return productPrice;
	}

	public List<String> getProduct_des() {

		List<String> desc = new ArrayList<String>();
		ResultSet rs = null;
		final String sql = "select product_des from products";
		rs = result(sql);
		try {
			while (rs.next()) {
				desc.add(rs.getString("product_des"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return desc;

	}
	
	
	

}
