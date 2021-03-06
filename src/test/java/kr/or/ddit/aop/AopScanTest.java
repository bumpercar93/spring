package kr.or.ddit.aop;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.service.IBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kr/or/ddit/aop/application-aop-scan.xml"})
public class AopScanTest {
	
	@Resource(name = "boardServiceImpl")
	private IBoardService boardService;
	
	@Test
	public void aopBeforeTest() {
		/***Given***/

		/***When***/
		String msg = boardService.sayHello();
		
		/***Then***/
		assertEquals("boardDao sayHello", msg);
		
	}
	
}
