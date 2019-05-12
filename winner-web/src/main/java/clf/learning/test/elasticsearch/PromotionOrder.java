package clf.learning.test.elasticsearch;

import java.util.ArrayList;
import java.util.List;

public class PromotionOrder {

    private String orderId;

    private Long goodsId;
    private String skuId;
    private Long catId;

    private Long money;
    private Integer num;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<Long> getSchemeIds() {
        return schemeIds;
    }

    public void setSchemeIds(List<Long> schemeIds) {
        this.schemeIds = schemeIds;
    }

    public List<Integer> getChannels() {
        return channels;
    }

    public void setChannels(List<Integer> channels) {
        this.channels = channels;
    }

    public List<String> getTypeAndChannels() {
        return typeAndChannels;
    }

    public void setTypeAndChannels(List<String> typeAndChannels) {
        this.typeAndChannels = typeAndChannels;
    }

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    private List<Long> schemeIds = new ArrayList<>();
    private List<Integer> channels = new ArrayList<>();
    private List<String> typeAndChannels = new ArrayList<>();
    private List<Integer> types = new ArrayList<>();


}
