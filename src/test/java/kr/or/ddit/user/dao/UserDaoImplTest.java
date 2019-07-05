package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserDaoImplTest extends LogicTestEnv {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImplTest.class);
	
	@Resource(name = "userDaoImpl")
	private IUserDao userDao;
	
	/**
	 * 
	* Method : userListTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 조회 테스트
	 */
	@Test
	public void userListTest() {
		/***Given***/

		/***When***/
		List<UserVO> userList = userDao.userList();

		/***Then***/
		assertNotNull(userList);
		assertEquals(107, userList.size());
	}
	
	/**
	 * 
	* Method : insertUserTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	 */
	@Test
	public void insertUserTest() {
		/***Given***/
		// 사용자 정보를 담고 있는 VO객체 준비
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVO = null;
		try {
			userVO = new UserVO("testName", "testId", "testAlias", "test1234",
								"testAddr1", "testAddr2", "12345", sdf.parse("1993-10-17"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/***When***/
		// userDao.insertUser();
		int insertCnt = userDao.insertUser(userVO);
		
		/***Then***/
		assertEquals(1, insertCnt);
		
		//data 삭제
		userDao.deleteUser(userVO.getUserId());
	}
	
	/**
	 * 
	* Method : getUserTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 1명의 사용자 정보 가져오기 테스트
	 */
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";

		/***When***/
		UserVO userVO = userDao.getUser(userId);

		/***Then***/
		assertNotNull(userVO);
		logger.debug("userVO : {}", userVO);
	}
	
	/**
	 * 
	* Method : updateUserTest
	* 작성자 : PC06
	* 변경이력 :
	* @throws ParseException
	* Method 설명 : 사용자 업데이트 테스트
	 */
	@Test
	public void updateUserTest() throws ParseException {
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		UserVO userVO = new UserVO("이름", "test1", "별명", "1234", "주소1", "주소2", "12345", sdf.parse("93-10-17"));

		/***When***/
		int cnt = userDao.updateUser(userVO);
				
		/***Then***/
		assertTrue(cnt > 0);
		assertEquals(cnt, 1);
	}

	/**
	 * 
	* Method : usersContTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 사용자 전체수 조회
	 */
	@Test
	public void usersContTest() {
		/***Given***/
		
		/***When***/
		int cnt = userDao.usersCnt();
		/***Then***/
		assertEquals(cnt, 107);
	}
	
	/**
	 * 
	* Method : userPagingListTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
	@Test
	public void userPagingListTest() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);
		
		/***When***/
		List<UserVO> userList = userDao.userPagingList(pageVO);
		
		/***Then***/
		assertNotNull(userList);
		assertEquals(userList.size(), 10);
	}
	
}
