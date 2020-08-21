package club.banyuan.mall.service.impl;

import club.banyuan.mall.entity.AdminUser;
import club.banyuan.mall.mapper.AdminUserMapper;
import club.banyuan.mall.service.AdminUserService;
import club.banyuan.mall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Override
    public AdminUser login(String userName, String password) {
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(userName, passwordMD5);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return null;
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        if( adminUser != null ){
            String originalPasswordMD5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            //比较原密码是否正确
            if(originalPasswordMD5.equals(adminUser.getLoginPassword())){
                String newPasswordMD5 = MD5Util.MD5Encode(newPassword, "UTF-8");
                adminUser.setLoginPassword(newPasswordMD5);
                if(adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        if(adminUser != null){
            //设置新名称并修改
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }
}
