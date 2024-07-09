package com.hello.helloSpring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.helloSpring.common.Constants;
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

	@RequestMapping(value = "/insertMember", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> insertMember(@RequestBody MemberBean bean) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "회원등록을 실패하였습니다.";

		int res = memberDao.insertMember(bean);
		if (res > 0) {
			result = Constants.RESULT_VAL_OK;
			resultMsg = "회원등록을 성공하였습니다.";
		} // if

		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);

		return map;
	}// method

	@RequestMapping(value = "/updateMember", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> updateMember(@RequestBody MemberBean bean) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "회원수정을 실패하였습니다.";

		int res = memberDao.updateMember(bean);
		if (res > 0) {
			result = Constants.RESULT_VAL_OK;
			resultMsg = "회원수정을 성공하였습니다.";
		} // if

		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);

		return map;
	}// method



}// class
