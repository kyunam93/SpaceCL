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

import com.hello.helloSpring.common.Constants;
import com.hello.helloSpring.common.bean.BoardBean;
import com.hello.helloSpring.common.bean.MemberBean;
import com.hello.helloSpring.service.BoardService;
import com.hello.helloSpring.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/insertBoard", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> insertBoard(@RequestBody BoardBean bean, HttpSession session) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "글 작성에 실패하였습니다.";

		// 세션값을 가져온다.
		MemberBean mBean = (MemberBean) session.getAttribute(Constants.KEY_SESSION_MEMBER_BEAN);

		if (mBean != null) {
			// 토근 값인 memberNo 값을 가져와서 insert 정보에 넣어준다.
			bean.setMemberNo(mBean.getMemberNo());
		} // if

		try {

			int res = boardService.insertBoard(bean);
			if (res > 0) {
				result = Constants.RESULT_VAL_OK;
				resultMsg = "글 작성에 성공하였습니다.";
			} // if
		} catch (Exception e) {

			e.printStackTrace();
			resultMsg = "회원 정보 입력이 올바르지 않습니다.";
		} // try ~ catch

		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);

		return map;
	}// method

	@RequestMapping(value = "/selectBoardList", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> selectBoardList(BoardBean bean) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "게시글 목록 조회에 실패하였습니다.";

		List<BoardBean> list = null;
		int totalCount = 0;
		int totalPage = 0;
		
		try {
			totalCount = boardService.selectBoardListCount(bean);
			totalPage = (int)Math.ceil(totalCount/10.0);
			
			int startOffset = ((bean.getPage() - 1) * 10);
			bean.setPage(startOffset);
			
			list = boardService.selectBoardList(bean);
			
			if (list != null) {
				result = Constants.RESULT_VAL_OK;
				resultMsg = "게시글 목록 조회에 성공하였습니다.";
			} // if
		} catch (Exception e) {

			e.printStackTrace();
			resultMsg = "회원 정보 입력이 올바르지 않습니다.";
		} // try ~ catch

		map.put("totalPage", totalPage);
		map.put("totalCount", totalCount);
		map.put(Constants.RESULT_KEY_DATA, list);
		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);

		return map;
	}// method
}
