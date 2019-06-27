package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVO;

public class ProfileView implements View {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileView.class);
	
	@Override
	public String getContentType() {
		return "img";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.debug("profileView.rander()");
		
		UserVO userVO = (UserVO) model.get("userVO");
		
		// path정보로 file을 읽어 들여서
		ServletOutputStream sos = response.getOutputStream();
		FileInputStream fis = null;
		String filePath = "";
		
		if(userVO.getPath() != null) { // 사용자가 업로드한 파일이 존재할 경우
			filePath = userVO.getPath();
		}else { // 사용자가 업로드한 파일이 존재하지 않을 경우
			filePath = request.getServletContext().getRealPath("/img/no_image.jpg"); //webapp/img/no_image.gif
		}
		
		File file = new File(filePath);
		fis = new FileInputStream(file);
		byte[] buffer = new byte[512];
		
		// response객체에 스트림으로 써준다
		while(fis.read(buffer, 0, 512) != -1) {
			sos.write(buffer);
		}
		
		fis.close();
		sos.close();
	}

}
