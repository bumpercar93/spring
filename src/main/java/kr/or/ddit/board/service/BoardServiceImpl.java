package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.IBoardDao;

@Transactional
@Service
public class BoardServiceImpl implements IBoardService {
	
	@Resource(name = "boardDaoImpl")
	private IBoardDao boardDao;

	public BoardServiceImpl() {
		
	}
	
	public BoardServiceImpl(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public IBoardDao getBoardDao() {
		return boardDao;
	}
	
	public void setBoardDao(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public String sayHello() {
		return boardDao.sayHello();
	}
	
}
