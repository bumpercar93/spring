package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

@Repository
public class UserDaoImpl implements IUserDao {
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * 
	* Method : userList
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	 */
	@Override
	public List<UserVO> userList() {
		return sqlSession.selectList("user.userList");
	}
	
	/**
	 * 
	* Method : insertUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 추가
	 */
	@Override
	public int insertUser(UserVO userVO) {
		return sqlSession.insert("user.insertUser", userVO);
	}
	
	/**
	 * 
	* Method : deleteUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* Method 설명 : 사용자 삭제
	 */
	@Override
	public void deleteUser(String userId) {
		sqlSession.delete("user.deleteUser", userId);
	}

	/**
	* 
	* Method : getUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 정보 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		return sqlSession.selectOne("user.getUser", userId);
	}

	/**
	 * 
	* Method : updateUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 수정
	 */
	@Override
	public int updateUser(UserVO userVO) {
		return sqlSession.update("user.updateUser", userVO);
	}

	/**
	 * 
	* Method : usersCnt
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체수 조회
	 */
	@Override
	public int usersCnt() {
		return sqlSession.selectOne("user.usersCnt");
	}

	/**
	 * 
	* Method : userPagingList
	* 작성자 : PC06
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Override
	public List<UserVO> userPagingList(PageVO pageVO) {
		return sqlSession.selectList("user.userPagingList", pageVO);
	}
	
}
