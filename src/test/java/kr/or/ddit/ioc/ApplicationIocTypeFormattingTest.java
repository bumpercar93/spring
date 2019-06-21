package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")
public class ApplicationIocTypeFormattingTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationIocTypeFormattingTest.class);
	
	@Resource(name = "formattingVO")
	FormattingVO formattingVO;
	
	@Test
	public void formattingConversionServiceTest() {
		/***Given***/
		
		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String reg_dt_str = sdf.format(formattingVO.getReg_dt());
		String mod_dt_str = sdf.format(formattingVO.getMod_dt());
		
		
		
		/***Then***/
		assertNotNull(formattingVO);
		assertEquals(reg_dt_str, "06-21-2019");
		assertEquals(mod_dt_str, "06-21-2019");
		assertEquals(formattingVO.getNumber(), 6000);
	}

}
