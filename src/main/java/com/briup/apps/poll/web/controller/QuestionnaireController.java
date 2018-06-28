package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Course;
import com.briup.apps.poll.bean.Questionnaire;
import com.briup.apps.poll.bean.extend.QuestionnaireVM;
import com.briup.apps.poll.service.ICourseService;
import com.briup.apps.poll.service.IQuestionnaireService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="问卷相关接口")
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

	/**
	 * @return
	 */
	@Autowired
	private IQuestionnaireService questionnaireService;
	
	@ApiOperation(value="查询出所有的问卷信息")
	@GetMapping("findAllQuestionnaire")
	public MsgResponse findAllQuestionnaire(){
		try {
			List<Questionnaire> list = questionnaireService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
//	@ApiOperation(value="查询出所有的问卷信息",notes="每个问卷信息中包含所有的题目的信息")
//	@GetMapping("findAllQuestionnaireVM")
//	public MsgResponse findAllQuestionnaireVM(){
//		try {
//			List<QuestionnaireVM> list = questionnaireService.findAllQuestionnaireVM();
//			return MsgResponse.success("success", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}
//	}
	
	
	
	@ApiOperation(value="通过关键字查询出问卷信息")
	@GetMapping("queryQuestionnaire")
	public MsgResponse queryQuestionnaire(String keywords){
		try {
			List<Questionnaire> list = questionnaireService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id删除问卷信息")
	@GetMapping("deleteQuestionnaireById")
	public MsgResponse deleteQuestionnaireById(@RequestParam Long id){
		try {
			questionnaireService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="添加问卷信息")
	@PostMapping("saveQuestionnaire")
	public MsgResponse saveQuestionnaire(Questionnaire questionnaire){
		try {
			questionnaireService.save(questionnaire);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="修改问卷信息")
	@PostMapping("updateQuestionnaire")
	public MsgResponse updateQuestionnaire(Questionnaire questionnaire){
		try {
			questionnaireService.update(questionnaire);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="批量删除问卷信息")
	@GetMapping("batchDeleteQuestionnaire")
	public MsgResponse batchDeleteQuestionnaire(long[] ids){
		try {
			questionnaireService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	
	
}
