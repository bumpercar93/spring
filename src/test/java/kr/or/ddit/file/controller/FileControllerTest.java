package kr.or.ddit.file.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

public class FileControllerTest extends ControllerTestEnv {

	private static final Logger logger = LoggerFactory.getLogger(FileControllerTest.class);
	
	/**
	 * 
	* Method : uploadViewTest
	* 작성자 : PC06
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 접속 테스트
	 */
	@Test
	public void uploadViewTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/file/uploadView")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals(viewName, "upload/upload");
	}
	
	/**
	 * 
	* Method : uploadTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 파일 업로드 테스트
	 * @throws Exception 
	 * @throws IOException 
	 */
	@Test
	public void uploadTest() throws IOException, Exception {
		/***Given***/
		File file = new File(getClass().getClassLoader().getResource("kr/or/ddit/testenv/moon.png").getFile());
//		File file = new File("src/test/resources/kr/or/ddit/testenv/nayana.png");
		logger.debug("file.getName() : {}", file.getName());
		
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = 
				new MockMultipartFile("img", file.getName(), "", fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/file/upload").file(mockMultipartFile)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String) mav.getModel().get("msg");
		/***Then***/
		assertEquals("SUCCESS", msg);
		assertEquals(viewName, "upload/upload");
	}
	
}
