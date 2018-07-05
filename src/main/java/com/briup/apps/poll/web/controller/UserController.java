package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Clazz;
import com.briup.apps.poll.bean.Course;
import com.briup.apps.poll.bean.User;
import com.briup.apps.poll.service.IUserService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "用户相关API接口")
@RestController
@RequestMapping("/user")
public class UserController {
	/**
	 * @return
	 */
	@Autowired
	private IUserService userService;
	
	@ApiOperation(value="添加或修改用户信息",notes="如果用户id不为空，表示更新操作;如果用户id为空，表示保存操作")
	@PostMapping("saveOrUpdateUser")
	public MsgResponse saveOrUpdateUser(User user){
		try {
			
			userService.saveOrUpdate(user);
		return MsgResponse.success("提交成功!", null);
	} catch (Exception e) {
		e.printStackTrace();
		return MsgResponse.error(e.getMessage());
	}
}

	@ApiOperation(value = "查询所有用户信息")
	@GetMapping("findAllUser")
	public MsgResponse findAllUser() {
		try {
			List<User> list = userService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value = "按关键字查询用户信息")
	@GetMapping("queryUser")
	public MsgResponse queryUser(String keywords) {
		try {
			List<User> list = userService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	/*@ApiOperation(value = "保存用户信息")
	@PostMapping("saveUser")
	public MsgResponse saveUser(User user) {
		try {
			userService.save(user);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value = "修改用户信息")
	@PostMapping("updateUser")
	public MsgResponse updateUser(User user) {
		try {
			userService.update(user);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}*/

	@ApiOperation(value = "通过id删除用户信息")
	@GetMapping("deleteByIdUser")
	public MsgResponse deleteByIdUser(@RequestParam long id) {
		try {
			userService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value = "批量删除用户信息")
	@GetMapping("batchDeleteUser")
	public MsgResponse batchDeleteUser(long[] ids) {
		try {
			userService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}
