package com.zhengyuan.liunao.controller.dealcontroller;


import com.alibaba.fastjson.JSON;
import com.zhengyuan.liunao.entity.Income;
import com.zhengyuan.liunao.entity.Order;
import com.zhengyuan.liunao.repository.OrderMapper;
import com.zhengyuan.liunao.service.IncomeService;
import com.zhengyuan.liunao.tools.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/Sys")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IncomeService incomeService;

    // 客户提交订单
    @ResponseBody //加这个注解，则直接返回数据，而不是模板路径
    @PostMapping("/addOrder")
    public String submitOrder(@RequestBody Order order, HttpSession httpSession) throws ParseException {
//        String senderName = map.get("senderName");
//        String senderPhone = map.get("senderPhone");
//        String departure = map.get("departure");
//        String receiveName = map.get("receiveName");
//        String receivePhone = map.get("receivePhone");
//        String destination = map.get("destination");
//        String cargoType = map.get("cargoType");
//        double weight = Double.parseDouble(map.get("weight"));
//        double volume = Double.parseDouble(map.get("volume"));
//        // 获取当前客户的ceid
//        String ceid = map.get("ceid");
//        // 新建order对象
//        Order order = new Order(ceid, senderName, senderPhone, departure, receiveName, receivePhone, destination, cargoType, weight, volume);
        // 对数据库的操作
        String ceid = (String) httpSession.getAttribute( "account");
        order.setCeid(ceid); //客户id
        orderMapper.addOrder(order);
        return "提交订单成功";
    }

    // 货运公司接单
    @PostMapping("/receiveOrder")
    public String receiveOrder(@RequestParam("oid") int oid, HttpSession httpSession){
        // 获取当前货运公司的coid
        String coid = (String) httpSession.getAttribute( "account");
        // 对数据库的操作：更改数据库中该条order的状态，设定关联货运公司
        orderMapper.updateCoidNState(oid, coid);

        // 更新income表
        double cost = orderMapper.findOrderByOid(oid).getCost();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int yearMonth = year*100+month+1;
        List<Income> list = incomeService.findByYearMonth(coid,yearMonth);
        if(list.size()==0){
            Income income = new Income(coid,yearMonth,cost);
            incomeService.insertIncome(income);
        }else{
            incomeService.updateIncome(yearMonth,cost,coid);
        }
        return "接单成功";
    }

    // 货运公司发货
    @PostMapping("/sendCargo")
    public String sendCargo(@RequestParam("oid") int oid) throws ParseException {
        // 获取当前时间
        Date now = new Date();
        SimpleDateFormat tFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sendTime = tFormat.parse(tFormat.format(now));
        // 修改数据库对应数据
        orderMapper.updateSendTNState(oid, sendTime);
        return "发货成功";
    }

    // 货运公司送达
    @PostMapping("/receiveCargo")
    public String receiveCargo(@RequestParam("oid") int oid) throws ParseException {
        // 获取当前时间
        Date now = new Date();
        SimpleDateFormat tFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date receiveTime = tFormat.parse(tFormat.format(now));
        // 修改数据库对应数据
        orderMapper.updateReceiveTNState(oid, receiveTime);
        return "货物已送达";
    }

    // 客户界面————展示自己的全部订单
    @RequestMapping("/findOrderByCeid")
    @ResponseBody
    public String findOrderByCeid(HttpSession httpSession){
        String ceid = (String) httpSession.getAttribute( "account");
        List<Order> orders = orderMapper.findOrderByCeid(ceid);
        int total = orders.size();
        Layui l = Layui.data(total,orders);
        return JSON.toJSONString(l);
    }

    // 货运公司界面————展示自己的全部订单
    @RequestMapping("/findOrderByCoid")
    @ResponseBody
    public String findOrderByCoid(HttpSession httpSession){
        String coid = (String) httpSession.getAttribute( "account");
        List<Order> orders = orderMapper.findOrderByCoid(coid);
        int total = orders.size();
        Layui l = Layui.data(total,orders);
        return JSON.toJSONString(l);
    }

    // 货运公司界面————展示全部待接单
    @RequestMapping("/findOrderWaitReceive")
    @ResponseBody
    public String findOrderWaitReceive(HttpSession httpSession){
        String coid = (String) httpSession.getAttribute( "account");
        List<Order> orders = orderMapper.findOrderWaitReceive(coid);
        int total = orders.size();
        Layui l = Layui.data(total,orders);
        return JSON.toJSONString(l);
    }

    // 管理员界面————展示全部订单
    @RequestMapping("/showAllOrder")
    @ResponseBody
    public String showAllOrder(){
        List<Order> orders = orderMapper.showAllOrder();
        int total = orders.size();
        Layui l = Layui.data(total,orders);
        return JSON.toJSONString(l);
    }

}
