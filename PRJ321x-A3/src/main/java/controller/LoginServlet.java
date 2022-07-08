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

import context.QueryDb;
import model.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset = UTF-8");
		request.setCharacterEncoding("utf-8"); // vietnamese
		try {

			request.getSession(true).invalidate();

			// make sure that email is valid
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regex = "[a-zA-Z0-9_!@$%^&*]+";

			// collect data from a login form
			String user = request.getParameter("fname");
			String password = request.getParameter("fpass");
			String remember = request.getParameter("remember");

			// info customer import from form login
			Account accLogin = new Account();
			accLogin.setName(user);
			accLogin.setPwd(password);

			// data customer from database
			List<Account> listAcc = new ArrayList<Account>();
			Account accDatabase = new Account();
			QueryDb Qdb = new QueryDb();
			listAcc = Qdb.getAccountList();

			HttpSession session = request.getSession(true);
			if (!password.matches(regex) || !user.matches(regexMail)) {

				session.setAttribute("error", "invalid systax");
				response.sendRedirect("login.jsp");
			}
			
			session.setAttribute("listAcc", listAcc);

			// user name and password from data base
			String uid = "";
			String pwd = "";
			int role = 0;

			for (int i = 0; i < listAcc.size(); i++) {

				accDatabase = listAcc.get(i);
				uid = accDatabase.getUsr();
				pwd = accDatabase.getPwd();
				role = accDatabase.getRole();

				if (user != null && accLogin.getPwd().equals(pwd) && accLogin.getName().equalsIgnoreCase(uid)) {

					Cookie cookieUser = new Cookie("cookUname", accLogin.getName());
					Cookie cookiePass = new Cookie("cookPass", accLogin.getPwd());
					Cookie cookieRemember = new Cookie("cookRemember", remember);

					response.addCookie(cookieUser);
					response.addCookie(cookiePass);
					response.addCookie(cookieRemember);
					if (role == 1) {
						session.setAttribute("user", user);
						response.sendRedirect("home.jsp");
					} else {
						session.setAttribute("user", user);
						response.sendRedirect("web/admin/index.jsp");
					}

					break;
				}

			}

		} catch (NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
