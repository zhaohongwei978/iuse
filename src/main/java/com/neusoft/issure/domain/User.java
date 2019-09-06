package com.neusoft.issure.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Proxy;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 *
 */
@Proxy(lazy = false)
@Entity
@Table(name = "user")
public class User {
    @Id // 声明主键的配置
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略（自增）
    @Column(nullable = false)
    private Long id;//记录标识

    @Column(length = 20,unique = true)
    private String userName;//登录帐号  建索引全局唯一 不能为空

    @Column(length = 50)
    private String password;//用户密码  不能为空

    @Column(unique = true)
    private String mobile;//手机号   唯一索引

    @Column(unique = true)
    private String email;//电子邮箱  唯一索引

    @ColumnDefault(value = "0")
    private Integer perfected;//信息是否完善  0未完善  1已完善

    @Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @Generated(GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;//更新时间  根据当前时间戳更新

    @Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//创建时间

    @Column(length = 10)
    private String role;//角色  USER ADMIN


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPerfected() {
        return perfected;
    }

    public void setPerfected(Integer perfected) {
        this.perfected = perfected;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserController{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", perfected=" + perfected +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", role='" + role + '\'' +
                '}';
    }
}
