package clf.learning.winner.springboot.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.webjars.WebJarAssetLocator;

/**
 * @author chenlongfei
 */
@Controller
public class WebJarsController {
	
	/**
	 * webJars将通用的Web前端资源打包成Java的Jar包，然后借助Maven工具对其管理，保证这些Web资源版本唯一性，升级也比较容易.(http://www.webjars.org/)
	 * 在spring boot中，默认将 "/webjars/**" 映射到 "classpath:/META-INF/resources/webjars/"
	 * 例如，<link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>，
	 * 系统会去"classpath:/META-INF/resources/webjars/"寻找
	 * 
	 * 但是bootstrap、jquery等的版本号是个变量，不宜出现在路径当中，我们可以借助WebJarAssetLocator自动推断出版本号
	 */

	private final WebJarAssetLocator assetLocator = new WebJarAssetLocator();

	
	@ResponseBody
	@RequestMapping("/webjarslocator/{webjar}/**")
	public ResponseEntity<Object> locateWebJarAsset(@PathVariable String webjar, HttpServletRequest request) {
		//bootstrap的引用可以简写为：  <link rel='stylesheet' href='/webjarslocator/bootstrap/css/bootstrap.min.css'>
		try {
			String mvcPrefix = "/webjarslocator" + "/" + webjar;  // =/webjarslocator/bootstrap
			String mvcPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);// =/webjarslocator/bootstrap
			String partialPath = mvcPath.substring(mvcPrefix.length()); // =/css/bootstrap.min.css
			String fullPath = assetLocator.getFullPath(webjar, partialPath);  // =META-INF/resources/webjars/bootstrap/3.3.7/css/bootstrap.min.css
			
			return new ResponseEntity<>(new ClassPathResource(fullPath), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
