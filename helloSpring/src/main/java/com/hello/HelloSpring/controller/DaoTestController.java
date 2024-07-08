package com.hello.helloSpring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.helloSpring.common.bean.MemberBean;
import com.hello.helloSpring.common.daos.MemberDao;

@Controller
public class DaoTestController {

	@Autowired
	private MemberDao memberDao;

	@RequestMapping(value = "/selectMember", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> selectMember(MemberBean bean) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		MemberBean resBean = memberDao.selectMember(bean);
		if (resBean != null) {
			map.put("memberBean", resBean);
		} else {
			map.put("memberBean", "존재하지 않는 id 멤버 입니다.");
		} // if ~ else

		return map;
	}// method
	
	@RequestMapping(value = "/selectMemberList", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> selectMemberList(MemberBean bean) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		List<MemberBean> list = memberDao.selectMemberList(bean);
		map.put("memberBean", list);
		
		return map;
	}// method
	
	
	@RequestMapping(value = "/insertMember", method = { RequestMethod.GET })
	@ResponseBody
	public int insertMember(@RequestBody MemberBean bean) throws Exception {
		int result = 0;
		
		return result;
	}// method
	
	
	@RequestMapping(value = "/updateMember", method = { RequestMethod.GET })
	@ResponseBody
	public int updateMember(@RequestBody MemberBean bean) throws Exception {
		int result = 0;
		
		return result;
	}// method
	
	
	@RequestMapping(value = "/deleteMember", method = { RequestMethod.GET })
	@ResponseBody
	public int deleteMember(@RequestBody MemberBean bean) throws Exception {
		int result = 0;
		
		return result;
	}// method
	
	
}// class
