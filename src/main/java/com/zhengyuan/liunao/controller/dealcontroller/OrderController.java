package com.zhengyuan.liunao.controller.dealcontroller;


import com.zhengyuan.liunao.entity.Order;
import com.zhengyuan.liunao.repository.OrderMapper;
import com.zhengyuan.liunao.service.impl.OrderServiceImpl;
import com.zhengyuan.liunao.tools.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/Sys")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    // 客户提交订单
    @ResponseBody //加这个注解，则直接返回数据，而不是模板路径
    @PostMapping("/addOrder")
    public String submitOrder(String senderName, String senderPhone, String departure, String receiveName, String receivePhone, String destination, String cargoType, double weight, double volume) throws ParseException {
        // 获取当前客户的ceid
        String ceid = Id.getCeid();
        // 新建order对象
        Order order = new Order(ceid, senderName, senderPhone, departure, receiveName, receivePhone, destination, cargoType, weight, volume);
        // 对数据库的操作
        orderMapper.addOrder(order);
        return "提交订单成功";
    }

    // 货运公司接单
    @PostMapping("receiveOrder")
    public String receiveOrder(int oid){
        // 获取当前货运公司的coid
        String coid = Id.getCoid();
        // 对数据库的操作：更改数据库中该条order的状态，设定关联货运公司
        orderMapper.updateCoidNState(oid, coid);
        return "接单成功";
    }

    // 货运公司发货
    @PostMapping("sendCargo")
    public String sendCargo(int oid) throws ParseException {
        // 获取当前时间
        Date now = new Date();
        SimpleDateFormat tFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date sendTime = tFormat.parse(tFormat.format(now));
        // 修改数据库对应数据
        orderMapper.updateSendTNState(oid, sendTime);
        return "发货成功";
    }

    // 货运公司送达
    @PostMapping("receiveCargo")
    public String receiveCargo(int oid) throws ParseException {
        // 获取当前时间
        Date now = new Date();
        SimpleDateFormat tFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date receiveTime = tFormat.parse(tFormat.format(now));
        // 修改数据库对应数据
        orderMapper.updateSendTNState(oid, receiveTime);
        return "货物已送达";
    }

    // 客户界面————展示自己的全部订单
    @PostMapping("findOrderByCeid")
    List<Order> findOrderByCeid(){
        String ceid = Id.getCeid();
        List<Order> orders = orderMapper.findOrderByCeid(ceid);
        return orders;
    }

    // 货运公司界面————展示自己的全部订单
    @PostMapping("findOrderByCoid")
    List<Order> findOrderByCoid(){
        String coid = Id.getCoid();
        List<Order> orders = orderMapper.findOrderByCoid(coid);
        return orders;
    }

    // 货运公司界面————展示全部待接单
    @PostMapping("findOrderWaitReceive")
    List<Order> findOrderWaitReceive(){
        String coid = Id.getCoid();
        List<Order> orders = orderMapper.findOrderWaitReceive(coid);
        return orders;
    }

    // 管理员界面————展示全部订单
    @PostMapping("showAllOrder")
    List<Order> showAllOrder(){
        List<Order> orders = orderMapper.showAllOrder();
        return orders;
    }

}
