package com.zhengyuan.liunao.controller.dealcontroller;


import com.zhengyuan.liunao.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping("/Sys")
public class OrderController {

    //todo:引入server实体,进行数据库存储

    @ResponseBody //加这个注解，则直接返回数据，而不是模板路径
    @PostMapping("/addOrder")
    public String addGoodsSource(HttpSession httpSession, @RequestBody Order newOrder) {

//        TODO：获取提交订单时间
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        TODO：计算订单cost


        String ceid = (String) httpSession.getAttribute("account");
        newOrder.setCeid(ceid); //客户id

        newOrder.setState("待接单");//订单状态
        System.out.println(newOrder.toString());

//        todo:加上数据库存储操作，修改判定条件
        if (newOrder.toString() != null) {
            return "提交订单成功";
        } else {
            return "提交订单失败";
        }
    }

}
