package clf.learning.winner.springboot.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenlongfei
  */
public class ServletFromRegistrationBean extends HttpServlet {

	private static final long serialVersionUID = 7152783496215274102L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("******ServletFromRegistrationBean doGet()******");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("******ServletFromRegistrationBean doPost()******");
		PrintWriter out = resp.getWriter();
		out.print("Hello World! ---from ServletFromRegistrationBean");
	}

}
