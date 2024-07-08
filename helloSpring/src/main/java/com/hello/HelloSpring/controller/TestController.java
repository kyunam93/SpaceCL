package com.hello.helloSpring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.helloSpring.common.bean.AddressBean;
import com.hello.helloSpring.common.bean.MemberTestBean;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)// 클래스 전체를 데이터를 json 형태로 받아온다
public class TestController {

	@RequestMapping(value = "/firstHello", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> firstHello(String name) throws Exception {

		System.out.println("name : " + name);

		Map<String, Object> map = new HashMap<String, Object>();

		MemberTestBean mBean = new MemberTestBean();

		if(name != null) {
			mBean.setName(name);	
		}
		mBean.setAge(20);
		mBean.setStudent(true);

		AddressBean aBean = new AddressBean();
		aBean.setStreet("금천구");
		aBean.setCity("서울");
		mBean.setAddress(aBean);

		List<String> languages = new ArrayList<String>();
		languages.add("한글");
		languages.add("영어");
		languages.add("일어");
		mBean.setLanguages(languages);

		// 마지막으로 데이터를 맵에 input
		map.put("MemberBean", mBean);

		return map;
	}// method firstHello

}// class
