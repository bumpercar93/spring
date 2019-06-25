package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;

// DI
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpringIocJunitTest {
	
	@Resource(name = "boardDaoImpl")
	private IBoardDao boardDao;
	
	@Resource(name = "boardDaoPrototype")
	private IBoardDao boardDaoPrototype;
	
	@Resource(name = "boardDaoPrototype")
	private IBoardDao boardDaoPrototype2;
	
	@Resource(name = "boardServiceImpl")
	private IBoardService boardService;

	@Resource(name = "boardServiceImpl")
	private IBoardService boardService2;
	
	@Resource(name = "boardServiceConstructor")
	private IBoardService boardServiceConstructor;
	
	/**
	 * 
	* Method : springIocTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너 생성 테스트
	 */
	@Test
	public void springIocTest() {
		/***Given***/

		/***When***/
		String result = boardService.sayHello();
		
		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", result);
	}
	
	/**
	 * 
	* Method : springSingletonScopeTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너의 singleton scope 테스트
	 */
	@Test
	public void springSingletonScopeTest() {
		// filed 기준	boardService, boardService2 : spring boardService bean (scope = singleton)
		//			boardServiceConstructor : spring boardServiceConstructor bean (scope =singleton)
		// 1. boardService, boardService2 : 같은 객체
		// 2. boardService, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton 개념에 따라 다른 객체
		// 3. boardService2, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton 개념에 따라 다른 객체
		/***Given***/
		
		/***When***/

		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardService);
		assertNotNull(boardService2);
		assertNotNull(boardServiceConstructor);
		
		// 생성자를 통해 boardDao를 생성한 예시
		assertEquals(boardDao, boardServiceConstructor.getBoardDao());
		
		// Bean의 id가 같기 때문에 같은 객체
		assertEquals(boardService, boardService2);
		
		// Bean의 id가 다르기 때문에 다른 객체
		assertNotEquals(boardServiceConstructor, boardService);
		assertNotEquals(boardServiceConstructor, boardService2);
	}
	
	/**
	 * 
	* Method : springPrototypeScopeTest
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너의 prototype scope 테스트
	 */
	@Test
	public void springPrototypeScopeTest() {
		// prototype scope : 요청시 마다 새로운 객체를 만들어서 준다
		// boardDao : spring boardDao(scope=singleton)
		// boardDaoPrototype : spring boardDaoPrototype(scope=prototype)
		// boardDaoPrototype2 : spring boardDaoPrototype(scope=prototype)
		// 1. boardDao, boardDaoPrototype : spring bean id가 다르기 때문에 다른 객체
		// 2. boardDaoPrototype, boardDaoPrototype2 : 두 객체는 같은 스프링 bean이지만 scope가 prototype이므로 다른 객체
		/***Given***/

		/***When***/

		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardDaoPrototype);
		assertNotNull(boardDaoPrototype2);
		// 모두 새로운 객체이기 때문에 다 다른 객체
		assertNotEquals(boardDao, boardDaoPrototype);
		assertNotEquals(boardDao, boardDaoPrototype2);
		assertNotEquals(boardDaoPrototype, boardDaoPrototype2);
	}

}
