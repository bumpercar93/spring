package kr.or.ddit.prod.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdServiceImplTest extends LogicTestEnv {

	@Resource(name = "prodServiceImpl")
	IProdService prodService;
	
	/**
	 * 
	* Method : getProdforLguTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : prod_lgu에 해당하는 prodVO리스트를 가져온다.
	 */
	@Test
	public void getProdforLguTest() {
		/***Given***/
		String prod_lgu = "P101";

		/***When***/
		List<ProdVO> prodList = prodService.getProdforLgu(prod_lgu);

		/***Then***/
		assertNotNull(prodList);
		assertEquals(6, prodList.size());
		
	}

}
