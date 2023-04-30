package com.zhengyuan.liunao.repository;

import com.zhengyuan.liunao.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
    // 添加订单
    void addOrder(Order order);

    // 货运公司接单————更新order表的coid、state
    void updateCoidNState(@Param("oid") int oid, @Param("coid")String coid);

    // 货运公司发货————更新order表的sendTime、state
    void updateSendTNState(@Param("oid")int oid, @Param("sendTime")Date sendTime);

    // 货运公司送达————更新order表的state、receiveTime
    void updateReceiveTNState(@Param("oid")int oid, @Param("receiveTime")Date receiveTime);

    // 客户界面————展示自己的全部订单
    List<Order> findOrderByCeid(@Param("ceid")String ceid);

    // 货运公司界面————展示自己的全部订单
    List<Order> findOrderByCoid(@Param("coid")String coid);

    // 货运公司界面————展示全部待接单
    List<Order> findOrderWaitReceive();

    // 管理员界面————展示全部订单
    List<Order> showAllOrder();

    // 通过订单id查找订单
    Order findOrderByOid(@Param("oid")int oid);

    // 检索不同货物的承运人账单
    List<Order> findOrderByCargotype(@Param("cargoType") String cargoType);

}
