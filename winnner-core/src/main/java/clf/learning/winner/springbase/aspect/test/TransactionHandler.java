package clf.learning.winner.springbase.aspect.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionHandler implements InvocationHandler {

	private Object target; // 被代理的对象

	public TransactionHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 入参分别代表：动态代理对象、被拦截的方法、方法入参
		System.out.println("---模拟事务开始");
		Object result = method.invoke(target, args); // 调用被代理对象的方法
		System.out.println("---模拟事务结束");
		return result;
	}

	public static void main(String[] args) {
		BaseService sampleService = new SampleService();
		TransactionHandler transactionHandler = new TransactionHandler(sampleService);

		ClassLoader loader = sampleService.getClass().getClassLoader();
		Class[] interfaces = sampleService.getClass().getInterfaces();

		// 实例化代理类
		BaseService proxyService = (BaseService) Proxy.newProxyInstance(loader, interfaces, transactionHandler);
		System.out.println("BaseService实现类： " + proxyService.getClass().getSimpleName());
		proxyService.doBusiness();
	}

}
