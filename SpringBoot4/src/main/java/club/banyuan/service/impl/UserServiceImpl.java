package club.banyuan.service.impl;

import club.banyuan.entity.Address;
import club.banyuan.entity.User;
import club.banyuan.mapper.AddressMapper;
import club.banyuan.mapper.UserMapper;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userDao;

    @Autowired
    AddressMapper addressDao;

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
