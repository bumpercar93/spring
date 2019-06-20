package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type.xml")
public class ApplicationIocTypeTest {
	
	// userVO라는 이름의 스프링 빈이 정상적으로 생성 되었는지 테스트
	@Resource(name = "userVO")
	private UserVO userVO;
	
	@Test
	public void typeInjectionTest() {
		/***Given***/
		
		/***When***/
		Date birth = userVO.getBirth();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birth_str = sdf.format(birth);
		
		/***Then***/
		assertNotNull(userVO);
		assertEquals("brown", userVO.getUserId());
		assertEquals("2019-08-08", birth_str);
	}

}
