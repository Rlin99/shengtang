package club.banyuan.service;

import club.banyuan.entity.Address;
import club.banyuan.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public User login(String username, String password) throws SQLException;
    public int regist(User user) throws SQLException;
    public List<Address> getAddress(Integer userId) throws Exception;
    public int addAddress(Address address) throws Exception;
    public Boolean selectLoginName( String loginName) throws Exception;
}
