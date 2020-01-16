package com.yansen.springMvcDemo;

import com.yansen.springDemo.Api;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiServlet extends HttpServlet {
	private Map<String, Method> m_cmdMethodMap = new HashMap();
	MyController con = new MyController();

	@Override
	public void init() throws ServletException {
		Method[] declaredMethod = con.getClass().getDeclaredMethods();
		for(Method m : declaredMethod){
			Api ano = m.getAnnotation(Api.class);
			if(ano != null){
				String path = ano.value();
				System.out.println(ano.value());
				Annotation[][] a = m.getParameterAnnotations();
				m_cmdMethodMap.put(path,m);
			}else{
				System.out.println("get ano null");
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("yansen doGet 1111 ");
		String data = processMethod(req, res);
		if(data != null){
			res.getWriter().println(data);
		}
	}
	protected  String processMethod(HttpServletRequest req,HttpServletResponse res){
		String cmd = req.getParameter("cmd");
		Method method = (Method)this.m_cmdMethodMap.get(cmd);
		System.out.println("method cmd ="+cmd);
		String result ="";
		if(method == null){
			System.out.println("no "+cmd+" method");
			return "no method err";
		}
		try {
			method.setAccessible(true);
			//实际上用的只是第一个注解的参数
			Annotation[][] a = method.getParameterAnnotations();
			ArrayList<Annotation[]> aList = new ArrayList(a.length);
			for(int i= 0;i<a.length;i++){
				aList.add(a[i]);
				aList.a
			}
			System.out.println("method name ="+method.getName());
			Object o = method.invoke(this,req,res);
			System.out.println("o="+o);
			result= (String)o;
		}catch (Exception e){
			System.out.println("processMethod err "+e.getStackTrace());
			System.out.println("processMethod err "+e.getMessage());
			System.out.println("processMethod err "+e);
		}
		return result;
	}
}
