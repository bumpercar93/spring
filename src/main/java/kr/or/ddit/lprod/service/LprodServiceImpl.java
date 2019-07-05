package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Transactional
@Service
public class LprodServiceImpl implements ILprodService {

	@Resource(name = "lprodDaoImpl")
	private ILprodDao lprodDao;

	/**
	 * 
	* Method : lprodPagingList
	* 작성자 : PC06
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : lprod 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> lprodPagingList(PageVO pageVO) {
		List<LprodVO> lprodList = lprodDao.lprodPagingList(pageVO);
		int cnt = lprodDao.lprodCnt();
		int paginationSize = (int) Math.ceil((double)cnt/pageVO.getPageSize());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("paginationSize", paginationSize);
		resultMap.put("lprodList", lprodList);
		
		return resultMap;
	}
	
}
