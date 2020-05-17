package com.micro.user.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "m_user")
@Data
@ToString
@NoArgsConstructor
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @Column(name = "username")
    private String username;//用户名

    @ApiModelProperty(value = "密码")
    @Column(name = "password")
    private String password;//密码

    @Column(name = "phone")
    private String phone;//注册手机号

    @Column(name = "email")
    private String email;//注册邮箱

    @Column(name = "created_time")
    private Date createdTime = new Date();//创建时间

    @Column(name = "updated_time")
    private Date updatedTime;//修改时间

    @Column(name = "last_login_time")
    private Date lastLoginTime;//最后登录时间
}
