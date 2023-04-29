package com.zhengyuan.liunao.controller.dealcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhengyuan.liunao.entity.Client;
import com.zhengyuan.liunao.service.ClientService;
import com.zhengyuan.liunao.tools.Layui;

import cn.hutool.crypto.SecureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/Sys")
@Api("ClientInfoDeal相关api")
public class ClientInfoController {
	@Autowired
    ClientService clientService;

	int mylim;
	int mystart;

	@RequestMapping(value = "/getClientInfo")
	@ResponseBody
	public Object getClientInfo(@RequestParam("limit") String limit, @RequestParam("page") String page) {
		int lim = Integer.parseInt(limit);
		int start = (Integer.parseInt(page) - 1) * lim;
		mylim = lim;
		mystart = start;
		System.out.println(mylim);
		System.out.println(mystart);
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("pagesize", lim);
		List<Client> allClient = clientService.findAllClient(map);
		int total = clientService.ClientCount();
		System.out.println(total);
		Layui l = Layui.data(total, allClient);
		return JSON.toJSON(l);
	}

	@RequestMapping(value = "/getClientSimpleInfo")
	@ResponseBody
	public Object getClientSimpleInfo(@RequestParam("limit") String limit, @RequestParam("page") String page) {
		int lim = Integer.parseInt(limit);
		int start = (Integer.parseInt(page) - 1) * lim;
		mylim = lim;
		mystart = start;
		System.out.println(mylim);
		System.out.println(mystart);
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("pagesize", lim);
		List<Client> allClient = clientService.findAllClient(map);
		List<Client>Client =  new ArrayList<>();
		for(int i=0;i<allClient.size();i++) {
			String ceid = allClient.get(i).getCeid();
			String ceName = allClient.get(i).getCeName();
			String phone = allClient.get(i).getPhone();
			Client.add(new Client(ceid, ceName, phone));
		}
		int total = clientService.ClientCount();
		System.out.println(total);
		Layui l = Layui.data(total, Client);
		return JSON.toJSON(l);
	}
	
	
	
	
	
	@ApiOperation("获取客户的信息")

	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping("/getClientByName")
	@ResponseBody
	public String getClientByName(@RequestParam("ceName") String ceName,@RequestParam("limit") String limit, @RequestParam("page") String page) {
		int lim = Integer.parseInt(limit);
		int start = (Integer.parseInt(page) - 1) * lim;
		mylim = lim;
		mystart = start;
		if (ceName.equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", mystart);
			map.put("pagesize", mylim);
			List<Client> ClientList = clientService.findAllClient(map);
			int total = clientService.ClientCount();
			Layui l = Layui.data(total, ClientList);
			return JSON.toJSONString(l);
		} else {
			List<Client> ClientList = clientService.findClientByName(ceName, mystart, mylim);
			int total = ClientList.size();
			Layui l = Layui.data(total, ClientList);
			System.out.println(JSON.toJSONString(l));
			return JSON.toJSONString(l);
		}

	}

	@RequestMapping("/getClientByNum")
	@ResponseBody
	public String getClientByNum(@RequestParam("num") String ceid) {
		List<Client> ClientList = new ArrayList<>();
		ClientList = clientService.findClientByNum(ceid);
		int total = ClientList.size();
		Layui l = Layui.data(total, ClientList);
		System.out.println("getClientByNum---->" + JSON.toJSONString(l));
		return JSON.toJSONString(l);

	}

	@RequestMapping("/updateClient")
	@ResponseBody
	public String updateClient(@RequestBody Map<String,String> map) {
		System.out.println("Client psw:"+map.get("psw"));
		map.put("psw", SecureUtil.md5(map.get("psw").toString()));
		clientService.updateClient(map);
		return "";

	}
	@RequestMapping("/deleteClients")
	@ResponseBody
	public String deleteClients(@RequestParam("ceid") Object ceid) {
		String datas = ceid.toString();
		System.out.println(datas);
		String[] str = datas.split(",");
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			data.add(str[i]);
		}

		System.out.println(data.toString());
		if (clientService.deleteByForeach(data) > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping("/deleteClient")
	@ResponseBody
	public String deleteClient(@RequestParam("ceid") String ceid) {
		if (clientService.deleteClient(ceid) > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

}
