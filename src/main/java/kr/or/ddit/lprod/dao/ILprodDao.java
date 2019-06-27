package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

public interface ILprodDao {
	
	/**
	 * 
	* Method : lprodPagingList
	* 작성자 : PC06
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : lprod 페이징 리스트 조회
	 */
	List<LprodVO> lprodPagingList(PageVO pageVO);
	
	/**
	 * 
	* Method : lprodCnt
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체수 조회
	 */
	int lprodCnt();
	
}
