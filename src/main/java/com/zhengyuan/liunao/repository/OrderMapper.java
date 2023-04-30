package com.zhengyuan.liunao.repository;

import com.zhengyuan.liunao.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
    // 添加订单
    void addOrder(Order order);

    // 货运公司接单————更新order表的coid、state
    void updateCoidNState(int oid, String coid);

    // 货运公司发货————更新order表的sendTime、state
    void updateSendTNState(int oid, Date sendTime);

    // 货运公司送达————更新order表的state、receiveTime
    void updateReceiveTNState(int oid, Date receiveTime);

    // 客户界面————展示自己的全部订单
    List<Order> findOrderByCeid(String ceid);

    // 货运公司界面————展示自己的全部订单
    List<Order> findOrderByCoid(String coid);

    // 货运公司界面————展示全部待接单
    List<Order> findOrderWaitReceive(String coid);

    // 管理员界面————展示全部订单
    List<Order> showAllOrder();
}
