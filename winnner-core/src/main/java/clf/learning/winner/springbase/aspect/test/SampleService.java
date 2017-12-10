package clf.learning.winner.springbase.aspect.test;

import org.springframework.stereotype.Service;

@Service("sampleService")
public class SampleService implements BaseService{
	
	@Override
	public void doBusiness() {
		System.out.println("执行业务逻辑");
	}

}

