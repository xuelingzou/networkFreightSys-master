package com.zhengyuan.liunao.repository;

import com.zhengyuan.liunao.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface OrderMapper {
    // 添加订单
    void addOrder(Order order);

    // 货运公司接单————更新order表的coid、state
    void updateCoidNState(int oid, String coid);

    // 货运公司发货————更新order表的sendTime、state
    void updateSendTNState(int oid, Date sendTime);

    // 货运公司送达——更新order表的state、receiveTime
    void updateReceiveTNState(int oid, Date receiveTime);
}
