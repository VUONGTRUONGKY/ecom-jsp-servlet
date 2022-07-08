package dao;
import java.util.ArrayList;
import java.util.List;

import model.*;
import context.QueryDb;

public class OrdersDAO {

	public OrdersDAO() {

	}

	// insert information of Order to data source, that including list of
	// products in cart (C) and information of buyer in Orders 0
	public void insertOrder(Orders o, List<Product> list) throws Exception {

		QueryDb queryDb = new QueryDb();
		List<Integer> listOrderId = new ArrayList<Integer>();
		//List<Product> list = new ArrayList<Product>();
		//list = c.getItems();

		// information for table orders
		String userEmail = "";
		int orderStatus = 0;
		String orderDiscountCode = "";
		String orderAddress = "";
		int productId = 0;
		int orderId = 0;

		// information for table orders detail
		int amountProduct = 0;
		double priceProduct = 0;

		userEmail = o.getUserMail();
		orderAddress = o.getAddress();
		orderDiscountCode = o.getDiscount();
		orderStatus = o.getStatus();
		orderId = o.getOrderId();
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

		queryDb.insertOrders(userEmail, orderStatus, date, orderDiscountCode, orderAddress);

		// add info to order_detail table
		listOrderId = queryDb.getOrderId();
		orderId = listOrderId.get(listOrderId.size() - 1);
		for (int i = 0; i < list.size(); i++) {
			productId = list.get(i).getId();
			amountProduct = list.get(i).getNumber();
			priceProduct = list.get(i).getPrice();
			queryDb.insertOrderDetail(orderId, productId, amountProduct, priceProduct);

		}

	}

}
