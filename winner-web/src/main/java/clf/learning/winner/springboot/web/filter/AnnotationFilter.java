package clf.learning.winner.springboot.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @author chenlongfei
  */
@WebFilter
public class AnnotationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init filter: AnnotationFilter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String serverName = request.getServerName();
		if (!serverName.equals("localhost")) {  //只允许以"localhost:8080/"的url访问
			PrintWriter out = response.getWriter();
			out.println("only localhost permitted !");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		System.out.println("destroy filter: AnnotationFilter");
	}

}
