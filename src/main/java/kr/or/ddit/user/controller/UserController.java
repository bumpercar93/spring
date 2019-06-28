package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encryt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.model.UserVOValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
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
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String userList(Model model) {
		
		model.addAttribute("userList", userService.userList());
		
		return "user/userList";
	}
	
	@RequestMapping("/userListExcel")
	public String userListExcel(Model model, String fileName) {
		List<String> header = new ArrayList<String>();
		header.add("userId");
		header.add("name");
		header.add("alias");
		header.add("addr1");
		header.add("addr2");
		header.add("zipcd");
		header.add("birth");
		
		model.addAttribute("header", header);
		model.addAttribute("data", userService.userList());
		model.addAttribute("fileName", fileName);
		
		return "userExcelView";
	}
	
	/**
	 * 
	* Method : userPagingList
	* 작성자 : PC06
	* 변경이력 :
	* @param pageVO
	* @param model
	* @return
	* Method 설명 : 사용자 페이징 리스트
	 */
	@RequestMapping("/pagingList")
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

		return "user/tiles.userPagingList";
	}
	
	/**
	 * 
	* Method : user
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 상세조회
	 */
	@RequestMapping("/user")
	public String user(String userId, Model model) {
		model.addAttribute("userVO", userService.getUser(userId));
		return "user/user";
	}
	
	/**
	 * 
	* Method : userAjax
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 정보 json 응답
	 */
	@RequestMapping("/userJson")
	public String userJson(String userId, Model model) {
		model.addAttribute("userVO", userService.getUser(userId));
		return "jsonView";
	}
	
	/**
	 * 
	* Method : userFormGet
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록화면
	 */
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}
	
	/**
	 * 
	* Method : userForm
	* 작성자 : PC06
	* 변경이력 :
	* @param model
	* @param userVO
	* @param profile
	* @return
	* Method 설명 : 사용자 등록
	 */
//	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userForm(Model model, UserVO userVO, BindingResult result, MultipartFile profile) {
		
		new UserVOValidator().validate(userVO, result);
		
		if(result.hasErrors()) 
			return "user/userForm";
		
		logger.debug("userVO : {}", userVO);
		String viewName = "";
		UserVO dbUser = userService.getUser(userVO.getUserId());
		
		if(dbUser == null) {
			if(profile.getSize() > 0) {
				String filename = profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				String uploadPath = PartUtil.getUploadPath();
				
				// 파일 쓰기
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVO.setPath(filePath);
				userVO.setFilename(filename);
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			int insertCnt = userService.insertUser(userVO);
			
			if(insertCnt > 0) {
				viewName = "redirect:/user/pagingList";
			}
			
		}else {
			model.addAttribute("msg", "이미 존재하는 사용자입니다.");
			viewName = userForm();
		}
		return viewName;
	}
	
	/**
	 * 
	* Method : userForm
	* 작성자 : PC06
	* 변경이력 :
	* @param model
	* @param userVO
	* @param profile
	* @return
	* Method 설명 : 사용자 등록
	 */
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userFormJsr(Model model, @Valid UserVO userVO, BindingResult result, MultipartFile profile) {
		
		if(result.hasErrors()) 
			return "user/userForm";
		
		logger.debug("userVO : {}", userVO);
		String viewName = "";
		UserVO dbUser = userService.getUser(userVO.getUserId());
		
		if(dbUser == null) {
			if(profile.getSize() > 0) {
				String filename = profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				String uploadPath = PartUtil.getUploadPath();
				
				// 파일 쓰기
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVO.setPath(filePath);
				userVO.setFilename(filename);
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			int insertCnt = userService.insertUser(userVO);
			
			if(insertCnt > 0) {
				viewName = "redirect:/user/pagingList";
			}
			
		}else {
			model.addAttribute("msg", "이미 존재하는 사용자입니다.");
			viewName = userForm();
		}
		return viewName;
	}
	
	/**
	 * 
	* Method : profile
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @param response
	* @param request
	* @throws IOException
	* Method 설명 : 사용자 사진 응답 생성
	 */
	@RequestMapping("/profile")
	public String profile(String userId, Model model) throws IOException {
		// 사용자 정보(path)를 조회
		UserVO userVO = userService.getUser(userId);
		model.addAttribute("userVO", userVO);
		
		return "profileView";
	}
	
	/**
	 * 
	* Method : userModify
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 수정 화면 요청
	 */
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String userId, Model model) {
		model.addAttribute("userVO", userService.getUser(userId));
		return "user/userModify";
	}
	
	/**
	 * 
	* Method : userModify
	* 작성자 : PC06
	* 변경이력 :
	* @param userVO
	* @param profile
	* @param session
	* @param model
	* @return
	* Method 설명 : 사용자 수정
	 */
	@RequestMapping(path = "/modify", method = RequestMethod.POST)
	public String userModify(UserVO userVO, MultipartFile profile, 
							HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		//추후 ajax 요청으로 분리
		//userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
		
		if(profile.getSize() > 0) {
			String filename = profile.getOriginalFilename();
			String ext = PartUtil.getExt(filename);
			String uploadPath = PartUtil.getUploadPath();
			
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			
			userVO.setPath(filePath);
			userVO.setFilename(filename);
			
			try {
				profile.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int updateCnt = userService.updateUser(userVO);
		if(updateCnt > 0) {
			//session.setAttribute("msg", "등록되었습니다");
			redirectAttributes.addFlashAttribute("msg", "등록되었습니다"); // 한번만 사용하고 자동 삭제
			redirectAttributes.addAttribute("userId", userVO.getUserId()); // 파라미터를 자동으로 붙여준다
			return "redirect:/user/user";
		}else {
			return userModify(userVO.getUserId(), model);
		}
	}
	
}
