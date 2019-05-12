package clf.learning.test.elasticsearch;

import com.google.common.collect.Lists;

import java.util.List;

public class MockUtil {

    public static List<PromotionOrder> mockPromotionOrderList() {
        List<PromotionOrder> list = Lists.newArrayList();
        list.add(mock1());
        list.add(mock2());
        list.add(mock3());
        list.add(mock4());
        list.add(mock5());
        list.add(mock6());

        return list;
    }

    static PromotionOrder mock1(){

        PromotionOrder order = new PromotionOrder();

        order.setOrderId("order-1");
        order.setSkuId("sku-1000001");
        order.setGoodsId(1000001L);
        order.setCatId(1001L);
        order.setMoney(100L);
        order.setNum(1);

        order.getSchemeIds().add(101L);
        order.getSchemeIds().add(102L);

        order.getChannels().add(1);
        order.getChannels().add(2);

        order.getTypeAndChannels().add("10-1");
        order.getTypeAndChannels().add("11-2");

        order.getTypes().add(10);
        order.getTypes().add(11);

        return order;
    }

    static PromotionOrder mock2(){

        PromotionOrder order = new PromotionOrder();

        order.setOrderId("order-2");
        order.setSkuId("sku-1000002");
        order.setGoodsId(1000002L);
        order.setCatId(1002L);
        order.setMoney(200L);
        order.setNum(2);

        order.getSchemeIds().add(103L);
        order.getSchemeIds().add(104L);

        order.getChannels().add(2);
        order.getChannels().add(3);

        order.getTypeAndChannels().add("10-2");
        order.getTypeAndChannels().add("13-3");

        order.getTypes().add(10);
        order.getTypes().add(13);

        return order;
    }

    static PromotionOrder mock3(){

        PromotionOrder order = new PromotionOrder();

        order.setOrderId("order-3");
        order.setSkuId("sku-1000003");
        order.setGoodsId(1000003L);
        order.setCatId(1003L);
        order.setMoney(300L);
        order.setNum(3);

        order.getSchemeIds().add(105L);
        order.getSchemeIds().add(106L);
        order.getSchemeIds().add(107L);

        order.getChannels().add(1);
        order.getChannels().add(3);
        order.getChannels().add(4);

        order.getTypeAndChannels().add("10-1");
        order.getTypeAndChannels().add("11-3");
        order.getTypeAndChannels().add("17-4");

        order.getTypes().add(10);
        order.getTypes().add(11);
        order.getTypes().add(17);

        return order;
    }

    static PromotionOrder mock4(){

        PromotionOrder order = new PromotionOrder();

        order.setOrderId("order-4");
        order.setSkuId("sku-1000004");
        order.setGoodsId(1000004L);
        order.setCatId(1001L);
        order.setMoney(400L);
        order.setNum(4);

        order.getSchemeIds().add(108L);
        order.getSchemeIds().add(109L);

        order.getChannels().add(1);
        order.getChannels().add(4);

        order.getTypeAndChannels().add("12-1");
        order.getTypeAndChannels().add("18-4");

        order.getTypes().add(12);
        order.getTypes().add(18);

        return order;
    }

    static PromotionOrder mock5(){

        PromotionOrder order = new PromotionOrder();

        order.setOrderId("order-5");
        order.setSkuId("sku-1000005");
        order.setGoodsId(1000005L);
        order.setCatId(1003L);
        order.setMoney(500L);
        order.setNum(5);

        order.getSchemeIds().add(110L);

        order.getChannels().add(3);

        order.getTypeAndChannels().add("14-3");

        order.getTypes().add(14);

        return order;
    }

    static PromotionOrder mock6(){

        PromotionOrder order = new PromotionOrder();

        order.setOrderId("order-6");
        order.setSkuId("sku-1000006");
        order.setGoodsId(1000006L);
        order.setCatId(1004L);
        order.setMoney(600L);
        order.setNum(6);

        order.getSchemeIds().add(111L);

        order.getChannels().add(2);

        order.getTypeAndChannels().add("11-2");

        order.getTypes().add(11);

        return order;
    }

}
