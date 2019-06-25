package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userServiceImpl")
	private IUserService userService;
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC06
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트 페이지
	 */
	@RequestMapping(path = "/userList", method = RequestMethod.GET)
	public String userList(Model model) {
		
		model.addAttribute("userList", userService.userList());
		
		return "user/userList";
	}
	
	@RequestMapping("/userPagingList")
//	방법1
//	public String userPagingList(@RequestParam(name = "page", defaultValue = "1") int page,
//								 @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
//								 Model model) {
//		PageVO pageVO = new PageVO(page, pageSize);
//		logger.debug("page : {}", pageVO.getPage());
//		logger.debug("pageSize: {}", pageVO.getPageSize());
	// 방법2
	public String userPagingList(PageVO pageVO, Model model) {
		logger.debug("pageVO : {}", pageVO);

		Map<String, Object> resultMap = userService.userPagingList(pageVO);
		List<UserVO> userList = (List<UserVO>)resultMap.get("userList");
		int paginationSize = (int)resultMap.get("paginationSize");

		model.addAttribute("userPagingList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVO", pageVO);

		return "user/userPagingList";
	}
	
}
