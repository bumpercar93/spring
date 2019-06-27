package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.model.ProdVO;

public interface IProdService {

	/**
	 * 
	* Method : getProdforLgu
	* 작성자 : PC06
	* 변경이력 :
	* @param prod_lgu
	* @return
	* Method 설명 : prod_lgu에 해당하는 prodVO리스트를 가져온다.
	 */
	List<ProdVO> getProdforLgu(String prod_lgu);

}
