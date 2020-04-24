package co.edu.app.member.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import co.edu.app.member.model.MemberDAO;
import co.edu.app.member.model.MemberVO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		MemberVO member = new MemberVO();
		member.setUserid(request.getParameter("userid"));
		member.setUserpw(request.getParameter("userpw"));
		
		//로그인 체크
		boolean result = MemberDAO.getInstance().loginCheck(member);
	
		//결과 저장(Object)
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("success", result);

		
		//JSON으로 변환
		String resStr = new Gson().toJson(Collections.singletonMap("success", result));
		
		//결과 전송
		response.getWriter().append(resStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
