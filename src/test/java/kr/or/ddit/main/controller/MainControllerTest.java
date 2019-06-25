package kr.or.ddit.main.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

public class MainControllerTest extends ControllerTestEnv {
	
	/**
	* Method : mainViewTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : Main View 호출 테스트
	 * @throws Exception 
	*/
	@Test
	public void mainViewTest() throws Exception {
		/***Given***/

		/***When***/
		// 테스팅 방법1
		// ModelAndView를 얻어와 처리하는 방식
		MvcResult mvcResult = mockMvc.perform(get("/main")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String userId = (String) mav.getModel().get("mainUserId");
		/***Then***/
		assertEquals(viewName, "main");
		assertEquals(userId, "brown");
	}
	
	@Test
	public void mainViewAndExpectTest() throws Exception {
		// 테스팅 방법2
		// MockMvcResultMatchers를 이용한 방식
		mockMvc.perform(get("/main"))
			.andExpect(status().isOk())
			.andExpect(view().name("main"))
			.andExpect(model().attribute("mainUserId", "brown"));
	}

}
