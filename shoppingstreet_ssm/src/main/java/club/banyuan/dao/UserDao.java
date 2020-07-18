package club.banyuan.dao;

import club.banyuan.entity.User;

import java.util.Map;

public interface UserDao {
    public int addUser(User user);
    public User getLoginUser(Map map);
    public int selectLoginName(String loginName);
}
