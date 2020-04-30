package com.micro.order.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "orders")
@Data
@ToString
@NoArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "订单编号")
    @Column(name = "code")
    private String code;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime = new Date();


}