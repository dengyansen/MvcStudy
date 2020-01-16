package com.yansen.springMvcDemo;

import com.yansen.springDemo.Api;
import com.yansen.springDemo.ReqArg;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyController {
	@Api("MyController/getMyName")
	String getMyName(HttpServletRequest req){
		System.out.println("getMyName");
		return "getMyName";
	}
	@Api("MyController/getMyName2")
	String getMyName2(HttpServletRequest req, HttpServletResponse res,@ReqArg(key="name") String name,@ReqArg(key="aid") int aid){
		System.out.println("getMyName2");
		System.out.println(key);
		return "getMyName2";
	}
}
