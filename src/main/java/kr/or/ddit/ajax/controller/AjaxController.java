package kr.or.ddit.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Resource(name = "userServiceImpl")
	IUserService userService;
	
	/**
	 * 
	* Method : view
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : ajax 호출용 view
	 */
	@RequestMapping("/view")
	public String view() {
		return "tiles.ajaxView";
	}
	
	@RequestMapping("/requestData")
	public String requestData(Model model) {
		model.addAttribute("pageVO", new PageVO(5, 10));
		model.addAttribute("pageVO2", new PageVO(2, 10));
		
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		model.addAttribute("rangers", rangers);
		return "jsonView";
	}
	
	@RequestMapping("/userData")
	public String userData(String userId, Model model) {
		logger.debug("userId : {}", userId);
		UserVO userVO = userService.getUser(userId);
		String birth = userVO.getBirthstr();
		model.addAttribute("userVO", userVO);
		model.addAttribute("birth", birth);
		return "jsonView";
	}
	
	@RequestMapping("/userHtml")
	public String userHtml(String userId, Model model) {
		model.addAttribute("userVO", userService.getUser(userId));
		return "user/userHtml";
	}
	
}
