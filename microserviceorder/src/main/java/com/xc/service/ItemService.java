package com.xc.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xc.Item;
import com.xc.feign.ItemFeignClient;
import com.xc.utils.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemService {

    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;
    @Value("${myspcloud.item.url}")
    private String itemUrl;
    @Autowired
    private OrderProperties orderProperties;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Resource
    private ItemFeignClient itemFeignClient;

    public Item queryItemById(Long id) {
//        String serviceId = "app-item";
//        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
//        if(instances.isEmpty()){
//            return null;
//        }
//        // 为了演示，在这里只获取第一个实例
//        ServiceInstance serviceInstance = instances.get(0);
//        String scheme = serviceInstance.getScheme();
//        URI uri = serviceInstance.getUri();
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        String d = "http://" + url + "/item/"+id;
//        return this.restTemplate.getForObject(d, Item.class);
//        System.out.println(forObject);
//        System.out.println("---------------------------");
//        return this.restTemplate.getForObject(itemUrl
//                + id, Item.class);
        // 该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)

//        return this.restTemplate.getForObject(orderProperties.getItem().getUrl()
//                + id, Item.class);

        String itemUrls = "http://app-item/item/";
        Item result = this.restTemplate.getForObject(itemUrls
                + id, Item.class);
        System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
    }

    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById2(Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("app-item");
        ServiceInstance serviceInstance = instances.get(0);

        int port = serviceInstance.getPort();

        String itemUrls = "http://app-item/item/";
        Item result = this.restTemplate.getForObject(itemUrls
                + id, Item.class);
        System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        result.setTitle("端口号：.."+port+"");
        return result;
    }

//    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById3(Long id) {
        Item item = itemFeignClient.queryItemById(id);
        System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + item);
        return item;
    }

    public Item queryItemByIdFallbackMethod(Long id){
        return new Item(id, "查询商品信息出错!", null, null, null);
    }

}