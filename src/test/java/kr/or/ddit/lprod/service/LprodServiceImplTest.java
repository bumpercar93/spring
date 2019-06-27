package kr.or.ddit.lprod.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodServiceImplTest extends LogicTestEnv {
	
	@Resource(name = "lprodServiceImpl")
	ILprodService lprodService;
	
	/**
	 * 
	* Method : lprodPagingList
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : LPROD 페이지 리스트 테스트
	 */
	@Test
	public void lprodPagingList() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 5);

		/***When***/
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVO);
		List<LprodVO> lprodList = (List<LprodVO>) resultMap.get("lprodList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		/***Then***/
		assertEquals(5, lprodList.size());
		assertEquals(2, paginationSize);
	}

}
