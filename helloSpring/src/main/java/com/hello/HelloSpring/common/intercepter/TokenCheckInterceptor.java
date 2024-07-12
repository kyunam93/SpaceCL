package com.hello.helloSpring.common.intercepter;

import java.io.PrintWriter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hello.helloSpring.common.Constants;
import com.hello.helloSpring.common.utils.JwtTokenHelper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 요청이 처리되기 전에 실행할 코드
		System.out.println("TokenIntercepter preHandle() ==> " + request.getRequestURI());

		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "회원정보 Token 검증에 실패하였습니다.";

		// 정상적인 토큰이 왔는지 체크
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

				return true;// 인터셉터 영역을 통과
			} catch (ExpiredJwtException expExcepton) {
				expExcepton.printStackTrace();
				resultMsg = "Token의 유효기간이 만료되었습니다. 다시 발급 받으세요.";

			} catch (Exception e) {

				e.printStackTrace();
				resultMsg = e.getMessage();
			} // try ~ catch
		} // if

		// 실패한 원인에 대해서 response를 준다.
		StringBuilder jsonMsg = new StringBuilder();
		jsonMsg.append("{");
		jsonMsg.append("\"result\"");
		jsonMsg.append(":");
		jsonMsg.append("\"");
		jsonMsg.append(Constants.RESULT_VAL_FAIL);
		jsonMsg.append("\"");
		jsonMsg.append(",");
		jsonMsg.append("\"resultMsg\"");
		jsonMsg.append(":");
		jsonMsg.append("\"");
		jsonMsg.append(resultMsg);
		jsonMsg.append("\"");
		jsonMsg.append("}");

		PrintWriter writer = response.getWriter();
		writer.write(jsonMsg.toString());
		writer.flush();
		writer.close();
		
		return false;

	}// method preHandle

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 요청이 처리된 후에 실행할 코드
		System.out.println("TokenIntercepter postHandle() <==");
	}// method postHandle

}// class
