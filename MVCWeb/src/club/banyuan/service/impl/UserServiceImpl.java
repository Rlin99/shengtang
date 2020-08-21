package club.banyuan.service.impl;

import club.banyuan.dao.UserDao;
import club.banyuan.dao.impl.UserDaoImpl;
import club.banyuan.entity.User;
import club.banyuan.service.UserService;
import club.banyuan.util.JdbcUtils;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String username, String password) throws SQLException {
        UserDao userDao = new UserDaoImpl(JdbcUtils.getConnection());
        return userDao.getLoginUser(username, password);
    }

    @Override
    public int regist(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl(JdbcUtils.getConnection());
        return userDao.add(user);
    }
}
