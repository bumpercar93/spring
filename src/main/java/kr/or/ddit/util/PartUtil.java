package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PartUtil {
	
	private static final String UPLOAD_PATH = "d:/springUpload/";

	/**
	 * 
	* Method : getExt
	* 작성자 : PC06
	* 변경이력 :
	* @param fileName
	* @return
	* Method 설명 : 파일명으로부터 파일 확장자를 반환
	 */
	public static String getExt(String filename) {
		String ext = "";
		if(filename.contains(".")) {
			String[] temps = filename.split("\\.");
			ext = temps[temps.length-1];
		}
		return ext.equals("") ? "" : "." + ext;
	}
	
	/**
	 * 
	* Method : checkUploadFolder
	* 작성자 : PC06
	* 변경이력 :
	* @param yyyy
	* @param mm
	* Method 설명 : 년, 월 업로드 폴더가 존재하는지 체크, 없을 경우 폴더 생성
	 */
	private static void checkUploadFolder(String yyyy, String mm) {
		// 신규년도로 넘어갔을 때 해당 년도의 폴더를 생성한다
		File yyyyFolder = new File(UPLOAD_PATH + yyyy);
		if(!yyyyFolder.exists()) {
			yyyyFolder.mkdir();
		}
		
		// 월에 해당하는 폴더가 있는지 확인
		File mmFolder = new File(UPLOAD_PATH + yyyy + File.separator + mm);
		if(!mmFolder.exists()) {
			mmFolder.mkdir();
		}
	}
	
	/**
	 * 
	* Method : getUploadPath
	* 작성자 : PC06
	* 변경이력 :
	* @param yyyy
	* @param mm
	* @return
	* Method 설명 : 업로드 경로를 반환
	 */
	public static String getUploadPath() {
		// 년도에 해당하는 폴더가 있는지, 년도안에 월에 해당하는 폴더가 있는지 검사
		Date dt = new Date();
		SimpleDateFormat yyyymmSdf = new SimpleDateFormat("yyyyMM");
		String yyyyMM = yyyymmSdf.format(dt);
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4);
		
		PartUtil.checkUploadFolder(yyyy,mm);
		
		return UPLOAD_PATH + yyyy + File.separator + mm;
	}

}
