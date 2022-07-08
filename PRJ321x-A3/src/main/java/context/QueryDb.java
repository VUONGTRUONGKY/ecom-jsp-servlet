package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Account;

public class QueryDb {
	private DBContext db = new DBContext();
	private Connection conn = null;
	private List<Account> listAcc = new ArrayList<Account>();

	public ResultSet query(String sqlStatement) {

		Statement stmt = null;
		ResultSet rs = null;
		String sql = sqlStatement;

		try {
			conn = db.getContextion();
			System.out.println("connection");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}

		return rs;

	}
	
	public List<Account> getAccountList() {
		
		List<Account> listAcc = new ArrayList<Account>();
		Account acc = new Account();
		final String sql = "select * from account";
		ResultSet r = query(sql);
		String userMail = "";
		String password = "";
		int accRole = 0;
		String name = "";
		String address = "";
		String phone = "";
		try {
			while(r.next()) {
				userMail = r.getString(1);
				password = r.getString(2);
				accRole = r.getInt(3);
				name = r.getString(4);
				address = r.getString(5);
				phone = r.getString(6);
				acc = new Account(userMail, password, accRole, name, address, phone);
				listAcc.add(acc);
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return listAcc;
	}

	public List<Integer> getOrderId() {

		final String sql = "select order_id from orders";
		List<Integer> list = new ArrayList<Integer>();
		ResultSet r = query(sql);

		try {
			while (r.next()) {

				list.add(r.getInt("order_id"));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return list;
	}

	public void insertAccount(String userMail, String password, int accountRole, String userName, String userAddress,
			String userPhone) {

		String sql = "insert into account (user_mail, password, account_role, user_name, user_address, user_phone) "
				+ "values(?, ?, ?, ?, ?, ?)";

		try {
			conn = db.getContextion();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userMail);
			pstmt.setString(2, password);
			pstmt.setInt(3, accountRole);
			pstmt.setString(4, userName);
			pstmt.setString(5, userAddress);
			pstmt.setString(6, userPhone);
			pstmt.executeUpdate();
			System.out.println("complete insert to account talble");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void insertOrders(String email, int orderStatus, Timestamp date, String discountCode, String orderAddress) {

		String sql = "insert into orders (user_mail, order_status, order_date, order_discount_code, order_address) "
				+ "values(?, ?, ?, ?, ?)";
		try {
			conn = db.getContextion();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, orderStatus);
			pstmt.setTimestamp(3, date);
			pstmt.setString(4, discountCode);
			pstmt.setString(5, orderAddress);
			pstmt.executeUpdate();
			System.out.println("complete insert to order talble");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void insertOrderDetail(int orderId, int productId, int amountProduct, double price_product) {

		String sql = "insert into orders_detail (order_id, product_id, amount_product, price_product) "
				+ "values(?, ?, ?, ?)";

		try {
			conn = db.getContextion();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, amountProduct);
			pstmt.setDouble(4, price_product);
			pstmt.executeUpdate();
			System.out.println("complete insert to order detail");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	

}
