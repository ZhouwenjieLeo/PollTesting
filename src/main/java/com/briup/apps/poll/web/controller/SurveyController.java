package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.service.ISurveyService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="评教相关接口")
@RestController
@RequestMapping("/survey")
public class SurveyController {

	/**
	 * @return
	 */
	@Autowired
	private ISurveyService surveyService;
	@Autowired
	private IAnswersService answersService;
	
	@ApiOperation(value="登录课调",notes="code表示课调编码")
	@GetMapping("loginSurvey")
	public MsgResponse loginSurvey(long code){
		try {
			//1 通过id查询课调信息
			SurveyVM surveyVM=surveyService.findById(code);
			//2 判断课调是否还在进行
			if(surveyVM.getStatus().equals(Survey.STATUS_BEGIN)){
				//3 判断用户是否已经完成答卷操作(暂时不做)
				
				return MsgResponse.success("success", surveyVM); 
			}else{
				return MsgResponse.error("课调状态不合法："+surveyVM.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="开启课调")
	@GetMapping("startSurvey")
	public MsgResponse startSurvey(long id){
		try {
			Survey survey=surveyService.findSurveyById(id);
			if(survey.getStatus().equals(Survey.STATUS_INIT)){
				survey.setStatus(Survey.STATUS_BEGIN);
				String code=survey.getId().toString();
				survey.setCode(code);
				surveyService.saveOrUpdate(survey);
						
				return MsgResponse.success("开启成功", survey);		
			}else{
				return MsgResponse.success("课调状态不合法："+survey.getStatus(), survey);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="关闭课调")
	@GetMapping("stopSurvey")
	public MsgResponse stopSurvey(long id){
		try {
			Survey survey=surveyService.findSurveyById(id);
			if(survey.getStatus().equals(Survey.STATUS_BEGIN)){
				survey.setStatus(Survey.STATUS_NO_CHECK);
				surveyService.saveOrUpdate(survey);
				return MsgResponse.success("成功关闭", survey);
			}else{
				return MsgResponse.success("课调状态不合法："+survey.getStatus(), survey);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="去审核课调",notes="返回课调基本信息以及课调中的主观题")
	@GetMapping("toCheckSurvey")
	public MsgResponse toCheckSurvey(long id){
		try {
			//1 通过id查询课调信息
			SurveyVM surveyVM=surveyService.findById(id);
			//2 查询该课调下所有的答案
			List<Answers> answers=answersService.findAnswersBySurveyId(id);
			//3 根据答卷计算平均分
			Double average=0.0;
			double total=0.0;    //所有学生平均分总和
			for(Answers answer:answers){
				String selectStr=answer.getSelections();
				double singleTotal = 0.0;
				double singleAverage = 0.0;
				if(selectStr!=null){
					String[] arr=selectStr.split("[|]");
					
					for(String a : arr){
						int select=Integer.parseInt(a);
						singleTotal+=select;		
					}
					 singleAverage=singleTotal/arr.length;
				   
				}
				total+=singleAverage;
			}
			 
			//4 设置平均分
			 average=total/answers.size();
			surveyVM.setAverage(average);
			return MsgResponse.success("success", surveyVM);
			
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="审核课调",notes="id表示课调编号，status表示课调状态，0审核不通过，1审核通过")
	@GetMapping("checkSurvey")
	public MsgResponse checkSurvey(long id,int status){
		try {
			Survey survey=surveyService.findSurveyById(id);
			String message="";
			if(survey.getStatus().equals(Survey.STATUS_NO_CHECK)){	
			    if(status==0){
				    message="审核不通过";
				    survey.setStatus(Survey.STATUS_CHECK_NOPASS);
			    }else{
			    	message="审核通过";
				    survey.setStatus(Survey.STATUS_CHECK_PASS);
			    }
			    surveyService.saveOrUpdate(survey);
				return MsgResponse.success(message, null);
			}else{
				message="课调状态不合法";
				return MsgResponse.success("课调状态不合法："+survey.getStatus(), null);
			}
//			surveyService.saveOrUpdate(survey);
//			return MsgResponse.success("message", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过状态查询出课调信息")
	@GetMapping("findSurveyVMByStatus")
	public MsgResponse findSurveyVMByStatus(String status){
		try {
			List<SurveyVM> list = surveyService.findByStatus(status);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="查询出所有的课调信息")
	@GetMapping("findAllSurvey")
	public MsgResponse findAllSurvey(){
		try {
			List<Survey> list = surveyService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id查询出所有的课调信息",notes="每个课调信息中包含所属课程、所属班级、班主任姓名和问卷名字的信息")
	@GetMapping("findSurveyVM")
	public MsgResponse findSurveyVM(){
		try {
			List<SurveyVM> list = surveyService.findAllSurveyVM();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id查询出课调信息")
	@GetMapping("selectSurveyById")
	public MsgResponse selectSurveyById(long id){
		try {
			SurveyVM surveyVM = surveyService.findById(id);
			return MsgResponse.success("success", surveyVM);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id删除课调信息")
	@GetMapping("deleteSurveyById")
	public MsgResponse deleteSurveyById(@RequestParam Long id){
		try {
			surveyService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="添加或修改课调信息",notes="只需要输入courseId,userId,clazzId,questionnaireId")
	@PostMapping("saveOrUpdateSurvey")
	public MsgResponse saveOrUpdateCourse(Survey survey){
		try {
			surveyService.saveOrUpdate(survey);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="批量删除课调信息")
	@GetMapping("batchDeleteSurvey")
	public MsgResponse batchDeleteSurvey(long[] ids){
		try {
			surveyService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
}
