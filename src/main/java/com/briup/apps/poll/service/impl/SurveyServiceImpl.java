package com.briup.apps.poll.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.SurveyExample;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.dao.SurveyMapper;
import com.briup.apps.poll.dao.extend.SurveyVMMapper;
import com.briup.apps.poll.service.ISurveyService;
@Service
public class SurveyServiceImpl implements ISurveyService{

	@Autowired
	private SurveyMapper surveyMapper;
	@Autowired 
	private SurveyVMMapper surveyVMMapper;
	
	@Override
	public List<Survey> findAll() throws Exception {
		//创建空模板
		SurveyExample example = new SurveyExample();
		//调用QBE查询，并且将查询结果返回
		return surveyMapper.selectByExample(example);
	}

	@Override
	public List<SurveyVM> findAllSurveyVM() throws Exception {
		return surveyVMMapper.selectAll();
	}
	
	@Override
	public List<Survey> query(String keywords) throws Exception {
		SurveyExample example = new SurveyExample();
		example.createCriteria().andCodeLike(keywords);
//		example.createCriteria().andStatusLike(keywords);
		return surveyMapper.selectByExample(example);
	}

	@Override
	public void deleteById(long id) throws Exception {
		surveyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for(long id : ids){
			surveyMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void saveOrUpdate(Survey survey) throws Exception {
		//在保存课调之前先初始化课调信息	
		if(survey.getId()!=null){
			surveyMapper.updateByPrimaryKey(survey);
		}else{
			survey.setStatus(Survey.STATUS_INIT);
			survey.setCode("");
			Date surveyDate=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str=sdf.format(surveyDate);
			survey.setSurveydate(str);
			surveyMapper.insert(survey);
		}
	
	}

	@Override
	public SurveyVM findById(long id) throws Exception {
		return surveyVMMapper.selectById(id);
	}

	@Override
	public Survey findSurveyById(long id) {
		return surveyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SurveyVM> findByStatus(String status) {
		return surveyVMMapper.selectByStatus(status);
	}

}
