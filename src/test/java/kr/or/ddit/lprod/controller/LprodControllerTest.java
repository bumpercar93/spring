package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class LprodControllerTest extends ControllerTestEnv {

	private static final Logger logger = LoggerFactory.getLogger(LprodControllerTest.class);
	
	/**
	 * 
	* Method : lprodPagingListTest
	* 작성자 : PC06
	* 변경이력 :
	* @throws Exception
	* Method 설명 : LPROD 페이지 리스트 테스트(파라미터 있음)
	 */
	@Test
	public void lprodPagingListWithParameterTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList")
									.param("page", "1")
									.param("pageSize", "5"))
									.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		PageVO pageVO = (PageVO) mav.getModel().get("pageVO");
		List<LprodVO> lprodList = (List<LprodVO>) mav.getModel().get("lprodList");
		logger.debug("lprodList : {}", lprodList);
		int paginationSize = (int) mav.getModel().get("paginationSize");
		
		/***Then***/
		assertEquals(viewName, "/lprod/lprodPagingList");
		assertEquals(1, pageVO.getPage());
		assertEquals(5, pageVO.getPageSize());
		assertEquals(5, lprodList.size());
		assertEquals(2, paginationSize);
	}
	
	/**
	 * 
	* Method : lprodPagingListWithOutParameterTest
	* 작성자 : PC06
	* 변경이력 :
	* @throws Exception
	* Method 설명 : LPROD 페이지 리스트 테스트(파라미터 없음)
	 */
	@Test
	public void lprodPagingListWithOutParameterTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		PageVO pageVO = (PageVO) mav.getModel().get("pageVO");
		List<LprodVO> lprodList = (List<LprodVO>) mav.getModel().get("lprodList");
		logger.debug("lprodList : {}", lprodList);
		int paginationSize = (int) mav.getModel().get("paginationSize");
		
		/***Then***/
		assertEquals(viewName, "/lprod/lprodPagingList");
		assertEquals(1, pageVO.getPage());
		assertEquals(5, pageVO.getPageSize());
		assertEquals(5, lprodList.size());
		assertEquals(2, paginationSize);
	}

}
