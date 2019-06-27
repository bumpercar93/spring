package kr.or.ddit.lprod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodDaoImplTest extends LogicTestEnv {
	
	@Resource(name = "lprodDaoImpl")
	private ILprodDao lprodDao;
	
	/**
	 * 
	* Method : lprodPagingListTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : LPROD 페이지 리스트 테스트
	 */
	@Test
	public void lprodPagingListTest() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 5);

		/***When***/
		List<LprodVO> lprodList = lprodDao.lprodPagingList(pageVO);

		/***Then***/
		assertNotNull(lprodList);
		assertEquals(5, lprodList.size());
	}
	
	/**
	 * 
	* Method : lprodCnt
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : LPROD 전체 갯수 테스트
	 */
	@Test
	public void lprodCnt() {
		/***Given***/
		

		/***When***/
		int cnt = lprodDao.lprodCnt();
		
		/***Then***/
		assertEquals(9, cnt);
		
	}

}
