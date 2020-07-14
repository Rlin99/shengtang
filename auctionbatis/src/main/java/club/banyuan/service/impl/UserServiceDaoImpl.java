package club.banyuan.service.impl;

import club.banyuan.dao.UserDao;
import club.banyuan.entity.User;
import club.banyuan.service.UserServiceDao;

import java.util.Map;

public class UserServiceDaoImpl implements UserServiceDao {
    UserDao userDao;
    public User login(Map map) {
        return userDao.getLoginUser(map);
    }

    public int regist(User user) {
        return userDao.add(user);
    }
}
