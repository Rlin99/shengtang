package club.banyuan.mall.controller;

import java.util.Map;

import club.banyuan.mall.service.MallUserService;
import club.banyuan.mall.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.banyuan.mall.util.Result;
import club.banyuan.mall.util.ResultGenerator;

@RestController
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private MallUserService mallUserService;

	@GetMapping("/users/list")
	public Result<?> list(@RequestParam Map<String, Object> params) {
		if(StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
			return ResultGenerator.genFailResult("参数异常！");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(mallUserService.getNewBeeMallUsersPage(pageUtil));
	}

	@PostMapping("/users/lock/{lockStatus}")
	public Result<?> delete(@RequestBody Integer[] ids, @PathVariable int lockStatus) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		if (lockStatus != 0 && lockStatus != 1) {
			return ResultGenerator.genFailResult("操作非法！");
		}
		if (mallUserService.lockUsers(ids, lockStatus)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("禁用失败");
		}
	}
}
