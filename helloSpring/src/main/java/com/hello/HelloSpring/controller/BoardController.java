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
import com.hello.helloSpring.common.bean.BoardBean;
import com.hello.helloSpring.service.BoardService;
import com.hello.helloSpring.service.MemberService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/insertBoard", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> insertBoard(@RequestBody BoardBean bean) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "글쓰기를 실패하였습니다.";

		try {

			int res = boardService.insertBoard(bean);
			if (res > 0) {
				result = Constants.RESULT_VAL_OK;
				resultMsg = "글쓰기를 성공하였습니다.";
			} // if
		} catch (Exception e) {

			e.printStackTrace();
			resultMsg = "서버 에러가 발생했습니다. 관리자에게 문의하세요.";
		} // try ~ catch

		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);

		return map;
	}// method
	
}
