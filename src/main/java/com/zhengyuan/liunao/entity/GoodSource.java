package com.zhengyuan.liunao.entity;

import cn.hutool.core.date.DateTime;

import java.util.Date;


public class GoodSource {

    public GoodSource(String customerID, String start, String end, String type, double weight, double size, int num, DateTime deliveryTime, String demand, double quotation, String other, String state) {
        this.customerID = customerID;
        this.start = start;
        this.end = end;
        this.type = type;
        this.weight = weight;
        this.size = size;
        this.num = num;
        this.deliveryTime = deliveryTime;
        this.demand = demand;
        this.quotation = quotation;
        this.other = other;
        this.state = state;
    }
    public GoodSource(){

    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public double getQuotation() {
        return quotation;
    }

    public void setQuotation(double quotation) {
        this.quotation = quotation;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String customerID;
    private int sourceID;
    private String start;
    private String end;
    private String type;
    private double weight;
    private double size;
    private int num;
    private Date deliveryTime;
    private String demand;
    private double quotation;
    private String other;
    private String state;

}
