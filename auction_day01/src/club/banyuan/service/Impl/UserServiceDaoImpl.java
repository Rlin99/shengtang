package club.banyuan.service.Impl;

import club.banyuan.dao.UserDao;
import club.banyuan.dao.impl.UserDaoImpl;
import club.banyuan.entity.User;
import club.banyuan.service.UserServiceDao;
import club.banyuan.util.JdbcUtils;

import java.sql.SQLException;

public class UserServiceDaoImpl implements UserServiceDao {
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
