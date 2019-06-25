package kr.or.ddit.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.user.model.UserVO;

/*
	servlet
		- extends HttpServlet
		- servlet-mapping(web.xml, @WebServlet)
		- service -> doXXX
	
	spring
		- pojo(Plain Old Java Object), @Controller
		- @RequestMapping(class, method)
		- @RequestMapping에 설정한 url method 호출
*/

@SessionAttributes("rangers")
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	// 메소드에 적용된 @ModelAttribute
	// @RequestMapping이 붙은 메소드가 실행될 때(요청이 처리될 때)
	// @ModelAttribute가 적용된 메소드가 리턴하는 값을 Model객체에 자동적으로 넣어준다
	// localhost/main --> @RequestMapping("/main") : mainView --> Model에는 rangers 속성이 들어가 있다
	@ModelAttribute("rangers")
	public List<String> rangers() {
		logger.debug("{}", "rangers()");
		
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("james");
		rangers.add("moon");
		
		return rangers;
	}
	
	
	/**
	 * 
	* Method : mainView
	* 작성자 : PC06
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : main 페이지 요청(viewName)
	 */
	@RequestMapping("/main")
	public String mainView(Model model, @ModelAttribute("userVO") UserVO userVO) {
		logger.debug("mainView");
		logger.debug("model.asMap().get(\"rangers\") : {}", model.asMap().get("rangers"));
		
//		UserVO userVO = new UserVO();
		userVO.setName("브라운");
//		model.addAttribute("userVO", userVO);
		logger.debug("userVO : {}", userVO);
		
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		
		// prefix + viewName + suffix
		// /WEB-INF/views/main.jsp
		
		model.addAttribute("mainUserId","brown");
		
		return "main";
	}
	
	/**
	 * 
	* Method : mainViewMav
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : main 페이지 요청(ModelAndView 사용)
	 */
	@RequestMapping("/mainMav")
	public ModelAndView mainViewMav(@ModelAttribute("rangers") List<String> rangers) {
		logger.debug("mainViewMav : {}", rangers);
		
		// viewName을 기반으로 ModelAndView 객체를 생성
		ModelAndView mav = new ModelAndView("main");
		
		// 위 문장은 아래 두 문장과 같다.
		// ModelAndView mav = new ModelAndView("main");
		// mav.setViewName("main");
		
		mav.addObject("mainUserId", "brown");
		
		return mav;
	}
	
	
	/**
	 * 
	* Method : pathvariable
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : pathvariable로부터 사용자 아이디 가져오기(ex : 도서관 사업소)
	 */
	// localhost/main/pathvariable/brown
	// localhost/main/pathvariable/sally
	@RequestMapping("/main/pathvariable/{userId}")
	public String pathvariable(@PathVariable("userId") String userId) {
		logger.debug("userId : {}", userId);
		return "main";
	}
	
	/**
	 * 
	* Method : header
	* 작성자 : PC06
	* 변경이력 :
	* @param accept
	* @return
	* Method 설명 : Accept 헤더 정보 가져오기
	 */
	@RequestMapping("/main/header")
	public String header(@RequestHeader(name = "Accept"/*, required = false*/) String accept) {
		logger.debug("Accept : {}", accept);
		return "main";
	}
	
}
