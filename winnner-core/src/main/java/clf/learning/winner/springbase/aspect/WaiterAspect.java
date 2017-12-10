package clf.learning.winner.springbase.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author chenlongfei
  */
@Component
@Aspect
public class WaiterAspect {

	
	@Pointcut(value = "execution( * clf.learning.winner.springbase.aspect.RestaurantCustomer.eat() )")
	public void eatPointcut() {  //该方法是一个pointcut标志：表示应用程序执行到CustomerImpl.eat()方法时织入advice
		
	}

	@Before(value = "eatPointcut()")
	public void beforeAdvice() {  //该方法是一个前置通知，在RestaurantCustomer.eat()之前执行
		System.out.println("---服务员引导客人入座、点菜");
	}
	
	@AfterReturning(value = "eatPointcut()")
	public void afterAdvice() {  //该方法是一个后置通知，在RestaurantCustomer.eat()之后执行
		System.out.println("---服务员收拾餐桌");
	}
	
}
