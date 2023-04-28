package com.zhengyuan.liunao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
    @Id
    String coid;    // 货运公司id
    String coName;  // 货运公司
    String pws;     // 货运公司登录密码
    String phone;   // 货运公司联系方式

    public Company(String coid, String coName, String pws, String phone) {
        this.coid = coid;
        this.coName = coName;
        this.pws = pws;
        this.phone = phone;
    }


    public Company() {

    }

    public String getCoid() {
        return coid;
    }

    public void setCoid(String coid) {
        this.coid = coid;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
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
