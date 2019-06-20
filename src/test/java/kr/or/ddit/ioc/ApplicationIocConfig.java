package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.BoardServiceImpl;

@Configuration
public class ApplicationIocConfig {
	
	// <bean id="boardDao" class="kr.or.ddit.board.dao.BoardDaoImpl"/>
	// @Bean(name = {"boardDao"}) // 이름을 메서드명말고 임의로 지정해줄 수 있다. 배열안에 여러가지 이름을 가질 수도 있다.
	@Bean
	public IBoardDao getBoardDao() {
		return new BoardDaoImpl();
	}
	
	/*<bean id="boardService" class="kr.or.ddit.board.service.BoardServiceImpl">
		<property name="boardDao" ref="boardDao"/>
	</bean>*/
	@Bean
	public BoardServiceImpl getBoardService() {
		BoardServiceImpl boardService = new BoardServiceImpl();
		boardService.setBoardDao(getBoardDao());
		//boardService.setBoardDao(new BoardDaoImpl()); // 새로운 객체를 만들어 주고 싶을 때
		return boardService;
	}
	
	
}
