package kr.or.ddit.batch;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import kr.or.ddit.batch.service.IBatchService;

public class YogultTask {

	
	@Resource(name = "batchService")
	private IBatchService batchService;
	
	public void yogultTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		batchService.createDaily(sdf.format(new Date()));
	}
	
}
