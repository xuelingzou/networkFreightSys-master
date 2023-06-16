package com.example.auto_warehouse.controller;

import java.util.*;

import cn.hutool.http.HttpStatus;
import com.example.auto_warehouse.bean.Income;
import com.example.auto_warehouse.service.IncomeService;

import com.example.auto_warehouse.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;


@RestController
@RequestMapping("/Sys")
public class FinanceController {

	@Autowired
	IncomeService incomeService;

	
//	@GetMapping("/v1/incomes")
	@PostMapping ("/v1/incomes")
	@ResponseBody
	public JsonResult<double[]> getGscomp(HttpSession httpSession, @RequestBody Map<String,String> map1) {
		String coid = (String) httpSession.getAttribute( "account");
		int year = Integer.parseInt(map1.get("year"));
		List<Income> incomes = incomeService.selectMonthIncome(coid,year*100);
		Map<String, Double> map = new HashMap<>();

		for(Income income:incomes){
			map.put(String.valueOf(income.getYearMonth()),income.getIncome());
		}

	    double[] scores = new double[12];
		for(int i=0;i<12;i++){
			if(map.get(String.valueOf(year*100+i+1)) !=null){
				scores[i] = map.get(String.valueOf(year*100+i+1));
			}else {
				scores[i] =0;
			}

		}
//	    String jsonString = JSON.toJSONString(scores);
//	    System.out.println(jsonString);
//		return jsonString;
		return new JsonResult<>(HttpStatus.HTTP_OK,scores);
	}



}
