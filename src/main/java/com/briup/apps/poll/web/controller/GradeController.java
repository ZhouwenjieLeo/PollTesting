package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Grade;
import com.briup.apps.poll.bean.extend.GradeVM;
import com.briup.apps.poll.service.IGradeService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description = "年级相关接口")
@RestController
@RequestMapping("/grade")

public class GradeController {
	/**
	 * @return
	 */
	@Autowired
	private IGradeService gradeService;
	
	@ApiOperation(value="查询所有年级")
	@GetMapping("findAllGrade")
	public MsgResponse findAllGrade(){
		try {
			List<Grade> list = gradeService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="查询所有年级",notes="每个年级信息中包含所属学校的信息")
	@GetMapping("findAllGradeVM")
	public MsgResponse findAllGradeVM(){
		try {
			List<GradeVM> list = gradeService.findAllGradeVM();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="按关键字查询年级信息",notes="如果年级id不为空，表示更新操作;如果年级id为空，表示保存操作")
	@GetMapping("queryGrade")
	public MsgResponse queryGrade(String keywords){
		try {
			List<Grade> list=gradeService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {		
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}	
	}
	
	@ApiOperation(value="保存或修改修改年级信息",notes="如果年级id不为空，表示更新操作;如果年级id为空，表示保存操作")
	@PostMapping("saveOrUpdateGrade")
	public MsgResponse saveOrUpdateGrade(Grade grade){
		try {
			if(grade!=null&&grade.getId()!=null){
				gradeService.update(grade);
			}else{
				gradeService.save(grade);
			}
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}	
	}
	
//	@ApiOperation(value="修改年级信息")	
//	@PostMapping("updateGrade")
//	public MsgResponse updateGrade(Grade grade){
//		try {
//			gradeService.update(grade);
//			return MsgResponse.success("success", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}	
//	}
	
	@ApiOperation(value="通过ID删除年级")
	@GetMapping("deleteByIdGrade")
	public MsgResponse deleteByIdGrade(long id){
		try {
			gradeService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}			
	}
	
	@ApiOperation(value="批量删除")
	@GetMapping("batchDeleteGrade")
	public MsgResponse batchDeleteGrade(long[] ids){
		try {
			gradeService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {			
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}			
	}
	
}
