package com.zhengyuan.liunao.controller.dealcontroller;

import com.zhengyuan.liunao.entity.GoodSource;
import com.zhengyuan.liunao.repository.GoodsSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping("/Sys")
public class GoodsSourceController {
    @Autowired
    private GoodsSourceMapper goodsSourceMapper;

    @PostMapping("/addGoodsSource")
    public String addGoodsSource(@RequestBody Map<String,String> map) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GoodSource goodSource = new GoodSource();
        goodSource.setCustomerID(map.get("customerID"));
        goodSource.setStart(map.get("start"));
        goodSource.setEnd(map.get("end"));
        goodSource.setType(map.get("type"));
        goodSource.setWeight(Double.parseDouble(map.get("weight")));
        goodSource.setSize(Double.parseDouble(map.get("size")));
        goodSource.setNum(Integer.parseInt(map.get("num")));
        try {
            goodSource.setDeliveryTime(sdf1.parse(map.get("deliveryTime")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        goodSource.setDemand(map.get("demand"));
        goodSource.setQuotation(Double.parseDouble(map.get("quotation")));
        goodSource.setOther(map.get("other"));
        goodSource.setState("待承运");
        int n = goodsSourceMapper.addGoodsSource(goodSource);
        if(n>0) {
            return "提交成功";
        }
        return "提交失败";

    }
}
