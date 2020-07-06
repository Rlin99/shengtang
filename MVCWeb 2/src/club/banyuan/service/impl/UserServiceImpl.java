package club.banyuan.service.impl;

import club.banyuan.dao.AddressDao;
import club.banyuan.dao.UserDao;
import club.banyuan.dao.impl.AddressDaoImlp;
import club.banyuan.dao.impl.UserDaoImpl;
import club.banyuan.entity.Address;
import club.banyuan.entity.User;
import club.banyuan.service.UserService;
import club.banyuan.util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String username, String password) throws SQLException {
        UserDao userDao = new UserDaoImpl(JdbcUtils.getConnection());
        return userDao.getLoginUser(username,password);
    }

    @Override
    public int regist(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl(JdbcUtils.getConnection());
        return userDao.add(user);
    }

    @Override
    public List<Address> getAddress(Integer userId) throws Exception {
        AddressDao addressDao = new AddressDaoImlp(JdbcUtils.getConnection());
        return addressDao.getAddressById(userId);
    }

    @Override
    public int addAddress(Address address) throws Exception {
        AddressDao addressDao = new AddressDaoImlp(JdbcUtils.getConnection());
        return addressDao.add(address);
    }

}
