package com.zhengyuan.liunao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    String ceid;    // 客户id
    String ceName;  // 客户名称
    String pws;     // 客户登录密码
    String phone;   // 客户联系方式

    public Client(String ceid, String name, String pws, String phone) {
        this.ceid = ceid;
        this.ceName = name;
        this.pws = pws;
        this.phone = phone;
    }

    public Client() {

    }

    public String getCeid() {
        return ceid;
    }

    public void setCeid(String ceid) {
        this.ceid = ceid;
    }

    public String getName() {
        return ceName;
    }

    public void setName(String name) {
        this.ceName = name;
    }

    public String getPws() {
        return pws;
    }

    public void setPws(String pws) {
        this.pws = pws;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
