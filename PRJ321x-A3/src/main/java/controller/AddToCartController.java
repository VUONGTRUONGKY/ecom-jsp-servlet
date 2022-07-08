package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Body;
import dao.ListProductDAO;
import model.Cart;
import model.Product;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String convert(int info) {

		return "" + info;
	}

	public String convert(double info) {

		return "" + info;
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		try {

			HttpSession session = request.getSession(true);
			String idd = request.getParameter("id");
			String action = request.getParameter("action");
			String check = request.getParameter("check");
			String pay = request.getParameter("pay");
			List<Product> items = new ArrayList<Product>();
			String amount = "";
			String idProduct = "";
			String price = "";
			String number = "";
			Cart c = new Cart();
			String user = (String) session.getAttribute("user");
			int numberOfItem = 0;
			List<Product> itemOfCart = new ArrayList<Product>();

			Body bd = new Body();
			List<Product> listProduct = new ArrayList<Product>();
			listProduct = bd.getProduct();
			
			if(pay != null &&pay.equalsIgnoreCase("off")) {
				
				session.setAttribute("pay", pay);
				
			}

			if (check == null) {

				check = "a";
			}
			

			if (action != null && action.equalsIgnoreCase("add")
					|| action != null && action.equalsIgnoreCase("addproduct")
					|| action != null && action.equalsIgnoreCase("lessproduct")) {

				if (session.getAttribute("cart") == null && check.equalsIgnoreCase("checked")) {
					session.setAttribute("cart", new Cart());
				}

				int id = Integer.parseInt(idd);
				Product p = new ListProductDAO().getProduct(id);
				c = (Cart) session.getAttribute("cart");
				if (action.equalsIgnoreCase("addproduct") || action.equalsIgnoreCase("add")) {
					c.add(new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getSrc(), p.getType(),
							p.getBrand(), 1));
				} else if (action.equalsIgnoreCase("lessproduct") || action.equalsIgnoreCase("add")) {

					c.less(new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getSrc(),
							p.getType(), p.getBrand(), 1));
				}

				amount = c.StringAmount();
			} else if (action != null && action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(idd);
				c = (Cart) session.getAttribute("cart");
				c.remove(id);
				amount = c.StringAmount();
			}

			items = c.getItems();

			String tempUserName = "";
			String name = "";
			// -------------------------------Add data in
			// cookies--------------------------------------------------
			if (user != null && c != null) {
				numberOfItem = items.size();
				String numItemOfUser = numberOfItem + "";
				tempUserName = user.replaceAll("[^a-zA-Z0-9]", "");
				name = "cookNumOfItem" + tempUserName; // name of attribute don't have a symbol character
				if (!items.isEmpty()) {

					Cookie numOfItem = new Cookie(name, numItemOfUser);
					response.addCookie(numOfItem);
					Cookie cookAmount = new Cookie("amount" + tempUserName, amount);
					response.addCookie(cookAmount);

				}

				for (int i = 0; i < numberOfItem; i++) {
					// value
					idProduct = convert(items.get(i).getId());
					price = convert(items.get(i).getPrice());
					number = convert(items.get(i).getNumber());

					Cookie cookIdProduct = new Cookie("cookId" + tempUserName + i, idProduct);
					cookIdProduct.setMaxAge(365 * 24 * 60 * 60);
					Cookie cookPrice = new Cookie("cookPrice" + tempUserName + i, price);
					cookPrice.setMaxAge(365 * 24 * 60 * 60);
					Cookie cookNumber = new Cookie("cookNumber" + tempUserName + i, number);
					cookNumber.setMaxAge(365 * 24 * 60 * 60);

					response.addCookie(cookIdProduct);
					response.addCookie(cookPrice);
					response.addCookie(cookNumber);

				}

			}

			Cookie[] cookies = request.getCookies();
			// ---------------------------------------End add
			// cookies------------------------------------------

			if (user != null && cookies != null) {
				cookies = request.getCookies();
				// Map<String, String> maps = new HashMap<String, String>();
				int num = 0;
				String tempName = "";
				int tempId = 0;
				float tempPrice = 0;
				Double tempAmount = 0.0;
				int tempNumOfProduct = 0;
				Product tempProduct = new Product();
				// take out number of item from cookie
				for (Cookie cook : cookies) {
					if (cook.getName().contains(name)) {
						num = Integer.parseInt(cook.getValue());

					}
				}
				for (int i = 0; i < num; i++) {
					for (Cookie cook : cookies) {

						if (cook.getName().equals("cookNumber" + tempUserName + i)) {

							tempNumOfProduct = Integer.parseInt(cook.getValue());

						} else if (cook.getName().equals("cookPrice" + tempUserName + i)) {

							tempPrice = Float.parseFloat(cook.getValue());

						} else if (cook.getName().equals("amount" + tempUserName)) {

							tempAmount = Double.parseDouble(cook.getValue()); // this is sum price of item in cart

						} else if (cook.getName().equals("cookId" + tempUserName + i)) {

							int id = Integer.parseInt(cook.getValue());
							tempId = id;

							for (int j = 0; j < listProduct.size(); j++) {
								if (listProduct.get(j).getId() == id) {

									tempName = listProduct.get(j).getName();

								}

							}
						}
					}
					tempProduct = new Product(tempId, tempName, "", tempPrice, "", "", "", tempNumOfProduct);
					itemOfCart.add(tempProduct);
					session.setAttribute("listItem", itemOfCart);
					session.setAttribute("amount", tempAmount);

				}

			}
			RequestDispatcher rq = request.getRequestDispatcher("cart.jsp");
			rq.forward(request, response);

		} catch (Exception ex) {

			response.getWriter().println(ex);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {

			response.getWriter().println(e);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
