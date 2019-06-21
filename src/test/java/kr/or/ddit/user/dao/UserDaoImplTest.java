package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserDaoImplTest extends LogicTestEnv {

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

}
