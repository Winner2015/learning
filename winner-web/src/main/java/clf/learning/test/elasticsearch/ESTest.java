package clf.learning.test.elasticsearch;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.List;

public class ESTest {

    private static final String HOST = "localhost";
    private static final int PORT = 9200;
    private static final String PROTOCAL = "http";

    private static final String PROMOTION_ORDER_INDEX = "/promtoion/order/";

    static RestClient restClient = RestClient.builder(
            new HttpHost(HOST, PORT, PROTOCAL)).build();

    public static void main(String args []){

//        init();

//        String queryByCatId = "{ \"query\" : { \"constant_score\" : { \"filter\" : { \"term\" : { \"catId\" : 1001 } } } } }";
//        query(queryByCatId);
//
//        String queryGroupByType = "{ \"query\" : { \"constant_score\" : { \"filter\" : { \"term\" : { \"types\" : 1001 } } } } }";
//        query(queryGroupByType);

/**

 {
 "size":0,
 "aggs":{
 "group_key":{
 "terms":{
 "field":"catId"
 },
 "aggs":{
 "sum_num":{
 "sum":{
 "field":"num"
 }
 },
 "sum_money":{
 "sum":{
 "field":"money"
 }
 }
 }
 }
 }
 }
 *
 */

        String sumNumByPromotionType = "{\n" +
                "    \"size\":0,\n" +
                "    \"aggs\":{\n" +
                "        \"group_key\":{\n" +
                "            \"terms\":{\n" +
                "                \"field\":\"catId\"\n" +
                "            },\n" +
                "            \"aggs\":{\n" +
                "                \"sum_num\":{\n" +
                "                    \"sum\":{\n" +
                "                        \"field\":\"num\"\n" +
                "                    }\n" +
                "                },\n" +
                "                \"sum_money\":{\n" +
                "                    \"sum\":{\n" +
                "                        \"field\":\"money\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        query(sumNumByPromotionType);
//{"took":2,"timed_out":false,"_shards":{"total":1,"successful":1,"skipped":0,"failed":0},"hits":{"total":{"value":6,"relation":"eq"},"max_score":null,"hits":[]},"aggregations":{"group_key":{"doc_count_error_upper_bound":0,"sum_other_doc_count":0,"buckets":[{"key":1001,"doc_count":2,"sum_num":{"value":5.0},"sum_money":{"value":500.0}},{"key":1003,"doc_count":2,"sum_num":{"value":8.0},"sum_money":{"value":800.0}},{"key":1002,"doc_count":1,"sum_num":{"value":2.0},"sum_money":{"value":200.0}},{"key":1004,"doc_count":1,"sum_num":{"value":6.0},"sum_money":{"value":600.0}}]}}}





        close();
    }


    private static void put(PromotionOrder order){
        Request request = new Request("POST",PROMOTION_ORDER_INDEX + order.getOrderId());
        request.setJsonEntity(JSON.toJSONString(order));

        Response response = null;
        try {
            response = restClient.performRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        print(response);
    }

    private static void delete(String orderId){
        Request request = new Request("DELETE", PROMOTION_ORDER_INDEX + orderId);
        Response response = null;
        try {
            response = restClient.performRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        print(response);
    }

    private static void query(String query){
        Request request = new Request("POST", PROMOTION_ORDER_INDEX + "_search");
        request.setJsonEntity(query);
        Response response = null;
        try {
            response = restClient.performRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        print(response);
    }


    private static void init() {

        List<PromotionOrder> list = MockUtil.mockPromotionOrderList();
        for (PromotionOrder o : list) {
            put(o);
        }

        //{"took":835,"timed_out":false,"_shards":{"total":1,"successful":1,"skipped":0,"failed":0},"hits":{"total":{"value":6,"relation":"eq"},"max_score":1.0,"hits":[{"_index":"promtoion","_type":"order","_id":"order-1","_score":1.0,"_source":{"catId":1001,"channels":[1,2],"goodsId":1000001,"money":100,"num":1,"orderId":"order-1","schemeIds":[101,102],"skuId":"sku-1000001","typeAndChannels":["10-1","11-2"],"types":[10,11]}},{"_index":"promtoion","_type":"order","_id":"order-2","_score":1.0,"_source":{"catId":1002,"channels":[2,3],"goodsId":1000002,"money":200,"num":2,"orderId":"order-2","schemeIds":[103,104],"skuId":"sku-1000002","typeAndChannels":["10-2","13-3"],"types":[10,13]}},{"_index":"promtoion","_type":"order","_id":"order-3","_score":1.0,"_source":{"catId":1003,"channels":[1,3,4],"goodsId":1000003,"money":300,"num":3,"orderId":"order-3","schemeIds":[105,106,107],"skuId":"sku-1000003","typeAndChannels":["10-1","11-3","17-4"],"types":[10,11,17]}},{"_index":"promtoion","_type":"order","_id":"order-4","_score":1.0,"_source":{"catId":1001,"channels":[1,4],"goodsId":1000004,"money":400,"num":4,"orderId":"order-4","schemeIds":[108,109],"skuId":"sku-1000004","typeAndChannels":["12-1","18-4"],"types":[12,18]}},{"_index":"promtoion","_type":"order","_id":"order-5","_score":1.0,"_source":{"catId":1003,"channels":[3],"goodsId":1000005,"money":500,"num":5,"orderId":"order-5","schemeIds":[110],"skuId":"sku-1000005","typeAndChannels":["14-3"],"types":[14]}},{"_index":"promtoion","_type":"order","_id":"order-6","_score":1.0,"_source":{"catId":1004,"channels":[2],"goodsId":1000006,"money":600,"num":6,"orderId":"order-6","schemeIds":[111],"skuId":"sku-1000006","typeAndChannels":["11-2"],"types":[11]}}]}}

    }

    private static void close() {
        try {
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(Response response){
        System.out.println("***************response***************");
        System.out.println(JSON.toJSONString(response.getStatusLine(), true));
        System.out.println("***************response***************");
        String result = null;
        try {
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        System.out.println("******************************");
    }

}
