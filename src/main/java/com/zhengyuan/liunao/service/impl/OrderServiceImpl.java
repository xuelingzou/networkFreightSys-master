package com.zhengyuan.liunao.service.impl;

import com.zhengyuan.liunao.entity.Order;
import com.zhengyuan.liunao.repository.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderMapper orderMapper;

    // 客户提交订单
    void submitOrder(String ceid, String senderName, String senderPhone, String departure, String receiveName, String receivePhone, String destination, String cargoType, double weight, double volume) throws ParseException {
        // 新建order对象
        Order order = new Order(ceid, senderName, senderPhone, departure, receiveName, receivePhone, destination, cargoType, weight, volume);
        // 对数据库的操作
        orderMapper.addOrder(order);
    }

    // 货运公司接单
    void receiveOrder(int oid, String coid){
        // 更改数据库中该条order的状态，设定关联货运公司
        orderMapper.updateCoidNState(oid, coid);
    }

    // 货运公司发货
    void sendCargo(int oid) throws ParseException {
        // 获取当前时间
        Date now = new Date();
        SimpleDateFormat tFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date sendTime = tFormat.parse(tFormat.format(now));
        // 修改数据库对应数据
        orderMapper.updateSendTNState(oid, sendTime);
    }

    // 货运公司送达
    void receiveCargo(int oid) throws ParseException {
        // 获取当前时间
        Date now = new Date();
        SimpleDateFormat tFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date receiveTime = tFormat.parse(tFormat.format(now));
        // 修改数据库对应数据
        orderMapper.updateSendTNState(oid, receiveTime);
    }
}