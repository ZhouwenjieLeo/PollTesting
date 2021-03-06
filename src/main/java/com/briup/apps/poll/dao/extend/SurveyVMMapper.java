package com.briup.apps.poll.dao.extend;

import java.util.List;

import com.briup.apps.poll.bean.extend.SurveyVM;

public interface SurveyVMMapper {

	List<SurveyVM> selectAll();
	
	SurveyVM selectById(long id);
	
	List<SurveyVM> selectByStatus(String status);
}
