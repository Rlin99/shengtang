package club.banyuan.service.impl;

import club.banyuan.dao.AddressDao;
import club.banyuan.dao.UserDao;
import club.banyuan.entity.Address;
import club.banyuan.entity.User;
import club.banyuan.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    UserDao userDao;
    AddressDao addressDao;

    @Override
    public User login(Map map) {
        return userDao.getLoginUser(map);
    }

    @Override
    public int regist(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<Address> getAddress(Integer userId) {
        return addressDao.getAddressById(userId);
    }

    @Override
    public int addAddress(Address address) {
        return addressDao.addAddress(address);
    }

    @Override
    public int selectLoginName(String loginName) {
        return userDao.selectLoginName(loginName);
    }
}
