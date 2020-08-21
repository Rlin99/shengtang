package club.banyuan.mall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import club.banyuan.mall.common.ServiceResultEnum;
import club.banyuan.mall.entity.AdminUser;
import club.banyuan.mall.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminUserService adminUserService;

	@PostMapping("/profile/password")
	public String updatePassword(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
            @RequestParam("newPassword") String newPassword) {
		if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
			return "参数不能为空";
		}
		Integer id = (int)request.getSession().getAttribute("loginUserId");
		if(adminUserService.updatePassword(id, originalPassword, newPassword)){
			//修改成功后清空session中的数据，前端控制跳转至登录页
			request.getSession().removeAttribute("loginUserId");
			request.getSession().removeAttribute("loginUser");
			request.getSession().removeAttribute("errorMsg");
			return ServiceResultEnum.SUCCESS.getResult();
		}else {
			return "修改失败";
		}
	}
	
	@PostMapping("/profile/name")
	public String updateName(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
            @RequestParam("nickName") String nickName) {
		if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
			return "参数不能为空";
		}
		Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
		if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
			return ServiceResultEnum.SUCCESS.getResult();
		} else {
			return "修改失败";
		}
	}
}
