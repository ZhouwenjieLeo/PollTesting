package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.AnswersExample;
import com.briup.apps.poll.bean.Course;
import com.briup.apps.poll.bean.CourseExample;
import com.briup.apps.poll.bean.extend.AnswersVM;
import com.briup.apps.poll.dao.AnswersMapper;
import com.briup.apps.poll.dao.extend.AnswersVMMapper;
import com.briup.apps.poll.service.IAnswersService;


@Service

public class AnswersServiceImpl implements IAnswersService {
	@Autowired
	private AnswersMapper answersMapper;
	@Autowired
	private AnswersVMMapper answersVMMapper;
	@Override
	public List<Answers> findAll() throws Exception {
		  //创建空模板
		AnswersExample example = new AnswersExample();
		//调用QBE查询，并且将查询结果返回
		return answersMapper.selectByExample(example);
	}

	@Override
	public List<Answers> query(String keywords) throws Exception {
		AnswersExample example = new AnswersExample();
		example.createCriteria().andCheckesLike(keywords);
		return answersMapper.selectByExample(example);
	}

	@Override
	public void deleteById(long id) throws Exception {
		answersMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for(long id : ids){
			answersMapper.deleteByPrimaryKey(id);
		}
	
	}

	@Override
	public List<AnswersVM> findAllAnswersVM() throws Exception {
		return answersVMMapper.selectAll();
	}

	@Override
	public void save(Answers answers) throws Exception {
		// TODO Auto-generated method stub
		answersMapper.insert(answers);
	}

	@Override
	public void update(Answers answers) throws Exception {
		// TODO Auto-generated method stub
		answersMapper.updateByPrimaryKey(answers);
	}

	


}
