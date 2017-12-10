package clf.learning.winner.springbase.aspect.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionAspect {

	@Around(value = "execution(void *.doBusiness())")
	public void doTransaction(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("---模拟事务开始");
		pjp.proceed();
		System.out.println("---模拟事务结束");
	}

}
