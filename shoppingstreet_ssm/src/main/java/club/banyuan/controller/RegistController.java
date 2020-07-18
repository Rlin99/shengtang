package club.banyuan.controller;

import club.banyuan.entity.User;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistController {

    @Autowired
    private UserService userService ;


    @RequestMapping("/regist.do")
    public String regist(HttpServletRequest request){
        User user = new User();
        user.setLoginName(request.getParameter("loginName"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        String sex = request.getParameter("sex");
        if(sex.equals("男")){
            user.setSex(1);
        }else {
            user.setSex(0);
        }
        user.setEmail(request.getParameter("email"));
        user.setMobile(request.getParameter("mobile"));
        boolean flag = false;
            int id = userService.regist(user);
            if(id>0){
                flag = true;
            }
            System.out.println(id);
        if(flag){
            return "login";
        }else{
            return "regist";
        }
    }
}
