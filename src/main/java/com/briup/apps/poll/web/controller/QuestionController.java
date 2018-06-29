package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Options;
import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.extend.QuestionVM;
import com.briup.apps.poll.dao.OptionsMapper;
import com.briup.apps.poll.dao.QuestionMapper;
import com.briup.apps.poll.dao.extend.QuestionVMMapper;
import com.briup.apps.poll.service.IQuestionService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="问题相关接口")
@RestController
@RequestMapping("/question")
public class QuestionController {

	/**
	 * @return
	 */
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private QuestionVMMapper  questionMapper;
	
	@ApiOperation(value="查询出所有的问题信息")
	@GetMapping("findAllQuestion") 
	public MsgResponse findAllQuestion(){
		try {
			List<Question> list = questionService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="查询出所有的问题信息",notes="每个题目信息中包含该题目下所有的选项信息")
	@GetMapping("findAllQuestionVM")
	public MsgResponse findAllQuestionVM(){
		try {
			List<QuestionVM> list = questionService.findAllQuestionVM();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过关键字查询出问题信息")
	@GetMapping("queryQuestion")
	public MsgResponse queryQuestion(String keywords){
		try {
			List<Question> list = questionService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id删除问题信息",notes="删除问题的同时删除选项")
	@GetMapping("deleteQuestionById")
	public MsgResponse deleteQuestionById(@RequestParam Long id){
		try {
			questionService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="批量删除问题信息")
	@GetMapping("batchDeleteQuestion")
	public MsgResponse batchDeleteQuestion(long[] ids){
		try {
			questionService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="保存或修改问题信息",notes="如果题目id不为空，表示更新操作;如果题目id为空，表示保存操作")
	@PostMapping("saveOrUpdateQuestion")
	public MsgResponse saveOrUpdateQuestion(QuestionVM question){
		try {
			//调用service层代码完成保存和更新操作
			questionService.saveOrUpdate(question);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
}
