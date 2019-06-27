package kr.or.ddit.lprod.service;


import java.util.Map;

import kr.or.ddit.paging.model.PageVO;

public interface ILprodService {
	
	/**
	 * 
	* Method : lprodPagingList
	* 작성자 : PC06
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : lprod 페이징 리스트 조회
	 */
	Map<String, Object> lprodPagingList(PageVO pageVO);
	
}
