package clf.learning.winner.springbase.aspect.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

	@Before(value = "execution(void *.doBusiness())")
	public void log() {
		System.out.println("---模拟记录日志");
	}

}
