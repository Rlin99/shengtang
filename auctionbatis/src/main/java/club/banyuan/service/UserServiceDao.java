package club.banyuan.service;

import club.banyuan.entity.User;

import java.sql.SQLException;
import java.util.Map;

public interface UserServiceDao {
    public User login(Map map);
    public int regist(User user);
}
