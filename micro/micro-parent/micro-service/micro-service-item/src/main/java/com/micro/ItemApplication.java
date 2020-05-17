package com.micro;

//import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.micro.item.pojo.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
//@EnableDistributedTransaction
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class,args);
    }

    @Bean
    public void newBean(){
        new Person();
    }
}
