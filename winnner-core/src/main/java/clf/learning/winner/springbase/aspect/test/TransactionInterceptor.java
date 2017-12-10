package clf.learning.winner.springbase.aspect.test;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TransactionInterceptor implements MethodInterceptor{
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		//入参分别代表：动态代理对象、被拦截的方法、方法入参、代理方法对象
		
		System.out.println("---模拟事务开始");
		Object result = proxy.invokeSuper(obj, args);  //调用被代理对象的方法
		System.out.println("---模拟事务结束");
		return result;
	}
	
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SampleService.class);  //设置被代理对象
		enhancer.setCallback(new TransactionInterceptor());  //设置方法拦截器，等同于AOP中的Advice
		
		SampleService sampleService = (SampleService)enhancer.create();  //生成动态代理
		System.out.println("SampleService实现类： " + sampleService.getClass().getSimpleName());
		sampleService.doBusiness();
	}

}
