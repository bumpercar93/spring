package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Repository
public class LprodDaoImpl implements ILprodDao {

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
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
	public List<LprodVO> lprodPagingList(PageVO pageVO) {
		return sqlSession.selectList("lprod.lprodPagingList", pageVO);
	}

	/**
	 * 
	* Method : lprodCnt
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체수 조회
	 */
	@Override
	public int lprodCnt() {
		return sqlSession.selectOne("lprod.lprodCnt");
	}

}
