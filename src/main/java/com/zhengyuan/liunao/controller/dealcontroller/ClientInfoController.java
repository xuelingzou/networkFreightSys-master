package com.zhengyuan.liunao.controller.dealcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.http.HttpStatus;
import com.zhengyuan.liunao.tools.JsonResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.zhengyuan.liunao.entity.Client;
import com.zhengyuan.liunao.service.ClientService;
import com.zhengyuan.liunao.tools.Layui;

import cn.hutool.crypto.SecureUtil;

@Controller
@RequestMapping("/Sys")
@Api("ClientInfoDeal相关api")
public class ClientInfoController {
	@Autowired
    ClientService clientService;

	int mylim;
	int mystart;

	@GetMapping(value = "/v1/clients")
	@ResponseBody
	public String getClientInfo(@RequestBody Map<String,String> map1) {
		int lim = Integer.parseInt(map1.get("limit"));
		int start = (Integer.parseInt(map1.get("page")) - 1) * lim;
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
		Layui l = Layui.data(String.valueOf(HttpStatus.HTTP_OK), total, allClient);
		return JSON.toJSONString(l);
		//return new JsonResult<>(HttpStatus.HTTP_OK,allClient);
	}

	@ApiOperation("获取客户的信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="limit",value = "3",required = true),
		@ApiImplicitParam(name="page",value = "1",required = true),
	})
	@GetMapping(value = "/v1/clients/simple")
	@ResponseBody
	public String getClientSimpleInfo(@RequestBody Map<String,String> map1) {
		int lim = Integer.parseInt(map1.get("limit"));
		int start = (Integer.parseInt(map1.get("page")) - 1) * lim;
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
		Layui l = Layui.data(String.valueOf(HttpStatus.HTTP_OK), total, Client);
		return JSON.toJSONString(l);
		//return new JsonResult<>(HttpStatus.HTTP_OK,Client);
	}
	
	

	@ApiOperation("根据客户名name获取客户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name="key[id]",value = "雪玲",required = true),
			@ApiImplicitParam(name="limit",value = "3",required = true),
			@ApiImplicitParam(name="page",value = "1",required = true),
	})
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@GetMapping("/v1/clients/ceName/{ceName}")
	@ResponseBody
	public String getClientByName(@PathVariable("ceName") String ceName, @RequestBody Map<String,String> map1) {
		int lim = Integer.parseInt(map1.get("limit"));
		int start = (Integer.parseInt(map1.get("page")) - 1) * lim;
		mylim = lim;
		mystart = start;
		if (ceName.equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", mystart);
			map.put("pagesize", mylim);
			List<Client> ClientList = clientService.findAllClient(map);
			int total = clientService.ClientCount();
			Layui l = Layui.data(String.valueOf(HttpStatus.HTTP_OK),total, ClientList);
			return JSON.toJSONString(l);
			//return new JsonResult<>(HttpStatus.HTTP_OK,ClientList);
		} else {
			List<Client> ClientList = clientService.findClientByName(ceName, mystart, mylim);
			int total = ClientList.size();
			Layui l = Layui.data(String.valueOf(HttpStatus.HTTP_OK),total, ClientList);
			//System.out.println(JSON.toJSONString(l));
			return JSON.toJSONString(l);
			//return new JsonResult<>(HttpStatus.HTTP_OK,ClientList);
		}

	}

	@ApiOperation("根据客户id获取客户信息")
	@ApiImplicitParam(name="num",value = "20301155",required = true)
	@GetMapping("/v1/clients/ceid/{ceid}")
	@ResponseBody
	public String getClientByNum(@PathVariable("ceid") String ceid) {
		List<Client> ClientList = new ArrayList<>();
		ClientList = clientService.findClientByNum(ceid);
		int total = ClientList.size();
		Layui l = Layui.data(String.valueOf(HttpStatus.HTTP_OK),total, ClientList);
		System.out.println("getClientByNum---->" + JSON.toJSONString(l));
		return JSON.toJSONString(l);
		//return new JsonResult<>(HttpStatus.HTTP_OK,ClientList);

	}

	@PutMapping("/v1/clients")
	@ResponseBody
	public JsonResult<Object> updateClient(@RequestBody Map<String,String> map) {
		System.out.println("Client psw:"+map.get("psw"));
		map.put("psw", SecureUtil.md5(map.get("psw").toString()));
		if(clientService.updateClient(map)>0){
			return new JsonResult<>(HttpStatus.HTTP_OK,"success");
		}else{
			return new JsonResult<>(HttpStatus.HTTP_INTERNAL_ERROR,"fail");
		}

	}
	@ApiOperation("多选删除客户信息")
	@DeleteMapping("/v1/clients")
	@ResponseBody
	public JsonResult<Object> deleteClients(@RequestBody Map<String,String> map1) {
		String datas = map1.get("nums").toString();
		System.out.println(datas);
		String[] str = datas.split(",");
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			data.add(str[i]);
		}

		System.out.println(data.toString());
		if (clientService.deleteByForeach(data) > 0) {
			return new JsonResult<>(HttpStatus.HTTP_NO_CONTENT,"success");
		} else {
			return new JsonResult<>(HttpStatus.HTTP_INTERNAL_ERROR,"fail");
		}
	}

	@ApiOperation("单选删除一条客户信息")
	@DeleteMapping("/v1/clients/{ceid}")
	@ResponseBody
	public JsonResult<Object> deleteClient(@PathVariable("ceid") String ceid) {
		if (clientService.deleteClient(ceid) > 0) {
			return new JsonResult<>(HttpStatus.HTTP_NO_CONTENT,"success");
		} else {
			return new JsonResult<>(HttpStatus.HTTP_INTERNAL_ERROR,"fail");
		}
	}

}
