package clf.learning.winner.springboot.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author chenlongfei
 */
@WebListener
public class AnnotationListener implements ServletContextListener {

	//还可以配置HttpSessionListener、ServletRequestListener接等
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("init ServletContextListener: AnnotationListener");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("destroy ServletContextListener: AnnotationListener");

	}

}
