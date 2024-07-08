package com.hello.HelloSpring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.HelloSpring.bean.MemberBean;

@Controller
public class InputController {

	@RequestMapping(value = "/insertStudent", method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)// 해당 클래스만 json 형태로 받아온다
	@ResponseBody // json 형태로 반환됨
	public Map<String, Object> inputStuden(@RequestBody MemberBean bean) {
		Map<String, Object> map = new HashMap<String, Object>();

		// bean 안에 데이터가 오는지 보자
		if (bean != null) {
			System.out.println("\nBean 데이터 확인");
			System.out.println(bean.getName());
			System.out.println(bean.getAge());
			System.out.println(bean.getAddress() != null ? bean.getAddress().getCity() : "");
			System.out.println(bean.getAddress().getStreet());
			System.out.println(bean.isStudent());
			System.out.println(bean.getLanguages());
		} // if

		return map;
	}// method inputStudent
}// class
