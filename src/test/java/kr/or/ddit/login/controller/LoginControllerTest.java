package kr.or.ddit.login.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class LoginControllerTest extends ControllerTestEnv {

	/**
	* Method : loginView
	* 작성자 : PC14
	* 변경이력 :
	* @param session
	* @return
	* Method 설명 :	사용자 로그인 화면 요청
	*/
	@Test
	public void loginViewNotLoginedTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		/***Then***/
		assertEquals("login/login", mav.getViewName());
	}

	/**
	* Method : loginViewLoginedTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 한 상황에서 로그인 뷰 요청 테스트
	*/
	@Test
	public void loginViewLoginedTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login").sessionAttr("USER_INFO", new UserVO())).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		/***Then***/
		assertEquals("tiles.main", mav.getViewName());
	}
	
	/**
	* Method : loginProcessTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 로그인 요청 처리 성공 테스트
	 * @throws Exception 
	*/
	@Test
	public void loginProcessSuccessTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "brown1234";

		/***When***/
		MvcResult mvcResult =  mockMvc.perform(post("/login")
									.param("userId", userId)
									.param("password", password))
									.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		HttpSession session = mvcResult.getRequest().getSession();
		
		String viewName = mav.getViewName();
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		/***Then***/
		assertEquals("tiles.main", viewName);
		assertEquals("브라운", userVO.getName());
		assertEquals("곰", userVO.getAlias());
	}
	
	/**
	* Method : loginProcessFailTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 요청 처리 실패 테스트
	*/
	@Test
	public void loginProcessFailTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "1234";	// 로그인 실패 처리 테스팅하기 위한 틀린 password
		
		/***When***/
		MvcResult mvcResult =  mockMvc.perform(post("/login")
				.param("userId", userId)
				.param("password", password))
				.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		HttpSession session = mvcResult.getRequest().getSession();
		
		String viewName = mav.getViewName();
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		/***Then***/
		assertEquals("login/login", viewName);
		assertNull(userVO);
	}
	
}
