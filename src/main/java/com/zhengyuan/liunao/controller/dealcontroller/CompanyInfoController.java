package com.zhengyuan.liunao.controller.dealcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.http.HttpStatus;
import com.zhengyuan.liunao.entity.Company;
import com.zhengyuan.liunao.tools.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.zhengyuan.liunao.service.CompanyService;
import com.zhengyuan.liunao.tools.Layui;

import cn.hutool.crypto.SecureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/Sys")
@Api("CompanyInfoDeal相关api")
@Slf4j
public class CompanyInfoController {
	@Autowired
	CompanyService companyService;

	
	@GetMapping(value = "/v1/companies")
	@ResponseBody
	public JsonResult<Object> getCompanyInfo(@RequestBody Map<String,String> map1) {
		int lim = Integer.parseInt(map1.get("limit"));
		int start = (Integer.parseInt(map1.get("page")) - 1) * lim;
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("pagesize", lim);
		List<Company> allCompany = companyService.findAllCompany(map);
		int total = companyService.companyCount();
//		Layui l = Layui.data(total, allCompany);
//		return JSON.toJSON(l);
		return new JsonResult<>(HttpStatus.HTTP_OK,allCompany);
	}

	
	@GetMapping(value = "/v1/companies/simple")
	@ResponseBody
	public JsonResult<Object> getCompanySimpleInfo(@RequestBody Map<String,String> map1) {
		int lim = Integer.parseInt(map1.get("limit"));
		int start = (Integer.parseInt(map1.get("page")) - 1) * lim;
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("pagesize", lim);
		List<Company> allCompany = companyService.findAllCompany(map);
		List<Company> company = new ArrayList<>();
		for(int i = 0; i< allCompany.size(); i++) {
			String coid = allCompany.get(i).getCoid();
			String coName = allCompany.get(i).getCoName();
			String phone = allCompany.get(i).getPhone();
			company.add(new Company(coid,coName,phone));
		}
		int total = companyService.companyCount();
//		System.out.println(total);
//		Layui l = Layui.data(total, company);
//		return JSON.toJSON(l);
		return new JsonResult<>(HttpStatus.HTTP_OK,company);
	}


	@ApiOperation("获取承运商公司的信息")
	
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })

	@GetMapping("/v1/companies/coName/{coName}")
	@ResponseBody
	public JsonResult<Object> getCompanyByName(@PathVariable("coName") String coName, @RequestBody Map<String,String> map1) {
		int lim = Integer.parseInt(map1.get("limit"));
		int start = (Integer.parseInt(map1.get("page")) - 1) * lim;
		if (coName.equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("pagesize", lim);
			List<Company> companyList = companyService.findAllCompany(map);
			int total = companyService.companyCount();
//			Layui l = Layui.data(total, companyList);
//			return JSON.toJSONString(l);
			return new JsonResult<>(HttpStatus.HTTP_OK,companyList);
		} else {
			List<Company> companyList = companyService.findCompanyByName(coName, start, lim);
			int total = companyList.size();
//			Layui l = Layui.data(total, companyList);
//			System.out.println("承运商信息："+JSON.toJSONString(l));
//			return JSON.toJSONString(l);
			return new JsonResult<>(HttpStatus.HTTP_OK,companyList);
		}
	}

	@DeleteMapping("/v1/companies")
	@ResponseBody
	public JsonResult<Object> deleteCompanys(@RequestBody Map<String,String> map1) {
		String datas = map1.get("nums").toString();
		System.out.println(datas);
		String[] str = datas.split(",");
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			data.add(str[i]);
		}

		System.out.println(data.toString());
		if (companyService.deleteByForeach(data) > 0) {
			return new JsonResult<>(HttpStatus.HTTP_NO_CONTENT,"success");
		} else {
			return new JsonResult<>(HttpStatus.HTTP_INTERNAL_ERROR,"fail");
		}
	}

	@DeleteMapping("/v1/companies/{coid}")
	@ResponseBody
	public JsonResult<Object> deleteCompany(@PathVariable("coid") String coid) {
		if (companyService.deleteCompany(coid) > 0) {
			return new JsonResult<>(HttpStatus.HTTP_NO_CONTENT,"success");
		} else {
			return new JsonResult<>(HttpStatus.HTTP_INTERNAL_ERROR,"fail");
		}
	}

	@GetMapping("/v1/companies/coid/{coid}")
	@ResponseBody
	public JsonResult<Object> getCompanyByNum(@PathVariable("coid") String coid) {
		String CompanyNo = coid.toString();
		List<Company> companyList = new ArrayList<>();
		companyList = companyService.getCompanyByNum(CompanyNo);
		int total = companyList.size();
//		Layui l = Layui.data(total, companyList);
//		System.out.println(coid);
//		System.out.println("getCompanyByNum---->" + JSON.toJSONString(l));
//		return JSON.toJSONString(l);
		return new JsonResult<>(HttpStatus.HTTP_OK,companyList);
	}

	@PutMapping("/v1/companies")
	@ResponseBody
	public JsonResult<Object> updateCompany(@RequestBody Map<String,String> map) {
		System.out.println("Company psw:"+map.get("psw"));
		map.put("psw", SecureUtil.md5(map.get("psw").toString()));
		if(companyService.updateCompany(map)>0){
			return new JsonResult<>(HttpStatus.HTTP_OK,"success");
		}else{
			return new JsonResult<>(HttpStatus.HTTP_INTERNAL_ERROR,"fail");
		}
	}


}
