package kr.or.ddit.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String mainView(Model model) {
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		
		// prefix + viewName + suffix
		// /WEB-INF/views/main.jsp
		
		model.addAttribute("mainUserId","brown");
		
		return "main";
	}
	
}
