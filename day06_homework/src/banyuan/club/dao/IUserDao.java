package banyuan.club.dao;

import banyuan.club.entity.User;

public interface IUserDao extends IBaseDao{
    int add(User user) throws Exception;//添加用户信息
    User getLoginUser(String loginName, String password);
}
