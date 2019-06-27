package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.prod.model.ProdVO;

@Repository
public class ProdDaoImpl implements IProdDao {

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 
	* Method : getProdforLgu
	* 작성자 : PC06
	* 변경이력 :
	* @param prod_lgu
	* @return
	* Method 설명 : prod_lgu에 해당하는 prodVO리스트를 가져온다.
	 */
	@Override
	public List<ProdVO> getProdforLgu(String prod_lgu) {
		return sqlSession.selectList("prod.getProdforLgu", prod_lgu);
	}

}
