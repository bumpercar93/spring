package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserControllerTest extends ControllerTestEnv{

	/**
	* Method : userListTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : userList 페이지 요청 테스트
	*/
	@Test
	public void userListTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/list")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertNotNull(mav.getModel().get("userList"));
		assertEquals(107, ((List<UserVO>)(mav.getModelMap().get("userList"))).size() );
	}

	/**
	* Method : userPagingListWithParameterTest
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 파라미터(page, pageSize)를 주고 사용자 페이징처리 페이지 요청 테스트
	*/
	@Test
	public void userPagingListWithParameterTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")
				.param("page", "1").param("pageSzie", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>) mav.getModel().get("userPagingList");
		PageVO pageVO = (PageVO) mav.getModel().get("pageVO");
		int paginationSize = (int) mav.getModel().get("paginationSize");

		/***Then***/
		assertEquals("user/tiles.userPagingList", viewName);
		assertEquals(1, pageVO.getPage());
		assertEquals(10, pageVO.getPageSize());
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
//		assertEquals(new PageVO(1,10), pageVO);
	}

	/**
	* Method : userPagingList
	* 작성자 : PC14
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 파라미터(page, pageSize) 없이 사용자 페이징처리 페이지 요청 테스트
	*/
	@Test
	public void userPagingListWithOutParameterTest() throws Exception {
		/***Given***/

		/***When***/
		mockMvc.perform(get("/user/pagingList"))
				.andExpect(view().name("user/tiles.userPagingList"))
				.andExpect(model().attributeExists("userPagingList"))
				.andExpect(model().attributeExists("pageVO"))
				.andExpect(model().attribute("paginationSize", 11))
				.andReturn();

	}
	
	/**
	 * 
	* Method : userTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 사용자 상세조회 테스트
	 * @throws Exception 
	 */
	@Test
	public void userTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVO = (UserVO) mav.getModel().get("userVO");
		
		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("브라운", userVO.getName());
	}
	
	/**
	 * 
	* Method : userFormGetTest
	* 작성자 : PC06
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록화면 테스트
	 */
	@Test
	public void userFormGetTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/form")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("user/userForm", viewName);
	}
	
	/**
	 * 
	* Method : userFormPostSuccessTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트 (Success 시나리오)
	 * @throws Exception 
	 */
	@Test
	public void userFormPostSuccessTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/moon.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
												.param("userId", "testId")
												.param("name", "testName")
												.param("pass", "test1234")
												.param("alias", "testAlias")
												.param("addr1", "testAddr1")
												.param("addr2", "testAddr2")
												.param("zipcd", "12345")
												.param("birth", "1993-10-17"))
												.andExpect(view().name("redirect:/user/pagingList"));
	}
	
	/**
	 * 
	* Method : userFormPostFailTest
	* 작성자 : PC06
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 테스트 (Fail 시나리오)
	 */
	@Test
	public void userFormPostFailTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/moon.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
												.param("userId", "brown") // 이미 존재하는 아이디
												.param("name", "testName")
												.param("pass", "test1234")
												.param("alias", "testAlias")
												.param("addr1", "testAddr1")
												.param("addr2", "testAddr2")
												.param("zipcd", "12345")
												.param("birth", "1993-10-17"))
												.andExpect(view().name("user/userForm"));
	}
	
	/**
	 * 
	* Method : profileTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 사용자 사진 응답 테스트
	 * @throws Exception 
	 */
	@Test
	public void profileTest() throws Exception {
		mockMvc.perform(get("/profile").param("userId", "brown")).andExpect(status().isOk());
	}
	
	/**
	 * 
	* Method : userModifyGetTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 사용자 수정 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void userModifyGetTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/modify").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVO = (UserVO) mav.getModel().get("userVO");
		
		/***Then***/
		assertEquals(viewName, "user/userModify");
		assertNotNull(userVO);
		assertEquals(userVO.getName(), "브라운");
	}
	
	/**
	 * 
	* Method : userModifyPostTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 사용자 정보 수정 테스트
	 * @throws Exception 
	 */
	@Test
	public void userModifyPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/moon.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/modify").file(file)
												.param("userId", "brown")
												.param("name", "testName")
												.param("pass", "test1234")
												.param("alias", "testAlias")
												.param("addr1", "testAddr1")
												.param("addr2", "testAddr2")
												.param("zipcd", "12345")
												.param("birth", "1993-10-17"))
												.andExpect(view().name("redirect:/user/user"));
	}
	
	
}