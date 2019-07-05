package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;

@Transactional
@Service
public class UserServiceImpl implements IUserService {
	
	@Resource(name="userDaoImpl")
	private IUserDao userDao;
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 리스트 조회
	 */
	@Override
	public List<UserVO> userList() {
		return userDao.userList();
	}
	
	/**
	 * 
	* Method : insertUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 등록
	 */
	@Override
	public int insertUser(UserVO userVO) {
		return userDao.insertUser(userVO);
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
		userDao.deleteUser(userId);
	}

	/**
	* 
	* Method : getUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		return userDao.getUser(userId);
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
	public Map<String, Object> userPagingList(PageVO pageVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", userDao.userPagingList(pageVO));
		
		int userCnt = userDao.usersCnt();
		
		int paginationSize = (int) Math.ceil((double)userCnt/pageVO.getPageSize());
		
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
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
		return userDao.updateUser(userVO);
	}

}
