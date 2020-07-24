package club.banyuan.mapper;

import club.banyuan.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
@Mapper
public interface UserMapper {
    public int addUser(User user);
    public User getLoginUser(Map map);
    public int selectLoginName(String loginName);
}
