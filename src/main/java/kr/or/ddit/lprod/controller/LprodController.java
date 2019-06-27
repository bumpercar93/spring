package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.paging.model.PageVO;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	
	private static final Logger logger = LoggerFactory.getLogger(LprodController.class);
	
	@Resource(name = "lprodServiceImpl")
	private ILprodService lprodService;
	
	@RequestMapping("/pagingList")
	public String lprodPagingList(@RequestParam(name = "page", defaultValue = "1")int page, 
									@RequestParam(name = "pageSize", defaultValue = "5")int pageSize, Model model) {
		
		PageVO pageVO = new PageVO(page, pageSize);
		
		logger.debug("pageVO : {}", pageVO);
		
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVO);
		List<LprodVO> lprodList = (List<LprodVO>) resultMap.get("lprodList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("paginationSize", paginationSize);
		
		return "/lprod/lprodPagingList";
	}
	
}
