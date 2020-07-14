package club.banyuan.dao;

import club.banyuan.entity.User;

import java.util.Map;

public interface UserDao {
    public int add(User user);
    public User getLoginUser(Map map);
    public User selectLoginName(String userName);
}
