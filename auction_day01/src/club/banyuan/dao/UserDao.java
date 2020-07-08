package club.banyuan.dao;

import club.banyuan.entity.User;

public interface UserDao extends IBaseDao{
    public int add(User user);
    public User getLoginUser(String userName,String pwd);
    public Boolean selectLoginName(String userName);
}
