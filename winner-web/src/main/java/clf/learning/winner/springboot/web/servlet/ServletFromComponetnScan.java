package clf.learning.winner.springboot.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenlongfei
 */
@WebServlet(urlPatterns = {"/ServletFromComponetnScan"}, name="servletFromComponetnScan")
public class ServletFromComponetnScan extends HttpServlet {

	private static final long serialVersionUID = 8100674006378811506L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("******ServletFromComponetnScan doGet()******");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("******ServletFromComponetnScan doPost()******");
		PrintWriter out = resp.getWriter();
		out.print("Hello World! ---from ServletFromComponetnScan");
	}
}