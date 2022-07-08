package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import context.QueryDb;
import dao.OrdersDAO;
import model.Account;
import model.Cart;
import model.Orders;
import model.Product;

/**
 * Servlet implementation class PayController
 */
@WebServlet("/PayController")
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		try {
			List<Product> list = new ArrayList<Product>();
			HttpSession session = request.getSession(true);
			String userName = request.getParameter("name");
			String user = (String) session.getAttribute("user");
			String discount = request.getParameter("discount");
			String address = request.getParameter("address");
			String pay = request.getParameter("pay");
			String action = request.getParameter("action");
			Cart c = (Cart) session.getAttribute("cart");

			List<Account> listAcc = new ArrayList<Account>();
			listAcc = (List<Account>) session.getAttribute("listAcc");
			
			String tempUserName = user.replaceAll("[^a-zA-Z0-9]", "");

			if (listAcc == null) {
				QueryDb Qdb = new QueryDb();
				listAcc = Qdb.getAccountList();
			}

			Orders d = new Orders(userName, 2, discount, address, "", null);
			OrdersDAO dao = new OrdersDAO();

			if (c == null) {

				list = (List<Product>) session.getAttribute("listItem");
				dao.insertOrder(d, list);

			}

			if (c != null) {
				dao.insertOrder(d, c.getItems());
			}

			System.out.println("pay" + pay);
			if (pay != null && pay.equals("on")) {
				Cookie[] cookies = request.getCookies();
				if (cookies != null)
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().contains(tempUserName)) {

							cookies[i].setMaxAge(0);
							response.addCookie(cookies[i]);

						}
					}

				session.removeAttribute("cart");
				action = null;
				session.setAttribute("pay", pay);

			}

			for (int i = 0; i < listAcc.size(); i++) {

				if (user.equals(listAcc.get(i).getUsr()) && listAcc.get(i).getRole() == 2) {
					response.sendRedirect("web/admin/index.jsp");

				} else {

					response.sendRedirect("pay.jsp");
				}

			}

		} catch (Exception ex) {

			response.getWriter().println(ex);
			ex.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
