package com.hello.helloSpring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.helloSpring.common.Constants;
import com.hello.helloSpring.common.bean.MemberBean;
import com.hello.helloSpring.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value = "/deleteMember", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> deleteMember(@RequestBody MemberBean bean) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "회원삭제을 실패하였습니다.";
		
		try {
			
			int res = memberService.deleteMember(bean);
			if (res > 0) {
				result = Constants.RESULT_VAL_OK;
				resultMsg = "회원삭제을 성공하였습니다.";
			} // if
		} catch (Exception e) {
			
			e.printStackTrace();
			resultMsg = "서버 에러가 발생했습니다. 관리자에게 문의하세요.";
		} // try ~ catch

		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);
		
		return map;
	}// method
}// class
