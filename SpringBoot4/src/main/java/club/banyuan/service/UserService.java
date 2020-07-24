package club.banyuan.service;

import club.banyuan.entity.Address;
import club.banyuan.entity.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
public interface UserService {
    public User login(Map map) ;
    public int regist(User user) ;
    public List<Address> getAddress(Integer userId);
    public int addAddress(Address address);
    public int selectLoginName(String loginName);
}
