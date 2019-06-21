package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.user.model.UserVO;

public interface IUserDao {
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	 */
	List<UserVO> userList();

	/**
	 * 
	* Method : insertUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 추가
	 */
	int insertUser(UserVO userVO);

	/**
	 * 
	* Method : deleteUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* Method 설명 : 사용자 삭제
	 */
	void deleteUser(String userId);
	
}
