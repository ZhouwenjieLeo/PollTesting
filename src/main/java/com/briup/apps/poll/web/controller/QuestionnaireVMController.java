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
import com.briup.apps.poll.service.IQuestionnaireService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="问卷相关接口")
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireVMController {

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
	
	@ApiOperation(value="通过问卷id查找问卷信息",notes="每个问卷信息中包含所有的题目的信息")
	@GetMapping("findQuestionnaireVMById")
	public MsgResponse findQuestionnaireVMById(long id){
		try {
			QuestionnaireVM qvm=questionnaireService.findById(id);
			return MsgResponse.success("success", qvm);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
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
	
	@ApiOperation(value="通过id删除问卷信息",notes="级联删除问卷和问题的关系")
	@GetMapping("deleteQuestionnaireById")
	public MsgResponse deleteQuestionnaireById(@RequestParam Long id){
		try {
			questionnaireService.deleteById(id);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="添加或修改问卷信息")
	@PostMapping("saveOrUpdateQuestionnaire")
	public MsgResponse saveOrUpdateQuestionnaire(Questionnaire questionnaire,long[] questionIds){
		try {
			questionnaireService.saveOrUpdate(questionnaire, questionIds);
			return MsgResponse.success("保存或更新成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
//	@ApiOperation(value="修改问卷信息")
//	@PostMapping("updateQuestionnaire")
//	public MsgResponse updateQuestionnaire(Questionnaire questionnaire){
//		try {
//			questionnaireService.update(questionnaire);
//			return MsgResponse.success("success", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}
//	}
	
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
