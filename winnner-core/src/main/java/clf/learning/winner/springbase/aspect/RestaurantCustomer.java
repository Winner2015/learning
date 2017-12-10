package clf.learning.winner.springbase.aspect;

import org.springframework.stereotype.Service;

@Service("restaurantCustomer")
public class RestaurantCustomer{

	//食客吃饭
	public void eat(){
		System.out.println("客人开始吃饭");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("客人结束吃饭");
	}

}
