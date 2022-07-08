package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;
import model.*;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/ListController")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // display information in data source
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	response.setContentType("text/html;charset=UTF-8");
    	try(PrintWriter out = response.getWriter()) {
    		
    		List<Product> ls = new ListProductDAO().search("");
    		request.setAttribute("products", ls);
    		RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
    		rd.forward(request, response);
    		
    	} catch (Exception ex) {
    		
    		Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
