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
import com.hello.helloSpring.common.utils.JwtTokenHelper;
import com.hello.helloSpring.service.MemberService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

	@RequestMapping(value = "/getLoginToken", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getLoginToken(@RequestBody MemberBean bean) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "회원정보 Token 발행에 실패하였습니다.";

		MemberBean resBean = memberService.selectMember(bean);

		// 아이디로 디비를 조회한 다음 파라메타로 넘어온 패스워드 값과 디비의 패스워드 값이 일치하는지 비교
		if (resBean != null && resBean.getPw().equals(bean.getPw())) {

			// 토큰의 유효기간은 1000 -> 1초, 24시간 ->86400000
			long expTime = 86400 * 1000;

			// 일치한다면 인증된 유저임, 토큰 발행
			String token = JwtTokenHelper.createJWT(resBean.getId(), resBean.getMemberNo(), resBean.getHp(), expTime);

			map.put("token", token);
			result = Constants.RESULT_VAL_OK;
			resultMsg = "토근 발행에 성공 하였습니다.";
		} // if

		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);

		return map;
	}// method getLoginToken

	@RequestMapping(value = "/verifyToken", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> verifyToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "회원정보 Token 검증에 실패하였습니다.";

		// 헤더로 부터 넘어온 토큰을 검증한다
		String authToken = request.getHeader("Authorization");
		System.out.println(authToken);

		if (authToken != null) {
			
			authToken = authToken.replace("Bearer ", "");
			
			try {
				
				Claims claims = JwtTokenHelper.parseClaims(authToken).getBody();
				String id = claims.getId();
				String issuer = claims.getIssuer();
				String subject = claims.getSubject();
				System.out.println("id : " + id + ", issuer : " + issuer + ", subject : " + subject);
				
				result = Constants.RESULT_VAL_OK;
				resultMsg = "회원정보 Token 검증에 성공하였습니다.";
			}catch(Exception e){
				
				e.printStackTrace();
			}// try ~ catch
		} // if

		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);
		
		return map;
	}// method verifyToken
	
}// class
