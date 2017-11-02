package clf.learning.winner.springboot.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletContextInitializer;

/**
 * @author chenlongfei
  */
public class ServletFromContextInitializer implements ServletContextInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("servletFromContextInitializer", new InnerServlet());
		dynamic.addMapping("/ServletFromContextInitializer");
		System.out.println("Mapping servlet: 'servletFromContextInitializer' to [/ServletFromContextInitializer]");
	}
	
	class InnerServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("******ServletFromContextInitializer doGet()******");
			doPost(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("******ServletFromContextInitializer doPost()******");
			PrintWriter out = resp.getWriter();
			out.print("Hello World! ---from ServletFromContextInitializer");
		}

	}
	
}
