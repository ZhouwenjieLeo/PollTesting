package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.CourseExample;
import com.briup.apps.poll.bean.Questionnaire;
import com.briup.apps.poll.bean.QuestionnaireExample;
import com.briup.apps.poll.bean.QuestionnaireQuestion;
import com.briup.apps.poll.bean.QuestionnaireQuestionExample;
import com.briup.apps.poll.bean.extend.QuestionnaireVM;
import com.briup.apps.poll.dao.QuestionnaireMapper;
import com.briup.apps.poll.dao.QuestionnaireQuestionMapper;
import com.briup.apps.poll.dao.extend.QuestionnaireVMMapper;
import com.briup.apps.poll.service.IQuestionnaireService;
@Service
public class QuestionnaireServiceImpl implements IQuestionnaireService{

	@Autowired
	private QuestionnaireMapper questionnaireMapper;
	@Autowired
	private QuestionnaireVMMapper questionnaireVMMapper;
	@Autowired
	private QuestionnaireQuestionMapper qqMapper;
	
	@Override
	public List<Questionnaire> findAll() throws Exception {
		//创建空模板
		QuestionnaireExample example = new QuestionnaireExample();
		//调用QBE查询，并且将查询结果返回
		return questionnaireMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public QuestionnaireVM findById(long id) throws Exception {
		return questionnaireVMMapper.selectById(id);
	}
	
	@Override
	public List<Questionnaire> query(String keywords) throws Exception {
		QuestionnaireExample example = new QuestionnaireExample();
		example.createCriteria().andNameLike(keywords);
		return questionnaireMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void deleteById(long id) throws Exception {
		questionnaireMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for(long id : ids){
			questionnaireMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void saveOrUpdate(Questionnaire questionnaire,long[] ids) throws Exception {
		
		if(questionnaire.getId()==null){
			questionnaireMapper.insert(questionnaire);
			for(long id:ids){
				QuestionnaireQuestion qq=new QuestionnaireQuestion();
				qq.setQuestionId(id);
				qq.setQuestionnaireId(questionnaire.getId());
				qqMapper.insert(qq);
			}
		}else{
			//1.2如果是修改
			//1.2.1更新问卷信息
			questionnaireMapper.updateByPrimaryKeyWithBLOBs(questionnaire);
			//1.2.2删除问卷下所有题目的关系
			QuestionnaireQuestionExample qqExample=new QuestionnaireQuestionExample();
			qqExample.createCriteria().andQuestionnaireIdEqualTo(questionnaire.getId());
			qqMapper.deleteByExample(qqExample);
			//1.2.3重新维护问卷和问题的关系
			for(long id:ids){
				QuestionnaireQuestion qq=new QuestionnaireQuestion();
				qq.setQuestionId(id);
				qq.setQuestionnaireId(questionnaire.getId());
				qqMapper.insert(qq);
			}
//			questionnaireMapper.updateByPrimaryKeyWithBLOBs(questionnaire);
		}
	}
}
